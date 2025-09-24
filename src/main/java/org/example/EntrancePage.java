package org.example;

import org.openqa.selenium.By;

public class EntrancePage {
    public By entranceLogo = By.xpath("//h2[contains(text(), 'Вход')]");
    public By emailField = By.xpath("//input[@name='name' or @type='email' or @placeholder='Email']");
    public By passwordField = By.xpath("//input[@type='password']");
    public By loginButton = By.xpath("//button[contains(text(), 'Войти') or @type='submit']");
}