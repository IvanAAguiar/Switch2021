package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.dtoModel.dto.TaskEffortDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {
    public static final String BASE_URL = "https://localhost:8443";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String sprintName = "sprint6";
        String usTitle = null;
        String systemUserID = "tdc@mymail.com";
        String resourceStartDate = "2022-03-10";
        String taskTitle = "task1";
        String taskDescription = "First task of Sprint 1";
        double taskEffortEstimate = 10.0;
        String taskType = "MEETING";

        TaskDTO taskDTO = new TaskDTO(projectId, sprintName, usTitle, systemUserID, resourceStartDate, taskTitle,
                taskDescription, taskEffortEstimate, taskType);
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/tasks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("status\":\"PLANNED"));
    }

    @Test
    void shouldNotCreateTask() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String sprintName = "6"; //sprint doesn't exist
        String usTitle = null;
        String systemUserID = "tdc@mymail.com";
        String resourceStartDate = "2022-03-10";
        String taskTitle = "task1";
        String taskDescription = "First task of Sprint 1";
        double taskEffortEstimate = 10.0;
        String taskType = "MEETING";

        TaskDTO taskDTO = new TaskDTO(projectId, sprintName, usTitle, systemUserID, resourceStartDate, taskTitle,
                taskDescription, taskEffortEstimate, taskType);
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/tasks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }

    @Test
    void shouldGetTheTask() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String sprintName = "sprint6";
        String taskTitle = "daily";
        String id = projectId + "&" + sprintName + "&" + taskTitle;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("status\":\"RUNNING"));
    }

    @Test
    void shouldNotGetTheTask() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String sprintName = "6"; //sprint doesn't exist
        String taskTitle = "daily";
        String id = projectId + "&" + sprintName + "&" + taskTitle;
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())//Assert
                .andReturn();
    }

    @Test
    void shouldGetAllTask() throws Exception {
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("taskID\":\"Project_2022_3&sprint6&daily\""));
        assertTrue(resultContent.contains("taskID\":\"Project_2022_3&as want US002&createTaskSD\""));
    }

    @Test
    void shouldGetTheTasksOfASprint() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String sprintName = "sprint6";
        String id = projectId + "&" + sprintName;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/taskContainer/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("taskID\":\"Project_2022_3&sprint6&daily\""));
        assertTrue(resultContent.contains("taskID\":\"Project_2022_3&sprint6&deploy\""));
        assertFalse(resultContent.contains("taskID\":\"Project_2022_3&as want US002&createTaskSD\""));
    }

    @Test
    void shouldGetNoTasksOfASprintThatHasNoTasks() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String sprintName = "sprint12";//has no Tasks
        String id = projectId + "&" + sprintName;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/taskContainer/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("{}"));
    }

    @Test
    void shouldNOTGetTheTasksOfAnUnknownSprint() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String sprintName = "sprint66"; //sprint doesn't exist
        String id = projectId + "&" + sprintName;
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/taskContainer/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }

    @Test
    void shouldGetTheTasksOfAUserStory() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String userStoryTitle = "as want US002";
        String id = projectId + "&" + userStoryTitle;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/taskContainer/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("taskID\":\"Project_2022_3&as want US002&createTaskSD\""));
        assertTrue(resultContent.contains("taskID\":\"Project_2022_3&as want US002&createTask\""));
        assertFalse(resultContent.contains("taskID\":\"Project_2022_3&as want US003&addUS_SD\""));
        assertFalse(resultContent.contains("taskID\":\"Project_2022_3&sprint6&daily\""));
    }

    @Test
    void shouldGetNoTasksOfAUserStoryThatHasNoTasks() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String userStoryTitle = "as want US001";//has no Tasks
        String id = projectId + "&" + userStoryTitle;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/taskContainer/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("{}"));
    }

    @Test
    void shouldNOTGetTheTasksOfAnUnknownUserStory() throws Exception {
        //Arrange
        String projectId = "Project_2022_3";
        String userStoryTitle = "as want US066"; //UserStory doesn't exist
        String id = projectId + "&" + userStoryTitle;
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/tasks/taskContainer/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }

    @Test
    void shouldRegisterEffort() throws Exception {
        //Arrange
        int effortHours = 1;
        int effortMinutes = 15;
        String effortDate = "2022-06-24";
        String comment = "doing";
        String attachment = "note.pdf";
        TaskEffortDTO taskEffortDTO = new TaskEffortDTO(effortHours, effortMinutes, effortDate, comment, attachment);

        String projectId = "Project_2022_3";
        String sprintName = "sprint6";
        String taskTitle = "daily";
        String id = projectId + "&" + sprintName + "&" + taskTitle;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/tasks/" + id + "/registerEffort")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskEffortDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("comment\":\"doing"));
    }

    @Test
    void shouldNotRegisterEffortWithNoTime() throws Exception {
        //Arrange
        int effortHours = 0;
        int effortMinutes = 0;
        String effortDate = "2022-06-24";
        String comment = "doing";
        String attachment = "note.pdf";
        TaskEffortDTO taskEffortDTO = new TaskEffortDTO(effortHours, effortMinutes, effortDate, comment, attachment);

        String projectId = "Project_2022_3";
        String sprintName = "sprint6";
        String taskTitle = "daily";
        String id = projectId + "&" + sprintName + "&" + taskTitle;
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL + "/tasks/" + id + "/registerEffort")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskEffortDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }

    @Test
    void shouldNotRegisterEffortInTheFuture() throws Exception {
        //Arrange
        int effortHours = 0;
        int effortMinutes = 15;
        String effortDate = LocalDate.now().plusDays(1).toString();
        String comment = "doing";
        String attachment = "note.pdf";
        TaskEffortDTO taskEffortDTO = new TaskEffortDTO(effortHours, effortMinutes, effortDate, comment, attachment);

        String projectId = "Project_2022_3";
        String sprintName = "sprint6";
        String taskTitle = "daily";
        String id = projectId + "&" + sprintName + "&" + taskTitle;
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL + "/tasks/" + id + "/registerEffort")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskEffortDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }
}

