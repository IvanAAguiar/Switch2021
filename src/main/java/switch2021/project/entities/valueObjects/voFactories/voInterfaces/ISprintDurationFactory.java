package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.entities.valueObjects.vos.SprintDuration;

public interface ISprintDurationFactory {
    SprintDuration create(int sprintDurationDays);
}
