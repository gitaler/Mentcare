package medcare.Services;

import medcare.Model.Medic;
import medcare.Model.Patient;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilsService {

    @Autowired
    protected MedicRepository medicRepository;
    @Autowired
    protected PatientRepository patientRepository;


    public Medic getMedic(Long id){
        Optional<Medic> medics = medicRepository.findById(id);
        if(!medics.isPresent())
            return null;
        return medics.get();
    }

    public Patient getPatient(Long id){
        Optional<Patient> patients = patientRepository.findById(id);
        if (!patients.isPresent())
            return null;
        return patients.get();
    }
}
