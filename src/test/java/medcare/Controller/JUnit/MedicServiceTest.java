package medcare.Controller.JUnit;

import medcare.Model.Medic;
import medcare.Model.Patient;
import medcare.Model.Prescription;
import medcare.Services.MedicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class MedicServiceTest extends UtilsTest {
    @Autowired
    protected MedicService service;

    @Test
    public void isPresent(){
        //Initialize DB
        Medic medic = medicRepository.save(new Medic("Username","Password"));

        assertTrue(medicService.isPresent(medic.getId()));
        assertFalse(medicService.isPresent((long)100));
    }

    @Test
    public void getMedic(){
        //Initialize DB
        Medic medic = medicRepository.save(new Medic("Username","Password"));
        assertTrue(medic.equals(service.getMedic(medic.getId())));
    }

    @Test
    public void savePatient(){
        Patient patient = patientRepository.save(new Patient("Username","Password"));

        medicService.savePatient(patient);
        assertTrue(patientRepository.findById(patient.getId()).get().getId().equals(patient.getId()));
    }

    @Test
    public void findAllPatient(){
        Patient patient1 = patientRepository.save(new Patient("Username1","Password1"));
        Patient patient2 = patientRepository.save(new Patient("Username2","Password2"));
        Patient patient3 = patientRepository.save(new Patient("Username3","Password3"));
        Boolean contained1 = false;
        Boolean contained2 = false;
        Boolean contained3 = false;
        for (Patient p : patientRepository.findAll()){
            if (patient1.getId().equals(p.getId())){ contained1 = true;}
            if (patient2.getId().equals(p.getId())){ contained2 = true;}
            if (patient3.getId().equals(p.getId())){ contained3 = true;}
        }
        assertTrue(contained1 && contained2 && contained3);
    }

    @Test
    public void setDiagnosis(){
        Patient patient = patientRepository.save(new Patient("Username","Password"));
        patient.setDiagnosis("diagnosis");
        patientRepository.save(patient);
        assertEquals("diagnosis", patientRepository.findById(patient.getId()).get().getDiagnosis() );
    }

    @Test
    public void getPrescription(){
        Patient patient = new Patient("Username","Password");
        Medic medic = new Medic("Username","Password");

        Prescription prescription = new Prescription("date", "drug", "quantity", "descritpion", medic, patient);
        Set<Prescription> prescriptions = new HashSet<>();
        prescriptions.add(prescription);
        patient.setPrescriptions(prescriptions);
        medicService.savePatient(patient);
        assertEquals(medicService.getPrescription(patient.getId(),prescription.getId()).toString(), prescription.toString());
    }

    @Test
    public void getPrescriptions(){
        Patient patient = patientRepository.save(new Patient("Username","Password"));
        Medic medic = medicRepository.save(new Medic("Username","Password"));

        Prescription prescription1 = new Prescription("date1", "drug1", "quantity1", "descritpion1", medic, patient);
        Prescription prescription2 = new Prescription("date2", "drug2", "quantity2", "descritpion2", medic, patient);

        Set<Prescription> prescriptions = new HashSet<>();
        prescriptions.add(prescription1);
        prescriptions.add(prescription2);
        patient.setPrescriptions(prescriptions);
        patientRepository.save(patient);

        List<Prescription> prescriptionsGot = medicService.getPrescriptions(medic);
        Boolean contained1 = false;
        Boolean contained2 = false;
        for (Prescription p : prescriptionsGot){
            if (p.toString().equals(prescription1.toString())){contained1 = true;}
            if (p.toString().equals(prescription2.toString())){contained2 = true;}
        }
        assertTrue(contained1 && contained2);
    }

    @Test
    public void validPrescription(){
        Patient patient = patientRepository.save(new Patient("Username","Password"));
        Medic medic = medicRepository.save(new Medic("Username","Password"));
        Prescription prescription = new Prescription("date", "drug", "quantity", "descritpion", medic, patient);
        Set<Prescription> prescriptions = new HashSet<>();
        prescriptions.add(prescription);
        patient.setPrescriptions(prescriptions);
        medicService.savePatient(patient);

        prescription.setDrug(null);
        assertEquals("Invalid Drug entered", medicService.validPrescription(patient,prescription));

        prescription.setDrug("drug");
        prescription.setQuantity(null);
        assertEquals("Invalid Quantity entered", medicService.validPrescription(patient,prescription));

        prescription.setQuantity("50");
        prescription.setDescription(null);
        assertEquals("Invalid Description entered", medicService.validPrescription(patient,prescription));

        prescription.setDescription("description");
        assertEquals("", medicService.validPrescription(patient,prescription));
    }

    @Test
    public void addPrescription(){
        Patient patient = patientRepository.save(new Patient("Username","Password"));
        Medic medic = medicRepository.save(new Medic("Username","Password"));

        Prescription prescription = new Prescription("date1", "drug1", "quantity1", "description1", medic, patient);
        Set<Prescription> prescriptions = new HashSet<>();
        prescriptions.add(prescription);

        assertEquals("", medicService.addPrescription(prescription,medic.getId(),patient.getId()));
        assertEquals(patientRepository.findById(patient.getId()).get().getPrescriptions().toString(),prescriptions.toString());
    }

    @Test
    public void editPrescription(){
        Patient patient = patientRepository.save(new Patient("Username","Password"));
        Medic medic = medicRepository.save(new Medic("Username","Password"));

        Prescription prescription = new Prescription("date", "drug", "quantity", "descritpion", medic, patient);
        Set<Prescription> prescriptions = new HashSet<>();
        prescriptions.add(prescription);

        Prescription newPrescription = new Prescription("date1", "drug1", "quantity1", "descritpion1", medic, patient);

        assertEquals("", medicService.editPrescription(newPrescription,medic.getId(),prescription.getId(),patient.getId()));

        Set<Prescription> prescriptionsGot = patientRepository.findById(patient.getId()).get().getPrescriptions();
        Boolean contained = false;
        for (Prescription p : prescriptionsGot){
            if (p.toString().equals(newPrescription.toString())){contained = true;}
        }
        assertTrue(contained);
    }

    @Test
    public void deletePrescription(){
        Patient patient = patientRepository.save(new Patient("Username","Password"));
        Medic medic = medicRepository.save(new Medic("Username","Password"));

        Prescription prescription = new Prescription("date", "drug", "quantity", "descritpion", medic,patient);
        Set<Prescription> prescriptions = new HashSet<>();
        prescriptions.add(prescription);
        patient.setPrescriptions(prescriptions);

        medicService.deletePrescription(patient.getId(), prescription.getId());
        prescriptions.remove(prescription);
        assertEquals(prescriptions.toString(),patientRepository.findById(patient.getId()).get().getPrescriptions().toString());
    }

}
