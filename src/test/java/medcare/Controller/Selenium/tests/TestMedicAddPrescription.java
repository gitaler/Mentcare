package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMedicAddPrescription extends BaseTest {
    @Test
    public void testAddPrescriptions() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Dottor.Vuoto";
        String password = "";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMedic(username, password);

        MedicHomePage profilePage = new MedicHomePage(driver);
        profilePage.goToAddPrescription();
        assertFalse(profilePage.isMedicHomePage());

        AllPatientsMedicList patientList = new AllPatientsMedicList(driver);
        patientList.addPrescriptionFirstPatient();

        MedicAddPrescription medicAddPrescription = new MedicAddPrescription(driver);
        assertTrue(medicAddPrescription.isMedicAddPrescription());

        medicAddPrescription.insertPrescription("DRUG TEST", "QUANTITY TEST", "DESCRIPTION TEST");
        MedicPrescriptionList medicPrescriptionList = new MedicPrescriptionList(driver);
        assertTrue(medicPrescriptionList.isPrescriptionListPage());

        assertEquals("Mario Rossi", medicPrescriptionList.getCompleteName().getText());
        assertEquals("DRUG TEST", medicPrescriptionList.getDrug().getText());
        assertEquals("QUANTITY TEST", medicPrescriptionList.getQuantity().getText());
        assertEquals("DESCRIPTION TEST", medicPrescriptionList.getDescription().getText());
        assertEquals("2023-01-21", medicPrescriptionList.getDate().getText());
    }
}
