package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import medcare.Model.Patient;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
public class TestAddNewPatient extends BaseTest {

    @Test
    public void testAddNewPatient() throws InterruptedException {
        driver = getDriver();
        driver.get("http://localhost:8080/admin_login");

        String username = "super.user";
        String password = "";


        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.login(username, password);

        assertFalse(adminLoginPage.isLoginPage());

        AdminHomePage profilePage = new AdminHomePage(driver);
        assertTrue(profilePage.isAdminHomePage());

        profilePage.goToAllPatients();
        AllPatientsList allPatients = new AllPatientsList(driver);
        assertTrue(allPatients.isAllPatientsListPage());

        int initSize = allPatients.getRowNumber();

        allPatients.goToAddNewPatientPage();
        AddNewPatientPage addNewPatientPage = new AddNewPatientPage(driver);
        assertTrue(addNewPatientPage.isAddPatientPage());

        addNewPatientPage.insertPatient("NAME", "SURNAME","PASSWORD", "+39 123456789", "22", "FACILITY", "WARD", "DIAGNOSIS");

        AllPatientsList allPatientsList = new AllPatientsList(driver);
        assertTrue(allPatientsList.isAllPatientsListPage());

        assertEquals(initSize, allPatientsList.getRowNumber()-1);
    }
}
