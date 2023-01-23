package medcare.Controller;

import medcare.Model.*;
import medcare.Services.AuthenticationService;
import medcare.Services.InitializeDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@Controller
public class AppController {
    @Autowired
    private InitializeDBService initialize;
    @Autowired
    private AuthenticationService authentication;

    @RequestMapping("/")
    public String index(){
        return "redirect:login";
    }
    private Timestamp timestamp;

    @RequestMapping("/login")
    public String login(
            HttpServletResponse response
    ){
        //Reset Cookie
        authentication.ResetCookie(response);
        //Initialize data on DB
        initialize.InitializeDB();

        return "login";
    }

    @RequestMapping("/create")
    public String create(
            @RequestParam(name="username") String username,
            @RequestParam(name="password") String password,
            @RequestParam(name="login") String user,
            Model model,
            HttpServletResponse response) {
        if(user.equals("patient")){
            Patient patient = authentication.PatientLogin(new Patient(username,password));
            if(patient == null){
                model.addAttribute("error", "Invalid Username or Password");
                return "login";
            }
            //Set Cookie
            authentication.setCookie("patient", patient.getId().toString(), response);

            return "redirect:/patient_home";
        }
        if (user.equals("medic")){
            Medic medic = authentication.MedicLogin(new Medic(username,password));
            if(medic == null) {
                model.addAttribute("error", "Invalid Username or Password");
                return "login";
            }
            //Set Cookie
            authentication.setCookie("medic", medic.getId().toString(), response);

            return "redirect:/medic_home";
        }
        return "login";
    }




}