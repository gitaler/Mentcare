package medcare.Controller;

import medcare.Model.Patient;
import medcare.Repository.PatientRepository;
import medcare.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientService service;
    @Autowired
    private PatientRepository patientRepository;
    private Optional<Patient> patient;
    private Timestamp timestamp;


    @RequestMapping("/patient_home")
    public String PatientHome(
            @CookieValue(value = "patient_id") Long id,
            Model model){
        //Controllo paziente esista ancora
        if(!service.isPresent(id))
            return "redirect:/login";
        //Implementare GUI
        model.addAttribute("patient", service.getPatient(id));
        return "patient_home";
    }

    @RequestMapping("/patient_info")
    public String PatiendInfo(
            @CookieValue(value = "patient_id") Long id,
            Model model) {

        if (!service.isPresent(id))
            return "notfound";
        model.addAttribute("patient",service.getPatient(id));
        return "patient_info";
    }

    //Prescriptions
    @RequestMapping("/prescriptions")
    public String prescriptions(
            @CookieValue(value="patient_id") Long id,
            Model model){

        if(!service.isPresent(id)){
            return "redirect/login";
        }
        model.addAttribute("prescriptions", service.getPatient(id).getPrescriptions());
        return "prescriptions";
    }
}
