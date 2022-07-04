package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OutputProjectDTOTest {

    @Test
    @DisplayName("Test to Get Output Project DTO")
    public void getOutputProjectDTO() {
        //Arrange
        String code = "code";
        String projectName = "projectName";
        String description = "description";
        String businessSector = "businessSector";
        String numberOfSprints = "numberOfSprints";
        String budget = "budget";
        String status = "status";
        String startDate = "startDate";
        String sprintDuration = "sprintDuration";
        //Act
        OutputProjectDTO outputProjectDTO = new OutputProjectDTO(code, projectName, description, businessSector, startDate,
                numberOfSprints, budget, status, sprintDuration);
        //Assert
        assertEquals(code, outputProjectDTO.code);
        assertEquals(projectName, outputProjectDTO.projectName);
        assertEquals(description, outputProjectDTO.description);
        assertEquals(businessSector, outputProjectDTO.businessSector);
        assertEquals(numberOfSprints, outputProjectDTO.numberOfSprints);
        assertEquals(budget, outputProjectDTO.budget);
        assertEquals(status, outputProjectDTO.status);
        assertEquals(startDate, outputProjectDTO.startDate);
        assertEquals(sprintDuration, outputProjectDTO.sprintDuration);

    }
}
