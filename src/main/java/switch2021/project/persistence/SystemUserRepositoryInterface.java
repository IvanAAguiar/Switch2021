package switch2021.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2021.project.dataModel.JPA.UserJpa;


public interface SystemUserRepositoryInterface extends JpaRepository<UserJpa, String> {

}
