package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.entities.valueObjects.vos.TaskID;

public interface ITaskIDFactory {
    TaskID createTaskID(TaskContainerID taskContainerID, String description);
}
