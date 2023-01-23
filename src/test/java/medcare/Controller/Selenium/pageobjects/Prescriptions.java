package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Prescriptions {

    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1/span")
    private WebElement prescriptionTitleText;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[1]")
    private WebElement prescriptionDrugText;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[2]")
    private WebElement prescriptionQuantityText;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[3]")
    private WebElement prescriptionDescriptionText;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[4]")
    private WebElement prescriptionMedicText;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[5]")
    private WebElement prescriptionDateText;

    public Prescriptions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public String getPrescriptionDescriptionText() {
        return prescriptionDescriptionText.getText();
    }

    public String getPrescriptionDrugText() {
        return prescriptionDrugText.getText();
    }

    public String getPrescriptionQuantityText() {
        return prescriptionQuantityText.getText();
    }

    public String getPrescriptionMedicText() {
        return prescriptionMedicText.getText();
    }

    public String getPrescriptionDateText() {
        return prescriptionDateText.getText();
    }

    public boolean isPrescriptionsPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isPrescriptionsPage = true;
        try {
            prescriptionTitleText.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isPrescriptionsPage = false;
        }

        return isPrescriptionsPage;

    }
}
