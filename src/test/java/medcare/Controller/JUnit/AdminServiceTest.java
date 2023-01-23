package medcare.Controller.JUnit;


import medcare.Model.Medic;
import medcare.Model.Patient;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import medcare.Services.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@RunWith( SpringRunner.class )
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdminServiceTest {
    @Autowired
    protected PatientRepository patientRepository;
    @Autowired
    protected MedicRepository medicRepository;
    @Autowired
    protected AdminService service;
    @Test
    public void addPatientTest(){
        Patient patient = new Patient();
        //Check input errors
        assertEquals("Invalid Name", service.addPatient(patient, (long)1));
        patient.setName("Name");
        assertEquals("Invalid Surname", service.addPatient(patient, (long)1));
        patient.setSurname("Surname");
        assertEquals("Invalid Telephone Number (min 9 numbers)", service.addPatient(patient, (long)1));
        patient.setTelephone("+3900000000");
        assertEquals("Invalid Age", service.addPatient(patient, (long)1));
        patient.setAge(20);
        assertEquals("Invalid Password (min 4 chars)", service.addPatient(patient, (long)1));
        patient.setPassword("Password");
        assertEquals("Invalid Diagnosis", service.addPatient(patient, (long)1));
        patient.setDiagnosis("Diagnosis");
        assertEquals("Invalid Facility", service.addPatient(patient, (long)1));
        patient.setFacility("Facility");
        assertEquals("Invalid Ward", service.addPatient(patient, (long)1));
        patient.setWard("Ward");
        //Check patient saved on DB
        Long id = (long) 1;
        assertEquals("", service.addPatient(patient, (long)1));
        assertTrue(patientRepository.findById(id).get().equals(patient));
    }

    @Test
    public void addMedicTest(){
        Medic medic = new Medic();
        Long adminId = (long) 1;
        //Check input errors
        assertEquals("Invalid Name", service.addMedic(medic, adminId));
        medic.setName("Name");
        assertEquals("Invalid Surname", service.addMedic(medic, adminId));
        medic.setSurname("Surname");
        assertEquals("Invalid Telephone Number (min 9 numbers)", service.addMedic(medic, adminId));
        medic.setTelephone("+3900000000");
        assertEquals("Invalid Password (min 4 chars)", service.addMedic(medic, adminId));
        medic.setPassword("Password");
        assertEquals("Invalid Specialization", service.addMedic(medic, adminId));
        medic.setSpecialization("Specialization");

        //Check medic saved on DB
        Long id = (long) 1;
        assertEquals("", service.addMedic(medic, adminId));
        assertTrue(medicRepository.findById(id).get().equals(medic));
    }

}
