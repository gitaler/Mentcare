package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AdminLoginPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"login\"]/div/form/input[1]")
    private WebElement usernameFieldLogin;

    @FindBy(xpath = "//*[@id=\"login\"]/div/form/input[2]")
    private WebElement passwordFieldLogin;

    @FindBy(xpath = "//*[@id=\"login\"]/div/form/p")
    private WebElement loginError;

    @FindBy(xpath = "//*[@id=\"login\"]/div/form/input[3]")
    private WebElement submitButtonLogin;


    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void login(String username, String password) {
        usernameFieldLogin.sendKeys(username);
        passwordFieldLogin.sendKeys(password);
        submitButtonLogin.click();
    }


    public String getError() {
        return loginError.getText();
    }

    public boolean isLoginPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isLoginFormPresent = true;
        try {
            usernameFieldLogin.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isLoginFormPresent = false;
        }

        return isLoginFormPresent;

    }
}
