package medcare.Controller;

import medcare.Services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("medcare.Repository")
@EntityScan("medcare.Model")
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    @Bean
    @Scope("singleton")
    public InitializeDBService initializeDBService() {
        return new InitializeDBService();
    }
    @Bean
    @Scope("singleton")
    public AuthenticationService authenticationService() {
        return new AuthenticationService();
    }
    @Bean
    @Scope("singleton")
    public AdminService adminService() {
        return new AdminService();
    }
    @Bean
    @Scope("singleton")
    public MedicService medicService() {
        return new MedicService();
    }
    @Bean
    @Scope("singleton")
    public PatientService patientService() {
        return new PatientService();
    }
    @Bean
    @Scope("singleton")
    public UtilsService utilsService() {
        return new UtilsService();
    }

}



