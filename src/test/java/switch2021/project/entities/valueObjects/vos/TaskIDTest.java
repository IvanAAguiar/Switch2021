package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskIDTest {

    @Test
    @DisplayName("Create Task with success")
    void CreateTaskIDWithSuccess() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        Description description = mock(Description.class);
        //Act
        TaskID taskID = new TaskID(taskContainerID, description);
        //Assert
        assertNotNull(taskID);
    }

    @Test
    @DisplayName("Create Task with success and get info")
    void getInformationFromTaskID() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        Description description = mock(Description.class);
        //Act
        TaskID taskID = new TaskID(taskContainerID, description);
        //Assert
        assertEquals(taskContainerID, taskID.getTaskContainerID());
        assertEquals(description, taskID.getTaskTitle());
    }

    @Test
    @DisplayName("Test same identity conditions for coverage purposes")
    public void sameIdentityAsTrue() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        Description description = mock(Description.class);
        TaskID taskID = new TaskID(taskContainerID, description);
        TaskID taskID2 = new TaskID(taskContainerID, description);
        //Act
        boolean expected = taskID.sameValueAs(taskID2);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test same identity conditions for coverage purposes")
    public void sameIdentityAsFalse() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        TaskContainerID taskContainerID2 = mock(TaskContainerID.class);
        Description description = mock(Description.class);
        Description description2 = mock(Description.class);
        TaskID taskID = new TaskID(taskContainerID, description);
        TaskID taskID2 = new TaskID(taskContainerID2, description2);
        //Act
        boolean expected = taskID.sameValueAs(taskID2);
        //Assert
        assertFalse(expected);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestTrue() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle =mock(UsTitle.class);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, want to test this string");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("US1 description");
        TaskID taskID = new TaskID(taskContainerID, description);
        TaskID taskID2 = new TaskID(taskContainerID, description);
        //Act
        boolean expected = taskID.equals(taskID2);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFalse() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        TaskContainerID taskContainerID2 = mock(TaskContainerID.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle =mock(UsTitle.class);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, want to test this string");
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        ProjectID projectID2 = mock(ProjectID.class);
        UsTitle usTitle2 =mock(UsTitle.class);
        when(userStoryID2.getProjectID()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Project_2022_2");
        when(userStoryID2.getUsTitle()).thenReturn(usTitle2);
        when(usTitle2.getTitleUs()).thenReturn("As a X, want to test this thing");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("US1 description");
        Description description2 = mock(Description.class);
        when(description2.getText()).thenReturn("US2 description");
        TaskID taskID = new TaskID(taskContainerID, description);
        TaskID taskID2 = new TaskID(taskContainerID2, description2);
        //Act
        boolean expected = taskID.equals(taskID2);
        //Assert
        assertFalse(expected);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        Description description = mock(Description.class);
        TaskID taskID = new TaskID(taskContainerID, description);
        TaskID taskID2 = new TaskID(taskContainerID, description);
        //Act
        int id1 = taskID.hashCode();
        int id2 = taskID2.hashCode();
        //Assert
        assertEquals(id1, id2);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSFail() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        TaskContainerID taskContainerID2 = mock(TaskContainerID.class);
        Description description = mock(Description.class);
        Description description2 = mock(Description.class);
        TaskID taskID = new TaskID(taskContainerID, description);
        TaskID taskID2 = new TaskID(taskContainerID2, description2);
        //Act
        int id1 = taskID.hashCode();
        int id2 = taskID2.hashCode();
        //Assert
        assertNotEquals(id1, id2);
    }

    @Test
    @DisplayName("Test to string")
    public void TestToString() {
        //Arrange
        TaskContainerID taskContainerID = mock(TaskContainerID.class);
        when(taskContainerID.toString()).thenReturn("Top");
        Description description = mock(Description.class);
        when(description.getText()).thenReturn("Test");
        TaskID taskID = new TaskID(taskContainerID, description);
        //Act
        String expected = taskID.toString();
        //Assert
        assertEquals("Top&Test", expected);

    }

}
