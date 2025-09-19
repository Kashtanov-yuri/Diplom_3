import org.example.DriverManager;
import org.example.EntrancePage;
import org.example.RegistrationPage;
import org.example.RegistrationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.stream.Stream;
import static org.example.Constants.REG_URL;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {

    static Stream<WebDriver> driverProvider() {
        DriverManager manager = new DriverManager();
        return Stream.of(
                manager.getDriverChrome(),
                manager.getDriverFirefox()
        );
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Проверка регистрации пользователя в Chrome и Firefox")
    public void testUserRegistration(WebDriver driver) {
        try {
            RegistrationService registrationService = new RegistrationService(driver);
            EntrancePage entrancePage = new EntrancePage();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            registrationService.registerDefaultUser();
            wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
            assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());

        } finally {
             new DriverManager().quitDriver(driver);
        }
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Проверка неправильного пароля в Chrome и Firefox")
    public void testUserRegistrationFailed(WebDriver driver) {
        try {
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

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }
}
