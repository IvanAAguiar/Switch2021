package switch2021.project.entities.factories.factories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockStatic;

/**
 * System Under Test (SUT) - Project.class and ProjectFactory.class
 */

@ExtendWith(MockitoExtension.class)
class ProjectFactoryTest {

    @Mock
    private IDescriptionFactory nameF;
    @Mock
    private IDescriptionFactory descF;
    @Mock
    private IBusinessSectorFactory busSecF;
    @Mock
    private INumberOfSprintsFactory numberSprintsF;
    @Mock
    private ISprintDurationFactory sprintDurationF;
    @Mock
    private IBudgetFactory budgetF;
    @Mock
    private ITypologyIDFactory typologyIDFactory;
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
    @Mock
    ProjectDTO dto;

    private static MockedStatic<Customer> customerClass;

    @InjectMocks
    ProjectFactory factory;

    @BeforeEach
    void init(){
        customerClass = mockStatic(Customer.class);

        String desc = "any text";
        String date = "2022-06-01";
        String number = "1";
        when(dto.getProjectName()).thenReturn(desc);
        when(dto.getDescription()).thenReturn(desc);
        when(dto.getBusinessSector()).thenReturn(desc);
        when(dto.getStartDate()).thenReturn(date);
        when(dto.getNumberOfSprints()).thenReturn(number);
        when(dto.getSprintDuration()).thenReturn(number);
        when(dto.getBudget()).thenReturn(number);
        when(dto.getTypology()).thenReturn(desc);
        when(dto.getCustomer()).thenReturn(desc);
        when(dto.getEndDate()).thenReturn(date);

        when(nameF.createDescription(desc)).thenReturn(projectName);
        when(descF.createDescription(desc)).thenReturn(description);
        when(busSecF.createBusinessSector(desc)).thenReturn(businessSector);
        when(numberSprintsF.create(Integer.parseInt(number))).thenReturn(numberOfSprints);
        when(sprintDurationF.create(Integer.parseInt(number))).thenReturn(sprintDuration);
        when(budgetF.create(Integer.parseInt(number))).thenReturn(budget);
        when(typologyIDFactory.createIdWithString(desc)).thenReturn(typology);
        customerClass.when(() -> Customer.create(desc)).thenReturn(customer);

    }

    @AfterEach
    void close() {
        customerClass.close();
    }

    @Test
    void hasCodeTrue() {
        //Arrange
        when(projectID.getCode()).thenReturn("Project_2022_1");
        //Act
        Project newProject = factory.createProject(dto);
        newProject.setProjectCode(projectID);
        //Assert
        assertTrue(newProject.hasCode("Project_2022_1"));
    }


    @Test
    void hasCodeFalse() {
        //Arrange
        when(projectID.getCode()).thenReturn("Project_2022_1");
        //Act
        Project newProject = factory.createProject(dto);
        newProject.setProjectCode(projectID);
        //Assert
        assertFalse(newProject.hasCode("testX"));
    }


    @Test
    void overrideTest() {
        //Arrange
        ProjectID projectID2 = mock(ProjectID.class);
        //Act
        Project newProject1 = factory.createProject(dto);
        newProject1.setProjectCode(projectID);

        Project newProject2 = factory.createProject(dto);
        newProject2.setProjectCode(projectID2);

        Project newProject3 = factory.createProject(dto);
        newProject3.setProjectCode(projectID);

        //Assert
        assertEquals(newProject1, newProject3);
        assertNotEquals(newProject1, newProject2);
        assertNotEquals(newProject3, newProject2);
    }


    @Test
    void testHashCode() {
        //Arrange
        ProjectID projectID2 = mock(ProjectID.class);
        //Act
        Project newProject1 = factory.createProject(dto);
        newProject1.setProjectCode(projectID);

        Project newProject2 = factory.createProject(dto);
        newProject2.setProjectCode(projectID2);

        Project newProject3 = factory.createProject(dto);
        newProject3.setProjectCode(projectID);
        //Assert
        assertEquals(newProject1.hashCode(), newProject3.hashCode());
        assertNotEquals(newProject1.hashCode(), newProject2.hashCode());
    }

    @Test
    void setAndGetProjectName() {
        //Arrange and Act
        Project newProject1 = factory.createProject(dto);
        newProject1.setProjectName(projectName);
        //Assert
        assertEquals(projectName, newProject1.getProjectName());
    }

