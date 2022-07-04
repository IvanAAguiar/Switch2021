package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputProjectRoleDTOTest {

    @Test
    void getRole() {
        //Arrange
        String role = "role";
        //Act
        OutputProjectRoleDTO result = new OutputProjectRoleDTO(role);
        //Assert
        assertEquals(role, result.getRole());
    }
}