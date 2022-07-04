package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputStatusDTOTest {

    @Test
    void getStatus() {
        //Arrange
        String status = "status";
        OutputStatusDTO expected = new OutputStatusDTO();
        //Act
        OutputStatusDTO result = new OutputStatusDTO(status);
        //Assert
        assertNotEquals(expected.getStatus(), result.getStatus());
    }
}