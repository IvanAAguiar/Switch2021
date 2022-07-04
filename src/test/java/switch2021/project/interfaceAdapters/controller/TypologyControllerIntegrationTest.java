package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import switch2021.project.dtoModel.dto.TypologyDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TypologyControllerIntegrationTest {

    public static final String BASE_URL = "https://localhost:8443";

    @Autowired
    TypologyController typologyController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @DisplayName("Create Typology - Fixed Cost")
    @Test
    public void shouldCreateTypology_FixedCost() throws Exception {
        //Arrange
        String description = "Fixed Cost";

        TypologyDTO typologyDTO = new TypologyDTO(description);
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/typologies")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(typologyDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        //Assert
        assertTrue(resultContent.contains("description\":\"Fixed Cost"));
    }

    @DisplayName("Create Typology - Time and Materials")
    @Test
    public void shouldCreateTypology_TimeAndMaterials() throws Exception {
        //Arrange
        String description = "Time and Materials";

        TypologyDTO typologyDTO = new TypologyDTO(description);
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/typologies")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(typologyDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        //Assert
        assertTrue(resultContent.contains("description\":\"Time and Materials"));
    }

    @DisplayName("Should Not Create Typology")
    @Test
    public void shouldNotCreateTypology() throws Exception {
        //Arrange
        String description = " ";

        TypologyDTO typologyDTO = new TypologyDTO(description);
        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/typologies")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(typologyDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @DisplayName("Get Typology")
    @Test
    public void shouldGetTypology() throws Exception {
        //Arrange
        String id = "Fixed cost";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/typologies/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        //Assert
        assertTrue(resultContent.contains("description\":\"Fixed cost"));
    }

    @DisplayName("Should Not Get Typology")
    @Test
    public void shouldNotGetTypology() throws Exception {
        //Arrange
        String id = " ";
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/typologies/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @DisplayName("Get All Typologies")
    @Test
    void mockMvcTestShowAllTypologies() throws Exception {
        //Arrange
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/typologies")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //Act
        String resultContent = result.getResponse().getContentAsString();
        //Assert
        assertNotNull(resultContent);
        assertNull(result.getResponse().getErrorMessage());
    }


    @DisplayName("Delete Typology")
    @Test
    void mockMvcTestDeleteTypology() throws Exception {
        //Arrange
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(BASE_URL + "/typologies/Fixed cost")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()) //Assert
                .andReturn();
        //Act
        String body = result.getResponse().getContentAsString();
        //Assert
        assertTrue(body.contains("\"responseMessage\":\"Typology successfully deleted.\""));
    }

}