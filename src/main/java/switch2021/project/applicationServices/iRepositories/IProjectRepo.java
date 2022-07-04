package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.ProjectID;

import java.util.List;
import java.util.Optional;


public interface IProjectRepo {

    /**
     * Finds all objects from this repository.
     */
    List<Project> findAll();

    /**
     * The repository should be able to find a object using given Identity.
     */
    Optional<Project> findById(ProjectID id);

    /**Check if project exists in repository**/
    boolean existsById(ProjectID id);

    /**Save Projects Method**/
   Project save(Project newProject);

    boolean delete(ProjectID id);

}
