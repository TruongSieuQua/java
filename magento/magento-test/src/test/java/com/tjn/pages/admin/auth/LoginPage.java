package com.tjn.pages.admin.auth;

import com.tjn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(id="username")
    private WebElement usernameInput;

    @FindBy(id="login")
    private WebElement passwordInput;

    @FindBy(css = ".action-login > span")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isAt() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(usernameInput));
        return this.usernameInput.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.usernameInput));
    }

    public void login(){
        this.loginButton.click();
    }

    public void enterUserCredentials(String username, String password){
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
    }
}
