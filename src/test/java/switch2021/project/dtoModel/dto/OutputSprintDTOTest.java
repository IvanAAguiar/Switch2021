package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputSprintDTOTest {

    @Test
    @DisplayName("Test to Get Output Sprint DTO")
    public void getOutputSprint() {
        //Arrange
        String projectID = "Project_2022_1";
        String sprintName = "Sprint Name";
        String sprintID = "Project_2022_1_Sprint Name";
        //Act
        OutputSprintDTO outputSprintDTO = new OutputSprintDTO(projectID, sprintID, sprintName);
        //Assert
        assertEquals(projectID, outputSprintDTO.getProjectID());
        assertEquals(sprintName, outputSprintDTO.getName());
        assertEquals(sprintID, outputSprintDTO.getSprintID());
    }
}
