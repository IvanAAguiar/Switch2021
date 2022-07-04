package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectIDTest {

    @Test
    void equalsProjectIDTrue() {
        //Arrange//Act
        ProjectID projectID = new ProjectID("Project_2022_1");
        ProjectID expected = new ProjectID("Project_2022_1");
        //Assert
        assertEquals(expected.getCode(), projectID.getCode());
    }

    @Test
    void equalsProjectIDTrue_1() {
        //Arrange//Act
        ProjectID projectID = new ProjectID("Project_2022_1");
        ProjectID expected = new ProjectID("Project_2022_1");
        //Assert
        assertTrue(projectID.equals(expected));
    }

    @Test
    void equalsProjectIDFalse() {
        //Arrange//Act
        ProjectID projectID = new ProjectID();
        ProjectID expected = new ProjectID("Project_2022_1");
        //Assert
        assertNotEquals(expected, projectID);
    }


    @Test
    void equalsProjectIDNull() {
        //Arrange
        String code = "Project_2022_1";
        //Act
        ProjectID projectID = new ProjectID("Project_2022_1");
        //Assert
        assertNotEquals(projectID, code);
    }

    @Test
    void sameValueAsTrue() {
        //Arrange//Act
        ProjectID projectID = new ProjectID("Project_2022_1");
        ProjectID expected = new ProjectID("Project_2022_1");
        //Assert
        assertTrue(projectID.sameValueAs(expected));
    }

    @Test
    void sameValueAsFalse() {
        //Arrange//Act
        ProjectID projectID = new ProjectID("Project_2022_1");
        ProjectID expected = new ProjectID();
        //Assert
        assertFalse(projectID.sameValueAs(expected));
    }

    @Test
    void HashCodeTrue() {
        //Arrange//Act
        ProjectID projectID = new ProjectID("Project_2022_1");
        ProjectID expected = new ProjectID("Project_2022_1");
        //Assert
        assertEquals(projectID.hashCode(), expected.hashCode());
    }

    @Test
    void HashCodeFalse() {
        //Arrange//Act
        ProjectID projectID = new ProjectID("Project_2022_1");
        ProjectID expected = new ProjectID();
        //Assert
        assertNotEquals(projectID.hashCode(), expected.hashCode());
    }
}