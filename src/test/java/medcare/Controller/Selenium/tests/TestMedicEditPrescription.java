package medcare.Controller.Selenium.tests;

import medcare.Controller.Selenium.pageobjects.*;
import medcare.Controller.Selenium.utils.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMedicEditPrescription extends BaseTest {
    @Test
    public void testEditPrescriptions() {
        driver = getDriver();
        driver.get("http://localhost:8080/login");

        String username = "Dottor.Viola";
        String password = "";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMedic(username, password);

        MedicHomePage profilePage = new MedicHomePage(driver);
        profilePage.goToPrescriptionList();

        MedicPrescriptionList medicPrescriptionList = new MedicPrescriptionList(driver);
        assertTrue(medicPrescriptionList.isPrescriptionListPage());

        medicPrescriptionList.editFirstPrescription();
        MedicEditPrescription medicEditPrescription = new MedicEditPrescription(driver);
        assertTrue(medicEditPrescription.isMedicEditPrescription());

        medicEditPrescription.editPrescription("DRUG EDIT", "QUANTITY EDIT", "DESCRIPTION EDIT");
        medicPrescriptionList = new MedicPrescriptionList(driver);
        assertTrue(medicPrescriptionList.isPrescriptionListPage());

        assertEquals("Mario Rossi", medicPrescriptionList.getCompleteName().getText());
        assertEquals("DRUG EDIT", medicPrescriptionList.getDrug().getText());
        assertEquals("QUANTITY EDIT", medicPrescriptionList.getQuantity().getText());
        assertEquals("DESCRIPTION EDIT", medicPrescriptionList.getDescription().getText());
        assertEquals("2023-01-21", medicPrescriptionList.getDate().getText());
    }
}
