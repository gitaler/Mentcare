package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMedicDeletePrescription extends BaseTest {
    @Test
    public void testDeletePrescriptions() {
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

        int initSize = medicPrescriptionList.getRowNumber();

        medicPrescriptionList.deleteFirstPrescription();
        medicPrescriptionList = new MedicPrescriptionList(driver);

        int finalSize = medicPrescriptionList.getRowNumber();
        assertEquals(finalSize,initSize-1);
    }
}
