package switch2021.project.entities.aggregates.Project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTest {

    @Mock
    ProjectID projectID;
    @Mock
    Description description;
    @Mock
    Description projectName;
    @Mock
    TypologyID typology;
    @Mock
    Customer customer;
    @Mock
    BusinessSector businessSector;
    @Mock
    Budget budget;
    @Mock
    NumberOfSprints numberOfSprints;
    @Mock
    SprintDuration sprintDuration;


    @Test
    void hasCodeTrue() {
        //Arrange
        when(projectID.getCode()).thenReturn("Project_2022_1");
        LocalDate startDate = LocalDate.now();
        //Act
        Project newProject = new Project(projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget);
        newProject.setProjectCode(projectID);
        //Assert
        assertTrue(newProject.hasCode("Project_2022_1"));
    }


    @Test
    void hasCodeFalse() {
        //Arrange
        when(projectID.getCode()).thenReturn("Project_2022_1");
        LocalDate startDate = LocalDate.now();
        //Act
        Project newProject = new Project(projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget);
        newProject.setProjectCode(projectID);
        //Assert
        assertFalse(newProject.hasCode("testX"));
    }


//    @Test
//    void overrideTest() {
//        //Arrange
//        when(projectID.getCode()).thenReturn("Project_2022_1");
//        LocalDate startDate = LocalDate.now();
//        Description name_2 = mock(Description.class);
//        ProjectID projectID2 = mock(ProjectID.class);
//        //Act
//        Project project1 = new Project(name_2, description, businessSector, startDate, numberOfSprints
//                , sprintDuration, budget);
//        project1.setProjectCode(projectID);
//
//        Project project2 = new Project(projectName, description, businessSector, startDate, numberOfSprints
//                , sprintDuration, budget);
//        project2.setProjectCode(projectID2);
//
//        Project project3 = new Project(projectName, description, businessSector, startDate, numberOfSprints
//                , sprintDuration, budget);
//        project3.setProjectCode(projectID2);
//
//        //Assert
//        assertEquals(project1, project1);
//        assertNotEquals(project1, project2);
//        assertNotEquals(project3, project1);
//        assertEquals(project2.getProjectCode(), project3.getProjectCode());
//        assertEquals(project2.getProjectName(), project3.getProjectName());
//
//    }


    @Test
    void testHashCode() {
        //Arrange
        ProjectID projectID2 = mock(ProjectID.class);
        LocalDate startDate = LocalDate.now();
        //Act
        Project project1 = new Project(projectName, description, businessSector, startDate, numberOfSprints
                , sprintDuration, budget);
        project1.setProjectCode(projectID);

        Project project2 = new Project(projectName, description, businessSector, startDate, numberOfSprints
                , sprintDuration, budget);
        project2.setProjectCode(projectID2);
        //Assert
        assertEquals(project1.hashCode(), project1.hashCode());
        assertNotEquals(project1.hashCode(), project2.hashCode());
    }

    @Test
    void setAndGetProjectName() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget);
        project.setProjectName(projectName);
        //Assert
        assertEquals(projectName, project.getProjectName());
    }

    @Test
    void setAndGetDescription() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget);
        project.setDescription(description);
        //Assert
        assertEquals(description, project.getDescription());
    }

    @Test
    void setAndGetTypology() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget);
        project.setTypologyId(typology);
        //Assert
        assertEquals(typology, project.getTypologyId());
    }

    @Test
    void setAndGetProjectStatus() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setProjectStatus(ProjectStatusEnum.PLANNED);
        //Assert
        assertEquals(ProjectStatusEnum.PLANNED, project.getProjectStatus());
    }

    @Test
    void setAndGetCustomer() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setCustomer(customer);
        //Assert
        assertEquals(customer, project.getCustomer());
    }

    @Test
    void setAndGetBusinessSector() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setBusinessSector(businessSector);
        //Assert
        assertEquals(businessSector, project.getBusinessSector());
    }

    @Test
    void setAndGetNumberOfSprints() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setNumberOfSprints(numberOfSprints);
        //Assert
        assertEquals(numberOfSprints, project.getNumberOfSprints());
    }

    @Test
    void setAndGetBudget() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setBudget(budget);
        //Assert
        assertEquals(budget, project.getBudget());
    }

    @Test
    void setAndGetSprintDuration() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setSprintDuration(sprintDuration);
        //Assert
        assertEquals(sprintDuration, project.getSprintDuration());
    }

    @Test
    void setAndGetStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setStartDate(startDate);
        //Assert
        assertEquals(startDate, project.getStartDate());
    }

    @Test
    void setAndGetEndDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setEndDate(endDate);
        //Assert
        assertEquals(endDate, project.getEndDate());
    }

    @Test
    void setAndGetEndDateNull() {
        //Arrange
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate date = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setEndDate(null);
        boolean expected = project.isActiveInThisDate(date);
        //Assert
        assertNull(project.getEndDate());
        assertTrue(expected);
    }

    @Test
    void setAndGetEndDateEqualDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate date = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setEndDate(LocalDate.now());
        boolean expected = project.isActiveInThisDate(date);
        //Assert
        assertTrue(expected);
    }

    @Test
    void setAndGetEndDateAfterDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate date = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setEndDate(LocalDate.now().plusDays(10));
        boolean expected = project.isActiveInThisDate(date);
        //Assert
        assertTrue(expected);
    }

    @Test
    void isActiveInThisDateWithStartDateAfter() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        //Act
        boolean expected = project.isActiveInThisDate(LocalDate.now().minusDays(1));
        //Assert
        assertFalse(expected);
    }

    @Test
    void isActiveInThisDateWithStartDateEqual() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        //Act
        boolean expected = project.isActiveInThisDate(LocalDate.now());
        //Assert
        assertTrue(expected);
    }

    @Test
    void isActiveInThisDateWithEndDateBefore() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        project.setEndDate(LocalDate.now().plusDays(10));
        //Act
        boolean expected = project.isActiveInThisDate(LocalDate.now().plusDays(20));
        //Assert
        assertFalse(expected);
    }

    @Test
    void isActiveInThisDateWithEndDateAfter() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        //Act
        project.setEndDate(LocalDate.now().plusDays(20));
        boolean expected = project.isActiveInThisDate(LocalDate.now().plusDays(10));
        //Assert
        assertTrue(expected);
    }

    @Test
    void isActiveInThisDateTrue() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        Project project = new Project(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        //Act
        project.setEndDate(LocalDate.now().plusDays(10));
        boolean expected = project.isActiveInThisDate(LocalDate.now().plusDays(10));
        //Assert
        assertTrue(expected);
    }


}