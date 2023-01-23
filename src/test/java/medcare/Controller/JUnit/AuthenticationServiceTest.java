package medcare.Controller.JUnit;

import medcare.Model.Admin;
import medcare.Model.Medic;
import medcare.Model.Patient;
import medcare.Repository.AdminRepository;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import medcare.Services.AdminService;
import medcare.Services.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

@RunWith( SpringRunner.class )
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthenticationServiceTest {

    @Autowired
    protected AuthenticationService service;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void PatientLoginTest(){
        //Initialize DB
        Patient patient = patientRepository.save(new Patient("Username","Password"));
        //Check Login
        assertEquals(service.PatientLogin(new Patient("Wrong", "Patient")), null);
        assertTrue(service.PatientLogin(patient).equals(patient));
    }

    @Test
    public void MedicLoginTest(){
        //Initialize DB
        Medic medic = medicRepository.save(new Medic("Username","Password"));
        //Check Login
        assertEquals(service.MedicLogin(new Medic("Wrong", "Medic")), null);
        assertTrue(service.MedicLogin(medic).equals(medic));
    }

    @Test
    public void AdminLoginTest(){
        //Initialize DB
        Admin admin = adminRepository.save(new Admin("Username","Password"));
        //Check Login
        assertEquals(service.AdminLogin(new Admin("Wrong", "Admin")), null);
        assertTrue(service.AdminLogin(admin).equals(admin));
    }

}
