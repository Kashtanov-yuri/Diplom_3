import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.example.Constants.REG_URL;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private final DriverManager driverManager = new DriverManager();

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driverManager.quitDriver(driver);
        }
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Проверка регистрации пользователя в Chrome и Firefox")
    public void testUserRegistration(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        RegistrationService registrationService = new RegistrationService(driver);
        EntrancePage entrancePage = new EntrancePage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        registrationService.registerDefaultUser();
        wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
        assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Проверка неправильного пароля в Chrome и Firefox")
    public void testUserRegistrationFailed(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        RegistrationPage registrationPage = new RegistrationPage();
        String email = new RegistrationPage().generateUniqueEmail();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(REG_URL);
        driver.findElement(registrationPage.emailField).sendKeys(email);
        driver.findElement(registrationPage.nameField).sendKeys("AutomaticTest");
        driver.findElement(registrationPage.passwordField).sendKeys("12345");
        driver.findElement(registrationPage.registrationButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.invalidPassword));
        assertTrue(driver.findElement(registrationPage.invalidPassword).isDisplayed());
    }
}