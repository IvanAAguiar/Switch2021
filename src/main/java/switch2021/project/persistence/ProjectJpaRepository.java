package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.JPA.ProjectJpa;
import switch2021.project.entities.valueObjects.vos.ProjectID;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpa, ProjectID> {

}
