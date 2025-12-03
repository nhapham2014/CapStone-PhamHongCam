package base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;

public class BaseTestWithLogin extends BaseTest{
    @BeforeClass
    @Override
    public void beforeClass(){
        super.beforeClass();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.get("https://demo1.cybersoft.edu.vn");
        homePage.navigateLoginPage();
        loginPage.login("cam0592","Diqit0505@"); // auto login cho các testcase cần login
    }
}
