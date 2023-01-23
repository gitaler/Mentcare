package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MedicEditPrescription {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1")
    private WebElement medicEditPrescriptionTitleText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[4]")
    private WebElement editDrugInput;

    @FindBy(xpath = "/html/body/div/div/div/form/input[5]")
    private WebElement editQuantityInput;

    @FindBy(xpath = "/html/body/div/div/div/form/input[6]")
    private WebElement editDescriptionInput;

    @FindBy(xpath = "/html/body/div/div/div/form/input[8]")
    private WebElement submitInput;

    @FindBy(xpath = "/html/body/div/div/div/p[2]")
    private WebElement errorMessage;

    public MedicEditPrescription(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public MedicPrescriptionList editPrescription(String drug, String quantity, String description){
        editDrugInput.clear();
        editDrugInput.sendKeys(drug);
        editQuantityInput.clear();
        editQuantityInput.sendKeys(quantity);
        editDescriptionInput.clear();
        editDescriptionInput.sendKeys(description);
        submitInput.click();
        return new MedicPrescriptionList(driver);
    }

    public boolean isMedicEditPrescription() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isMedicEditPrescription = true;
        try {
            medicEditPrescriptionTitleText.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isMedicEditPrescription = false;
        }

        return isMedicEditPrescription;

    }
}
