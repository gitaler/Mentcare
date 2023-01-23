package medcare.Services;

import medcare.Model.*;
import medcare.Repository.AdminRepository;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AuthenticationService extends UtilsService{
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private AdminRepository adminRepository;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public void setCookie(String name, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(name + "_id", value);
        response.addCookie(cookie);
    }

    public void ResetCookie(HttpServletResponse response){
        Cookie patientCookie = new Cookie("patient_id", null);
        patientCookie.setMaxAge(0);
        response.addCookie(patientCookie);
        Cookie medicCookie = new Cookie("medic_id", null);
        medicCookie.setMaxAge(0);
        response.addCookie(medicCookie);
        Cookie adminCookie = new Cookie("admin_id", null);
        adminCookie.setMaxAge(0);
        response.addCookie(adminCookie);
    }

    public Patient PatientLogin (Patient patient){
        Optional<Patient> patients = patientRepository.findByUsernameAndPassword(patient.getUsername(), patient.getPassword());
        if (!patients.isPresent()){
            return null;
        }
        return patients.get();
    }

    public Medic MedicLogin (Medic medic){

        Optional<Medic> medics = medicRepository.findByUsernameAndPassword(medic.getUsername(), medic.getPassword());
        if (!medics.isPresent()){
            return null;
        }
        return medics.get();
    }

    public Admin AdminLogin(Admin admin) {

        Optional<Admin> admins = adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (!admins.isPresent()){
            return null;
        }
        return admins.get();
    }

}
