package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class PatientInfo {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/div/div/p[1]")
    private WebElement nameText;

    @FindBy(xpath = "/html/body/div/div/div/p[2]")
    private WebElement surnameText;

    @FindBy(xpath = "/html/body/div/div/div/p[3]")
    private WebElement telephoneText;

    @FindBy(xpath = "/html/body/div/div/div/p[4]")
    private WebElement ageText;

    @FindBy(xpath = "/html/body/div/div/div/p[5]")
    private WebElement diagnosisText;

    @FindBy(xpath = "/html/body/div/div/div/p[6]")
    private WebElement facilityText;

    @FindBy(xpath = "/html/body/div/div/div/p[7]")
    private WebElement wardText;

    @FindBy(xpath = "//html/body/div/div/div/a")
    private WebElement prescriptionsLink;

    @FindBy(xpath = "//html/body/div/div/div/p[9]")
    private WebElement medicsText;

    public PatientInfo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Prescriptions goToTerapy(){
        prescriptionsLink.click();
        return new Prescriptions(driver);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public String getNameText() {
        return nameText.getText();
    }

    public String getSurnameText() {
        return surnameText.getText();
    }

    public String getTelephoneText() {
        return telephoneText.getText();
    }

    public String getAgeText() {
        return ageText.getText();
    }

    public String getDiagnosisText() {
        return diagnosisText.getText();
    }

    public String getFacilityText() {
        return facilityText.getText();
    }

    public String getWardText() {
        return wardText.getText();
    }

    public String getMedicsText() {
        return medicsText.getText();
    }

    public boolean isPatientInfo() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isPatientInfoDataPresent = true;
        try {
            nameText.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isPatientInfoDataPresent = false;
        }

        return isPatientInfoDataPresent;

    }
}
