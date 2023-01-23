package medcare.Controller.Selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AllMedicsList {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/a[1]")
    private WebElement HomePageLink;

    @FindBy(xpath = "/html/body/div/h1")
    private WebElement allMedicsListTitleText;

    @FindBy(xpath = "/html/body/div/a")
    private WebElement addNewMedicLink;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[2]")
    private WebElement firstMedicNameText;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[6]/a")
    private WebElement editFirstMedicLink;

    @FindBy(xpath = "/html/body/div/table/tbody/tr")
    private List<WebElement> rowNumber;

    public AllMedicsList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdminHomePage close(){
        HomePageLink.click();
        return new AdminHomePage(driver);
    }

    public EditMedic editMedic(){
        editFirstMedicLink.click();
        return new EditMedic(driver);
    }

    public String getFirstMedicNameText() {
        return firstMedicNameText.getText();
    }

    public String getEditedName() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        WebElement editedMedicNameText = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[2]"));
        return editedMedicNameText.getText();
    }

    public int getRowNumber(){
        return this.rowNumber.size();
    }

    public AddNewMedicPage goToAddNewMedicPage(){
        addNewMedicLink.click();
        return new AddNewMedicPage(driver);
    }

    public boolean isAllMedicsListPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
        boolean isAllMedicsListPage = true;
        try {
            allMedicsListTitleText.getText().equals("Medic list");
        }
        catch (NoSuchElementException e) {
            isAllMedicsListPage = false;
        }

        return isAllMedicsListPage;

    }
}
