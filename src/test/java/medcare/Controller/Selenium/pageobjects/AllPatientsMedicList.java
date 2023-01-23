package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AllPatientsMedicList {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1")
    private WebElement allPatientMedicListTitleText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[1]")
    private WebElement firstPatientNameText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[2]")
    private WebElement firstPatientSurnameText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[3]")
    private WebElement firstPatientAgeText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[4]")
    private WebElement firstPatientTelephoneText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[5]")
    private WebElement firstPatientDiagnosisText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[2]/td[6]")
    private WebElement addPrescriptionLink;


    public AllPatientsMedicList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PatientHomePage close(){
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public MedicAddPrescription addPrescriptionFirstPatient(){
        addPrescriptionLink.click();
        return new MedicAddPrescription(driver);
    }
    public boolean isAllPatientsListPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isAllPatientsListPage = true;
        try {
            allPatientMedicListTitleText.getText().equals("Patient list");
        }
        catch (NoSuchElementException e) {
            isAllPatientsListPage = false;
        }

        return isAllPatientsListPage;

    }
}
