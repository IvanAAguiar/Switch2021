package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDtoTest {

    @Test
    void getEmail() {
        //Arrange
        String email = "email";
        String password = "password";
        //Act
        LoginDto loginDto = new LoginDto(email,password);
        //Assert
        assertEquals(email, loginDto.getEmail());
        assertEquals(password, loginDto.getPassword());
    }
}