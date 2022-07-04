package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.TaskService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class TaskControllerTest {

    @MockBean
    TaskService taskService;

    @InjectMocks
    TaskController taskController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasksCatchException() {
        //Arrange
        doThrow(IllegalArgumentException.class).when(taskService).getAllTasks();
        //Act
        ResponseEntity<?> response = taskController.getAll();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }
}