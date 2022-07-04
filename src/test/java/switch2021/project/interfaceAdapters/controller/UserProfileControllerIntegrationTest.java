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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.dtoModel.dto.TypologyDTO;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerIntegrationTest {


    public static final String BASE_URL = "https://localhost:8443";

    @Autowired
    UserProfileController userProfileController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


//    @DisplayName("Create User Profile - Visitor")
//    @Test
//    public void shouldCreateUserProfile_Visitor() throws Exception {
//        //Arrange
//        String description = "Visitor";
//
//        UserProfileDTO userProfileDTO = new UserProfileDTO(description);
//        //Act
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.post(BASE_URL + "/profiles")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userProfileDTO))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//        String resultContent = result.getResponse().getContentAsString();
//        //Assert
//        assertTrue(resultContent.contains("userProfileName\":\"Visitor"));
//    }

    @DisplayName("Should not Create User Profile")
    @Test
    public void shouldNotCreateUserProfile() throws Exception {
        //Arrange
        String description = " ";

        TypologyDTO typologyDTO = new TypologyDTO(description);
        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/profiles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(typologyDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

//    @DisplayName("Get User Profile")
//    @Test
//    public void shouldGetUserProfile() throws Exception {
//        //Arrange
//        String id = "Visitor";
//        //Act
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.get(BASE_URL + "/profiles/" + id)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        String resultContent = result.getResponse().getContentAsString();
//        //Assert
//        assertTrue(resultContent.contains("userProfileName\":\"Visitor"));
//    }

    @DisplayName("Should Not Get User Profile")
    @Test
    public void shouldNotGetUserProfile() throws Exception {
        //Arrange
        String id = " ";
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/profiles/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
//
//    @DisplayName("Get All User Profiles")
//    @Test
//    void mockMvcTestShowAllUserProfiles() throws Exception {
//        //Arrange
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.get(BASE_URL + "/profiles")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        //Act
//        String resultContent = result.getResponse().getContentAsString();
//        //Assert
//        assertNotNull(resultContent);
//        assertNull(result.getResponse().getErrorMessage());
//    }

//    @SneakyThrows
//    @DisplayName("Delete User Profile")
//    @Test
//    void mockMvcTestDeleteUserProfile() {
//        //Arrange
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.delete(BASE_URL + "/profiles/Visitor")
//                        .contentType("application/json")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isAccepted())
//                .andReturn();
//        //Act
//        int x = result.getResponse().getStatus();
//        String body = result.getResponse().getContentAsString();
//        //Assert
//        assertEquals(x,202);
//        assertNotNull(body);
//    }
}
