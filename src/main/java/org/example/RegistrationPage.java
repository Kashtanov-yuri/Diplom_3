package org.example;

import org.openqa.selenium.By;

public class RegistrationPage {

    public final By nameField = By.cssSelector("input[name='name']");
    public final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    public final By passwordField = By.cssSelector("input[name='Пароль']");
    public final By registrationButton = By.cssSelector("button.button_button__33qZ0");
    public final By invalidPassword = By.cssSelector(".input__error, .error-message, p.text_color_red");
    public final By loginButton = By.xpath("//a[text()='Войти']");

    public String generateUniqueEmail() {
        return "AutomaticTest_" + System.currentTimeMillis() + "@example.com";
    }
}