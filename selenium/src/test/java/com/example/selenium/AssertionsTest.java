package com.example.selenium;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AssertionsTest extends SpringBaseTest {

    @Test(dataProvider = "getData")
    public void stringTest(String input){
        Assertions.assertThat(input).hasSize(8).startsWith("se").doesNotContain("api").doesNotContainAnyWhitespaces().containsOnlyOnce("i");
    }

    @DataProvider
    public Object[] getData(){
        return new String[]{
                "selenium",
                "selenais",
                "sellasid"
        };
    }

}
