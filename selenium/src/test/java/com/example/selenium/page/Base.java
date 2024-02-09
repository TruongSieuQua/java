package com.example.selenium.page;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Base {

    protected WebDriver driver;

    protected WebDriverWait webDriverWait;

    public Base(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isAt();

}
