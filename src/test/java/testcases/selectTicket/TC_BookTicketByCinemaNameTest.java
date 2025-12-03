package testcases.selectTicket;
import base.BaseTest;
import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

import java.time.LocalDateTime;
import java.util.List;

public class TC_BookTicketByCinemaNameTest extends BaseTestWithLogin {
    HomePage homePage;
    LoginPage loginPage;
    /*
     * Pre-condition:
     * Login thành công trước khi chạy test
     */
//    private void loginBeforeTest() {
//        loginPage = new LoginPage(driver);
//        homePage = new HomePage(driver);
//        System.out.println("driver = " + driver);
//        Reporter.log("Step 1: Go to https://demo1.cybersoft.edu.vn");
//        driver.get("https://demo1.cybersoft.edu.vn");
//
//        //Step 2: Click 'Đăng Nhập' link
//        homePage.navigateLoginPage();
//        loginPage.login("cam0592","Diqit0505@");
//
//    }
    @Test
    public void TC01_verifyListCinemaWhenSelectCinemaLogo(){
        homePage = new HomePage(driver);
        //loginBeforeTest();
        homePage.clickCinemaLogo("bhd");
        //Lấy danh sách rạp hiển thị
        List<String> cinemas = homePage.getCinemaList();
        System.out.println();
        //Verify danh sách rạp hiển thị đúng
        for (String c : cinemas) {
            Assert.assertTrue(c.toLowerCase().contains("bhd"),
                    "Rạp " + c + " KHÔNG thuộc hệ thống!");
        }

    }
    @Test
    public void TC02_verifyAllShowTimeLargerCurrent(){
        homePage = new HomePage(driver);
        loginPage= new LoginPage(driver);
        //loginBeforeTest();
        homePage.clickCinemaLogo("bhd");
        homePage.selectCinemaBranch("BHD Star Cineplex - Phạm Hùng");
        List<LocalDateTime> listShowTime = homePage.getAllShowtimesByCinema();
        LocalDateTime now = LocalDateTime.now();
        for (LocalDateTime time : listShowTime){
            Assert.assertEquals(time.isAfter(now),"Ngày giờ suất chiếu phải lớn hơn hiện tại. UI: " + time + " | NOW: "+ now);
        }
    }
    @Test
    public void TC03_verifyForShowTimeOverLap(){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        //loginBeforeTest();
        homePage.clickCinemaLogo("bhd");
        homePage.selectCinemaBranch("BHD Star Cineplex - Phạm Hùng");
        List<LocalDateTime> listShowTimeMovie = homePage.getAllShowtimesByMovie("John Wick");
        System.out.println(listShowTimeMovie);
        Assert.assertTrue(homePage.isShowtimeListUnique(listShowTimeMovie),"Lỗi: có suất chiếu của phim trùng nhau");

    }
    @Test
    public void TC04_navigateToSeatPageAfterSelectShowTime(){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        //loginBeforeTest();
        homePage.clickCinemaLogo("cgv");
        homePage.selectCinemaBranch("CGV - Pandora City");
        homePage.selectShowTime("John Wick","15-10-2020","13:10");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase/"));
    }
}
