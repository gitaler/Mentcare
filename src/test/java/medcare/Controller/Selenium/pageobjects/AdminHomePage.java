package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AdminHomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"services\"]/h1")
    private WebElement adminHomeTitleText;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[1]/a")
    private WebElement allPatientsLink;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[2]/a")
    private WebElement allMedicsLink;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[3]/a")
    private WebElement logOutLink;

    public AdminHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdminLoginPage logout(){
        logOutLink.click();
        return new AdminLoginPage(driver);
    }


    public AllPatientsList goToAllPatients () {
        allPatientsLink.click();
        return new AllPatientsList(driver);
    }

    public AllMedicsList goToAllMedics () {
        allMedicsLink.click();
        return new AllMedicsList(driver);
    }

    public boolean isAdminHomePage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isAdminHomePage = true;
        try {
            adminHomeTitleText.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isAdminHomePage = false;
        }

        return isAdminHomePage;

    }
}
