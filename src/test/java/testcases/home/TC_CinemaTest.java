package testcases.home;
import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;

import java.time.LocalDateTime;
import java.util.List;
public class TC_CinemaTest extends BaseTestWithLogin{
    HomePage homePage;
    LoginPage loginPage;
    //TC01: Verify list cinema after user select a cinema logo
    @Test
    public void TC01_verifyListCinemaWhenSelectCinemaLogo(){
        homePage = new HomePage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.clickCinemaLogo("bhd");
        //Step 2: Get list cinema
        ExtentReportManager.info("Step 2: Get list cinema");
        LOG.info("Step 2: Get list cinema");
        List<String> cinemas = homePage.getCinemaList();
        //Step 3: Verify list cinema
        ExtentReportManager.info("Step 3: Verify list cinema ");
        LOG.info("Step 3: Verify list cinema ");
        for (String c : cinemas) {
            Assert.assertTrue(c.toLowerCase().contains("bhd"),
                    "Rạp " + c + " KHÔNG thuộc hệ thống!");
        }
    }
    //TC02: Verfy list show time must larger than current
    @Test
    public void TC02_verifyAllShowTimeLargerCurrent(){
        homePage = new HomePage(driver);
        loginPage= new LoginPage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.clickCinemaLogo("bhd");
        //Step 2: Select a cinema branch
        ExtentReportManager.info("Step 2: Select a cinema branch");
        LOG.info("Step 2: Select a cinema branch");
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
