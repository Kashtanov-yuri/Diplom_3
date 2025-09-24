package org.example;

import org.openqa.selenium.By;

public class MainPage {

    public final By personalAccountButton = By.xpath("//p[contains(text(), 'Личный Кабинет')]");
    public final By loginAccountButton = By.cssSelector("button[class*='button_button']");
    public final By inscriptionCollectABurger = By.cssSelector("h1");
    public final By saucesButton = By.cssSelector("div[class*='tab']:nth-child(2)");
    public final By saucesSpicy = By.cssSelector("img[alt*='Острый'], img[alt*='Spicy']");
    public final By saucesTraditional = By.xpath("//section[contains(@class, 'BurgerIngredients')]//a[3]");
    public final By fillingButton = By.cssSelector("div[class*='tab']:nth-child(3)");
    public final By shellfishMeat = By.cssSelector("[class*='shellfish'], [alt*='моллюск']");
    public final By rollsButton = By.cssSelector("div[class*='tab']:nth-child(1)");
    public final By craterBun = By.cssSelector("[class*='crater'], [alt*='Краторная']");
}