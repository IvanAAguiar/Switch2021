package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStoryUpdateDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        int priority = 1;
        String description = "TeamMember";
        double timeEstimate = 20.0;
        String usStartDate = "TeamMember";
        String usEndDate = "TeamMember";

        UserStoryUpdateDTO dtoAll = new UserStoryUpdateDTO(priority, description, timeEstimate, usStartDate, usEndDate);
        //Act + Assert
        assertEquals(priority, dtoAll.getPriority());
        assertEquals(description, dtoAll.getDescription());
        assertEquals(timeEstimate, dtoAll.getTimeEstimate());
        assertEquals(usStartDate, dtoAll.getUsStartDate());
        assertEquals(usEndDate, dtoAll.getUsEndDate());
    }
}
