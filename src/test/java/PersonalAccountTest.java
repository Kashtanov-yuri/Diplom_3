import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalAccountTest {

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
    @DisplayName("Проверка отображения полей в личном кабинете")
    public void testPersonalAccountFieldsDisplay(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        WebDriverWait wait = getWait();
        RegistrationService registrationService = new RegistrationService(driver);
        MainPage mainPage = new MainPage();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();

        String email = registrationService.registerDefaultUser();
        registrationService.loginAfterRegistration(email, "123456");
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(mainPage.personalAccountButton));
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.nameField)).isDisplayed());
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.loginField)).isDisplayed());
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.passwordField)).isDisplayed());
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Проверка перехода в личный кабинет")
    public void testTransferYourPersonalAccount(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        WebDriverWait wait = getWait();
        RegistrationService registrationService = new RegistrationService(driver);
        MainPage mainPage = new MainPage();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();

        String email = registrationService.registerDefaultUser();
        registrationService.loginAfterRegistration(email, "123456");
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(mainPage.personalAccountButton));
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.nameField)).isDisplayed());
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.loginField)).isDisplayed());
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.passwordField)).isDisplayed());
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Переход по клику на «Конструктор»")
    public void testClickConstructor(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        WebDriverWait wait = getWait();
        RegistrationService registrationService = new RegistrationService(driver);
        MainPage mainPage = new MainPage();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();

        String email = registrationService.registerDefaultUser();
        registrationService.loginAfterRegistration(email, "123456");
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(mainPage.personalAccountButton));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.constructorButton));
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(personalAccountPage.constructorButton));
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.inscriptionCollectABurger)).isDisplayed());
    }

    @ParameterizedTest
    @EnumSource(DriverManager.Browser.class)
    @DisplayName("Переход по клику на лого бургера")
    public void testClickBurgerLogo(DriverManager.Browser browser) {
        this.driver = driverManager.getDriver(browser);
        WebDriverWait wait = getWait();
        RegistrationService registrationService = new RegistrationService(driver);
        MainPage mainPage = new MainPage();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();

        String email = registrationService.registerDefaultUser();
        registrationService.loginAfterRegistration(email, "123456");
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(mainPage.personalAccountButton));
        JavaScriptClickHelper.clickWithJavaScript(driver, driver.findElement(personalAccountPage.burgerLogo));
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.inscriptionCollectABurger)).isDisplayed());
    }
}