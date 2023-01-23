package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestMedicLogOut extends BaseTest {
    @Test
    public void testMedicLogOut() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Dottor.Verdi";
        String password = "";


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMedic(username, password);

        MedicHomePage profilePage = new MedicHomePage(driver);

        profilePage.logout();
        LoginPage loginpage = new LoginPage(driver);
        assertTrue(loginpage.isLoginPage());

    }
}
