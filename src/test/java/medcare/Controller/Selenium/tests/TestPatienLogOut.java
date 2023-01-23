package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.LoginPage;
import medcare.Controller.Selenium.pageobjects.PatientHomePage;
import medcare.Controller.Selenium.pageobjects.PatientInfo;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestPatienLogOut extends BaseTest {
    @Test
    public void testPatientLogOut() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Mario.Rossi";
        String password = "";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPatient(username, password);

        PatientHomePage profilePage = new PatientHomePage(driver);
        assertTrue(profilePage.isPatientHomePage());

        profilePage.logout();
        LoginPage loginpage = new LoginPage(driver);
        assertTrue(loginpage.isLoginPage());

    }
}
