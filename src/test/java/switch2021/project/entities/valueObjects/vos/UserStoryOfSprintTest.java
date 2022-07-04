package switch2021.project.entities.valueObjects.vos;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class UserStoryOfSprintTest {

    @Test
    @DisplayName("Validate is the same Object - true result")
    public void sameValueAs() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        //Act
        boolean expected = userStoryOfSprint.sameValueAs(userStoryOfSprint2);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Validate is the same Object - true result get UsID")
    public void sameValueAsUsID() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        //Act
        boolean equalsUsID = userStoryOfSprint.getUserStoryId().equals(userStoryOfSprint2.getUserStoryId());
        //Assert
        assertTrue(equalsUsID);
    }

    @Test
    @DisplayName("Validate is the same Object - true result - get Status")
    public void sameValueAsStatus() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        //Act
        boolean equalStatus =
                userStoryOfSprint.getUserStoryOfSprintStatus().equals(userStoryOfSprint2.getUserStoryOfSprintStatus());
        //Assert
        assertTrue(equalStatus);
    }

    @Test
    @DisplayName("Validate is the same Object - false result")
    public void sameValueAsFalse() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus2 = mock(UserStoryOfSprintStatus.class);
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID2, userStoryOfSprintStatus2,
                sprintName);
        //Act
        boolean expected = userStoryOfSprint.sameValueAs(userStoryOfSprint2);
        boolean equalStatus =
                userStoryOfSprint.getUserStoryOfSprintStatus().equals(userStoryOfSprint2.getUserStoryOfSprintStatus());
        boolean equalsUsID = userStoryOfSprint.getUserStoryId().equals(userStoryOfSprint2.getUserStoryId());

        //Assert
        assertFalse(expected);
        assertFalse(equalStatus);
        assertFalse(equalsUsID);
    }

    @Test
    @DisplayName("Validate is the same Object - false result")
    public void getSprintName() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        String sprintName = "Make test";
        String sprintName2 = "";
        //Act
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName2);

        //Assert
        assertEquals(sprintName, userStoryOfSprint.getSprintName());
        assertEquals(sprintName2, userStoryOfSprint2.getSprintName());
    }

    @Test
    @DisplayName("Change US status in a sprint")
    public void setUserStoryOfSprintStatus() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        //Act
        userStoryOfSprint.setUserStoryOfSprintStatus(UserStoryOfSprintStatus.Done);
        //Assert
        assertEquals(userStoryOfSprint.getUserStoryOfSprintStatus(),UserStoryOfSprintStatus.Done);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        //Act & Assert
        assertEquals(userStoryOfSprint, userStoryOfSprint2);

    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestTrue() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        //Act
        boolean expected = userStoryOfSprint.equals(userStoryOfSprint2);
        boolean expectedUsID = userStoryOfSprint.getUserStoryId().equals(userStoryOfSprint2.getUserStoryId());
        boolean expectedStatus = userStoryOfSprint.getUserStoryOfSprintStatus().equals(userStoryOfSprint2.getUserStoryOfSprintStatus());
        boolean expectedName = userStoryOfSprint.getSprintName().equals(userStoryOfSprint2.getSprintName());

        // Assert
        assertTrue(expected);
        assertTrue(expectedUsID);
        assertTrue(expectedStatus);
        assertTrue(expectedName);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFalse() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID2, userStoryOfSprintStatus, sprintName);
        //Act
        boolean expected = userStoryOfSprint.equals(userStoryOfSprint2);
        // Assert
        assertFalse(expected);
        assertNotEquals(userStoryOfSprint, userStoryOfSprint2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCodeTestSuccess() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        //Act and Assert
        assertEquals(userStoryOfSprint.hashCode(), userStoryOfSprint2.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCodeTestFail() {
        //Arrange
        UserStoryOfSprintStatus userStoryOfSprintStatus = mock(UserStoryOfSprintStatus.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);

        String sprintName = "Make test";
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(userStoryID, userStoryOfSprintStatus, sprintName);
        UserStoryOfSprint userStoryOfSprint2 = new UserStoryOfSprint(userStoryID2, userStoryOfSprintStatus, sprintName);
        //Act and Assert
        assertNotEquals(userStoryOfSprint.hashCode(), userStoryOfSprint2.hashCode());
    }
}
