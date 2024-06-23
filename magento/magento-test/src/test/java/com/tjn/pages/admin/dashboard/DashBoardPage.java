package com.tjn.pages.admin.dashboard;

import com.tjn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashBoardPage extends AbstractPage {

    @FindBy(css = "span.admin-user-account-text")
    private WebElement accountButton;

    public DashBoardPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isAt() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(accountButton));
        return accountButton.isDisplayed();
    }

    public String getUsername(){
        return accountButton.getText();
    }
}
