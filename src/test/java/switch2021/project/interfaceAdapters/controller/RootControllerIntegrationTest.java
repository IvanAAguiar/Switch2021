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
import switch2021.project.dtoModel.dto.LoginDto;
import switch2021.project.dtoModel.dto.TypologyDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RootControllerIntegrationTest {

    public static final String BASE_URL = "https://localhost:8443";

    @Autowired
    RootController ctrl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @SneakyThrows
    @Test
    void test() {
        //Arrange
        LoginDto dto = new LoginDto();
        dto.email = "jsz@mymail.com";
        dto.password = "Qwerty1!";
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //Assert
        assertEquals(200,result.getResponse().getStatus());
    }

    @SneakyThrows
    @Test
    void testFail() {
        //Arrange
        LoginDto dto = new LoginDto();
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        //Assert
        assertEquals(400,result.getResponse().getStatus());
    }
}