package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailMoviePage extends CommonPage {
    private By byLbMovieName = By.xpath("//h1[contains(@class,'MuiTypography-h1')]");
    private By byListShowTime = By.xpath("//div[contains(@class,'MuiGrid-item')]//a[p[contains(text(),'-')]]");
    private By byDateText = By.xpath(".//p[contains(text(),'-')]");
    private By byTimeText = By.xpath(".//p[contains(text(),':')]");
    public DetailMoviePage(WebDriver driver) {
        super(driver);
    }
    public String getMovieDetailTitle() {
        waitForVisibilityOfElementLocated(byLbMovieName);

        return driver.findElement(byLbMovieName)
                .getText()
                .trim();
    }
    public List<LocalDateTime> getAllShowtimes() {
        waitForVisibilityOfElementLocated(byListShowTime);
        List<WebElement> links = driver.findElements(byListShowTime);
        List<LocalDateTime> dateTimes = new ArrayList<>();
        // Date format trong UI
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (WebElement link : links) {
            // Ghép thành chuỗi datetime
            String dateTimeText = getText(byDateText) + " " + getText(byTimeText);
            // Parse thành LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, formatter);
            dateTimes.add(dateTime);
        }
        return dateTimes;

    }
    public void selectShowTime(String date, String time){
        By byShowTimeOption = By.xpath("//a[p[normalize-space()='" + date +"'] and p[normalize-space()='" + time +"']]");
        click(byShowTimeOption);

    }
}
