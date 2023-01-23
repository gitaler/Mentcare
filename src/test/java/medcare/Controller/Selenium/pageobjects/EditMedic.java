package medcare.Controller.Selenium.pageobjects;

import medcare.Model.Medic;
import medcare.Model.Patient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class EditMedic {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1")
    private WebElement editMedicTitleText;

    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement editMedicNameText;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement editMedicPasswordText;

    @FindBy(xpath = "/html/body/div/div/div/form/p")
    private WebElement editPatientErrorText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[7]")
    private WebElement editMedicSubmitButton;

    public EditMedic(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public AllMedicsList editMedic(String name, String password){
        editMedicNameText.clear();
        editMedicNameText.sendKeys(name);
        editMedicPasswordText.sendKeys(password);
        editMedicSubmitButton.submit();
        return new AllMedicsList(driver);
    }

    public boolean isEditMedicPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isEditMedicPage = true;
        try {
            editMedicTitleText.isDisplayed();
        }
        catch (Exception e) {
            isEditMedicPage = false;
        }

        return isEditMedicPage;

    }
}
