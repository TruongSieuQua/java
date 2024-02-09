package com.example.selenium.googletest;

import com.example.selenium.SpringBaseTest;
import com.example.selenium.page.google.GooglePage;
import com.example.selenium.util.ScreenShotUtil;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.io.IOException;


public class Google2Test extends SpringBaseTest {

    @Autowired
    private GooglePage googlePage;

    @Autowired
    private ScreenShotUtil screenShotUtil; //Thís bean is WebDriver bean

    @Test
    public void googleTest() throws IOException {
        this.googlePage.goTo();
        Assertions.assertTrue(this.googlePage.isAt());
        this.googlePage.getSearchComponent().search("Selenium");
        Assertions.assertTrue(this.googlePage.getSearchResult().isAt());
        Assertions.assertTrue(this.googlePage.getSearchResult().getCount() > 2);
        this.screenShotUtil.takeScreenShot("google2test-result");
        this.googlePage.close();
    }
}

