package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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
import switch2021.project.applicationServices.service.ResourceService;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.DefineRoleOfResourceDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ResourceControllerIntegrationTest {

    public static final String BASE_URL = "https://localhost:8443";

   @Autowired
   ResourceController ctrl;

   @Autowired
   MockMvc mockMvc;

   @Autowired
   ObjectMapper objectMapper;

   @Autowired
   ResourceService service;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }

    @SneakyThrows
    @Test
    void createResourceSuccess() {
        //Arrange
        CreateResourceDTO dto = new CreateResourceDTO("asz@mymail.com", "Project_2022_3", "TeamMember",
                "2022-03-12", "2023-01-12", 500, 0.1);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/resources")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

    @SneakyThrows
    @Test
    void createResourceFailAllocation() {
        //Arrange
        CreateResourceDTO dto = new CreateResourceDTO("tdc@mymail.com", "Project_2022_3", "TeamMember",
                "2022-03-10", "2023-01-12", 500, 0.1);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/resources")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
        //Act
        String body = result.getResponse().getContentAsString();
        assertTrue(body.contains("\"errorMessage\":\"Is not valid to create - Allocation\""));
    }

    @SneakyThrows
    @Test
    void createResourceFailDates() {
        //Arrange
        CreateResourceDTO dto = new CreateResourceDTO("tdc@mymail.com", "Project_2022_3", "TeamMember",
                "2020-03-10", "2023-01-12", 500, 0.1);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/resources")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
        //Act
        String body = result.getResponse().getContentAsString();
        assertTrue(body.contains("\"errorMessage\":\"Dates are not inside project!\""));
    }

    @SneakyThrows
    @Test
    void createResourceFailProjectNotExist() {
        //Arrange
        CreateResourceDTO dto = new CreateResourceDTO("tdc@mymail.com", "Project_2022_53", "TeamMember",
                "2020-03-10", "2023-01-12", 500, 0.1);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/resources")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
        //Act
        String body = result.getResponse().getContentAsString();
        assertTrue(body.contains("\"errorMessage\":\"Project does not exist!\""));
    }

    @SneakyThrows
    @Test
    void defineProjectRole() {
        //Arrange
        DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO("ProductOwner", "2022-03-12", "2023-01-12", 500, 0.2);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/resources/nel.m@mymail.com&Project_2022_3&2022-03-07")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @SneakyThrows
    @Test
    void defineProjectRoleFalse() {
        //Arrange
        DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO("ProductOwner", "2022-03-12", "2023-01-12", 500, 1.1);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/resources/nel.m@mymail.com&Project_2022_3&2022-03-07")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void deleteResource() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(BASE_URL + "/resources/nel.m@mymail.com&Project_2022_3&2022-03-07")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void deleteResourceFail() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(BASE_URL + "/resources/nel.m@mymail.com&Project_2022_3&2022-03-09")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void findProjectRoles() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/resources/roles")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }
    @SneakyThrows
    @Test
    void getProjectTeamTrue() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/resources/registerOfResources/Project_2022_3")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void getProjectTeamFail() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/resources/registerOfResources/Project_2022_999999")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void getCurrentProjectTeamTrue() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/resources/currentProjectTeam/Project_2022_3")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void getCurrentProjectTeamFail() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/resources/currentProjectTeam/Project_2022_999999")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void getCurrentProjectTeamTrue2() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/resources/currentprojectTeam/Project_2022_3")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void getCurrentProjectTeamFail2() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/resources/currentprojectTeam/Project_2022_999999")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }

    @SneakyThrows
    @Test
    void showAllResourcesTrue() {
        //Arrange

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/resources")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String x = result.getResponse().getContentAsString();

        assertNotNull(x);
    }


}