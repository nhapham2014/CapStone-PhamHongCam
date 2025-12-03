package testcases.selectTicket;
import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

public class TC_BookTicketByMovieNameTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    /*
     * Pre-condition:
     * Login thành công trước khi chạy test
     */
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
    public void TC01_cinemaAndDateHaveOnlyDefaultOptionWhenMovieNotSelected() {
        homePage = new HomePage(driver);
        loginBeforeTest();
        homePage.waitCinemaDefaultOnly();
        int cinemaCount = homePage.getCinemaOptionsCount();
        Assert.assertEquals(cinemaCount, 1,
                "Dropdown Rạp phải chỉ có 1 option mặc định khi chưa chọn Phim");
    }
    @Test
    public void TC02_changeFilm_cinemaAndShowtimeMustReset() {
        homePage = new HomePage(driver);
        loginBeforeTest();

        // Step 1: Chọn phim AVATAR 2
        homePage.selectMovie("AVATAR 2");
        homePage.selectCinema("CGV - Golden Plaza");
        homePage.selectDate("15/10/2021 ~ 08:42");

        // Step 2: Đổi sang phim khác → Thor 6
        homePage.selectMovie("Thor 6");

        // Expected
        Assert.assertTrue(homePage.cinemaIsReset(),
                "Sau khi đổi phim, dropdown Rạp phải reset về mặc định");
        Assert.assertTrue(homePage.showtimeIsReset(),
                "Sau khi đổi phim, dropdown Ngày giờ phải reset về mặc định");

    }
    @Test
    public void TC03_changeCinema_showtimeMustReset() {
        homePage = new HomePage(driver);
        loginBeforeTest();

        // Step 1: Chọn phim A
        homePage.selectMovie("AVATAR 2");
        homePage.selectCinema("CGV - Golden Plaza");
        homePage.selectDate("15/10/2021 ~ 08:42");

        // Step 2: Đổi sang phim khác → Frozen 2
        homePage.selectCinema("MegaGS - Cao Thắng");

        // Expected
        Assert.assertTrue(homePage.showtimeIsReset(),
                "Sau khi đổi phim, dropdown Ngày giờ phải reset về mặc định");
    }
    @Test
    public void TC04_selectMovie_thenCinemaLoaded() {
        homePage = new HomePage(driver);
        loginBeforeTest();

        homePage.selectMovie("AVATAR 2");
       // homePage.waitCinemaOptionsLoaded();

        int cinemaCount = homePage.getCinemaOptionsCount();

        Assert.assertTrue(cinemaCount > 1,
                "Sau khi chọn phim, dropdown Rạp phải load danh sách rạp");
    }
    @Test
    public void TC05_selectCinema_thenShowtimeLoaded() {
        homePage = new HomePage(driver);
        loginBeforeTest();

        homePage.selectMovie("AVATAR 2");
        homePage.selectCinema("CGV - Golden Plaza");

        int showtimeCount = homePage.getShowtimeOptionsCount();
        Assert.assertTrue(showtimeCount > 1,
                "Dropdown Giờ chiếu phải load dữ liệu sau khi chọn Rạp");
    }
    @Test
    public void TC05_fullFlowClickBuyTicket() {
        homePage = new HomePage(driver);
        loginBeforeTest();

        homePage.selectMovie("AVATAR 2");
        homePage.selectCinema("CGV - Golden Plaza");
        homePage.selectDate("15/10/2021 ~ 08:42");
        homePage.clickBuyTicket();

        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase"),
                "Không chuyển sang màn hình chọn ghế sau khi bấm Mua Vé Ngay");
    }
}
