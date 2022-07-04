package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.entities.valueObjects.vos.SprintID;

public interface ISprintIDFactory {
    SprintID create(String projectID, String sprintDescription);
}
