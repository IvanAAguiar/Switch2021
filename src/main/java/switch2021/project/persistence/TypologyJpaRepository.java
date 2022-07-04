package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.JPA.TypologyJpa;
import switch2021.project.entities.valueObjects.vos.TypologyID;

public interface TypologyJpaRepository extends JpaRepository<TypologyJpa, TypologyID> {
}
