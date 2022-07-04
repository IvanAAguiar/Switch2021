package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import java.util.List;
import java.util.Optional;

public interface ISprintRepo {


    /** Find a List Of Sprints. */

    List<Sprint> findAllSprints();

    /** Find a List Of Sprint by ID. */

    Optional<Sprint> findBySprintID(SprintID id);

    /** Find the Current Sprint. */

    Sprint findCurrentSprint();

    /** Save Sprint. */

    Sprint save(Sprint newSprint) throws Exception;

    /** Delete a Sprint. */

    boolean deleteSprint(SprintID sprintID);

    List<Sprint> findAllByProjectID(ProjectID projectID);

    boolean existsSprintByID(SprintID id);
}

