package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.JPA.UserStoryOfSprintJpa;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

public interface UserStoryOfSprintJpaRepository extends JpaRepository<UserStoryOfSprintJpa, UserStoryID> {

}
