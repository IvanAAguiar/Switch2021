package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.TaskDTO;

import switch2021.project.entities.aggregates.Task.Task;

public interface ITaskFactory {
    Task createTask(TaskDTO taskDTO);
}
