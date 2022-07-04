package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.JPA.UserProfileJpa;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

public interface UserProfileJpaRepository extends JpaRepository<UserProfileJpa, UserProfileID> {
}