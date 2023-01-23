package medcare.Controller.Selenium.pageobjects;

import medcare.Model.Prescription;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MedicAddPrescription {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1")
    private WebElement medicAddPrescriptionTitleText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[3]")
    private WebElement addDrugInput;

    @FindBy(xpath = "/html/body/div/div/div/form/input[4]")
    private WebElement addQuantityInput;

    @FindBy(xpath = "/html/body/div/div/div/form/input[5]")
    private WebElement addDescriptionInput;

    @FindBy(xpath = "/html/body/div/div/div/form/input[7]")
    private WebElement submitInput;

    @FindBy(xpath = "/html/body/div/div/div/p[2]")
    private WebElement errorMessage;

    public MedicAddPrescription(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public MedicPrescriptionList insertPrescription(String drug, String quantity, String description){
        addDrugInput.sendKeys(drug);
        addQuantityInput.sendKeys(quantity);
        addDescriptionInput.sendKeys(description);
        submitInput.click();
        return new MedicPrescriptionList(driver);
    }

    public boolean isMedicAddPrescription() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isMedicAddPrescription = true;
        try {
            medicAddPrescriptionTitleText.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isMedicAddPrescription = false;
        }

        return isMedicAddPrescription;

    }
}
