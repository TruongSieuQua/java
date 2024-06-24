package com.tjn.pages.web;

import com.tjn.pages.AbstractPage;
import com.tjn.pages.NavigationLink;
import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter@Setter
public class DashBoardPage extends AbstractPage implements NavigationLink {

    @Getter
    @RequiredArgsConstructor
    public enum DashboardLink {
        ACCOUNT_DASHBOARD("Account Dashboard"),
        MY_ORDERS("My Orders"),
        MY_WISH_LIST("My Wish List"),
        ADDRESS_BOOK("Address Book"),
        ACCOUNT_INFORMATION("Account Information"),
        STORED_PAYMENT_METHODS("Stored Payment Methods");
        private final String linkText;
    }

    @FindBy(linkText = "My Dashboard")
    private WebElement pageTitle;

    @FindBy(css = ".customer-name")
    private WebElement customerName;

    @FindBy(id="search")
    private WebElement searchInput;

    public DashBoardPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isAt() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(pageTitle));
        return this.pageTitle.isDisplayed();
    }

    @Override
    public <T> void navigate(T link) {
        if (link instanceof DashboardLink) {
            String linkText = ((DashboardLink) link).getLinkText();
            driver.findElement(By.linkText(linkText)).click();
        } else {
            throw new IllegalArgumentException("Unsupported link type: Expected "
                    + DashboardLink.class.getSimpleName()
            );
        }
    }
}