    @Test
    void setAndGetDescription() {
        //Arrange and Act
        Project newProject1 = factory.createProject(dto);
        newProject1.setDescription(description);
        //Assert
        assertEquals(description, newProject1.getDescription());
    }

    @Test
    void setAndGetTypology() {
        //Arrange and Act
        Project newProject1 = factory.createProject(dto);
        newProject1.setTypologyId(typology);
        //Assert
        assertEquals(typology, newProject1.getTypologyId());
    }

    @Test
    void setAndGetProjectStatus() {
        //Arrange and Act
        Project newProject1 = factory.createProject(dto);
        newProject1.setProjectStatus(ProjectStatusEnum.PLANNED);
        //Assert
        assertEquals(ProjectStatusEnum.PLANNED, newProject1.getProjectStatus());
    }

    @Test
    void setAndGetCustomer() {
        //Arrange and Act
        Project newProject1 = factory.createProject(dto);
        newProject1.setCustomer(customer);
        //Assert
        assertEquals(customer, newProject1.getCustomer());
    }

    @Test
    void setAndGetBusinessSector() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        project.setBusinessSector(businessSector);
        //Assert
        assertEquals(businessSector, project.getBusinessSector());
    }

    @Test
    void setAndGetNumberOfSprints() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        project.setNumberOfSprints(numberOfSprints);
        //Assert
        assertEquals(numberOfSprints, project.getNumberOfSprints());
    }

    @Test
    void setAndGetBudget() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        project.setBudget(budget);
        //Assert
        assertEquals(budget, project.getBudget());
    }

    @Test
    void setAndGetSprintDuration() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        project.setSprintDuration(sprintDuration);
        //Assert
        assertEquals(sprintDuration, project.getSprintDuration());
    }

    @Test
    void setAndGetStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = factory.createProject(dto);
        project.setStartDate(startDate);
        //Assert
        assertEquals(startDate, project.getStartDate());
    }

    @Test
    void setAndGetEndDate() {
        //Arrange
        LocalDate endDate = LocalDate.now();
        //Act
        Project project = factory.createProject(dto);
        project.setEndDate(endDate);
        //Assert
        assertEquals(endDate, project.getEndDate());
    }

    @Test
    void setAndGetEndDateNull() {
        //Arrange
        LocalDate date = LocalDate.now();
        //Act
        Project project = factory.createProject(dto);
        project.setEndDate(null);
        boolean expected = project.isActiveInThisDate(date);
        //Assert
        assertNull(project.getEndDate());
        assertTrue(expected);
    }

    @Test
    void setAndGetEndDateEqualDate() {
        //Arrange
        LocalDate date = LocalDate.now();
        //Act
        Project project = factory.createProject(dto);
        project.setEndDate(LocalDate.now());
        boolean expected = project.isActiveInThisDate(date);
        //Assert
        assertTrue(expected);
    }

    @Test
    void setAndGetEndDateAfterDate() {
        //Arrange
        LocalDate date = LocalDate.now();
        //Act
        Project project = factory.createProject(dto);
        project.setEndDate(LocalDate.now().plusDays(10));
        boolean expected = project.isActiveInThisDate(date);
        //Assert
        assertTrue(expected);
    }

    @Test
    void isActiveInThisDateWithStartDateAfter() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        boolean expected = project.isActiveInThisDate(LocalDate.of(2022, 5, 1));
        //Assert
        assertFalse(expected);
    }

    @Test
    void isActiveInThisDateWithStartDateEqual() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        boolean expected = project.isActiveInThisDate(LocalDate.of(2022, 6, 1));
        //Assert
        assertTrue(expected);
    }

    @Test
    void isActiveInThisDateWithEndDateBefore() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        project.setEndDate(LocalDate.now().plusDays(10));
        //Act
        boolean expected = project.isActiveInThisDate(LocalDate.now().plusDays(20));
        //Assert
        assertFalse(expected);
    }

    @Test
    void isActiveInThisDateWithEndDateAfter() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        project.setEndDate(LocalDate.now().plusDays(20));
        boolean expected = project.isActiveInThisDate(LocalDate.now().plusDays(10));
        //Assert
        assertTrue(expected);
    }

    @Test
    void isActiveInThisDateTrue() {
        //Arrange and Act
        Project project = factory.createProject(dto);
        project.setEndDate(LocalDate.now().plusDays(10));
        boolean expected = project.isActiveInThisDate(LocalDate.now().plusDays(10));
        //Assert
        assertTrue(expected);
    }


}