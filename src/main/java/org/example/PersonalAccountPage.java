package org.example;

import org.openqa.selenium.By;

public class PersonalAccountPage {
    public final By nameField = By.cssSelector("input[name='Name']");
    public final By loginField = By.xpath("/html/body/div/div/main/div/div/div/ul/li[2]/div/div");
    public final By passwordField = By.xpath("/html/body/div/div/main/div/div/div/ul/li[3]/div/div");
    public final By burgerLogo = By.cssSelector("div.AppHeader_header__logo__2D0X2");
    public final By constructorButton = By.xpath("//p[text()='Конструктор']/parent::a");
    public final By exitButton = By.xpath("/html/body/div/div/main/div/nav/ul/li[3]/button");
}
