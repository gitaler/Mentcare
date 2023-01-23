package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.LoginPage;
import medcare.Controller.Selenium.pageobjects.PatientHomePage;
import medcare.Controller.Selenium.pageobjects.PatientInfo;
import medcare.Controller.Selenium.pageobjects.Prescriptions;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestPatientInfo extends BaseTest {

    @Test
    public void testProfileInfo() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Mario.Rossi";
        String password = "";


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPatient(username, password);

        PatientHomePage profilePage = new PatientHomePage(driver);
        profilePage.goToPatinetInfo();

        PatientInfo patientInfo = new PatientInfo(driver);
        assertEquals("Name: Mario", patientInfo.getNameText());
        assertEquals("Surname: Rossi", patientInfo.getSurnameText());
        assertEquals("Telephone: +39 12341234", patientInfo.getTelephoneText());
        assertEquals("Age: 24", patientInfo.getAgeText());
        assertEquals("Diagnosis: Anxiety", patientInfo.getDiagnosisText());
        assertEquals("Facility: Villafranca", patientInfo.getFacilityText());
        assertEquals("Ward: Psichiatria", patientInfo.getWardText());
    }

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

        profilePage.goToPatinetInfo();
        PatientInfo patientInfo = new PatientInfo(driver);
        assertTrue(patientInfo.isPatientInfo());
    }
}
