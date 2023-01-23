package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class PatientHomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"services\"]/h1/span")
    private WebElement patientHomeTitleText;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[1]/a")
    private WebElement profileInfoLink;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[2]/a")
    private WebElement prescriptionsLink;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[3]/a")
    private WebElement logOutLink;

    public PatientHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public LoginPage logout(){
        logOutLink.click();

        return new LoginPage(driver);
    }


    public PatientInfo goToPatinetInfo () {

        profileInfoLink.click();
        return new PatientInfo(driver);
    }

    public Prescriptions goToPrescriptionsPage () {
        prescriptionsLink.click();
        return new Prescriptions(driver);
    }

    public boolean isPatientHomePage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isPatientHomePage = true;
        try {
            patientHomeTitleText.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isPatientHomePage = false;
        }

        return isPatientHomePage;

    }
}
