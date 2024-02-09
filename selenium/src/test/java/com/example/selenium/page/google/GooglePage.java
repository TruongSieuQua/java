package com.example.selenium.page.google;

import com.example.selenium.page.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GooglePage extends Base {

    @Autowired
    private SearchComponent searchComponent;

    @Autowired
    private SearchResult searchResult;

    @Value("${application.url}")
    private String url;

    public GooglePage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    @Override
    public boolean isAt() {
        return searchComponent.isAt();
    }

    public void goTo(){
        this.driver.get(url);
    }

    public SearchComponent getSearchComponent() {
        return searchComponent;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void close(){
        this.driver.quit();
    }
}
