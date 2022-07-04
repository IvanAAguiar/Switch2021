package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateUserStoryDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        int priority = 2;
        double timeEstimate = 20.0;

        UpdateUserStoryDTO dtoAll = new UpdateUserStoryDTO(priority, timeEstimate);
        //Act + Assert
        assertEquals(priority, dtoAll.getPriority());
        assertEquals(timeEstimate, dtoAll.getTimeEstimate());
    }
}
