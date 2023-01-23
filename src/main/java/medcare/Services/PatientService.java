package medcare.Services;

import medcare.Model.*;
import medcare.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService extends UtilsService{
    @Autowired
    private PatientRepository patientRepository;

    public boolean isPresent (Long id){
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent())
            return true;
        return false;
    }

    public Patient getPatient(Long id){
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.get();
    }


}

