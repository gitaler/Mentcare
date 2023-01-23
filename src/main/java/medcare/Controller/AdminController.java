package medcare.Controller;

import medcare.Model.*;
import medcare.Repository.AdminRepository;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import medcare.Services.AdminService;
import medcare.Services.AuthenticationService;
import medcare.Services.InitializeDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private InitializeDBService initialize;
    @Autowired
    private AuthenticationService authentication;
    @Autowired
    private AdminService service;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private AdminRepository adminRepository;
    private Timestamp timestamp;

    @RequestMapping("/admin_login")
    public String login(
            HttpServletResponse response
    ){
        //Reset Cookie
        authentication.ResetCookie(response);
        //Initialize data on DB
        initialize.InitializeDB();
        return "admin_login";
    }

    @RequestMapping("/create_admin")
    public String createAdmin(
            @RequestParam(name="username") String username,
            @RequestParam(name="password") String password,
            Model model,
            HttpServletResponse response) {
        Admin admin = authentication.AdminLogin(new Admin(username,password));
        if(admin == null) {
            model.addAttribute("error", "Invalid Username or Password");
            return "admin_login";
        }
        //Set Cookie
        authentication.setCookie("admin", admin.getId().toString(), response);
        return "redirect:/admin_home";
    }

    //Admin Home
    @RequestMapping("/admin_home")
    public String AdminHome(
            @CookieValue(value="admin_id") Long id){
        return "admin_home";
    }

    //Get all patients's page
    @RequestMapping("/all_patients_list")
    public String AllPatientList(
            @CookieValue(value="admin_id") Long id,
            Model model){

        Iterable<Patient> patients = patientRepository.findAll();

        //Get error message if no patient is found
        if(patients.equals(null))
            model.addAttribute("error", "No patients found");

        model.addAttribute("patients", patients);
        return "all_patients_list";
    }

    //Get all medics's page
    @RequestMapping("/all_medics_list")
    public String AllMedicList(
            @CookieValue(value="admin_id") Long id,
            Model model){
        Iterable<Medic> medics = medicRepository.findAll();

        //Get error message if no medic is found
        if(medics.equals(null))
            model.addAttribute("error", "No medics found");

        model.addAttribute("medics", medics);
        return "all_medics_list";
    }

    //Get page for adding new patients
    @RequestMapping("/add_new_patient")
    public String AddNewPatient(
            @CookieValue(value="admin_id") Long id
    ){
        return "add_new_patient";
    }

    //Create new patient
    @RequestMapping("/create_patient")
    public String createPatient(
            @CookieValue(value="admin_id") Long id,
            @RequestParam(name="name") String name,
            @RequestParam(name="surname") String surname,
            @RequestParam(name="password") String password,
            @RequestParam(name="telephone") String telephone,
            @RequestParam(name="age") Integer age,
            @RequestParam(name="diagnosis") String disgnosis,
            @RequestParam(name="facility") String facility,
            @RequestParam(name="ward") String ward,
            Model model
    ) {
        Patient insertPatient = new Patient (name, surname, password, telephone, age, disgnosis, facility, ward);
        String errors = service.addPatient(insertPatient, id);
        if (errors == "")
            return "redirect:/all_patients_list";
        model.addAttribute("error", errors);
        return "add_new_patient";
    }

    //Get page for adding new medic
    @RequestMapping("/add_new_medic")
    public String AddNewMedic(
            @CookieValue(value="admin_id") Long id
    ){
        return "add_new_medic";
    }

    //Create new medic
    @RequestMapping("/create_medic")
    public String createMedic(
            @CookieValue(value="admin_id") Long id,
            @RequestParam(name="name") String name,
            @RequestParam(name="surname") String surname,
            @RequestParam(name="password") String password,
            @RequestParam(name="telephone") String telephone,
            @RequestParam(name="specialization") String specialization,
            Model model
    ) {

        Medic insertMedic = new Medic (name, surname, password, telephone, null, specialization);
        String errors = service.addMedic(insertMedic, id);
        if (errors == "")
            return "redirect:/all_medics_list";
        model.addAttribute("error", errors);
        return "add_new_medic";
    }

    @RequestMapping("/edit_patient")
    public String editPatient(
            @CookieValue(value="admin_id") Long id,
            @RequestParam(value="id") Long patientId,
            @RequestParam(value="error", required = false) String error,
            Model model){
        Patient patient = service.getPatient(patientId);
        //Get error message if no medic is found
        if(patient == null)
            return "notfound";
        if(error != "")
            model.addAttribute("error", error);
        model.addAttribute("patient", patient);
        return "edit_patient";
    }

    @RequestMapping("/editPatient")
    public String editPatient(
            @CookieValue(value="admin_id") Long id,
            @RequestParam(name="id") Long patientId,
            @RequestParam(name="name") String name,
            @RequestParam(name="surname") String surname,
            @RequestParam(name="password") String password,
            @RequestParam(name="telephone") String telephone,
            @RequestParam(name="age") Integer age,
            @RequestParam(name="diagnosis") String diagnosis,
            @RequestParam(name="facility") String facility,
            @RequestParam(name="ward") String ward,
            Model model
    ) {
        Set<Prescription> patientPrescriptions = service.getPatient(patientId).getPrescriptions();
        Patient insertPatient = new Patient (name, surname, password, telephone, age, diagnosis, facility, ward);
        insertPatient.setPrescriptions(patientPrescriptions);
        insertPatient.setId(patientId);
        String errors = service.addPatient(insertPatient, id);
        if (errors == "")
            return "redirect:/all_patients_list";
        return editPatient(id, patientId, errors, model);
    }

    //Get page for editing new medic
    @RequestMapping("/edit_medic")
    public String showMedic(
            @CookieValue(value="admin_id") Long id,
            @RequestParam(name="id") Long medicId,
            @RequestParam(value="error", required = false) String error,
            Model model
    ){
        Medic medic = service.getMedic(medicId);
        //Get error message if no medic is found
        if(medic == null)
            return "notfound";
        if(error != "")
            model.addAttribute("error", error);
        model.addAttribute("medic", medic);
        return "edit_medic";
    }

    //Create new medic
    @RequestMapping("/editMedic")
    public String editMedic(
            @CookieValue(value="admin_id") Long id,
            @RequestParam(name="id") Long medicId,
            @RequestParam(name="name") String name,
            @RequestParam(name="surname") String surname,
            @RequestParam(name="password") String password,
            @RequestParam(name="telephone") String telephone,
            @RequestParam(name="specialization") String specialization,
            Model model
    ) {

        Medic insertMedic = new Medic (name, surname, password, telephone, null, specialization);
        insertMedic.setId(medicId);
        String errors = service.addMedic(insertMedic, id);
        if (errors == "")
            return "redirect:/all_medics_list";
        return showMedic(id, medicId, errors, model);
    }
}
