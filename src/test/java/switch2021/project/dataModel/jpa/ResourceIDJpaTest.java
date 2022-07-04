package switch2021.project.dataModel.jpa;

import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.JPA.ResourceIDJpa;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.UserID;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceIDJpaTest {

    @Test
    void equalsSuccess() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse("2022-10-12");
        ResourceID id = mock(ResourceID.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        ResourceIDJpa result = new ResourceIDJpa(userID, projectID, startDate);
        ResourceIDJpa expected = new ResourceIDJpa(userID, projectID, startDate);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getProjectSuccess() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse("2022-10-12");
        ResourceID id = mock(ResourceID.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        ResourceIDJpa result = new ResourceIDJpa(userID, projectID, startDate);
        ResourceIDJpa expected = new ResourceIDJpa(userID, projectID, startDate);
        //Assert
        assertEquals(expected.getProject(), result.getProject());
    }

    @Test
    void getUserSuccess() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse("2022-10-12");
        ResourceID id = mock(ResourceID.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        ResourceIDJpa result = new ResourceIDJpa(userID, projectID, startDate);
        ResourceIDJpa expected = new ResourceIDJpa(userID, projectID, startDate);
        //Assert
        assertEquals(expected.getUser(), result.getUser());
    }

    @Test
    void equalsFail() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse("2022-10-12");
        ResourceID id = mock(ResourceID.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        ResourceIDJpa result = new ResourceIDJpa();
        ResourceIDJpa expected = new ResourceIDJpa(userID, projectID, startDate);
        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void hashCodeSuccess() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse("2022-10-12");
        ResourceID id = mock(ResourceID.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        ResourceIDJpa result = new ResourceIDJpa(userID, projectID, startDate);
        ResourceIDJpa expected = new ResourceIDJpa(userID, projectID, startDate);
        //Assert
        assertEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    void hashCodeFail() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse("2022-10-12");
        ResourceID id = mock(ResourceID.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        ResourceIDJpa result = new ResourceIDJpa();
        ResourceIDJpa expected = new ResourceIDJpa(userID, projectID, startDate);
        //Assert
        assertNotEquals(expected.hashCode(), result.hashCode());
    }
}