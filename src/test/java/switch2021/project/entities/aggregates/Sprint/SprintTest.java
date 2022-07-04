package switch2021.project.entities.aggregates.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SprintTest {


    @Test
    @DisplayName("Constructor Test, with success")
    void ConstructorSprint_Success() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        Sprint sprint = new Sprint(sprintID);
        ProjectID projectID = mock(ProjectID.class);
        Description sprintName = mock(Description.class);
        //Act
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(sprintName);
        when(sprintName.getText()).thenReturn("Sprint Name");
        //Assert
        assertEquals(sprintID, sprint.getSprintID());
    }

    @Test
    @DisplayName("Constructor Test, with failure")
    void ConstructorSprint_Failure() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        Sprint sprint = new Sprint(sprintID);
        ProjectID projectID = mock(ProjectID.class);
        Description sprintName = mock(Description.class);
        //Act
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(sprintName);
        when(sprintName.getText()).thenReturn("Sprint Name");
        //Assert
        assertNotEquals("Project_2022_1_Sprint", sprint.getSprintID().toString());
    }

    @Test
    @DisplayName("Verification test if EndDate is set with failure")
    public void changeEndDateFail() {
        //Assert
        SprintID sprintID = new SprintID("Project_2022_1&Sprint 1");
        Sprint sprint = new Sprint(sprintID);
        sprint.changeEndDate(300);
        LocalDate endDate = sprint.getEndDate();
        //Assert
        assertNotEquals(LocalDate.of(2022, 2, 16), endDate);
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintFalseTest() {
        SprintID sprintID = new SprintID("Project_2022_1&Sprint 1");
        Sprint sprint = new Sprint(sprintID);
        sprint.setStartDate(LocalDate.of(2021, 1, 1));
        sprint.setEndDate(LocalDate.of(2020, 1, 1));
        assertFalse(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintTrueTest() {
        SprintID sprintID = new SprintID("Project_2022_1&Sprint 1");
        Sprint sprint = new Sprint(sprintID);
        sprint.setStartDate(LocalDate.of(2022, 1, 1));
        sprint.setEndDate(LocalDate.of(2022, 1, 15));
        sprint.changeEndDate(14);
        assertTrue(sprint.isCurrentSprint());
    }

    @Test
    @DisplayName("Verification test if is a current Sprint")
    public void isCurrentSprintEndDateNullTest() {
        assertThrows(NullPointerException.class, () -> {
            SprintID sprintID = new SprintID("Project_2022_1&Sprint 1");
            Sprint sprint = new Sprint(sprintID);
            sprint.isCurrentSprint();
        });
    }

    @Test
    @DisplayName("Verification test of hasSprintID method")
    public void hasSprintIDTest_Fail() {
        //Arrange
        SprintID sprintID = new SprintID("Project_2022_1&Sprint 2");
        Sprint sprint = new Sprint(sprintID);
        //Assert
        assertFalse(sprint.hasSprintID("Project_2022_1&Sprint New"));
    }


    @Test
    @DisplayName("HashCode Verification")
    public void hashCodeTest() {
        //Arrange
        SprintID sprintID1 = new SprintID("Project_2022_1&Sprint 1");
        Sprint sprint = new Sprint(sprintID1);
        SprintID sprintID2 = new SprintID("Project_2022_1&Sprint 2");
        Sprint sprint2 = new Sprint(sprintID1);
        Sprint sprint3 = new Sprint(sprintID2);
        //Assert
        assertEquals(sprint.hashCode(), sprint2.hashCode());
        assertEquals(sprint, sprint);
        assertNotEquals(sprint3.hashCode(), sprint2.hashCode());
        assertNotEquals(sprint3, sprint2);
    }

    @Test
    @DisplayName("Same Identify As - True")
    public void sameIdentityAs_true() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        Sprint sprint1 = new Sprint(sprintID);
        Sprint sprint2 = new Sprint(sprintID);
        //Act
        when(sprintID.sameValueAs(sprintID)).thenReturn(true);
        //Assert
        assertTrue(sprint1.sameIdentityAs(sprint2));
    }

    @Test
    @DisplayName("Same Identify As - False")
    public void sameIdentityAs_false() {
        //Arrange
        SprintID sprintID1 = mock(SprintID.class);
        SprintID sprintID2 = mock(SprintID.class);
        Sprint sprint1 = new Sprint(sprintID1);
        Sprint sprint2 = new Sprint(sprintID2);
        //Act
        when(sprintID1.sameValueAs(sprintID2)).thenReturn(false);
        //Assert
        assertFalse(sprint1.sameIdentityAs(sprint2));
    }

    @Test
    @DisplayName("Same Identify As - Null")
    public void sameIdentityAs_Null() {
        //Arrange
        SprintID sprintID1 = mock(SprintID.class);
        Sprint sprint1 = new Sprint(sprintID1);
        Sprint sprint2 = new Sprint(null);
        //Act
        when(sprintID1.sameValueAs(null)).thenReturn(false);
        //Assert
        assertFalse(sprint1.sameIdentityAs(sprint2));
    }


}
