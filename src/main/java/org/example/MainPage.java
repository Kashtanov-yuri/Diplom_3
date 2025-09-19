package org.example;

import org.openqa.selenium.By;

public class MainPage {

    public final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    public final By loginAccountButton = By.xpath("/html/body/div/div/main/section[2]/div/button");
    public final By inscriptionCollectABurger = By.xpath("/html/body/div/div/main/section[1]/h1");
    public final By saucesButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[2]");
    public final By saucesSpicy = By.xpath("/html/body/div/div/main/section[1]/div[2]/ul[2]/a[1]/img");
    public final By saucesTraditional = By.xpath("/html/body/div/div/main/section[1]/div[2]/ul[2]/a[3]");
    public final By fillingButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[3]");
    public final By shellfishMeat = By.xpath("/html/body/div/div/main/section[1]/div[2]/ul[3]/a[1]");
    public final By rollsButton= By.xpath("/html/body/div/div/main/section[1]/div[1]/div[1]");
    public final By craterBun = By.xpath("/html/body/div/div/main/section[1]/div[2]/ul[1]/a[2]");
}