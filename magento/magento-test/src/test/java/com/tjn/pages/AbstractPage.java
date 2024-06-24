package com.tjn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected  final WebDriver driver;

    protected final WebDriverWait webDriverWait;

    public AbstractPage(WebDriver webDriver){
        this.driver = webDriver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void goTo(String url){
        this.driver.get(url);
    };

    public abstract boolean isAt();
}
