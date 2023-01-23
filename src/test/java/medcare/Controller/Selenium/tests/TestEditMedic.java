package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import medcare.Model.Medic;
import medcare.Model.Patient;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEditMedic extends BaseTest {
    @Test
    public void testEditMedic() throws InterruptedException {
        driver = getDriver();
        driver.get("http://localhost:8080/admin_login");

        String username = "super.user";
        String password = "";


        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.login(username, password);


        AdminHomePage profilePage = new AdminHomePage(driver);
        assertTrue(profilePage.isAdminHomePage());

        profilePage.goToAllMedics();
        AllMedicsList allMedicsList = new AllMedicsList(driver);
        assertTrue(allMedicsList.isAllMedicsListPage());

        allMedicsList.editMedic();
        EditMedic editMedic = new EditMedic(driver);
        assertTrue(editMedic.isEditMedicPage());

        editMedic.editMedic("NAME", "PASSWORD");

        allMedicsList = new AllMedicsList(driver);
        assertTrue(allMedicsList.isAllMedicsListPage());

        assertEquals("NAME", allMedicsList.getFirstMedicNameText());

    }
}
