package org.example;

import org.openqa.selenium.By;

public class PersonalAccountPage {

    public final By nameField = By.cssSelector("input[name='Name']");
    public final By loginField = By.cssSelector("input[name='name'][disabled]");
    public final By passwordField = By.cssSelector("input[type='password'][disabled]");
    public final By burgerLogo = By.cssSelector("a[href='/'], .app-logo, header a");
    public final By constructorButton = By.xpath("//a[.//p[text()='Конструктор']] | //a[contains(@href, 'constructor')]");
    public final By exitButton = By.xpath("//button[text()='Выход']");
}