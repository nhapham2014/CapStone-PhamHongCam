package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends CommonPage {

    private By byTxtAccount = By.name("taiKhoan");;
    private By byTxtPassword = By.id("matKhau");;
    private By byTxtConfirmPassword = By.id("confirmPassWord");;
    private By byTxtName = By.id("hoTen");;
    private By byTxtEmail = By.id("email");;
    private By byBtnRegister = By.xpath("//button[.='Đăng ký']");;
    private By byLblRegisterMsg = By.id("swal2-title");;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterAccount(String account) {
        sendKeys(byTxtAccount, account);
    }

    public void enterPassword(String password) {
        sendKeys(byTxtPassword, password);
    }

    public void enterConfirmPassword(String password) {
        sendKeys(byTxtConfirmPassword, password);
    }

    public void enterName(String name) {
        sendKeys(byTxtName, name);
    }

    public void enterEmail(String email) {
        sendKeys(byTxtEmail, email);
    }

    public void clickRegister() {
        click(byBtnRegister);
    }

    public String getMessage() {
        return getText(byLblRegisterMsg);
    }
}
