import org.example.DriverManager;
import org.example.EntrancePage;
import org.example.MainPage;
import org.example.RecoverPasswordPage;
import org.example.RegistrationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.stream.Stream;
import static org.example.Constants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckingTheLoginButtonsTest {

    static Stream<WebDriver> driverProvider() {
        DriverManager manager = new DriverManager();
        return Stream.of(
                manager.getDriverChrome(),
                manager.getDriverFirefox()
        );
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Вход по кнопке «Войти в аккаунт»")
    public void testTheLoginToAccountButton(WebDriver driver) {
        try {
            MainPage mainPage = new MainPage();
            EntrancePage entrancePage = new EntrancePage();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.get(BASE_URL);
            js.executeScript("arguments[0].click();", driver.findElement(mainPage.loginAccountButton));
            wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
            assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Вход по кнопке «Личный кабинет»")
    public void testPersonalAccountAccess(WebDriver driver) {
        try {
            MainPage mainPage = new MainPage();
            EntrancePage entrancePage = new EntrancePage();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.get(BASE_URL);
            js.executeScript("arguments[0].click();", driver.findElement(mainPage.personalAccountButton));
            wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
            assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Вход по кнопке из формы регистрации")
    public void testSwitchingFromRegistrationForm(WebDriver driver) {
        try {
            RegistrationPage registrationPage = new RegistrationPage();
            EntrancePage entrancePage = new EntrancePage();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.get(REG_URL);
            js.executeScript("arguments[0].click();", driver.findElement(registrationPage.loginButton));
            wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
            assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Вход по кнопке из формы восстановления пароля")
    public void testSwitchingFromPasswordRecoveryForm(WebDriver driver) {
        try {
            RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage();
            EntrancePage entrancePage = new EntrancePage();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.get(PAS_URL);
            js.executeScript("arguments[0].click();", driver.findElement(recoverPasswordPage.loginButton));
            wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
            assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }

}
