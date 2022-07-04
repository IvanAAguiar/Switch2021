package switch2021.project.dataModel.REST;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileRestDTOTest {

    @Test
    void userProfileRestDTO() {
        //Arrange
        String designation = "designation";
        UserProfileRestDTO expected = new UserProfileRestDTO();
        //Act
        UserProfileRestDTO result = new UserProfileRestDTO(designation);
        //Assert
        assertNotEquals(expected.getDesignation(), result.getDesignation());
    }
}