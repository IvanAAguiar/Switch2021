package switch2021.project.entities.aggregates.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.entities.valueObjects.vos.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserTest {

    @Test
    void itShouldCreateASystemUser() {
        //S.U.T. {SystemUser}
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        //Act
        User underTest = new User(id, name, photo, password, password,
                function, profileID);
        //Assert
        assertEquals("Visitor", underTest.getAssignedProfiles().get(0).getUserProfileName().getText());
        assertFalse(underTest.isActive());
    }

    @Test
    public void assignInvalidPassword() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserID id = mock(UserID.class);
            Name name = mock(Name.class);
            Photo photo = mock(Photo.class);
            Password password = mock(Password.class);
            Password passwordConfirmation = mock(Password.class);
            Function function = mock(Function.class);
            Description description = mock(Description.class);
            UserProfileID profileID = mock(UserProfileID.class);
            when(password.getPwd()).thenReturn("xxxxx");
            when(passwordConfirmation.getPwd()).thenReturn("ddddd");
            when(profileID.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            //Act
            new User(id, name, photo, password, passwordConfirmation, function, profileID);
        });
    }

    @Test
    void itShouldNotCreateASystemUser() {
        assertThrows(IllegalArgumentException.class, () -> {
            //S.U.T. {SystemUser}
            //Arrange
            UserID id = mock(UserID.class);
            Name name = mock(Name.class);
            Photo photo = mock(Photo.class);
            Password password = mock(Password.class);
            Function function = mock(Function.class);
            Description description = mock(Description.class);
            UserProfileID profileID = mock(UserProfileID.class);
            when(password.getPwd()).thenReturn("Qwerty_1");
            when(profileID.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Regular");
            //Act
            new User(id, name, photo, password, password,
                    function, profileID);
        });
    }

    @Test
    public void getAssignedProfiles() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Description password = mock(Description.class);
        Function function = mock(Function.class);
        Description desVisitor = mock(Description.class);
        Description desDirector = mock(Description.class);
        Description desUser = mock(Description.class);
        UserProfileID visitor = mock(UserProfileID.class);
        UserProfileID user = mock(UserProfileID.class);
        UserProfileID director = mock(UserProfileID.class);
        List<UserProfileID> assignedProfiles = new ArrayList<>();
        assignedProfiles.add(visitor);
        assignedProfiles.add(director);
        assignedProfiles.add(user);
        when(visitor.getUserProfileName()).thenReturn(desVisitor);
        when(director.getUserProfileName()).thenReturn(desDirector);
        when(user.getUserProfileName()).thenReturn(desUser);
        when(desVisitor.getText()).thenReturn("Visitor");
        when(desDirector.getText()).thenReturn("Director");
        when(desUser.getText()).thenReturn("User");
        User underTest = new User(id, name, photo, password, function, true, assignedProfiles);
        //Act
        List<UserProfileID> result = underTest.getAssignedIdProfiles();
        //Assert
        assertEquals(assignedProfiles, result);
    }

    @Test
    public void getRequestedProfiles() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Description password = mock(Description.class);
        Function function = mock(Function.class);
        Request reqVisitor = mock(Request.class);
        Request reqUser = mock(Request.class);
        Request reqDirector = mock(Request.class);
        List<UserProfileID> assignedProfiles = new ArrayList<>();
        List<Request> requestedProfiles = new ArrayList<>();
        requestedProfiles.add(reqVisitor);
        requestedProfiles.add(reqUser);
        requestedProfiles.add(reqDirector);
        User underTest = new User(id, name, photo, password, function, true, assignedProfiles, requestedProfiles);
        //Act
        List<Request> result = underTest.getRequestedProfiles();
        //Assert
        assertEquals(requestedProfiles, result);
    }

    @Test
    public void editPersonalData() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Name changeName = mock(Name.class);
        Photo photo = mock(Photo.class);
        Photo changePhoto = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Function changeFunction = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(changeName.getText()).thenReturn("Change Name");
        when(changeFunction.getText()).thenReturn("Change Function");
        when(changePhoto.getExtension()).thenReturn("ChangePhoto.png");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password,
                function, profileID);
        //Act
        underTest.editPersonalData("Change Name", "Change Function", "ChangePhoto.png");
        //Assert
        assertEquals("Change Name", underTest.getUserName().getText());
        assertEquals("Change Function", underTest.getFunction().getText());
        assertEquals("ChangePhoto.png", underTest.getPhoto().getExtension());
    }

    @Test
    public void editPersonalData_AssignName() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Name changeName = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(changeName.getText()).thenReturn("Change Name");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password,
                function, profileID);
        //Act
        underTest.editPersonalData("Change Name", null, null);
        //Assert
        assertEquals("Change Name", underTest.getUserName().getText());
    }

    @Test
    public void editPersonalData_AssignFunction() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Function changeFunction = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(changeFunction.getText()).thenReturn("Change Function");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password,
                function, profileID);
        //Act
        underTest.editPersonalData(null, "Change Function", null);
        //Assert
        assertEquals("Change Function", underTest.getFunction().getText());
    }

    @Test
    public void editPersonalData_AssignPhoto() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Photo changePhoto = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        when(changePhoto.getExtension()).thenReturn("ChangePhoto.png");
        User underTest = new User(id, name, photo, password, password,
                function, profileID);
        //Act
        underTest.editPersonalData(null, null, "ChangePhoto.png");
        //Assert
        assertEquals("ChangePhoto.png", underTest.getPhoto().getExtension());
    }

    @Test
    public void updatePasswordSuccess() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        User expected = new User(id, name, photo, password, password, function, profileID);
        //Act
        underTest.updatePassword("Qwerty_1", "Qwerty_3");
        //Assert
        assertNotEquals(expected, underTest);
    }

    @Test
    public void updatePasswordFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserID id = mock(UserID.class);
            Name name = mock(Name.class);
            Photo photo = mock(Photo.class);
            Password password = mock(Password.class);
            Function function = mock(Function.class);
            Description description = mock(Description.class);
            UserProfileID profileID = mock(UserProfileID.class);
            when(password.getPwd()).thenReturn("Qwerty_1");
            when(profileID.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            User underTest = new User(id, name, photo, password, password, function, profileID);
            //Act
            underTest.updatePassword("Qwerty_4", "Qwerty_3");
        });
    }

    @Test
    public void createProfileRequest() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Description password = mock(Description.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getText()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        List<UserProfileID> assignedProfiles = new ArrayList<>();
        List<Request> requestedProfiles = new ArrayList<>();
        User underTest = new User(id, name, photo, password, function, true, assignedProfiles, requestedProfiles);
        //Act
        underTest.createProfileRequest(profileID);
        //Assert
        assertEquals(1 ,underTest.getRequestedProfiles().size());
    }

    @Test
    void activateStatusSuccess() {
        //S.U.T. {SystemUser}
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act
        underTest.activateStatus();
        //Assert
        assertTrue(underTest.isActive());
    }

    @Test
    void activateStatusFail() {
        //S.U.T. {SystemUser}
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        underTest.activateStatus();
        //Act
        underTest.activateStatus();
        //Assert
        assertTrue(underTest.isActive());
    }

    @Test
    void inactivateStatusSuccess() {
        //S.U.T. {SystemUser}
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        underTest.activateStatus();
        //Act
        underTest.inactivateStatus();
        //Assert
        assertFalse(underTest.isActive());
    }

    @Test
    void inactivateStatusFail() {
        //S.U.T. {SystemUser}
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act
        underTest.inactivateStatus();
        //Assert
        assertFalse(underTest.isActive());
    }

    @Test
    public void removeProfile() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act
        underTest.removeProfile(profileID);
        //Assert
        assertEquals(0, underTest.getAssignedProfiles().size());
    }

    @Test
    public void isYourEmailTrue() {
        //Arrange
        Email email = mock(Email.class);
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(email.getEmailText()).thenReturn("test@test.test");
        when(id.getEmail()).thenReturn(email);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act and Assert
        assertTrue(underTest.isYourEmail("test@test.test"));
    }

    @Test
    public void isYourEmailFalse() {
        //Arrange
        Email email = mock(Email.class);
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(email.getEmailText()).thenReturn("test@test.test");
        when(id.getEmail()).thenReturn(email);
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act and Assert
        assertFalse(underTest.isYourEmail("test@test.com"));
    }

    @Test
    public void hasNameTrue() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(name.getText()).thenReturn("test");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act and Assert
        assertTrue(underTest.hasName("test"));
    }

    @Test
    public void hasNameFalse() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(name.getText()).thenReturn("test");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act and Assert
        assertFalse(underTest.hasName("tester"));
    }

    @Test
    public void hasFunctionTrue() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(function.getText()).thenReturn("tester");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act and Assert
        assertTrue(underTest.hasFunction("tester"));
    }

    @Test
    public void hasFunctionFalse() {
        //Arrange
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(function.getText()).thenReturn("tester");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        User underTest = new User(id, name, photo, password, password, function, profileID);
        //Act and Assert
        assertFalse(underTest.hasFunction("test"));
    }

    @Test
    public void equalsTrue() {
        //Arrange
        Email email = mock(Email.class);
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(email.getEmailText()).thenReturn("test@test.test");
        when(id.getEmail()).thenReturn(email);
        when(function.getText()).thenReturn("tester");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        when(id.sameValueAs(id)).thenReturn(true);
        User result = new User(id, name, photo, password, password, function, profileID);
        //Act
        User expected = new User(id, name, photo, password, password, function, profileID);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void equalsFalse() {
        //Arrange
        Email email = mock(Email.class);
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(email.getEmailText()).thenReturn("test@test.test");
        when(id.getEmail()).thenReturn(email);
        when(function.getText()).thenReturn("tester");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        when(id.sameValueAs(id)).thenReturn(false);
        User result = new User(id, name, photo, password, password, function, profileID);
        //Act
        User expected = new User(id, name, photo, password, password, function, profileID);
        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    public void equalsNull() {
        //Arrange
        Email email = mock(Email.class);
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(email.getEmailText()).thenReturn("test@test.test");
        when(id.getEmail()).thenReturn(email);
        when(function.getText()).thenReturn("tester");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        when(id.sameValueAs(id)).thenReturn(true);
        User result = new User(id, name, photo, password, password, function, profileID);
        //Act
        User expected = null;
        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    public void equalsFalseClass() {
        //Arrange
        Email email = mock(Email.class);
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(email.getEmailText()).thenReturn("test@test.test");
        when(id.getEmail()).thenReturn(email);
        when(function.getText()).thenReturn("tester");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        when(id.sameValueAs(id)).thenReturn(true);
        //Act
        User result = new User(id, name, photo, password, password, function, profileID);
        //Assert
        assertNotEquals(result, id);
    }

    @Test
    public void hashCodeTrue() {
        //Arrange
        Email email = mock(Email.class);
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(email.getEmailText()).thenReturn("test@test.test");
        when(id.getEmail()).thenReturn(email);
        when(function.getText()).thenReturn("tester");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        when(id.sameValueAs(id)).thenReturn(true);
        User result = new User(id, name, photo, password, password, function, profileID);
        //Act
        User expected = new User(id, name, photo, password, password, function, profileID);
        //Assert
        assertEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    public void hashCodeFalse() {
        //Arrange
        Email email = mock(Email.class);
        UserID id = mock(UserID.class);
        Name name = mock(Name.class);
        Photo photo = mock(Photo.class);
        Password password = mock(Password.class);
        Function function = mock(Function.class);
        Description description = mock(Description.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(email.getEmailText()).thenReturn("test@test.test");
        when(id.getEmail()).thenReturn(email);
        when(function.getText()).thenReturn("tester");
        when(password.getPwd()).thenReturn("Qwerty_1");
        when(profileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        when(id.sameValueAs(id)).thenReturn(true);
        //Act
        User result = new User(id, name, photo, password, password, function, profileID);
        //Assert
        assertNotEquals(photo.hashCode(), result.hashCode());
    }
}