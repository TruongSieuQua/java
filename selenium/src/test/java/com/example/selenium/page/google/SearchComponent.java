package com.example.selenium.page.google;

import com.example.selenium.page.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchComponent extends Base {

    public SearchComponent(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name="btnK")
    private List<WebElement> searchBtns;

    @Override
    public boolean isAt() {
       return this.webDriverWait.until((d) -> this.searchBox.isDisplayed());
    }

    public void search(final String keyword){
        this.searchBox.sendKeys(keyword);
        this.searchBox.sendKeys(Keys.TAB); // escape from searchbox
        this.searchBtns.stream()
                .filter(btn -> btn.isDisplayed() && btn.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
    }
}
