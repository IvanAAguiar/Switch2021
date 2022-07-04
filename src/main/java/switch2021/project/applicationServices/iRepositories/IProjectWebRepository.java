package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Project.Project;

import javax.net.ssl.SSLException;
import java.util.List;

public interface IProjectWebRepository {

    List<Project> findAll() throws SSLException;
}
