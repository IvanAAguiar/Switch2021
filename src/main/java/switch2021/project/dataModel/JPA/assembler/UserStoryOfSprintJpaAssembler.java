package switch2021.project.dataModel.JPA.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.dataModel.JPA.UserStoryOfSprintJpa;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;

@Component
public class UserStoryOfSprintJpaAssembler {

    public UserStoryOfSprintJpa toData(UserStoryOfSprint userStoryOfSprint) {

        UserStoryID userStoryID = userStoryOfSprint.getUserStoryId();
        String sprintName = userStoryOfSprint.getSprintName();
        String status = userStoryOfSprint.getUserStoryOfSprintStatus().toString();

        return new UserStoryOfSprintJpa(userStoryID, status, sprintName);
    }

    public UserStoryOfSprint toDomain(UserStoryOfSprintJpa userStoryOfSprintJpa) {

        UserStoryID userStoryID = userStoryOfSprintJpa.getUserStoryId();
        UserStoryOfSprintStatus status = UserStoryOfSprintStatus.valueOf(userStoryOfSprintJpa.getStatus());
        String sprintName = userStoryOfSprintJpa.getSprintName();

        return new UserStoryOfSprint(userStoryID, status, sprintName);
    }
}
