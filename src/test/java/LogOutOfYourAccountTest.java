import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOutOfYourAccountTest {

    private WebDriver driver;
    private final DriverManager driverManager = new DriverManager();

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driverManager.quitDriver(driver);
        }
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Выход из аккаунта")
    public void testOut(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        WebDriverWait wait = getWait();
        RegistrationService registrationService = new RegistrationService(driver);
        MainPage mainPage = new MainPage();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();
        EntrancePage entrancePage = new EntrancePage();

        String email = registrationService.registerDefaultUser();
        registrationService.loginAfterRegistration(email, "123456");
        WebElement personalAccountButton = wait.until(ExpectedConditions.elementToBeClickable(mainPage.personalAccountButton));
        JavaScriptClickHelper.clickWithJavaScript(driver, personalAccountButton);
        WebElement exitButton = wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.exitButton));
        JavaScriptClickHelper.clickWithJavaScript(driver, exitButton);
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo)).isDisplayed());
    }
}