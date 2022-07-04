package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.LoginDto;
import switch2021.project.dtoModel.dto.OutputLoginDTO;
import switch2021.project.dtoModel.mapper.LoginMapper;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.voFactories.voFactories.PasswordFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.Email;
import switch2021.project.entities.valueObjects.vos.Password;
import switch2021.project.entities.valueObjects.vos.UserID;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthServiceTest {

    @MockBean
    private IUserRepo repo;
    @MockBean
    private IUserIDFactory idFactory;
    @MockBean
    private LoginMapper mapper;
    @MockBean
    PasswordFactory passwordFactory;
    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authentication() {
       //Assert
       assertThrows(Exception.class, () -> {
           //Arrange
           LoginDto loginDto = mock(LoginDto.class);
           when(loginDto.getEmail()).thenReturn("test@test.test");
           when(loginDto.getPassword()).thenReturn("Test-12");
           UserID id = mock(UserID.class);
           when(idFactory.createUserID(loginDto.email)).thenReturn(id);
           when(repo.findByUserId(id)).thenReturn(Optional.empty());
           //Act
           authService.authentication(loginDto);
       });
    }

    @Test
    void authenticationFail() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            LoginDto login = mock(LoginDto.class);
            when(login.getEmail()).thenReturn("test@test.test");
            UserID id = mock(UserID.class);
            when(idFactory.createUserID(login.email)).thenReturn(id);
            User user = mock(User.class);
            Optional<User> logged = Optional.of(user);
            when(repo.findByUserId(id)).thenReturn(logged);
            User userLogged = mock(User.class);
            when(logged.get()).thenReturn(userLogged);

            when(login.getPassword()).thenReturn("Test-12");
            Password userPassword = mock(Password.class);
            when(passwordFactory.createPassword("Test-10")).thenReturn(userPassword);
            //Act
            authService.authentication(login);
        });
    }
}