package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewSprintDTOTest {

    @Test
    @DisplayName("Test to Get New Sprint DTO")
    public void getNewSprint() {
        //Arrange
        String projectID = "Project_2022_1";
        String sprintName = "Sprint Name";
        String date = "2022-03-13";
        //Act
        NewSprintDTO newSprintDTO = new NewSprintDTO(projectID, sprintName, date);
        //Assert
        assertEquals(projectID, newSprintDTO.getProjectID());
        assertEquals(sprintName, newSprintDTO.getName());
        assertEquals(date, newSprintDTO.getStartDate());
    }
}
