package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TestAdminAllPatientsPage extends BaseTest {

    @Test
    public void testAllPatients() {
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

    }
}
