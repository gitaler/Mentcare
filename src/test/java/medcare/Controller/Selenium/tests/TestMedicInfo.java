package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMedicInfo extends BaseTest {

    @Test
    public void testProfileInfo() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Dottor.Verdi";
        String password = "";


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMedic(username, password);

        MedicHomePage profilePage = new MedicHomePage(driver);
        profilePage.goToMedicInfo();

        MedicInfo medicInfo = new MedicInfo(driver);

        assertTrue(medicInfo.isMedicInfoPage());

        assertEquals("Name: Dottor", medicInfo.getNameText());
        assertEquals("Surname: Verdi", medicInfo.getSurnameText());
        assertEquals("Telephone: +39 12341234", medicInfo.getTelephoneText());
        assertEquals("Specialization: psychiatrist", medicInfo.getSpecializationText());
    }

    @Test
    public void testMedicPrescriptionList() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Dottor.Verdi";
        String password = "";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMedic(username, password);

        MedicHomePage profilePage = new MedicHomePage(driver);
        profilePage.goToPrescriptionList();

        MedicPrescriptionList medicPrescriptionList = new MedicPrescriptionList(driver);
        assertTrue(medicPrescriptionList.isPrescriptionListPage());
    }
}
