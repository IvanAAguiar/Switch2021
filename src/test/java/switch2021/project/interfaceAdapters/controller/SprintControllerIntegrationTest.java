package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.dtoModel.dto.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SprintControllerIntegrationTest {
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
    void shouldCreateSprint() throws Exception {
        //Arrange
        String projectID = "Project_2022_3";
        String name = "sprint16";
        String startDate = "";
        NewSprintDTO sprintDTO = new NewSprintDTO(projectID, name, startDate);
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("\"projectID\":\"Project_2022_3\""));
    }

    @Test
    void shouldCreateSprintWithStartDate() throws Exception {
        //Arrange
        String projectID = "Project_2022_3";
        String name = "sprint17";
        String startDate = "2022-05-09";
        NewSprintDTO sprintDTO = new NewSprintDTO(projectID, name, startDate);
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("\"projectID\":\"Project_2022_3\""));
    }

    @Test
    void shouldNotCreateSprintProjectUnknown() throws Exception {
        //Arrange
        String projectID = "Project_2022_5";
        String name = "sprint6";
        String startDate = "";

        NewSprintDTO newSprintDTO = new NewSprintDTO(projectID, name, startDate);
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newSprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }

    @Test
    void shouldNotCreateSprintThatAlreadyExists() throws Exception {
        //Arrange
        String projectID = "Project_2022_3";
        String name = "sprint6";
        String startDate = "";

        NewSprintDTO newSprintDTO = new NewSprintDTO(projectID, name, startDate);
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newSprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }

    @Test
    void shouldNotCreateSprintThatStartsBeforeTheProject() throws Exception {
        //Arrange
        String projectID = "Project_2022_3";
        String name = "sprint18";
        String startDate = "2022-02-01";

        NewSprintDTO newSprintDTO = new NewSprintDTO(projectID, name, startDate);
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newSprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }

    @Test
    void shouldNotCreateSprintThatStartsAfterTheProjectEnded() throws Exception {
        //Arrange
        String projectID = "Project_2022_2";
        String name = "sprint6";
        String startDate = "2022-05-05";

        NewSprintDTO newSprintDTO = new NewSprintDTO(projectID, name, startDate);
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newSprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }

    @Test
    void shouldAddUserStoryToSprintBacklog() throws Exception {
        //Arrange
        String sprintID = "Project_2022_3&sprint6";
        String projectID = "Project_2022_3";
        String title = "as want US002";

        UserStoryIdDTO userStoryIdDTO = new UserStoryIdDTO();
        userStoryIdDTO.title = title;
        userStoryIdDTO.projectID = projectID;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints/" + sprintID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userStoryIdDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("User story added to sprintbacklog"));
    }

    @Test
    void shouldNotAddUserStoryUnknownToSprintBacklog() throws Exception {
        //Arrange
        String sprintID = "Project_2022_3&sprint6";
        String projectID = "Project_2022_3";
        String title = "as want US006";//don´t exist!

        UserStoryIdDTO userStoryIdDTO = new UserStoryIdDTO();
        userStoryIdDTO.title = title;
        userStoryIdDTO.projectID = projectID;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints/" + sprintID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userStoryIdDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("User story does not exist"));
    }


    @Test
    void shouldGetAllSprints() throws Exception {
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("sprintID\":\"Project_2022_1&4\""));
        assertTrue(resultContent.contains("sprintID\":\"Project_2022_3&sprint6\""));
    }

    @Test
    void shouldGetSprint() throws Exception {
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/Project_2022_1&1")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("sprintID\":\"Project_2022_1&1\""));
    }

    @Test
    void shouldGetSprintsOfAProject() throws Exception {
        //Arrange
        String projectID = "Project_2022_3";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/sprintList/" + projectID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("sprintID\":\"Project_2022_3&sprint6\""));
        assertTrue(resultContent.contains("sprintID\":\"Project_2022_3&sprint12\""));
    }

    @Test
    void shouldNotGetSprintsOfAnUnknownProject() throws Exception {
        //Arrange
        String projectID = "Project_2022_5";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/sprintList/" + projectID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("Project does not exist"));
    }

    @Test
    void shouldGetNoSprintsOfAProjectThatDontHaveSprints() throws Exception {
        //Arrange
        //create new Project
        String projectName = "Dummy 04";
        String description = "Just another dummy project";
        String businessSector = "It doesn't matter";
        String startDate = "2022-06-20";
        String sprintDuration = "14";
        String numberOfSprints = "5";
        String budget = "100000";
        String typology = "Fixed cost";
        String customer = "XPTO, SA";

        ProjectDTO projectDTO = new ProjectDTO(projectName, description, businessSector, startDate, numberOfSprints,
                budget,
                sprintDuration, typology, customer);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/projects")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(projectDTO))
                .accept(MediaType.APPLICATION_JSON));
        //Project created: Project_2022_4
        String projectID = "Project_2022_4";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/sprintList/" + projectID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("Was not created any sprint yet!"));
    }

    @Test
    void shouldGetSprintsOfAProject2() throws Exception {
        //Arrange
        String projectID = "Project_2022_3";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/sprintsList/" + projectID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("sprintID\":\"Project_2022_3&sprint6\""));
        assertTrue(resultContent.contains("sprintID\":\"Project_2022_3&sprint12\""));
    }

    @Test
    void shouldNotGetSprintsOfAnUnknownProject2() throws Exception {
        //Arrange
        String projectID = "Project_2022_5";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/sprintsList/" + projectID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("Project does not exist"));
    }

    @Test
    void shouldGetNoSprintsOfAProjectThatDontHaveSprints2() throws Exception {
        //Arrange
        //create new Project
        String projectName = "Dummy 04";
        String description = "Just another dummy project";
        String businessSector = "It doesn't matter";
        String startDate = "2022-06-20";
        String sprintDuration = "14";
        String numberOfSprints = "5";
        String budget = "100000";
        String typology = "Fixed cost";
        String customer = "XPTO, SA";

        ProjectDTO projectDTO = new ProjectDTO(projectName, description, businessSector, startDate, numberOfSprints,
                budget,
                sprintDuration, typology, customer);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/projects")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(projectDTO))
                .accept(MediaType.APPLICATION_JSON));
        //Project created: Project_2022_4
        String projectID = "Project_2022_4";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/sprintsList/" + projectID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
    }

    @Test
    void shouldDeleteSprint() throws Exception {
        //Arrange
        String sprintID = "Project_2022_3&sprint10";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(BASE_URL + "/sprints/" + sprintID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("Sprint was deleted successfully"));
    }

    @Test
    void shouldNotDeleteSprintUnknown() throws Exception {
        //Arrange
        String sprintID = "Project_2022_3&sprint29";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(BASE_URL + "/sprints/" + sprintID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("Sprint does not exist"));
    }

    @Test
    void shouldNotReturnSprint() throws Exception {
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/Project_2022_3&sprint200")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("Sprint doesnt exist"));
    }

    @Test
    void shouldReturnSprint() throws Exception {
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/Project_2022_1&1")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("sprintID\":\"Project_2022_1&1\""));
    }

   @Test
    void shouldStartASprint() throws Exception {
        //Arrange
        String projID = "Project_2022_3";
        String name = "sprint17";
        String startDate = "";
        NewSprintDTO sprintDTO = new NewSprintDTO(projID, name, startDate);
        //Act
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sprintDTO))
                        .accept(MediaType.APPLICATION_JSON));

        String sprintID = "Project_2022_3&sprint17";
        StartSprintDTO startSprintDTO = new StartSprintDTO("2022-06-30");
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/sprints/" + sprintID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(startSprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("\"sprintID\":\"Project_2022_3&sprint17\""));
    }

    @Test
    void shouldNotStartASprintUnknown() throws Exception {
        //Arrange
        String projID = "Project_2022_3";
        String name = "sprint20";
        String startDate = "";
        NewSprintDTO sprintDTO = new NewSprintDTO(projID, name, startDate);
        //Act
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/sprints")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(sprintDTO))
                .accept(MediaType.APPLICATION_JSON));

        String sprintID = "Project_2022_3&sprint30";
        StartSprintDTO startSprintDTO = new StartSprintDTO("2022-06-30");
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/sprints/" + sprintID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(startSprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("Sprint doesnt exist"));
    }



    @Test
    void shouldShowScrumBoard() throws Exception {
        //Arrange
        String sprintID = "Project_2022_1&4";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/scrumBoard/"+ sprintID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("\"usTitle\":\"as want US15\""));
    }

    @Test
    void shouldNotShowScrumBoardOfUnknownSprint() throws Exception {
        //Arrange
        String sprintID = "Project_2022_1&10";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/sprints/scrumBoard/"+ sprintID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("Sprint does not exist!"));
    }

    @Test
    void shouldChangeStatusScrumBoard() throws Exception {
        //Arrange
        String sprintID = "Project_2022_1&4";

        String usTitle="as want US14";
        String projectId="Project_2022_1";
        String sprintName="4";
        String status="Done";
        UserStoryOfSprintDTO userStoryOfSprintDTO = new UserStoryOfSprintDTO();
        userStoryOfSprintDTO.usTitle=usTitle;
        userStoryOfSprintDTO.projectId=projectId;
        userStoryOfSprintDTO.sprintName=sprintName;
        userStoryOfSprintDTO.status=status;

        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/sprints/scrumBoard/"+ sprintID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userStoryOfSprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("\"status\":\"Done\""));
    }

    @Test
    void shouldNotChangeStatusScrumBoardOfUnknownSprint() throws Exception {
        //Arrange
        String sprintID = "Project_2022_1&10";

        String usTitle="as want US14";
        String projectId="Project_2022_1";
        String sprintName="10";
        String status="Done";
        UserStoryOfSprintDTO userStoryOfSprintDTO = new UserStoryOfSprintDTO();
        userStoryOfSprintDTO.usTitle=usTitle;
        userStoryOfSprintDTO.projectId=projectId;
        userStoryOfSprintDTO.sprintName=sprintName;
        userStoryOfSprintDTO.status=status;

        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/sprints/scrumBoard/"+ sprintID)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userStoryOfSprintDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("Sprint does not exist!"));
    }

}
