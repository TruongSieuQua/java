package com.tjn.pages.web;

import com.tjn.pages.AbstractPage;
import com.tjn.pages.NavigationLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage implements NavigationLink {

    @Getter
    @AllArgsConstructor
    public static enum HomePageLink{
        LOGIN("Sign In"),
        REGISTER("Create an Account"),
        NEW("What's New"),
        WOMEN("Women"),
        MEN("Men"),
        GEAR("Gear"),
        TRAINING("Training"),
        SALE("Sale");
        private final String linkText;
    }

    @FindBy(className = "page-title")
    private WebElement pageTitle;

    @FindBy(css = ".authorization-link")
    private WebElement loginLink;

    @FindBy(linkText = "Create an Account")
    private WebElement registerLink;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isAt() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(pageTitle));
        return this.pageTitle.isDisplayed();
    }

    @Override
    public <T> void navigate(T link) {
        if (link instanceof HomePageLink) {
            String linkText = ((HomePageLink) link).getLinkText();
            driver.findElement(By.linkText(linkText)).click();
        } else {
            throw new IllegalArgumentException("Unsupported link type: Expected "
                    + HomePageLink.class.getSimpleName()
            );
        }
    }
}
