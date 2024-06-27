package com.tjn.tests.web.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tjn.BaseTest;
import com.tjn.aggregator.TestCase;
import com.tjn.aggregator.TestResult;
import com.tjn.pages.web.DashBoardPage;
import com.tjn.pages.web.HomePage;
import com.tjn.pages.web.LoginPage;
import com.tjn.util.JsonUtil;
import com.tjn.web.dto.LoginDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class LoginEmailNotExistsTests extends BaseTest {

    static List<TestCase<LoginDto, TestResult>> jsonProvider() {
        TypeReference<List<TestCase<LoginDto, TestResult>>> typeRef = new TypeReference<>() {};
        return JsonUtil.getTestCases("/test-data/web/auth/login-email-not-exists-testcases.json", typeRef);
    }

    @ParameterizedTest
    @DisplayName("Login Successful Test")
    @MethodSource("jsonProvider")
    public void loginSuccessfulTests(TestCase<LoginDto, TestResult> testCase){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        DashBoardPage dashBoardPage = new DashBoardPage(driver);

        LoginDto dto = testCase.getData();
        TestResult expectedResult = testCase.getExpectedResult();

        homePage.goTo("http://localhost");
        Assertions.assertTrue(homePage.isAt());
        homePage.navigate(HomePage.HomePageLink.LOGIN);
        Assertions.assertTrue(loginPage.isAt());
        loginPage.enterData(dto);
        loginPage.submitData();
        Assertions.assertTrue(loginPage.getPageErrorMessage().isDisplayed());
    }
    
}
