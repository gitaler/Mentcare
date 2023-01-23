package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import medcare.Model.Medic;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TestAddNewMedic extends BaseTest {
    @Test
    public void testAddNewMedic() throws InterruptedException {
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

        int initSize = allMedics.getRowNumber();

        allMedics.goToAddNewMedicPage();
        AddNewMedicPage addNewMedicsPage = new AddNewMedicPage(driver);
        assertTrue(addNewMedicsPage.isAddMedicPage());

        addNewMedicsPage.insertMedic("NAME", "SURNAME","PASSWORD", "+39 123456789", "SPECIALIZATION");

        AllMedicsList allMedicsList = new AllMedicsList(driver);
        assertTrue(allMedicsList.isAllMedicsListPage());

        assertEquals(initSize, allMedicsList.getRowNumber()-1);
    }
}
