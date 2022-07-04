package switch2021.project.dataModel.jpa;

import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.JPA.SprintJpa;
import switch2021.project.dataModel.JPA.UserStoryOfSprintJpa;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryOfSprintJpaTest {

    @Test
    void setUserStoryId() {
        //Arrange
        UserStoryID id = mock(UserStoryID.class);
        UserStoryOfSprintJpa userStoryOfSprintJpa = new UserStoryOfSprintJpa();
        //Act
        userStoryOfSprintJpa.setUserStoryId(id);
        userStoryOfSprintJpa.setSprintName("SprintName");
        userStoryOfSprintJpa.setStatus("Status");
        //Assert
        assertEquals(id, userStoryOfSprintJpa.getUserStoryId());
    }
}