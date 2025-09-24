package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.example.Constants.BASE_URL;

public class RegistrationService {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegistrationService(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String registerUser(String name, String email, String password) {
        RegistrationPage registrationPage = new RegistrationPage();
        EntrancePage entrancePage = new EntrancePage();

        driver.get(Constants.REG_URL);
        driver.findElement(registrationPage.nameField).sendKeys(name);
        driver.findElement(registrationPage.emailField).sendKeys(email);
        driver.findElement(registrationPage.passwordField).sendKeys(password);
        driver.findElement(registrationPage.registrationButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(entrancePage.entranceLogo));

        return email;
    }

    public String registerUserWithGeneratedEmail(String name, String password) {
        RegistrationPage registrationPage = new RegistrationPage();
        String email = registrationPage.generateUniqueEmail();
        return registerUser(name, email, password);
    }

    public String registerDefaultUser() {
        return registerUserWithGeneratedEmail("AutomaticTest", "123456");
    }

    public void loginAfterRegistration(String email, String password) {
        EntrancePage entrancePage = new EntrancePage();
        driver.findElement(entrancePage.emailField).sendKeys(email);
        driver.findElement(entrancePage.passwordField).sendKeys(password);
        driver.findElement(entrancePage.loginButton).click();
        wait.until(ExpectedConditions.urlToBe(BASE_URL));
    }
}
