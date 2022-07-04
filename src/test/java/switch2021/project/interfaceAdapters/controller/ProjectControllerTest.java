package switch2021.project.interfaceAdapters.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.ProjectService;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.Budget;

import javax.net.ssl.SSLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    ProjectController ctrl;

    @MockBean
    ProjectService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProjectSuccess() throws SSLException {
        //Arrange
        Map<String, CollectionModel<PartialProjectDTO>> allProjectsDto = new HashMap<>();
        when(service.getAllProjects()).thenReturn(allProjectsDto);
        //Act
        ResponseEntity<?> response = ctrl.getAllProjects();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getAllProjectCatchException() throws SSLException {
        //Arrange
        doThrow(IllegalArgumentException.class).when(service).getAllProjects();
        //Act
        ResponseEntity<?> response = ctrl.getAllProjects();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void getProjectRequestedSuccess() {
        //Arrange
        OutputProjectDTO test = mock(OutputProjectDTO.class);
        String x = "1";
        when(test.getCode()).thenReturn(x);
        when(service.showProject(x)).thenReturn(test);
        //Act
        ResponseEntity<?> response = ctrl.showProjectRequested(x);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getProjectRequestedCatchException() {
        //Arrange
        when(service.showProject(anyString())).thenThrow(Exception.class);
        //Act
        ResponseEntity<?> response = ctrl.showProjectRequested("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @SneakyThrows
    @Test
    void testCreateProject() {
        //Arrange
        ProjectDTO test = mock(ProjectDTO.class);
        OutputProjectDTO outTest = mock(OutputProjectDTO.class);
        when(service.createAndSaveProject(test)).thenReturn(outTest);
        //Act
        ResponseEntity<?> response = ctrl.createProject(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @SneakyThrows
    @Test
    void testCreateProjectException() {
        //Arrange
        ProjectDTO test = mock(ProjectDTO.class);
        when(service.createAndSaveProject(test)).thenThrow(Exception.class);
        //Act
        ResponseEntity<?> response = ctrl.createProject(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testUpdateProject() {
        //Arrange
        EditProjectInfoDTO test = mock(EditProjectInfoDTO.class);
        OutputProjectDTO outTest = mock(OutputProjectDTO.class);
        when(service.updateProjectPartially("1", test)).thenReturn(outTest);
        //Act
        ResponseEntity<?> response = ctrl.updateProjectPartially("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testUpdateProjectException() {
        //Arrange
        EditProjectInfoDTO test = mock(EditProjectInfoDTO.class);
        Project project = mock(Project.class);
        Budget budget = mock(Budget.class);
        doThrow(IllegalArgumentException.class).when(project).setBudget(budget);
        when(service.updateProjectPartially(any(), any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.updateProjectPartially("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testDeleteProject() {
        //Arrange
        ProjectDTO projDto = mock(ProjectDTO.class);
        projDto.code = "1";
        when(service.deleteProjectRequest(projDto.code)).thenReturn(true);
        //Act
        ResponseEntity<?> response = ctrl.deleteProjectRequest("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void testDeleteProjectException() {
        //Arrange
        String projId = "1";
        doThrow(IllegalArgumentException.class).when(service).deleteProjectRequest(projId);
        //Act
        ResponseEntity<?> response = ctrl.deleteProjectRequest("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @SneakyThrows
    @Test
    void testShowCurrentProjectByUser() {
        //Arrange
        String email = "jsz@mymail.com";
        when(service.getCurrentProjectsByUser(email)).thenReturn(CollectionModel.empty());
        //Act
        ResponseEntity<?> response = ctrl.getCurrentProjectsByUser(email);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void testShowCurrentProjectByUserException() {
        //Arrange
        String projId = "1";
        doThrow(IllegalArgumentException.class).when(service).getCurrentProjectsByUser(projId);
        //Act
        ResponseEntity<?> response = ctrl.getCurrentProjectsByUser("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void getProjectStatusSuccess() {
        //Arrange
        OutputStatusDTO statusDTO = mock(OutputStatusDTO.class);
        List<OutputStatusDTO> dtos = new ArrayList<>();
        dtos.add(statusDTO);
        when(service.getProjectStatus()).thenReturn(CollectionModel.of(dtos));
        //Act
        ResponseEntity<Object> result = ctrl.getProjectStatus();
        //Assert
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getProjectStatusFail() {
        //Arrange
        doThrow(IllegalArgumentException.class).when(service).getProjectStatus();
        //Act
        ResponseEntity<Object> result = ctrl.getProjectStatus();
        //Assert
        assertThat(result.getStatusCodeValue()).isEqualTo(404);
    }
}
