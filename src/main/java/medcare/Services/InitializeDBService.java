package medcare.Services;

import medcare.Model.*;
import medcare.Repository.AdminRepository;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Service
public class InitializeDBService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private AdminRepository adminRepository;

    //Initialize DataBase with 1 medic and 2 patients
    public void InitializeDB() {
        //Create Patients
//        Patient patient3 = new Patient("a", "a", "", "+39 12341234",24,  "Anxiety", null, "Peschiera", "Psichiatria");
        Patient patient3 = new Patient("Mario", "Rossi", "", "+39 12341234",24,  "Anxiety", null, "Villafranca", "Psichiatria");
        Patient patient2 = new Patient("Gino", "Gialli", "", "+39 12341234",22, "Depression", null, "Borgo Trento", "Pscichiatria");

        //Create Medic
        Medic medic0 = new Medic("Dottor", "Nullo", "+39 123456789","+39 123456789", null, "nothing");
        medicRepository.save(medic0);
        Medic medic1 = new Medic("Dottor", "Verdi", "","+39 12341234", null, "psychiatrist");
        Medic medic2 = new Medic("Dottor", "Neri", "","+39 12341234", null, "psychologist ");
        Medic medic3 = new Medic("Dottor", "Viola", "","+39 12341234", null, "psychotherapist ");
        Medic medic4 = new Medic("Dottor", "Vuoto", "","+39 12341234", null, "nothing ");

        //create Admin
        Admin admin = new Admin("super", "user", "");
        //Create Admin
        // Check if we already add data tu DB
        if (((Collection<Patient>) patientRepository.findAll()).size() == 0) {
            //PATIENT;
            //Set Prescriptions
            Set<Prescription> prescriptions = new HashSet<>();
            for(int i = 0; i < 3; i++){
                prescriptions.add(new Prescription("2023-01-21", "Farmaco "+i, (i*10)+"mg", "Prescription 1"+i, medic1, patient3));
                patient3.setPrescriptions(prescriptions);
            }
            for(int i = 0; i < 3; i++){
                prescriptions.add(new Prescription("2023-01-21", "Farmaco "+(i+1)*10, (i*100)+"mg", "Prescription 2"+i, medic1, patient3));
                patient3.setPrescriptions(prescriptions);
            }
            for(int i = 0; i < 3; i++){
                prescriptions.add(new Prescription("2023-01-21", "Farmaco "+(i+1)*20, (i*50)+"mg", "Prescription 2"+i, medic2, patient3));
                patient3.setPrescriptions(prescriptions);
            }

            prescriptions.add(new Prescription("2023-01-21", "Farmaco 42", "42mg", "Prescription 42", medic3, patient3));
            patient3.setPrescriptions(prescriptions);

            prescriptions = new HashSet<>();
            prescriptions.add(new Prescription("2023-01-21", "Farmaco 15", "15mg", "Prescription 15", medic1, patient2));
            patient2.setPrescriptions(prescriptions);

//            patientRepository.save(patient);
            patientRepository.save(patient2);
            patientRepository.save(patient3);

            medicRepository.save(medic0);
            medicRepository.save(medic1);
            medicRepository.save(medic2);
            medicRepository.save(medic3);
            medicRepository.save(medic4);

            //ADMIN
            adminRepository.save(admin);
        }
    }
}
