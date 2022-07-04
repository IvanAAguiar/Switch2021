package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.AuthService;
import switch2021.project.dtoModel.dto.LoginDto;
import switch2021.project.dtoModel.dto.OutputLoginDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RootControllerTest {

    @MockBean
    private AuthService service;
    @InjectMocks
    private RootController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticationSuccess() throws Exception {
        //Arrange
        LoginDto loginDto = mock(LoginDto.class);
        when(loginDto.getEmail()).thenReturn("email@email.test");
        when(loginDto.getPassword()).thenReturn("Qwert-1");
        OutputLoginDTO outputLoginDTO = mock(OutputLoginDTO.class);
        when(outputLoginDTO.getUsername()).thenReturn("username");
        when(outputLoginDTO.getToken()).thenReturn("token");
        when(service.authentication(loginDto)).thenReturn(outputLoginDTO);
        //Act
        ResponseEntity<Object> result = controller.authentication(loginDto);
        //Assert
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void authenticationFail() throws Exception {
        //Arrange
        LoginDto loginDto = mock(LoginDto.class);
        when(loginDto.getEmail()).thenReturn("email@email.test");
        when(loginDto.getPassword()).thenReturn("Qwert-1");
        when(service.authentication(loginDto)).thenThrow(new Exception());
        //Act
        ResponseEntity<Object> result = controller.authentication(loginDto);
        //Assert
        assertEquals(400, result.getStatusCodeValue());
    }
}