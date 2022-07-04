package switch2021.project.dataModel.jpa;

import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.JPA.UserJpa;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserJpaTest {

    @Test
    void setEmail() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        when(name.getText()).thenReturn("Test");
        Photo photo = mock(Photo.class);
        when(photo.getExtension()).thenReturn("test.png");
        Function function = mock(Function.class);
        when(function.getText()).thenReturn("tester");
        Description password = mock(Description.class);
        when(password.getText()).thenReturn("tesT-21");
        List<Request> requests = new ArrayList<>();
        Request request = mock(Request.class);
        when(request.getRequestDate()).thenReturn(LocalDate.of(2022,12,12));
        UserProfileID userProfileID = mock(UserProfileID.class);
        when(request.getProfileIdRequested()).thenReturn(userProfileID);
        requests.add(request);
        UserJpa expected = new UserJpa();
        expected.setEmail(id);
        expected.setUserName("Test");
        expected.setFunction("Tester");
        expected.setPhoto("Test.png");
        expected.setPassword("test-21");
        expected.setIsActive("true");
        expected.setAssignedIDProfiles(new ArrayList<>());
        expected.setRequests(requests);
        //Act
        UserJpa result = new UserJpa(id,"Test","Tester","Test.png","test-21","true",new ArrayList<>());
        //Assert
        assertEquals(expected.getEmail(), result.getEmail());
    }
}