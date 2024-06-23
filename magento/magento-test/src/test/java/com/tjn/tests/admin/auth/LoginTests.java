package com.tjn.tests.admin.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tjn.BaseTest;
import com.tjn.admin.dto.LoginDto;
import com.tjn.aggregator.TestData;
import com.tjn.pages.admin.auth.LoginPage;
import com.tjn.pages.admin.dashboard.DashBoardPage;
import com.tjn.util.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class LoginTests extends BaseTest {

    static List<TestData<LoginDto, String>> jsonProvider() {
        TypeReference<List<TestData<LoginDto, String>>> typeRef = new TypeReference<>() {};
        return JsonUtil.getTestCases("/test-data/admin/auth/login-testcases.json", typeRef);
    }

    @ParameterizedTest
    @DisplayName("loginValidCredentialsSuccessTest")
    @MethodSource("jsonProvider")
    public void loginValidCredentialsSuccessTest(TestData<LoginDto, String> testData){
        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashBoardPage = new DashBoardPage(driver);

        LoginDto dto = testData.getData();
        String expectedResult = testData.getExpectedResult();

        loginPage.goTo("http://localhost/admin");
        Assertions.assertTrue(loginPage.isAt());
        loginPage.enterUserCredentials(dto.username(), dto.password());
        loginPage.login();
        Assertions.assertTrue(dashBoardPage.isAt());
        Assertions.assertEquals(expectedResult, dashBoardPage.getUsername());
    }
}
