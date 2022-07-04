package switch2021.project.dataModel.REST.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.REST.ProjectRestDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.voFactories.voFactories.*;
import switch2021.project.entities.valueObjects.vos.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserProfileDomainDataRestAssemblerTest {

    @MockBean
    private DescriptionFactory descriptionFactory;
    @MockBean
    private BusinessSectorFactory businessSectorFactory;
    @MockBean
    private NumberOfSprintsFactory numberOfSprintsFactory;
    @MockBean
    private SprintDurationFactory sprintDurationFactory;
    @MockBean
    private BudgetFactory budgetFactory;
    @InjectMocks
    private ProjectDomainDataRestAssembler assembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToDomain() {
        //Arrange
        ProjectRestDTO dto = mock(ProjectRestDTO.class);
        when(dto.getProjectCode()).thenReturn("Project_2022_1");
        when(dto.getProjectName()).thenReturn("ProjectName");
        when(dto.getProjectDescription()).thenReturn("ProjectDescription");
        when(dto.getProjectBusinessSector()).thenReturn("ProjectBusinessSector");
        when(dto.getTypologyDescription()).thenReturn("typologyDescription");
        when(dto.getCustomerName()).thenReturn("customerName");
        when(dto.getStartDate()).thenReturn("12/12/2022");
        when(dto.getEndDate()).thenReturn("12/12/2023");
        when(dto.getProjectNumberOfPlannedSprints()).thenReturn("30");
        when(dto.getProjectBudget()).thenReturn("50000");
        when(dto.getStatus()).thenReturn("status");
        when(dto.getProjectSprintDuration()).thenReturn("7");
        Description name = mock(Description.class);
        Description description = mock(Description.class);
        BusinessSector businessSector = mock(BusinessSector.class);
        NumberOfSprints numberOfSprints = mock(NumberOfSprints.class);
        SprintDuration sprintDuration = mock(SprintDuration.class);
        Budget budget = mock(Budget.class);
        when(descriptionFactory.createDescription(dto.getProjectName())).thenReturn(name);
        when(descriptionFactory.createDescription(dto.getProjectDescription())).thenReturn(description);
        when(businessSectorFactory.createBusinessSector(dto.getProjectBusinessSector())).thenReturn(businessSector);
        when(numberOfSprintsFactory.create(Integer.parseInt(dto.getProjectNumberOfPlannedSprints())))
                .thenReturn(numberOfSprints);
        when(numberOfSprints.getNumberOfSprintsVO()).thenReturn(3);
        when(sprintDurationFactory.create(Integer.parseInt(dto.getProjectSprintDuration()))).thenReturn(sprintDuration);
        when(budgetFactory.create(Double.parseDouble(dto.getProjectBudget()))).thenReturn(budget);
        List<ProjectRestDTO> dtos = new ArrayList<>();
        dtos.add(dto);
        //Act
        List<Project> projects = assembler.toDomain(dtos);
        //Assert
        assertEquals(1, projects.size());
    }

    @Test
    void testToDomainSprintDurationException() {
        //Arrange
        ProjectRestDTO dto = mock(ProjectRestDTO.class);
        when(dto.getProjectCode()).thenReturn("Project_2022_1");
        when(dto.getProjectName()).thenReturn("ProjectName");
        when(dto.getProjectDescription()).thenReturn("ProjectDescription");
        when(dto.getProjectBusinessSector()).thenReturn("ProjectBusinessSector");
        when(dto.getTypologyDescription()).thenReturn("typologyDescription");
        when(dto.getCustomerName()).thenReturn("customerName");
        when(dto.getStartDate()).thenReturn("12/12/2022");
        when(dto.getEndDate()).thenReturn("12/12/2023");
        when(dto.getProjectNumberOfPlannedSprints()).thenReturn("30");
        when(dto.getProjectBudget()).thenReturn("50000");
        when(dto.getStatus()).thenReturn("status");
        when(dto.getProjectSprintDuration()).thenReturn("7");
        Description name = mock(Description.class);
        Description description = mock(Description.class);
        BusinessSector businessSector = mock(BusinessSector.class);
        NumberOfSprints numberOfSprints = mock(NumberOfSprints.class);
        Budget budget = mock(Budget.class);
        when(descriptionFactory.createDescription(dto.getProjectName())).thenReturn(name);
        when(descriptionFactory.createDescription(dto.getProjectDescription())).thenReturn(description);
        when(businessSectorFactory.createBusinessSector(dto.getProjectBusinessSector())).thenReturn(businessSector);
        when(numberOfSprintsFactory.create(Integer.parseInt(dto.getProjectNumberOfPlannedSprints())))
                .thenReturn(numberOfSprints);
        when(sprintDurationFactory.create(Integer.parseInt(dto.getProjectSprintDuration())))
                .thenThrow(NullPointerException.class);
        when(budgetFactory.create(Double.parseDouble(dto.getProjectBudget()))).thenReturn(budget);
        List<ProjectRestDTO> dtos = new ArrayList<>();
        dtos.add(dto);
        //Act
        List<Project> projects = assembler.toDomain(dtos);
        //Assert
        assertEquals(1, projects.size());
    }
}


