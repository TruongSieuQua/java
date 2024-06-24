import com.tjn.BaseTest;
import com.tjn.pages.admin.auth.LoginPage;

import com.tjn.pages.admin.dashboard.DashBoardPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class LoginValidCredentialsSuccessTest extends BaseTest {
//
//    @ParameterizedTest(name = "Login by username({0}) should return user {2}")
//    @CsvFileSource(resources = "/test-data/admin/auth/login-successful-test.csv", numLinesToSkip = 1)
//    @DisplayName("loginValidCredentialsSuccessTest")
//    public void loginValidCredentialsSuccessTest(
//            @AggregateWith(LoginArgumentAggregator.class) TestData<LoginDto, String> testData
//    ){
//        LoginPage loginPage = new LoginPage(driver);
//        DashBoardPage dashBoardPage = new DashBoardPage(driver);
//
//        LoginDto dto = testData.getData();
//        String expectedResult = testData.getExpectedResult();
//
//        loginPage.goTo("http://localhost/admin");
//        Assertions.assertTrue(loginPage.isAt());
//        loginPage.enterUserCredentials(dto.username(), dto.password());
//        loginPage.login();
//        Assertions.assertTrue(dashBoardPage.isAt());
//        Assertions.assertEquals(expectedResult, dashBoardPage.getUsername());
//    }
//}

