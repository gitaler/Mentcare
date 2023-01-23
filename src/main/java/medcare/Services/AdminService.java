package medcare.Services;

import medcare.Model.*;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService extends UtilsService{
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicRepository medicRepository;

    public String addPatient(Patient patient, Long id){

        String errors = patientHasErrors(patient);
        if (errors == "") {

            Patient returnedPatient = patientRepository.save(patient);

            return errors;
        }
        return errors;
    }

    private String patientHasErrors(Patient patient){
        if(patient.getName() == null || patient.getName() .trim().isEmpty())
            return "Invalid Name";
        else if(patient.getSurname() == null || patient.getSurname() .trim().isEmpty())
            return "Invalid Surname";
        else if(patient.getTelephone() == null ||patient.getTelephone().length() < 9)
            return "Invalid Telephone Number (min 9 numbers)";
        else if(patient.getAge() == null || patient.getAge() < 0 || patient.getAge() > 130)
            return "Invalid Age";
        else if(patient.getPassword() == null ||patient.getPassword().length() < 4)
            return "Invalid Password (min 4 chars)";
        else if(patient.getDiagnosis() == null || patient.getDiagnosis() .trim().isEmpty())
            return "Invalid Diagnosis";
        else if(patient.getFacility() == null || patient.getFacility() .trim().isEmpty())
            return "Invalid Facility";
        else if(patient.getWard() == null || patient.getWard() .trim().isEmpty())
            return "Invalid Ward";
        else
            return "";
    }

    public String addMedic(Medic medic, Long id){

        String errors = medicHasErrors(medic);
        if (errors == "") {
            Medic returnedMedic = medicRepository.save(medic);
            return errors;
        }
        return errors;
    }

    private String medicHasErrors(Medic medic){
        if(medic.getName() == null || medic.getName() .trim().isEmpty())
            return "Invalid Name";
        else if(medic.getSurname() == null || medic.getSurname() .trim().isEmpty())
            return "Invalid Surname";
        else if(medic.getTelephone() == null || medic.getTelephone().length() < 9)
            return "Invalid Telephone Number (min 9 numbers)";
        else if(medic.getPassword() == null || medic.getPassword().length() < 4)
            return "Invalid Password (min 4 chars)";
        else if(medic.getSpecialization() == null || medic.getSpecialization() .trim().isEmpty())
            return "Invalid Specialization";
        else
            return "";
    }

}
