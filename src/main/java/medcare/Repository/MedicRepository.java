package medcare.Repository;
import medcare.Model.Medic;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MedicRepository extends CrudRepository<Medic, Long> {
    Optional<Medic> findByUsernameAndPassword(String username, String password);
}
