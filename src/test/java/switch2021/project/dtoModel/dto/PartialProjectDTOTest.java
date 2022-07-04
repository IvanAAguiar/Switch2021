package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartialProjectDTOTest {

    @Test
    void getData() {
        //Arrange
        String code = "code";
        String projectName = "projectName";
        String description = "description";
        String startDate = "2022-12-12";
        String status = "CLOSED";
        //Act
        PartialProjectDTO dto = new PartialProjectDTO(code, projectName, description, startDate, status);
        //Assert
        assertEquals(code, dto.getCode());
        assertEquals(projectName, dto.getProjectName());
        assertEquals(description, dto.getDescription());
        assertEquals(startDate, dto.getStartDate());
        assertEquals(status, dto.getProjectStatus());
    }
}