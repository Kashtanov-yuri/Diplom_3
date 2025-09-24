import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.example.Constants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckingTheLoginButtonsTest {

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
    @DisplayName("Вход по кнопке «Войти в аккаунт»")
    public void testTheLoginToAccountButton(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        MainPage mainPage = new MainPage();
        EntrancePage entrancePage = new EntrancePage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get(BASE_URL);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.loginAccountButton));
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(mainPage.loginAccountButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
        assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Вход по кнопке «Личный кабинет»")
    public void testPersonalAccountAccess(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        MainPage mainPage = new MainPage();
        EntrancePage entrancePage = new EntrancePage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get(BASE_URL);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.personalAccountButton));
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(mainPage.personalAccountButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
        assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Вход по кнопке из формы регистрации")
    public void testSwitchingFromRegistrationForm(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        RegistrationPage registrationPage = new RegistrationPage();
        EntrancePage entrancePage = new EntrancePage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(REG_URL);
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.loginButton));
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(registrationPage.loginButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
        assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Вход по кнопке из формы восстановления пароля")
    public void testSwitchingFromPasswordRecoveryForm(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage();
        EntrancePage entrancePage = new EntrancePage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(PAS_URL);
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordPage.loginButton));
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(recoverPasswordPage.loginButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));
        assertTrue(driver.findElement(entrancePage.entranceLogo).isDisplayed());
    }
}