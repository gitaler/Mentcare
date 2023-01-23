package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import medcare.Model.Patient;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TestEditPatient extends BaseTest {
    @Test
    public void testEditPatient() throws InterruptedException {
        driver = getDriver();
        driver.get("http://localhost:8080/admin_login");

        String username = "super.user";
        String password = "";

        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.login(username, password);

        AdminHomePage profilePage = new AdminHomePage(driver);
        assertTrue(profilePage.isAdminHomePage());

        profilePage.goToAllPatients();
        AllPatientsList allPatients = new AllPatientsList(driver);
        assertTrue(allPatients.isAllPatientsListPage());

        allPatients.editPatient();
        EditPatient editPatient = new EditPatient(driver);
        assertTrue(editPatient.isEditPatientPage());
        editPatient.editPatient("NAME","PASSWORD" );

        AllPatientsList allPatientsList = new AllPatientsList(driver);
        assertTrue(allPatientsList.isAllPatientsListPage());

        String editedName = allPatients.getFirstPatientNameText();

        assertEquals(editedName, "NAME");

    }

}

