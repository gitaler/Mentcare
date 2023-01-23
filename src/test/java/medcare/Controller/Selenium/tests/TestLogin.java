package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.LoginPage;
import medcare.Controller.Selenium.pageobjects.MedicHomePage;
import medcare.Controller.Selenium.pageobjects.PatientHomePage;

import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;


public class TestLogin extends BaseTest {

    @Test
    public void testPatientLoginValid() throws InterruptedException {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Mario.Rossi";
        String password = "";


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPatient(username, password);
        assertFalse(loginPage.isLoginPage());

        PatientHomePage profilePage = new PatientHomePage(driver);
        assertTrue(profilePage.isPatientHomePage());
    }

    @Test
    public void testMedicLoginValid()  {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Dottor.Verdi";
        String password = "";


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMedic(username, password);

        MedicHomePage medicHomePage = new MedicHomePage(driver);
        assertTrue(medicHomePage.isMedicHomePage());
    }

    @Test
    public void testLoginInvalid() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "";
        String password = "";


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPatient(username, password);

        assertTrue(loginPage.isLoginPage());

        assertEquals("Invalid Username or Password", loginPage.getError());

    }


}
