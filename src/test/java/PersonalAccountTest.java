import org.example.DriverManager;
import org.example.MainPage;
import org.example.PersonalAccountPage;
import org.example.RegistrationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalAccountTest {

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
    @DisplayName("Проверка перехода в личный кабинет")
    public void testUserRegistration(WebDriver driver) {
        try {
            WebDriverWait wait = getWait(driver);
            RegistrationService registrationService = new RegistrationService(driver);
            MainPage mainPage = new MainPage();
            PersonalAccountPage personalAccountPage = new PersonalAccountPage();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String email = registrationService.registerDefaultUser();
            registrationService.loginAfterRegistration(email, "123456");

            js.executeScript("arguments[0].click();", driver.findElement(mainPage.personalAccountButton));
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.nameField)).isDisplayed());
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.loginField)).isDisplayed());
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.passwordField)).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Проверка перехода в личный кабинет")
    public void testTransferYourPersonalAccount(WebDriver driver) {
        try {
            WebDriverWait wait = getWait(driver);
            RegistrationService registrationService = new RegistrationService(driver);
            MainPage mainPage = new MainPage();
            PersonalAccountPage personalAccountPage = new PersonalAccountPage();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String email = registrationService.registerDefaultUser();
            registrationService.loginAfterRegistration(email, "123456");

            js.executeScript("arguments[0].click();", driver.findElement(mainPage.personalAccountButton));
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.nameField)).isDisplayed());
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.loginField)).isDisplayed());
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.passwordField)).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Переход по клику на «Конструктор»")
    public void testClickConstructor(WebDriver driver) {
        try {
            WebDriverWait wait = getWait(driver);
            RegistrationService registrationService = new RegistrationService(driver);
            MainPage mainPage = new MainPage();
            PersonalAccountPage personalAccountPage = new PersonalAccountPage();

            String email = registrationService.registerDefaultUser();
            registrationService.loginAfterRegistration(email, "123456");

            wait.until(ExpectedConditions.elementToBeClickable(mainPage.personalAccountButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.constructorButton)).click();
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.inscriptionCollectABurger)).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Переход по клику на лого бургера")
    public void testClickBurgerLogo(WebDriver driver) {
        try {
            WebDriverWait wait = getWait(driver);
            RegistrationService registrationService = new RegistrationService(driver);
            MainPage mainPage = new MainPage();
            PersonalAccountPage personalAccountPage = new PersonalAccountPage();

            String email = registrationService.registerDefaultUser();
            registrationService.loginAfterRegistration(email, "123456");

            wait.until(ExpectedConditions.elementToBeClickable(mainPage.personalAccountButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.burgerLogo)).click();
            assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.inscriptionCollectABurger)).isDisplayed());

        } finally {
            new DriverManager().quitDriver(driver);
        }
    }
}