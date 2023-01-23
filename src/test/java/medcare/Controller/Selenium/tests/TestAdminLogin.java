package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.AdminHomePage;
import medcare.Controller.Selenium.pageobjects.AdminLoginPage;
import medcare.Controller.Selenium.pageobjects.LoginPage;
import medcare.Controller.Selenium.pageobjects.PatientHomePage;
import medcare.Controller.Selenium.utils.BaseTest;
import medcare.Model.Admin;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAdminLogin extends BaseTest {
    @Test
    public void testLoginValid() {
        driver = getDriver();
        driver.get("http://localhost:8080/admin_login");

        String username = "super.user";
        String password = "";

        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.login(username, password);

        assertFalse(adminLoginPage.isLoginPage());


        AdminHomePage profilePage = new AdminHomePage(driver);
        assertTrue(profilePage.isAdminHomePage());

    }

    @Test
    public void testLoginInvalid() {
        driver = getDriver();
        driver.get("http://localhost:8080/admin_login");

        String username = "";
        String password = "";

        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.login(username, password);

        assertTrue(adminLoginPage.isLoginPage());

        assertEquals("Invalid Username or Password", adminLoginPage.getError());

    }
}
