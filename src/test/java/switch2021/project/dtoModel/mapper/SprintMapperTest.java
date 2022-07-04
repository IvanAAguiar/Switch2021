package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.OutSprintDTO;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.ProjectID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SprintMapperTest {

    @Test
    @DisplayName("toDTO test")
    public void toDTOSuccess() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");

        OutputSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);

        //Assert
        assertEquals(sprint.getSprintID().toString(), outPutSprintDTO.sprintID);
    }

    @Test
    @DisplayName("toDTO test, SprintID")
    public void toDTOSuccess_SprintID() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        OutputSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);
        //Assert
        assertEquals(sprintID.toString(), outPutSprintDTO.sprintID);
    }

    @Test
    @DisplayName("toDTO test, get Sprint Name")
    public void toDTOSuccess_SprintName() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        OutputSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);
        //Assert
        assertEquals(description.getText(), outPutSprintDTO.name);
    }

    @Test
    @DisplayName("toDTO test, get ProjectID")
    public void toDTOSuccess_ProjectID() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        OutputSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);
        //Assert
        assertEquals(projectID.getCode(), outPutSprintDTO.projectID);
    }

    @Test
    @DisplayName("Test OutputSprintDTO - SprintID")
    public void outPutSprintDTO_SprintID() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);

        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");

        OutputSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);

        //Assert
        assertEquals(outPutSprintDTO.getSprintID(), sprintID.toString());
    }

    @Test
    @DisplayName("Test OutputSprintDTO - ProjectID")
    public void outPutSprintDTO_ProjectID() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);

        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");

        OutputSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);

        //Assert
        assertEquals(outPutSprintDTO.getProjectID(), "Project_2022_1");
    }

    @Test
    @DisplayName("Test OutputSprintDTO - Sprint Name")
    public void outPutSprintDTO_SprintName() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);

        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");

        OutputSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);

        //Assert
        assertEquals(outPutSprintDTO.getName(), "Sprint Name");
    }

    @Test
            public void toCollection() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        List<Sprint> sprints = new ArrayList<>();
        sprints.add(sprint);
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        //Act
        CollectionModel<OutputSprintDTO> outPutSprintDTO = sprintMapper.toCollectionDto(sprints);
        //Assert
        assertEquals(1, outPutSprintDTO.getContent().size());
    }

    @Test
    public void toCollections() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        List<Sprint> sprints = new ArrayList<>();
        sprints.add(sprint);
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        //Act
        CollectionModel<OutSprintDTO> outSprintDTO = sprintMapper.toCollectionsDto(sprints);
        //Assert
        assertEquals(1, outSprintDTO.getContent().size());
    }
}
