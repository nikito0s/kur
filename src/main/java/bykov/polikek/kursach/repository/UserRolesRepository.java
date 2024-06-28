package bykov.polikek.kursach.repository;

import bykov.polikek.kursach.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
}
