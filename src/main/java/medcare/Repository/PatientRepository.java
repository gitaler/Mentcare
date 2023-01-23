package medcare.Repository;

import medcare.Model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByUsernameAndPassword(String username, String password);
    List<Patient> findByPrescriptionsIsNull();

}
