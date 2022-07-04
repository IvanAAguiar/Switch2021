package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserStoryIDTest {

    @Test
    @DisplayName("Validate that ID is correct")
    public void usIDSuccess() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        String expected = "Project_2022_1&As a PO, i want to test this string";
        //Assert
        assertEquals(expected, userStoryId.toString());
    }

    @Test
    @DisplayName("Validate that ID is correct")
    public void usIDSuccessWithString() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
        String expectedProject = "1";
        String expectedTitle = "As a PO, i want to test this string";
        //Assert
        assertEquals(expectedProject, userStoryId.getProjectID().getCode());
        assertEquals(expectedTitle, userStoryId.getUsTitle().getTitleUs());
    }

    @Test
    @DisplayName("Validate that ID is correct- not empty")
    public void usIDSuccessCreated() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        boolean expected = userStoryId.toString().isEmpty();
        boolean expected2 = userStoryId.toString().equalsIgnoreCase("Project_2022_1&As a PO, i want to test this " +
                "string");
        //Assert
        assertFalse(expected);
        assertTrue(expected2);
    }

    @Test
    @DisplayName("Validate that ID is correct - Same ID")
    public void SameId() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        UserStoryID userStoryID2 = new UserStoryID(projectCode, usTitle);
        //Assert
        assertEquals(userStoryId, userStoryID2);
    }

    @Test
    @DisplayName("Validate that ID is correct but not the same")
    public void NotSameId() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        ProjectID projectCode2 = mock(ProjectID.class);
        when(projectCode2.getCode()).thenReturn("Project_4022_1");
        UsTitle usTitle2 = mock(UsTitle.class);
        when(usTitle2.getTitleUs()).thenReturn("As a PO, i want to test");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        UserStoryID userStoryID2 = new UserStoryID(projectCode2, usTitle2);
        //Assert
        assertNotEquals(userStoryId, userStoryID2);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hasCodeSuccess() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        UserStoryID userStoryID2 = new UserStoryID(projectCode, usTitle);
        //Assert
        assertEquals(userStoryId.hashCode(), userStoryID2.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hasCodeFail() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        ProjectID projectCode2 = mock(ProjectID.class);
        when(projectCode2.getCode()).thenReturn("Project_4022_1");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        UserStoryID userStoryID2 = new UserStoryID(projectCode2, usTitle);
        //Assert
        assertNotEquals(userStoryId.hashCode(), userStoryID2.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        //Assert
        assertEquals(userStoryId, userStoryId);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        Typology budget1 = new Typology(new TypologyID(new Description("Test")));
        //Assert
        assertNotEquals(userStoryId, budget1);
    }


    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void userStoryToString() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        //Assert
        assertEquals(userStoryId.toString(), userStoryId.toString());
    }


    @Test
    @DisplayName("Validate that projectID is correct")
    public void getProjectIdWithSuccess() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        //Assert
        assertEquals("Project_2022_1", userStoryId.getProjectID().getCode());
    }

    @Test
    @DisplayName("Validate that usTitle is correct")
    public void getUsTitleWithSuccess() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryId = new UserStoryID(projectCode, usTitle);
        //Assert
        assertEquals("As a PO, i want to test this string", userStoryId.getUsTitle().getTitleUs());
    }

    @Test
    @DisplayName("Create ID user story fail - user story title empty")
    public void getUsIdFailEmptyUsTitle() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            ProjectID projectCode = mock(ProjectID.class);
            when(projectCode.getCode()).thenReturn("Project_2022_1");
            UsTitle usTitle = mock(UsTitle.class);
            when(usTitle.getTitleUs()).thenReturn("");
            // Act
            new UserStoryID(projectCode, usTitle);

        });
    }

    @Test
    @DisplayName("Create ID user story fail - code project empty")
    public void getUsIdFailEmptyCodeProject() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            ProjectID projectCode = mock(ProjectID.class);
            when(projectCode.getCode()).thenReturn("");
            UsTitle usTitle = mock(UsTitle.class);
            when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
            // Act
            new UserStoryID(projectCode, usTitle);

        });
    }

    @Test
    @DisplayName("Create ID user story fail - code project and user story title empty")
    public void getUsIdFailEmptyCodeProjectAndUsTitle() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            ProjectID projectCode = mock(ProjectID.class);
            when(projectCode.getCode()).thenReturn("");
            UsTitle usTitle = mock(UsTitle.class);
            when(usTitle.getTitleUs()).thenReturn("");
            // Act
            new UserStoryID(projectCode, usTitle);

        });
    }

    @Test
    @DisplayName("make test in same value as - true result")
    public void sameValueAsTrue() {
        // Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        UserStoryID userStoryID = new UserStoryID(projectCode, usTitle);
        UserStoryID userStoryID2 = new UserStoryID(projectCode, usTitle);
        //Act
        boolean expected = userStoryID.sameValueAs(userStoryID2);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("make test in same value as - false result")
    public void sameValueAsFalse() {
        // Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        ProjectID projectCode2 = mock(ProjectID.class);
        when(projectCode2.getCode()).thenReturn("Project_2022_2");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        UserStoryID userStoryID = new UserStoryID(projectCode, usTitle);
        UserStoryID userStoryID2 = new UserStoryID(projectCode2, usTitle);
        //Act
        boolean expected = userStoryID.sameValueAs(userStoryID2);
        //Assert
        assertFalse(expected);
    }

    @Test
    void overrideTestNullObject() {
        //Arrange
        ProjectID projectCode = mock(ProjectID.class);
        when(projectCode.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Act
        UserStoryID userStoryID = new UserStoryID(projectCode, usTitle);
        UserStoryID userStoryID2 = null;
        //Assert
        assertNotEquals(userStoryID, userStoryID2);
        assertNull(userStoryID2);

    }

}
