package org.example;

import org.openqa.selenium.By;

public class RegistrationPage {
    public final By nameField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    public final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    public final By passwordField = By.xpath("//input[@name='Пароль']");
    public final By registrationButton = By.xpath("/html/body/div/div/main/div/form/button");
    public final By invalidPassword = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/p");
    public final By loginButton = By.xpath("/html/body/div/div/main/div/div/p/a");




    public String generateUniqueEmail() {
        return "AutomaticTest_" + System.currentTimeMillis() + "@example.com";
    }
}