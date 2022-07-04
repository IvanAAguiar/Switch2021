package switch2021.project.dataModel.jpa.assembler;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dataModel.JPA.UserJpa;
import switch2021.project.dataModel.JPA.assembler.UserJpaAssembler;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserJpaAssemblerTest {

    @InjectMocks
    private UserJpaAssembler assembler;

    @Test
    void testToData() {
        //Arrange
        UserID id = mock(UserID.class);
        User user = mock(User.class);
        when(user.getUserId()).thenReturn(id);
        Name name = mock(Name.class);
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("Test");
        Photo photo = mock(Photo.class);
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("test.png");
        Function function = mock(Function.class);
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("tester");
        Description password = mock(Description.class);
        when(user.getEncryptedPassword()).thenReturn(password);
        when(password.getText()).thenReturn("tesT-21");
        when(user.isActive()).thenReturn(true);
        when(user.getAssignedIdProfiles()).thenReturn(new ArrayList<>());
        List<Request> requests = new ArrayList<>();
        Request request = mock(Request.class);
        when(request.getRequestDate()).thenReturn(LocalDate.of(2022,12,12));
        UserProfileID userProfileID = mock(UserProfileID.class);
        when(request.getProfileIdRequested()).thenReturn(userProfileID);
        requests.add(request);
        when(user.getRequestedProfiles()).thenReturn(requests);
        List<User> users = new ArrayList<>();
        users.add(user);
        //Act
        List<UserJpa> result = assembler.toData(users);
        //Assert
        assertEquals(1, result.size());
    }

    @Test
    void testToDomain() {
        //Arrange
        UserID id = mock(UserID.class);
        UserJpa userJpa = mock(UserJpa.class);
        when(userJpa.getEmail()).thenReturn(id);
        when(userJpa.getUserName()).thenReturn("Test");
        when(userJpa.getPhoto()).thenReturn("test.png");
        when(userJpa.getFunction()).thenReturn("tester");
        when(userJpa.getPassword()).thenReturn("tesT-21");
        when(userJpa.getIsActive()).thenReturn("true");
        when(userJpa.getAssignedIDProfiles()).thenReturn(new ArrayList<>());
        List<Request> requests = new ArrayList<>();
        Request request = mock(Request.class);
        when(request.getRequestDate()).thenReturn(LocalDate.of(2022,12,12));
        UserProfileID userProfileID = mock(UserProfileID.class);
        when(request.getProfileIdRequested()).thenReturn(userProfileID);
        requests.add(request);
        when(userJpa.getRequests()).thenReturn(requests);
        List<UserJpa> userJpas = new ArrayList<>();
        userJpas.add(userJpa);
        //Act
        List<User> result = assembler.toDomain(userJpas);
        //Assert
        assertEquals(1, result.size());
    }
}