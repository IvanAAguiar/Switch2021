package switch2021.project.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.JPA.ResourceIDJpa;
import switch2021.project.dataModel.JPA.ResourceJpa;

public interface ResourceJpaRepository extends JpaRepository<ResourceJpa, ResourceIDJpa> {

}
