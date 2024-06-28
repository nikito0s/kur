package bykov.polikek.kursach.repository;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import bykov.polikek.kursach.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
