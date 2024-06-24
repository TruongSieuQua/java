package com.tjn.pages.web;

import com.tjn.pages.AbstractPage;
import com.tjn.pages.FormAction;
import com.tjn.web.dto.LoginDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage implements FormAction<LoginDto> {

    @FindBy(xpath = "//span[contains(.,'Customer Login')]")
    private WebElement pageTitle;

    @FindBy(id = "maincontent")
    private WebElement mainContent;

    @FindBy(className = "form-login")
    private WebElement formLogin;

    @FindBy(css = ".page.messages")
    private WebElement pageMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void goTo(String url) {
        this.driver.get(url);

    }

    @Override
    public boolean isAt() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.isDisplayed();
    }

    @Override
    public void enterData(LoginDto data) {
        formLogin.findElement(By.id("email")).sendKeys(data.email());
        formLogin.findElement(By.id("pass")).sendKeys(data.password());
    }

    @Override
    public void submitData() {
        formLogin.findElement(By.cssSelector(".login.primary")).click();
    }

    public WebElement getEmailError(){
        return formLogin.findElement(By.id("email-error"));
    }

    public WebElement getPasswordError(){
        return formLogin.findElement(By.id("pass-error"));
    }

    public WebElement getPageErrorMessage(){
        return pageMessage.findElement(By.id("message-error"));
    }
}
