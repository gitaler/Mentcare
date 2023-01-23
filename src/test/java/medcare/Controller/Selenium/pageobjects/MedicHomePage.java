package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MedicHomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"services\"]/h1/span")
    private WebElement medicHomeTitleText;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[1]/a")
    private WebElement profileInfoLink;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[2]/a")
    private WebElement prescriptionListLink;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[3]/a")
    private WebElement addPrescriptionLink;

    @FindBy(xpath = "//*[@id=\"services\"]/div/div[4]/a")
    private WebElement logOutLink;


    public MedicHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public LoginPage logout(){
        logOutLink.click();
        return new LoginPage(driver);
    }


    public MedicInfo goToMedicInfo () {

        profileInfoLink.click();
        return new MedicInfo(driver);
    }

    public MedicPrescriptionList goToPrescriptionList () {

        prescriptionListLink.click();
        return new MedicPrescriptionList(driver);
    }

    public MedicInfo goToAddPrescription () {

        addPrescriptionLink.click();
        return new MedicInfo(driver);
    }

    public boolean isMedicHomePage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isMedicHomePage = true;
        try {
            medicHomeTitleText.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isMedicHomePage = false;
        }

        return isMedicHomePage;
    }
}
