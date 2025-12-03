package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected final Logger LOG = LogManager.getLogger(getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    public WebElement waitForVisibilityOfElementLocated(By locator) {
        LOG.info("waitForVisibilityOfElementLocated: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForPresentOfElementLocated(By locator) {
        LOG.info("waitForPresentOfElementLocated" + locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        LOG.info("waitForElementToBeClickable: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void sendKeys(By locator, String value) {
        LOG.info("sendKeys: " + locator + " with " + value);
        WebElement element = waitForVisibilityOfElementLocated(locator);
        element.sendKeys(value);
    }

    public void click(By locator) {
        LOG.info("click: " + locator);
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    public String getText(By locator) {
        LOG.info("getText: " + locator);
        WebElement element = waitForVisibilityOfElementLocated(locator);
        return element.getText();
    }

    public String getSelectedOptionText(By locator) {
        LOG.info("getText: " + locator);
        WebElement element = waitForVisibilityOfElementLocated(locator);
        return new Select(element).getFirstSelectedOption().getText();
    }

    public void waitOptionsDropDownLoaded(By locator) {
        LOG.info("waitOptionsDropDownLoaded: " + locator);
         wait.until(driver ->
                driver.findElements(locator).size() > 1
        );

    }
    public void waitValueDefaultOnly(By locator) {
        wait.until(driver ->
                driver.findElements(locator).size() == 1
        );
    }
    public int getOptionsCount(By locator) {
        List<WebElement> options = driver.findElements(locator);
        return options.size();
    }

    public void selectOptionByText(By locator, String optionText) {
        WebElement element = waitForElementToBeClickable(locator);
        // Chọn option bằng Select
        Select select = new Select(element);
            select.selectByVisibleText(optionText);
    }
    public void hover(By locator){
        waitForVisibilityOfElementLocated(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).perform();
    }

}
