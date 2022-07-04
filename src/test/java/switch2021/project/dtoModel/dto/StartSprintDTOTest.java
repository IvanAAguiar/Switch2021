package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartSprintDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        String startDate = "zbz@mymail.com";

        StartSprintDTO dtoAll = new StartSprintDTO(startDate);
        //Act + Assert
        assertEquals(startDate, dtoAll.getStartDate());
    }
}
