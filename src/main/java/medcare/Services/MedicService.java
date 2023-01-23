package medcare.Services;

import medcare.Model.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MedicService extends UtilsService {


    public boolean isPresent (Long id){
        Optional<Medic> medic = medicRepository.findById(id);
        if(medic.isPresent())
            return true;
        return false;
    }

    public void savePatient (Patient patient){
        patientRepository.save(patient);
    }

    public List<Patient> findPatients(){
        return (List<Patient>) patientRepository.findAll();
    }

    //Get prescription by ID
    public Prescription getPrescription(Long patientId, Long prescriptionId){
        Optional<Patient> patients = patientRepository.findById(patientId);
        Set<Prescription> prescriptions = patients.get().getPrescriptions();
        Prescription prescription = new Prescription();
        for(Prescription p : prescriptions){
            if(p.getId() == prescriptionId)
                return p;
        }
        return prescription;
    }

    public List<Prescription> getPrescriptions(Medic medic){
        Iterable<Patient> patients = patientRepository.findAll();
        List<Prescription> prescriptions = new ArrayList<>();
        for(Patient pat : patients){
            for(Prescription p : pat.getPrescriptions()){
                if(p.getMedic().getId() == medic.getId())
                    prescriptions.add(p);
            }
        }
        return prescriptions;
    }

    public String validPrescription(Patient patient, Prescription prescription){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(prescription.getDrug() == null || prescription.getDrug().trim().isEmpty())
            return "Invalid Drug entered";
        if(prescription.getQuantity() == null || prescription.getQuantity().trim().isEmpty())
            return "Invalid Quantity entered";
        if(prescription.getDescription() == null || prescription.getDescription().trim().isEmpty())
            return "Invalid Description entered";
        return "";
    }

    //Add Prescription
    public String addPrescription(Prescription prescription, Long idMedic, Long idPatient){

        Patient patient = getPatient(idPatient);
        String errors = validPrescription(patient, prescription);
        if (errors == "") {
            Set<Prescription> prescriptions = patient.getPrescriptions();
            prescriptions.add(prescription);
            patient.setPrescriptions(prescriptions);
            savePatient(patient);

            return errors;
        }
        return errors;
    }
    //Edit Prescription
    public String editPrescription(Prescription prescription, Long idMedic, Long idPrescription, Long idPatient){

        Patient patient = getPatient(idPatient);
        String errors = validPrescription(patient, prescription);
        if (errors == "") {
            Set<Prescription> prescriptions = patient.getPrescriptions();
            for(Prescription p: prescriptions){
                if(p.getId() == prescription.getId()){
                    prescriptions.remove(p);
                    break;
                }
            }
            prescriptions.add(prescription);
            patient.setPrescriptions(prescriptions);
            patient.setId(idPatient);
            savePatient(patient);

            return errors;
        }
        return errors;
    }

    //Delete Prescription by ID
    public void deletePrescription(Long patientId, Long prescriptionId){
        Patient patient = getPatient(patientId);
        Set<Prescription> prescriptions = patient.getPrescriptions();
        for(Prescription p: prescriptions){
            if(p.getId() == prescriptionId){
                prescriptions.remove(p);
                break;
            }
        }
        patient.setPrescriptions(prescriptions);
        patient.setId(patientId);
        savePatient(patient);
    }

}
