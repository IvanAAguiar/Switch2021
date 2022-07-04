package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.dtoModel.dto.TaskEffortDTO;
import switch2021.project.entities.valueObjects.vos.TaskEffort;

public interface IEffortFactory {
    TaskEffort createTaskEffort(TaskEffortDTO taskEffortDTO);
}
