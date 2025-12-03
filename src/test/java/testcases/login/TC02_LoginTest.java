package testcases.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

public class TC02_LoginTest extends BaseTest {

    //class variable
    LoginPage loginPage;
    HomePage homePage;

    @Test
    public void testValidLogin() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        //Step 1: Go to https://demo1.cybersoft.edu.vn
        System.out.println("driver = " + driver);
        Reporter.log("Step 1: Go to https://demo1.cybersoft.edu.vn");
        driver.get("https://demo1.cybersoft.edu.vn");

        //Step 2: Click 'Đăng Nhập' link
        homePage.navigateLoginPage();

        //Step 3: Enter account
        String newAcount = "cam";
        loginPage.enterAccount(newAcount);

        //Step 4: Enter password
        loginPage.enterPassword("123456");

        //Step 5: Click login
        loginPage.clickLogin();

        //Step 6: Verify login successully
        //VP1: "Đăng nhập thành công" message displays
        String actualLoginMsg = loginPage.getMessage();
        Assert.assertEquals(actualLoginMsg, "Đăng nhập thành công", "Login message");

        //VP2: User profile name displays
        String actualDisplayName = homePage.getUserProfileName();
        Assert.assertEquals(actualDisplayName, "Pham Hong Cam", "User Profile name");

    }
}
