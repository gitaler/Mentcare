package medcare.Controller.JUnit;

import medcare.Model.Medic;
import medcare.Model.Patient;
import medcare.Repository.PatientRepository;
import medcare.Services.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PatientTest extends UtilsTest {

    @Autowired
    protected PatientRepository patientRepository;
    @Autowired
    protected PatientService service;

    @Test
    public void isPresent(){
        //Initialize DB
        Patient patient = patientRepository.save(new Patient("Username","Password"));

        assertTrue(patientService.isPresent(patient.getId()));
        assertFalse(patientService.isPresent((long)100));
    }

    @Test
    public void getPatient(){
        Patient patient = patientRepository.save(new Patient("Username","Password"));

        assertTrue(patientService.getPatient(patient.getId()).equals(patient));
    }

}
