package com.tjn.pages.web;

import com.tjn.pages.AbstractPage;
import com.tjn.pages.FormAction;
import com.tjn.web.dto.RegisterDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends AbstractPage implements FormAction<RegisterDto> {

    @FindBy(css = ".page-title .base")
    private WebElement pageTitle;

    @FindBy(id = "firstname")
    private WebElement firstNameInput;

    @FindBy(id = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "email_address")
    private WebElement emailAddressInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "password-confirmation")
    private WebElement passwordConfirmationInput;


    @FindBy(css = ".form-create-account btn.primary")
    private WebElement registerButton;

    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isAt() {
        webDriverWait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.isDisplayed();
    }

    @Override
    public void enterData(RegisterDto dto){
        this.firstNameInput.sendKeys(dto.firstname());
        this.lastNameInput.sendKeys(dto.lastname());
        this.emailAddressInput.sendKeys(dto.emailAddress());
        this.passwordInput.sendKeys(dto.password());
        this.passwordConfirmationInput.sendKeys(dto.passwordConfirmation());
    }

    @Override
    public void submitData() {
        this.registerButton.click();
    }
}
