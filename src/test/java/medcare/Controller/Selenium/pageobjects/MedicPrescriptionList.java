package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MedicPrescriptionList {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1")
    private WebElement prescriptionListText;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[1]")
    private WebElement completeName;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[2]")
    private WebElement drug;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[3]")
    private WebElement quantity;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[4]")
    private WebElement description;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[5]")
    private WebElement date;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[6]")
    private WebElement edit;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr[1]/td[7]")
    private WebElement delete;

    @FindBy(xpath = "/html/body/div/div/table/tbody/tr")
    private List<WebElement>  rowNumber;

    public MedicPrescriptionList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public int getRowNumber(){
        return this.rowNumber.size();
    }

    public PatientHomePage close() {
        HomePageLink.click();
        return new PatientHomePage(driver);
    }

    public MedicPrescriptionList deleteFirstPrescription(){
        delete.click();
        return new MedicPrescriptionList(driver);
    }

    public MedicEditPrescription editFirstPrescription(){
        edit.click();
        return new MedicEditPrescription(driver);
    }

    public boolean isPrescriptionListPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isPrescriptionListPage = true;
        try {
            prescriptionListText.isDisplayed();
        } catch (NoSuchElementException e) {
            isPrescriptionListPage = false;
        }

        return isPrescriptionListPage;
    }

    public WebElement getCompleteName() {
        return completeName;
    }

    public void setCompleteName(WebElement completeName) {
        this.completeName = completeName;
    }

    public WebElement getDrug() {
        return drug;
    }

    public void setDrug(WebElement drug) {
        this.drug = drug;
    }

    public WebElement getQuantity() {
        return quantity;
    }

    public void setQuantity(WebElement quantity) {
        this.quantity = quantity;
    }

    public WebElement getDescription() {
        return description;
    }

    public void setDescription(WebElement description) {
        this.description = description;
    }

    public WebElement getDate() {
        return date;
    }

    public void setDate(WebElement date) {
        this.date = date;
    }
}
