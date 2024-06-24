package com.tjn.tests.web.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tjn.BaseTest;
import com.tjn.pages.web.DashBoardPage;
import com.tjn.pages.web.HomePage;
import com.tjn.pages.web.HomePage.HomePageLink;
import com.tjn.pages.web.LoginPage;
import com.tjn.web.dto.LoginDto;
import com.tjn.aggregator.TestData;
import com.tjn.util.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class LoginTests extends BaseTest {
    static List<TestData<LoginDto, String>> jsonProvider() {
        TypeReference<List<TestData<LoginDto, String>>> typeRef = new TypeReference<>() {};
        return JsonUtil.getTestCases("/test-data/web/auth/login-testcases.json", typeRef);
    }

    @ParameterizedTest
    @DisplayName("Login Feature Test")
    @MethodSource("jsonProvider")
    public void loginValidCredentialsSuccessTest(TestData<LoginDto, String> testData){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        DashBoardPage dashBoardPage = new DashBoardPage(driver);

        LoginDto dto = testData.getData();
        String expectedResult = testData.getExpectedResult();

        homePage.goTo("http://localhost");
        Assertions.assertTrue(homePage.isAt());
        homePage.navigate(HomePageLink.LOGIN);
        Assertions.assertTrue(loginPage.isAt());
        loginPage.enterData(dto);
        loginPage.submitData();
        Assertions.assertTrue(dashBoardPage.isAt());
        Assertions.assertEquals(expectedResult, dashBoardPage.getCustomerName().getText());
    }
}
