import org.example.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LogOutOfYourAccountTest {

    static Stream<WebDriver> driverProvider() {
        DriverManager manager = new DriverManager();
        return Stream.of(
                manager.getDriverChrome(),
                manager.getDriverFirefox()
        );
    }

    private WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Выход из аккаунта")
    public void testOut(WebDriver driver) {
        try {
            WebDriverWait wait = getWait(driver);
            RegistrationService registrationService = new RegistrationService(driver);
            MainPage mainPage = new MainPage();
            PersonalAccountPage personalAccountPage = new PersonalAccountPage();
            EntrancePage entrancePage = new EntrancePage();

            String email = registrationService.registerDefaultUser();
            registrationService.loginAfterRegistration(email, "123456");
            wait.until(ExpectedConditions.elementToBeClickable(mainPage.personalAccountButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.exitButton)).click();
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo)).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }
}
