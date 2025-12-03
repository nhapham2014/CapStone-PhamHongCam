package testcases.selectTicket;
import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.DetailMoviePage;
import pages.HomePage;
import pages.LoginPage;

import java.time.LocalDateTime;
import java.util.List;

public class TC_BookTicketByPosterTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    DetailMoviePage detailMoviePage;
    private void loginBeforeTest() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        System.out.println("driver = " + driver);
        Reporter.log("Step 1: Go to https://demo1.cybersoft.edu.vn");
        driver.get("https://demo1.cybersoft.edu.vn");

        //Step 2: Click 'Đăng Nhập' link
        homePage.navigateLoginPage();
        loginPage.login("cam0592","Diqit0505@");

    }

    @Test
    public void TC01_verifyMovieNameAtDetailPage() {
        homePage = new HomePage(driver);
        detailMoviePage = new DetailMoviePage(driver);
        loginBeforeTest();
        // Lấy tên phim ở trang danh sách
        String titleList = homePage.getMovieTitle("avatar-2_gp09");
        System.out.println("Tên phim trang list: " + titleList);

        // Click vào phim
        homePage.selectThumbnailMovie("avatar-2_gp09");

        // Lấy tên phim ở trang chi tiết
        String titleDetail = detailMoviePage.getMovieDetailTitle();
        System.out.println("Tên phim trang detail: " + titleDetail);
        Assert.assertEquals(titleDetail, titleList,
                "Tên phim KHÔNG trùng nhau!");
    }

    @Test
    public void TC02_verifyTrailerPopupDisplay() {
        homePage = new HomePage(driver);
        loginBeforeTest();
        // Click nút video trailer
        homePage.clickTrailerMovie("avatar-2_gp09");

        // Verify trailer hiển thị
        Assert.assertTrue(
                homePage.isTrailerDisplayed(),
                "Trailer KHÔNG hiển thị sau khi click Video!"
        );
    }
    @Test
    public void TC03_navigateToMovieDetailPageAfterClickBuyTicketButton(){
        homePage = new HomePage(driver);
        loginBeforeTest();
        // Click nút Mua Vé
        homePage.clickBuyTicketAtMovie("avatar-2_gp09");

        // Navigate đến màn hình detail movie
        Assert.assertTrue(driver.getCurrentUrl().contains("detail"),
                "Không chuyển sang màn hình chi tiết phim sau khi bấm nút Mua Vé");

    }
    @Test
    public void TC04_verifyAllShowtimesAreAfterCurrentTime() {
        homePage = new HomePage(driver);
        detailMoviePage = new DetailMoviePage(driver);
        loginBeforeTest();
        homePage.clickBuyTicketAtMovie("avatar-2_gp09");

        // Lấy tất cả ngày giờ từ UI
        List<LocalDateTime> showtimes = detailMoviePage.getAllShowtimes();
        LocalDateTime now = LocalDateTime.now();
        // Kiểm tra từng datetime
        for (LocalDateTime time : showtimes) {
            Assert.assertTrue(
                    time.isAfter(now),
                    "Ngày giờ suất chiếu phải lớn hơn hiện tại. UI: " + time + " | NOW: " + now
            );
        }
    }
    @Test
    public void TC05_navigateToSeatPageAfterSelectShowTime(){
        homePage = new HomePage(driver);
        detailMoviePage = new DetailMoviePage(driver);
        loginBeforeTest();
        homePage.clickBuyTicketAtMovie("avatar-2_gp09");
        // Chọn 1 suất chiếu bất kỳ
        detailMoviePage.selectShowTime("17-10-2021","08:43");
        // Verify điều hướng sang trang chọn ghế
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase"));

    }
    @Test
    public  void test(){

    }
}
