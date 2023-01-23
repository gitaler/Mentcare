package medcare.Repository;

import medcare.Model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByUsernameAndPassword(String username, String password);
}
