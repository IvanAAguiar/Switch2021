package switch2021.project.entities.aggregates.UserStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryStatusEnum;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryTest {

    @Test
    @DisplayName("Create User Story with success - using user story ID")
    void CreateUserStoryWithSuccess() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        Description description = mock(Description.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Assert
        assertEquals(usID, userStory.getUserStoryID());
    }

    @Test
    @DisplayName("Create User Story refined with success - using user story parent")
    void CreateUserStoryParentWithSuccess() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        Description description = mock(Description.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        UserStory parentUs = new UserStory(usID, usPriority, description, timeEstimate);
        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        //Act
        userStory.assignParentUserStory(parentUs);
        //Assert
        assertEquals(parentUs,userStory.getParentUserStory());
    }

    @Test
    public void hasProjectIdSuccess() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        Description description = mock(Description.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        //Act and Assert
        assertTrue(userStory.hasProjectId("Project_2022_1"));
    }

    @Test
    public void hasProjectIdFail() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        Description description = mock(Description.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        UserStory userStory = new UserStory();
        userStory.setUserStoryID(usID);
        userStory.setDescription(description);
        userStory.setPriority(usPriority);
        userStory.setTimeEstimate(timeEstimate);
        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_3");
        //Act and Assert
        assertFalse(userStory.hasProjectId("Project_2022_1"));
    }

    @Test
    @DisplayName("test to get information about object attributes")
    public void getUserStoryAttributes() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(description.getText()).thenReturn("Make mock test");
        when(timeEstimate.getUsHours()).thenReturn(5.0);
        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        userStory.refinedUs();

        //Assert
        assertEquals(1, userStory.getPriority().getPriorityUs());
        assertEquals("Make mock test", userStory.getDescription().getText());
        assertEquals(5.0, userStory.getTimeEstimate().getUsHours());
    }

    @Test
    @DisplayName("test to update priority")
    public void updatePriority() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsPriority newUsPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(description.getText()).thenReturn("Make mock test");
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        when(newUsPriority.getPriorityUs()).thenReturn(3);

        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        userStory.updatePriority(newUsPriority);

        //Assert
        assertEquals(3, userStory.getPriority().getPriorityUs());
    }

    @Test
    @DisplayName("test to updateTimeEstimate")
    public void updateTimeEstimate() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);
        UsHour newTimeEstimate = mock(UsHour.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(description.getText()).thenReturn("Make mock test");
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        when(newTimeEstimate.getUsHours()).thenReturn(8.0);

        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        userStory.updateTimeEstimate(newTimeEstimate);

        //Assert
        assertEquals(8.0, userStory.getTimeEstimate().getUsHours());
    }

    @Test
    @DisplayName("test to start User Story")
    public void startUserStory() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(description.getText()).thenReturn("Make mock test");
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        userStory.startUserStory();

        //Assert
        assertEquals(LocalDate.now(), userStory.getUsStartDate());
    }

    @Test
    @DisplayName("test to finish UserStory")
    public void finishUserStory() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(description.getText()).thenReturn("Make mock test");
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        userStory.finishUserStory();

        //Assert
        assertEquals(LocalDate.now(), userStory.getUsEndDate());
    }

    @Test
    @DisplayName("test to cancel UserStory")
    public void cancelUserStory() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(description.getText()).thenReturn("Make mock test");
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        userStory.cancelUserStory();

        //Assert
        assertEquals(LocalDate.now(), userStory.getUsEndDate());
    }

    @Test
    @DisplayName("Test hasCode conditions for coverage purposes")
    void hasCodeTest() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStoryID usID2 = mock(UserStoryID.class);
        ProjectID projID2 = mock(ProjectID.class);
        UsTitle usTitle2 = mock(UsTitle.class);
        when(usID2.getProjectID()).thenReturn(projID2);
        when(projID2.getCode()).thenReturn("Project_2022_1");
        when(usID2.getUsTitle()).thenReturn(usTitle2);
        when(usTitle2.getTitleUs()).thenReturn("As a PO, i want to test this string");
        UserStory userStory2 = new UserStory(usID2, usPriority, description, timeEstimate);
        when(userStory2.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStoryID usID3 = mock(UserStoryID.class);
        ProjectID projID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);
        when(usID3.getProjectID()).thenReturn(projID3);
        when(projID3.getCode()).thenReturn("Project_2024_1");
        when(usID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this override method");
        UserStory userStory3 = new UserStory(usID3, usPriority, description, timeEstimate);
        when(userStory3.getUserStoryID().toString()).thenReturn("Project_2024_1_As a PO, i want to test this override" +
                " method");
        //Act
        boolean expected = userStory.hasCode(userStory.getUserStoryID());
        boolean expectedF = userStory.hasCode(userStory3.getUserStoryID());
        //Assert
        assertTrue(expected);
        assertFalse(expectedF);
        assertEquals(userStory.hasCode(usID), userStory2.hasCode(usID2));
    }

    @Test
    @DisplayName("Test same identity conditions for coverage purposes")
    public void sameIdentityAsTrue() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        UserStory userStory2 = new UserStory(usID, usPriority, description, timeEstimate);
        //Act
        when(usID.sameValueAs(usID)).thenReturn(true);
        //Assert
        assertTrue(userStory.sameIdentityAs(userStory2));
    }

    @Test
    @DisplayName("Test same identity conditions for coverage purposes")
    public void sameIdentityAsFalse() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        UserStory userStory2 = new UserStory(usID, usPriority, description, timeEstimate);
        //Act
        when(usID.sameValueAs(usID)).thenReturn(false);
        //Assert
        assertFalse(userStory.sameIdentityAs(userStory2));
    }

    @Test
    @DisplayName("Test same identity conditions for coverage purposes")
    public void sameIdentityAsNull() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        UserStory userStory2 = new UserStory(usID, usPriority, description, timeEstimate);
        //Act
        when(usID.sameValueAs(null)).thenReturn(false);
        //Assert
        assertFalse(userStory.sameIdentityAs(userStory2));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange & Act
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UserStoryID usID = mock(UserStoryID.class);
        UsHour timeEstimate = mock(UsHour.class);
        Description description = mock(Description.class);
        UsPriority usPriority = mock(UsPriority.class);
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStory userStory2 = new UserStory(usID, usPriority, description, timeEstimate);

        UserStoryID usID3 = mock(UserStoryID.class);
        UserStory userStory3 = new UserStory(usID3, usPriority, description, timeEstimate);
        ProjectID projID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);

        when(usID3.getProjectID()).thenReturn(projID3);
        when(projID3.getCode()).thenReturn("Project_2024_1");
        when(usID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this override method");
        when(userStory3.getUserStoryID().toString()).thenReturn("Project_2024_1_As a PO, i want to test this override" +
                " method");

        //Assert
        assertNotSame(userStory, userStory3);
        assertEquals(userStory.getUserStoryID(), userStory2.getUserStoryID());
        assertNotEquals(userStory.getUserStoryID(), userStory3.getUserStoryID());
        assertNotEquals(userStory, userStory2);
    }


    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UsHour timeEstimate = mock(UsHour.class);
        Description description = mock(Description.class);
        UsPriority usPriority = mock(UsPriority.class);
        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        UserStory userStory2 = new UserStory(usID, usPriority, description, timeEstimate);
        //Assert
        assertEquals(userStory.hashCode(), userStory2.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeTestFail() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UserStoryID usID2 = mock(UserStoryID.class);
        UsHour timeEstimate = mock(UsHour.class);
        Description description = mock(Description.class);
        UsPriority usPriority = mock(UsPriority.class);
        //Act
        UserStory userStory = new UserStory(usID, usPriority, description, timeEstimate);
        UserStory userStory2 = new UserStory(usID2, usPriority, description, timeEstimate);
        //Assert
        assertNotEquals(userStory.hashCode(), userStory2.hashCode());

    }
}