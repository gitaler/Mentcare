package medcare.Controller.JUnit;

import medcare.Model.*;
import medcare.Repository.AdminRepository;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import medcare.Services.AdminService;
import medcare.Services.MedicService;
import medcare.Services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith( SpringRunner.class )
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UtilsTest {

    @Autowired
    public AdminService adminService;
    @Autowired
    public MedicService medicService;
    @Autowired
    public PatientService patientService;
    @Autowired
    public MedicRepository medicRepository;
    @Autowired
    public PatientRepository patientRepository;
    @Autowired
    public AdminRepository adminRepository;

    @BeforeEach
    public void Initialize(){
        //Create Patients
        Patient patient3 = new Patient("a", "a", "", "+39 12341234", 24, "Anxiety", null, "Peschiera", "Psichiatria");
        Patient patient = new Patient("Mario", "Rossi", "", "+39 12341234", 24, "Anxiety", null, "Peschiera", "Psichiatria");
        Patient patient2 = new Patient("Gino", "Gialli", "", "+39 12341234", 22, "Depression", null, "Borgo Roma", "Pscichiatria");
        //Create Medic
        Medic medic1 = new Medic("d", "a", "", "+39 12341234", null, "psychiatrist");
        Medic medic2 = new Medic("d", "b", "", "+39 12341234", null, "chiropractor ");

        //create Admin
        Admin admin = new Admin("s", "u", "");
        //Create Admin
        // Check if we already add data tu DB
        patientRepository.save(patient);
        patientRepository.save(patient2);
        patientRepository.save(patient3);

        medicRepository.save(medic1);
        medicRepository.save(medic2);

        //ADMIN
        adminRepository.save(admin);

        //PATIENT;
        //Set Prescriptions
        Set<Prescription> prescriptions = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            prescriptions.add(new Prescription("2023-01-01", "Debrum", (i * 10) + "mg", "Prescription 1" + i, medic1, patient3));
            patient3.setPrescriptions(prescriptions);
        }
        for (int i = 0; i < 3; i++) {
            prescriptions.add(new Prescription("2023-01-01", "Debrum", (i * 10) + "mg", "Prescription 1" + i, medic1, patient3));
            patient3.setPrescriptions(prescriptions);
        }
    }

}
