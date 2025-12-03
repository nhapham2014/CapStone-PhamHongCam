package pages;

import base.BasePage;
import components.TopBarNavigation;
import org.openqa.selenium.WebDriver;

public class CommonPage extends BasePage {

    protected TopBarNavigation topBarNavigation;

    public CommonPage(WebDriver driver) {
        super(driver);
        System.out.println("CommonPage driver = " + driver);
        topBarNavigation = new TopBarNavigation(driver);
    }

    public void navigateLoginPage() {
        topBarNavigation.navigateLoginPage();
    }

    public void navigateRegisterPage() {
        topBarNavigation.navigateRegisterPage();
    }
}
