import org.example.DriverManager;
import org.example.MainPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

import static org.example.Constants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransitionsInTheConstructorTest {

    private WebDriver driver;

    static Stream<WebDriver> driverProvider() {
        DriverManager manager = new DriverManager();
        return Stream.of(
                manager.getDriverChrome(),
                manager.getDriverFirefox()
        );
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    @DisplayName("Переходы по разделам")
    public void testTransitions(WebDriver webDriver) {
        this.driver = webDriver;
        MainPage mainPage = new MainPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        DriverManager manager = new DriverManager();

        try {
            openMainPage();
            clickSaucesButton(js, mainPage);
            verifySaucesDisplayed(mainPage);
            clickFillingButton(js, mainPage);
            verifyFillingDisplayed(mainPage);
            clickRollsButton(js, mainPage);
            verifyRollsDisplayed(mainPage);

        } finally {
            quitDriver(manager);
        }
    }

    @Step("Открыть главную страницу")
    private void openMainPage() {
        driver.get(BASE_URL);
    }

    @Step("Кликнуть на кнопку 'Соусы'")
    private void clickSaucesButton(JavascriptExecutor js, MainPage mainPage) {
        WebElement saucesButton = driver.findElement(mainPage.saucesButton);
        js.executeScript("arguments[0].click();", saucesButton);
    }

    @Step("Проверить отображение соусов")
    private void verifySaucesDisplayed(MainPage mainPage) {
        WebElement spicySauce = driver.findElement(mainPage.saucesSpicy);
        WebElement traditionalSauce = driver.findElement(mainPage.saucesTraditional);

        assertTrue(spicySauce.isDisplayed(), "Острый соус должен отображаться");
        assertTrue(traditionalSauce.isDisplayed(), "Традиционный соус должен отображаться");
    }

    @Step("Кликнуть на кнопку 'Начинки'")
    private void clickFillingButton(JavascriptExecutor js, MainPage mainPage) {
        WebElement fillingButton = driver.findElement(mainPage.fillingButton);
        js.executeScript("arguments[0].click();", fillingButton);
    }

    @Step("Проверить отображение начинок")
    private void verifyFillingDisplayed(MainPage mainPage) {
        WebElement shellfishMeat = driver.findElement(mainPage.shellfishMeat);
        assertTrue(shellfishMeat.isDisplayed(), "Мясо моллюсков должно отображаться");
    }

    @Step("Кликнуть на кнопку 'Булки'")
    private void clickRollsButton(JavascriptExecutor js, MainPage mainPage) {
        WebElement rollsButton = driver.findElement(mainPage.rollsButton);
        js.executeScript("arguments[0].click();", rollsButton);
    }

    @Step("Проверить отображение булок")
    private void verifyRollsDisplayed(MainPage mainPage) {
        WebElement craterBun = driver.findElement(mainPage.craterBun);
        assertTrue(craterBun.isDisplayed(), "Кратерная булка должна отображаться");
    }

    @Step("Закрыть драйвер")
    private void quitDriver(DriverManager manager) {
        manager.quitDriver(driver);
    }
}
