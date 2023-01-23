package medcare.Controller.Selenium.pageobjects;

import medcare.Model.Patient;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AddNewPatientPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1/span")
    private WebElement addNewParientTitleText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[1]")
    private WebElement addNewPatientNameText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[2]")
    private WebElement addNewPatientSurnameText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[3]")
    private WebElement addNewPatientPasswordText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[4]")
    private WebElement addNewPatientTelephoneText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[5]")
    private WebElement addNewPatientAgeText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[6]")
    private WebElement addNewPatientDiagnosisText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[7]")
    private WebElement addNewPatientFacilityText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[8]")
    private WebElement addNewPatientWardText;

    @FindBy(xpath = "/html/body/div/div/div/form/p")
    private WebElement addNewPatientErrorText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[9]")
    private WebElement addNewPatientSubmitButton;

    public AddNewPatientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public String getAddNewPatientErrorText() {
        return addNewPatientErrorText.getText();
    }

    public AllPatientsList insertPatient(String name, String surname, String password, String telephone, String age, String facility, String ward, String diagnosis){
        addNewPatientNameText.sendKeys(name);
        addNewPatientSurnameText.sendKeys(surname);
        addNewPatientPasswordText.sendKeys(password);
        addNewPatientTelephoneText.sendKeys(telephone);
        addNewPatientAgeText.sendKeys(age);
        addNewPatientFacilityText.sendKeys(facility);
        addNewPatientWardText.sendKeys(ward);
        addNewPatientDiagnosisText.sendKeys(diagnosis);
        addNewPatientSubmitButton.submit();
        return new AllPatientsList(driver);
    }

    public boolean isAddPatientPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isAddPatientPage = true;
        try {
            addNewParientTitleText.isDisplayed();
        }
        catch (Exception e) {
            isAddPatientPage = false;
        }

        return isAddPatientPage;

    }
}
