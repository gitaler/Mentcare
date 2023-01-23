package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MedicInfo {
    private WebDriver driver;
    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/div/div/h3")
    private WebElement medicInfoPageText;

    @FindBy(xpath = "/html/body/div/div/div/p[1]")
    private WebElement nameText;

    @FindBy(xpath = "/html/body/div/div/div/p[2]")
    private WebElement surnameText;

    @FindBy(xpath = "/html/body/div/div/div/p[3]")
    private WebElement telephoneText;

    @FindBy(xpath = "/html/body/div/div/div/p[4]")
    private WebElement specializationText;

    @FindBy(xpath = "/html/body/div/div/div/a")
    private WebElement listOfPatientLink;


    public MedicInfo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
//
//    public MedicPatientList goToPrescriptionList() {
//        listOfPatientLink.click();
//        return new MedicPatientList(driver);
//    }

    public MedicHomePage close() {
        HomePageLink.click();
        return new MedicHomePage(driver);
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

    public String getSpecializationText() {
        return specializationText.getText();
    }

    public boolean isMedicInfoPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isMedicInfoPage = true;
        try {
            medicInfoPageText.isDisplayed();
        } catch (NoSuchElementException e) {
            isMedicInfoPage = false;
        }

        return isMedicInfoPage;
    }

}

