package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.LoginPage;
import medcare.Controller.Selenium.pageobjects.PatientHomePage;
import medcare.Controller.Selenium.pageobjects.PatientInfo;
import medcare.Controller.Selenium.pageobjects.Prescriptions;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPatientPrescriptions extends BaseTest {

    @Test
    public void testPatientTerapy() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Gino.Gialli";
        String password = "";


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPatient(username, password);

        assertFalse(loginPage.isLoginPage());


        PatientHomePage profilePage = new PatientHomePage(driver);
        assertTrue(profilePage.isPatientHomePage());
        profilePage.goToPrescriptionsPage();

        Prescriptions prescriptions = new Prescriptions(driver);
        assertTrue(prescriptions.isPrescriptionsPage());
        assertEquals("Farmaco 15", prescriptions.getPrescriptionDrugText());
        assertEquals("15mg", prescriptions.getPrescriptionQuantityText());
        assertEquals("Prescription 15", prescriptions.getPrescriptionDescriptionText());
        assertEquals("doc.Verdi (psychiatrist)", prescriptions.getPrescriptionMedicText());
        assertEquals("2023-01-21", prescriptions.getPrescriptionDateText());

    }
}
