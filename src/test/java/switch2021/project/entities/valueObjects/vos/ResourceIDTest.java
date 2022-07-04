package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceIDTest {

    @Test
    void createResourceIDGetUser() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID.getUser(),userID);
    }

    @Test
    void createResourceIDGetProject() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID.getProject(),projectID);
    }

    @Test
    void createResourceIDGetStartDAte() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID.getStartDate(),startDate);
    }

    @Test
    void sameValueAsTrue() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userID,projectID,startDate);
        //Assert
        assertTrue(resourceID.sameValueAs(expected));
    }

    @Test
    void sameValueAsFalse() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        UserID userIDexp = mock(UserID.class);
        ProjectID projectIDexp = mock(ProjectID.class);
        LocalDate startDateexp = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userIDexp,projectIDexp,startDateexp);
        //Assert
        assertFalse(resourceID.sameValueAs(expected));
    }

    @Test
    void equalsFalse() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        UserID userIDexp = mock(UserID.class);
        ProjectID projectIDexp = mock(ProjectID.class);
        LocalDate startDateexp = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userIDexp,projectIDexp,startDateexp);
        //Assert
        assertNotEquals(resourceID,expected);
    }

    @Test
    void equalsNull() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID();
        //Assert
        assertNotEquals(resourceID,expected);
    }

    @Test
    void equalsObject() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertNotEquals(resourceID,userID);
    }

    @Test
    void equalsTrue() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID,expected);
    }

    @Test
    void HashCodeTrue() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID.hashCode(),expected.hashCode());
    }

    @Test
    void HashCodeFalse() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertNotEquals(resourceID.hashCode(),projectID.hashCode());
    }

    @Test
    void toStringSuccess() {
        //Arrange
        UserID userID = mock(UserID.class);
        Email email = mock(Email.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("zcd@gmail.com");
        when(projectID.getCode()).thenReturn("Project_2022_1");
        String expected = "zcd@gmail.com&Project_2022_1&2022-10-12";
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Act
        String result = resourceID.toString();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void sameValueAs_True() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userID,projectID,startDate);
        //Assert
        assertTrue(resourceID.sameValueAs(expected));
    }

    @Test
    void sameValueAs_False() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        LocalDate date = LocalDate.parse("2022-10-13");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userID,projectID, date);
        //Assert
        assertFalse(resourceID.sameValueAs(expected));
    }
}