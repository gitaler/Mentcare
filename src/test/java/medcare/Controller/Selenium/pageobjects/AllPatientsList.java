package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import java.util.concurrent.TimeUnit;

public class AllPatientsList {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1")
    private WebElement allPatientListTitleText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[1]")
    private WebElement firstPatientIdText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[2]")
    private WebElement firstPatientNameText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[3]")
    private WebElement firstPatientSurnameText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[4]")
    private WebElement firstPatientAgeText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[5]")
    private WebElement firstPatientTelephoneText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[6]")
    private WebElement firstPatientDiagnosisText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[7]")
    private WebElement firstPatientEditLink;

    @FindBy(xpath = "/html/body/div/a")
    private WebElement addNewPatientLink;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[2]/td[2]")
    private WebElement editedPatientName;

    @FindBy(xpath = "/html/body/div/table/tbody/tr")
    private List<WebElement> rowNumber;

    public AllPatientsList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public String getFirstPatientNameText() {
        return firstPatientNameText.getText();
    }

    public int getRowNumber(){
        return this.rowNumber.size();
    }

    public String getNewPatientName() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);

        WebElement newPatientName = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[3]/td[2]"));

        return newPatientName.getText();
    }

    public String getEditedPatientName() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        WebElement editedPatientName = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[2]"));
        return editedPatientName.getText();
    }


    public AddNewPatientPage goToAddNewPatientPage(){
        addNewPatientLink.click();
        return new AddNewPatientPage(driver);
    }

    public EditPatient editPatient(){
        firstPatientEditLink.click();
        return new EditPatient(driver);
    }


    public boolean isAllPatientsListPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isAllPatientsListPage = true;
        try {
            allPatientListTitleText.getText().equals("Patient list");
        }
        catch (NoSuchElementException e) {
            isAllPatientsListPage = false;
        }

        return isAllPatientsListPage;

    }
}
