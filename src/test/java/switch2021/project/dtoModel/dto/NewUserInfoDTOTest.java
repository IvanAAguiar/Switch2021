package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewUserInfoDTOTest {

    @Test
    @DisplayName("Test to Get New User Information DTO")
    public void getNewUserInfo() {
        //Arrange
        String username = "Maria";
        String email = "maria@email.com";
        String function = "PM";
        String password = "Querty1!";
        String passwordConfirmation = "Querty1!";
        String photo = "photo.png";
        //Act
        NewUserInfoDTO newUserInfoDTO = new NewUserInfoDTO(username, email, function, password, passwordConfirmation, photo);
        //Assert
        assertEquals(username, newUserInfoDTO.userName);
        assertEquals(email, newUserInfoDTO.email);
        assertEquals(function, newUserInfoDTO.function);
        assertEquals(password, newUserInfoDTO.password);
        assertEquals(passwordConfirmation, newUserInfoDTO.passwordConfirmation);
        assertEquals(photo, newUserInfoDTO.photo);
    }
}
