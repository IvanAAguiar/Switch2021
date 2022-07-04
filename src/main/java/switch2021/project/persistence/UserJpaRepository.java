package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.JPA.UserJpa;
import switch2021.project.entities.valueObjects.vos.UserID;

public interface UserJpaRepository extends JpaRepository<UserJpa, UserID> {

}
