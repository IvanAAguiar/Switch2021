package switch2021.project.dataModel.jpa;

import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.JPA.UserStoryJpa;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryJpaTest {

    @Test
    void setId() {
        //Arrange
        UserStoryID id = mock(UserStoryID.class);
        UserStoryJpa parentUserStory = new UserStoryJpa();
        UserStoryJpa result = new UserStoryJpa();
        //Act
        result.setId(id);
        result.setPriority(2);
        result.setDescription("description");
        result.setTimeEstimate(20.2);
        result.setUsStatus("status");
        result.setParentUserStory(parentUserStory);
        result.setStartDate("2022-12-12");
        result.setEndDate("2023-12-12");
        result.setRefined("refined");
        //Assert
        assertEquals(id, result.getId());
    }
}