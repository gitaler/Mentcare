package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.AdminHomePage;
import medcare.Controller.Selenium.pageobjects.AdminLoginPage;
import medcare.Controller.Selenium.pageobjects.AllMedicsList;
import medcare.Controller.Selenium.pageobjects.AllPatientsList;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAdminAllMedicsPage extends BaseTest {

    @Test
    public void testAllMedics() {
        driver = getDriver();
        driver.get("http://localhost:8080/admin_login");

        String username = "super.user";
        String password = "";

        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.login(username, password);

        assertFalse(adminLoginPage.isLoginPage());

        AdminHomePage profilePage = new AdminHomePage(driver);
        assertTrue(profilePage.isAdminHomePage());

        profilePage.goToAllMedics();
        AllMedicsList allMedics = new AllMedicsList(driver);
        assertTrue(allMedics.isAllMedicsListPage());
    }
}
