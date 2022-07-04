package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.PartialProjectDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectMapperTest {

    @Test
    @DisplayName("Transform newProject into DTO")
    public void ToDtoNewProject() {
        //Arrange
        ProjectMapper projectMapper = new ProjectMapper();
        Project project = mock(Project.class);
        ProjectID projectID = mock(ProjectID.class);
        Description name = mock(Description.class);
        Description description = mock(Description.class);
        BusinessSector businessSector = mock(BusinessSector.class);
        Description bs = mock(Description.class);
        NumberOfSprints numberOfSprints = mock(NumberOfSprints.class);
        Budget budget = mock(Budget.class);
        SprintDuration duration = mock(SprintDuration.class);

        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2021_2");
        when(project.getProjectName()).thenReturn(name);
        when(name.getText()).thenReturn("Make test");
        when(project.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("make DS");
        when(project.getBusinessSector()).thenReturn(businessSector);
        when(businessSector.getDescription()).thenReturn(bs);
        when(bs.getText()).thenReturn("Medical");
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(numberOfSprints.getNumberOfSprintsVO()).thenReturn(1);
        when(project.getProjectStatus()).thenReturn(ProjectStatusEnum.CLOSED);
        when(project.getBudget()).thenReturn(budget);
        when(budget.getBudgetVO()).thenReturn(5.0);
        when(project.getStartDate()).thenReturn(LocalDate.now());
        when(duration.getSprintDurationDays()).thenReturn(1L);
        when(project.getSprintDuration()).thenReturn(duration);

        //Act
        OutputProjectDTO outputProjectDTO = projectMapper.model2Dto(project);

        //Assert
        assertEquals(project.getProjectCode().getCode(), outputProjectDTO.code);
        assertEquals(project.getProjectName().getText(), outputProjectDTO.projectName);
        assertEquals(project.getBusinessSector().getDescription().getText(), outputProjectDTO.businessSector);
        assertEquals(project.getDescription().getText(), outputProjectDTO.description);
    }

    @Test
    @DisplayName("Validate data from outputDTO")
    public void getInfoFromOutPutProjectDTO() {
        //Arrange
        ProjectMapper projectMapper = new ProjectMapper();
        Project project = mock(Project.class);
        ProjectID projectID = mock(ProjectID.class);
        Description name = mock(Description.class);
        Description description = mock(Description.class);
        BusinessSector businessSector = mock(BusinessSector.class);
        Description bs = mock(Description.class);
        NumberOfSprints numberOfSprints = mock(NumberOfSprints.class);
        Budget budget = mock(Budget.class);
        SprintDuration duration = mock(SprintDuration.class);

        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2021_2");
        when(project.getProjectName()).thenReturn(name);
        when(name.getText()).thenReturn("Make test");
        when(project.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("make DS");
        when(project.getBusinessSector()).thenReturn(businessSector);
        when(businessSector.getDescription()).thenReturn(bs);
        when(bs.getText()).thenReturn("Medical");
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(numberOfSprints.getNumberOfSprintsVO()).thenReturn(1);
        when(project.getProjectStatus()).thenReturn(ProjectStatusEnum.CLOSED);
        when(project.getBudget()).thenReturn(budget);
        when(budget.getBudgetVO()).thenReturn(5.0);
        when(project.getStartDate()).thenReturn(LocalDate.now());
        when(duration.getSprintDurationDays()).thenReturn(1L);
        when(project.getSprintDuration()).thenReturn(duration);

        //Act
        OutputProjectDTO outputProjectDTO = projectMapper.model2Dto(project);

        //Assert
        assertEquals(outputProjectDTO.getProjectName(), "Make test");
        assertEquals(outputProjectDTO.getCode(), "Project_2021_2");
        assertEquals(outputProjectDTO.getDescription(), "make DS");
        assertEquals(outputProjectDTO.getBusinessSector(), "Medical");
    }


    @Test
    @DisplayName("Transform newProject into DTO")
    public void ToDto2NewProject() {
        //Arrange
        ProjectMapper projectMapper = new ProjectMapper();
        Project project = mock(Project.class);
        ProjectID projectID = mock(ProjectID.class);
        Description name = mock(Description.class);
        Description description = mock(Description.class);
        BusinessSector businessSector = mock(BusinessSector.class);
        Description bs = mock(Description.class);
        NumberOfSprints numberOfSprints = mock(NumberOfSprints.class);
        Budget budget = mock(Budget.class);
        SprintDuration duration = mock(SprintDuration.class);

        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2021_2");
        when(project.getProjectName()).thenReturn(name);
        when(name.getText()).thenReturn("Make test");
        when(project.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("make DS");
        when(project.getBusinessSector()).thenReturn(businessSector);
        when(businessSector.getDescription()).thenReturn(bs);
        when(bs.getText()).thenReturn("Medical");
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(numberOfSprints.getNumberOfSprintsVO()).thenReturn(1);
        when(project.getProjectStatus()).thenReturn(ProjectStatusEnum.CLOSED);
        when(project.getBudget()).thenReturn(budget);
        when(budget.getBudgetVO()).thenReturn(5.0);
        when(project.getStartDate()).thenReturn(LocalDate.now());
        when(duration.getSprintDurationDays()).thenReturn(1L);
        when(project.getSprintDuration()).thenReturn(duration);

        //Act
        PartialProjectDTO partialProjectDTO = projectMapper.model2Dto2(project);

        //Assert
        assertEquals(project.getProjectCode().getCode(), partialProjectDTO.code);
        assertEquals(project.getProjectName().getText(), partialProjectDTO.projectName);
        assertEquals(project.getStartDate().toString(), partialProjectDTO.startDate);
        assertEquals(project.getDescription().getText(), partialProjectDTO.description);
        assertEquals(project.getProjectStatus().name(), partialProjectDTO.projectStatus);
    }

    @Test
    @DisplayName("Transform newProject into DTO")
    public void ToCollection2NewProject() {
        //Arrange
        ProjectMapper projectMapper = new ProjectMapper();
        Project project = mock(Project.class);
        ProjectID projectID = mock(ProjectID.class);
        Description name = mock(Description.class);
        Description description = mock(Description.class);
        BusinessSector businessSector = mock(BusinessSector.class);
        Description bs = mock(Description.class);
        NumberOfSprints numberOfSprints = mock(NumberOfSprints.class);
        Budget budget = mock(Budget.class);
        SprintDuration duration = mock(SprintDuration.class);
        List<Project> projects = new ArrayList<>();
        projects.add(project);

        when(project.getProjectCode()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2021_2");
        when(project.getProjectName()).thenReturn(name);
        when(name.getText()).thenReturn("Make test");
        when(project.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("make DS");
        when(project.getBusinessSector()).thenReturn(businessSector);
        when(businessSector.getDescription()).thenReturn(bs);
        when(bs.getText()).thenReturn("Medical");
        when(project.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(numberOfSprints.getNumberOfSprintsVO()).thenReturn(1);
        when(project.getProjectStatus()).thenReturn(ProjectStatusEnum.CLOSED);
        when(project.getBudget()).thenReturn(budget);
        when(budget.getBudgetVO()).thenReturn(5.0);
        when(project.getStartDate()).thenReturn(LocalDate.now());
        when(duration.getSprintDurationDays()).thenReturn(1L);
        when(project.getSprintDuration()).thenReturn(duration);
        //Act
        CollectionModel<PartialProjectDTO> result = projectMapper.toCollectionDto2(projects, false);
        //Assert
        assertEquals(1, result.getContent().size());
    }
}
