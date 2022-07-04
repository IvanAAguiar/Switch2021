package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputUserStoryDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        String id = "com";
        String projectID = "com";
        String usTitle = "com";
        int priority = 1;
        String description = "com";
        double timeEstimate = 20.0;
        String parentUserStory = "com";
        String usStartDate = "com";
        String usEndDate = "com";
        String usRefined = "com";
        String status = "com";

        OutputUserStoryDTO dtoAll = new OutputUserStoryDTO(id, projectID, usTitle, priority, description,
                timeEstimate, parentUserStory, usStartDate, usEndDate, usRefined, status);
        new OutputUserStoryDTO(id, projectID, usTitle, priority, description, timeEstimate);

        //Act + Assert
        assertEquals(id, dtoAll.getId());
        assertEquals(projectID, dtoAll.getProjectID());
        assertEquals(usTitle, dtoAll.getUsTitle());
        assertEquals(priority, dtoAll.getPriority());
        assertEquals(description, dtoAll.getDescription());
        assertEquals(timeEstimate, dtoAll.getTimeEstimate());
        assertEquals(parentUserStory, dtoAll.getParentUserStory());
        assertEquals(usStartDate, dtoAll.getUsStartDate());
        assertEquals(usEndDate, dtoAll.getUsEndDate());
        assertEquals(usRefined, dtoAll.getUsRefined());
        assertEquals(status, dtoAll.getStatus());

    }
}
