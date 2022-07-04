package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Task.Task;
import java.util.List;
import java.util.Optional;


public interface ITaskRepo {

    List<Task> findAll();

    Optional<Task> findById(String taskID);

    Task save (Task newTask);

    boolean existsById(String taskID);

    List<Task> findAllByTaskContainerID(String taskContainerID);
}
