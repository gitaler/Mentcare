package medcare.Controller.Selenium.pageobjects;

import medcare.Model.Medic;
import medcare.Model.Patient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AddNewMedicPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1/span")
    private WebElement addNewMedicTitleText;

    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement addNewMedicNameText;

    @FindBy(xpath = "//*[@id=\"surname\"]")
    private WebElement addNewMedicSurnameText;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement addNewMedicPasswordText;

    @FindBy(xpath = "//*[@id=\"telephone\"]")
    private WebElement addNewMedicTelephoneText;

    @FindBy(xpath = "//*[@id=\"specialization\"]")
    private WebElement addNewMedicSpecializationText;


    @FindBy(xpath = "/html/body/div/div/div/form/p")
    private WebElement addNewMedicErrorText;

    @FindBy(xpath = "/html/body/div/div/div/form/input[7]")
    private WebElement addNewMedicSubmitButton;

    public AddNewMedicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdminHomePage close(){
        HomePageLink.click();
        return new AdminHomePage(driver);
    }

    public String getAddNewPatientErrorText() {
        return addNewMedicErrorText.getText();
    }

    public AllMedicsList insertMedic(String name, String surname, String password, String telephone, String specialization){
        addNewMedicNameText.sendKeys(name);
        addNewMedicSurnameText.sendKeys(surname);
        addNewMedicPasswordText.sendKeys(password);
        addNewMedicTelephoneText.sendKeys(telephone);
        addNewMedicSpecializationText.sendKeys(specialization);
        addNewMedicSubmitButton.submit();
        return new AllMedicsList(driver);
    }

    public boolean isAddMedicPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isAddMedicPage = true;
        try {
            addNewMedicTitleText.isDisplayed();
        }
        catch (Exception e) {
            isAddMedicPage = false;
        }

        return isAddMedicPage;

    }
}
