package medcare.Controller.Selenium.pageobjects;

import medcare.Model.Patient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class EditPatient {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1")
    private WebElement editPatientTitleText;

    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement editPatientNameText;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement editPatientPasswordText;

    @FindBy(xpath = "/html/body/div/div/div/form/p")
    private WebElement editPatientErrorText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[9]")
    private WebElement editPatientSubmitButton;

    public EditPatient(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }


    public AllPatientsList editPatient(String name, String password){
        editPatientNameText.clear();
        editPatientNameText.sendKeys(name);
        editPatientPasswordText.sendKeys(password);
        editPatientSubmitButton.submit();
        return new AllPatientsList(driver);
    }
    public void clickSubmit(){
        editPatientSubmitButton.click();
    }

    public boolean isEditPatientPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isEditPatientPage = true;
        try {
            editPatientTitleText.isDisplayed();
        }
        catch (Exception e) {
            isEditPatientPage = false;
        }

        return isEditPatientPage;

    }
}
