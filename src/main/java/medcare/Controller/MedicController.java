package medcare.Controller;

import medcare.Model.*;
import medcare.Repository.MedicRepository;
import medcare.Repository.PatientRepository;
import medcare.Services.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class MedicController {
    @Autowired
    private MedicService service;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private PatientRepository patientRepository;
    private Timestamp timestamp;
    private Optional<Medic> medic;

    @RequestMapping("/medic_home")
    public String MedicHome(
            @CookieValue(value="medic_id") Long id,
            Model model){
        Medic medic = service.getMedic(id);
        if(medic == null)
            return "redirect:/login";
        model.addAttribute("medic", medic);
        return "medic_home";
    }

    @RequestMapping("/medic_info")
    public String MedicInfo(
            @CookieValue(value = "medic_id") Long id,
            Model model) {
        Medic medic = service.getMedic(id);
        if(medic == null)
            return "notfound";
        model.addAttribute("medic", medic);
        return "medic_info";
    }

    @RequestMapping("patients_info")
    public String infoPatients(
            @CookieValue(value = "medic_id") Long medicId,
            @RequestParam(name="id") Long patientId,
            @RequestParam(name="text", required = false) String text,
            Model model) {
        //Set Patient
        Patient patient = service.getPatient(patientId);
        if (patient == null)
            return "notfound";
        model.addAttribute("patient", patient);
        //Set success message
        if(text != null) {
            if (text.equals("Success")){
                model.addAttribute("error", "");
                model.addAttribute("success", text);
            }
            //Set error message
            model.addAttribute("success", "");
            model.addAttribute("error", text);
        }
        //Set Prescription
        model.addAttribute("prescriptions", patient.getPrescriptions());
        return "patients_info";
    }

    @RequestMapping("/medic_prescriptions")
    public String prescriptions(
            @CookieValue(value="medic_id") Long id,
            Model model){

        if(!service.isPresent(id)){
            return "redirect/login";
        }
        model.addAttribute("prescriptions", service.getPrescriptions(service.getMedic(id)));
        model.addAttribute("error", "");
        return "medic_prescriptions";
    }

    @RequestMapping("/edit_prescription")
    public String getPrescription(
            @CookieValue(value="medic_id") Long id,
            @RequestParam(value="idPrescription") Long prescriptionId,
            @RequestParam(value="idPatient") Long patientId,
            @RequestParam(value="error", required = false) String text,
            Model model){

        Prescription prescription = service.getPrescription(patientId, prescriptionId);

        //Get error message if no prescription is found
        if(prescription== null)
            return "notfound";
        if(text != null) {
            if (text.equals("Success")){
                model.addAttribute("error", "");
                model.addAttribute("success", text);
            }
            //Set error message
            model.addAttribute("success", "");
            model.addAttribute("error", text);
        }
        model.addAttribute("idPatient", patientId);
        model.addAttribute("prescription", prescription);
        return "edit_prescription";
    }

    @RequestMapping("/editPrescription")
    public String editPrescription(
            @CookieValue(value="medic_id") Long idMedic,
            @RequestParam(name="id") Long idPrescription,
            @RequestParam(name="idPatient") Long idPatient,
            @RequestParam(name="drug") String drug,
            @RequestParam(name="quantity") String quantity,
            @RequestParam(name="description") String description,
            Model model
    ) {
        //Set today Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateObj = LocalDate.now();
        //Prescription inserted
        Prescription insertedPrescription = new Prescription (dateObj.format(formatter), drug, quantity, description, service.getMedic(idMedic), service.getPatient(idPatient));
        //Set prescription if valid
        insertedPrescription.setId(idPrescription);
        String errors = service.editPrescription(insertedPrescription, idMedic, idPrescription, idPatient);
        if (errors == "")
            return prescriptions(idMedic, model);
        return getPrescription(idMedic, idPrescription,  idPatient, errors, model);
    }


    @RequestMapping("/delete_prescription")
    public String deletePrescription(
            @CookieValue(value="medic_id") Long idMedic,
            @RequestParam(name="idPrescription") Long idPrescription,
            @RequestParam(name="idPatient") Long idPatient,
            Model model
    ) {
        service.deletePrescription(idPatient, idPrescription);
        return prescriptions(idMedic, model);
    }

    @RequestMapping("/add_prescription")
    public String getAddPrescription(
            @CookieValue(value="medic_id") Long id,
            @RequestParam(value="idPatient") Long patientId,
            @RequestParam(value="error", required = false) String text,
            Model model
    ) {
        if(text != null) {
            if (text.equals("Success")){
                model.addAttribute("error", "");
                model.addAttribute("success", text);
            }
            //Set error message
            model.addAttribute("success", "");
            model.addAttribute("error", text);
        }
        model.addAttribute("patient", service.getPatient(patientId));
        model.addAttribute("medic", service.getMedic(id));
        return "add_prescription";
    }

    @RequestMapping("/addPrescription")
    public String addPrescription(
            @CookieValue(value="medic_id") Long idMedic,
            @RequestParam(name="idPatient") Long idPatient,
            @RequestParam(name="drug") String drug,
            @RequestParam(name="quantity") String quantity,
            @RequestParam(name="description") String description,
            Model model
    ) {
        //Set today Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateObj = LocalDate.now();
        //Prescription inserted
        Prescription insertedPrescription = new Prescription (dateObj.format(formatter), drug, quantity, description, service.getMedic(idMedic), service.getPatient(idPatient));
        //Set prescription if valid
        String errors = service.addPrescription(insertedPrescription, idMedic, idPatient);
        if (errors == "")
            return prescriptions(idMedic, model);
        return getAddPrescription(idMedic,  idPatient, errors, model);
    }

    @RequestMapping("/all_patients_medic")
    public String patientList(
            @CookieValue(value="medic_id") Long id,
            Model model){
        Medic medic = service.getMedic(id);
        if(medic == null)
            return "notfound";
        model.addAttribute("patients", service.findPatients());
        return "all_patients_medic";
    }

}
