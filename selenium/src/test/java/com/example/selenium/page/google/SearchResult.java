package com.example.selenium.page.google;

import com.example.selenium.page.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchResult extends Base {

    @FindBy(className = "MjjYud")
    private List<WebElement> results;

    public SearchResult(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    @Override
    public boolean isAt() {
        return this.webDriverWait.until((d) -> !this.results.isEmpty());
    }

    public int getCount(){
        return this.results.size();
    }
}
