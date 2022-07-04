package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.applicationServices.iRepositories.*;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.dtoModel.mapper.TaskMapper;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.factories.factoryInterfaces.ITaskFactory;
import switch2021.project.entities.valueObjects.vos.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @MockBean
    TaskMapper taskMapper;
    @MockBean
    ITaskRepo taskRepo;
    @MockBean
    IResourceRepo resourceRepo;
    @MockBean
    ISprintRepo sprintRepo;
    @MockBean
    IUserStoryRepo userStoryRepo;
    @MockBean
    ITaskFactory taskFactory;
    @InjectMocks
    TaskService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateASprintTask() {
        //Arrange
        TaskDTO inputDTO = mock(TaskDTO.class);
        Task newTask = mock(Task.class);
        when(taskFactory.createTask(inputDTO)).thenReturn(newTask);

        TaskID taskID = mock(TaskID.class);
        when(newTask.getTaskID()).thenReturn(taskID);
        when(taskID.toString()).thenReturn("Project_2022_3&sprint6&task1");
        when(taskRepo.existsById("Project_2022_3&sprint6&task1")).thenReturn(false);

        ResourceID resourceID = mock(ResourceID.class);
        when(newTask.getResponsible()).thenReturn(resourceID);
        when(resourceRepo.existsById(resourceID)).thenReturn(true);

        SprintID sprintID = mock(SprintID.class);
        when(taskID.getTaskContainerID()).thenReturn(sprintID);
        Sprint optionalSprint = mock(Sprint.class);
        when(sprintRepo.findBySprintID(sprintID)).thenReturn(Optional.of(optionalSprint));

        Task savedTask = mock(Task.class);
        OutputTaskDTO outputTaskDTO = mock(OutputTaskDTO.class);
        when(taskRepo.save(newTask)).thenReturn(savedTask);
        when(taskMapper.toDto(savedTask)).thenReturn(outputTaskDTO);
        //Act
        OutputTaskDTO result = underTest.createAndSaveTask(inputDTO);
        //Assert
        assertEquals(outputTaskDTO, result);
    }

    @Test
    void shouldCreateAUserStoryTask() {
        //Arrange
        TaskDTO inputDTO = mock(TaskDTO.class);
        Task newTask = mock(Task.class);
        when(taskFactory.createTask(inputDTO)).thenReturn(newTask);

        TaskID taskID = mock(TaskID.class);
        when(newTask.getTaskID()).thenReturn(taskID);
        when(taskID.toString()).thenReturn("Project_2022_3&as want US002&task1");
        when(taskRepo.existsById("Project_2022_3&as want US002&task1")).thenReturn(false);

        ResourceID resourceID = mock(ResourceID.class);
        when(newTask.getResponsible()).thenReturn(resourceID);
        when(resourceRepo.existsById(resourceID)).thenReturn(true);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(taskID.getTaskContainerID()).thenReturn(userStoryID);
        UserStory optionalUserStory = mock(UserStory.class);
        when(userStoryRepo.findByUserStoryId(userStoryID)).thenReturn(Optional.of(optionalUserStory));

        Task savedTask = mock(Task.class);
        OutputTaskDTO outputTaskDTO = mock(OutputTaskDTO.class);
        when(taskRepo.save(newTask)).thenReturn(savedTask);
        when(taskMapper.toDto(savedTask)).thenReturn(outputTaskDTO);
        //Act
        OutputTaskDTO result = underTest.createAndSaveTask(inputDTO);
        //Assert
        assertEquals(outputTaskDTO, result);
    }

    @Test
    void shouldFailToCreateATaskThatAlreadyExists() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TaskDTO inputDTO = mock(TaskDTO.class);
            Task newTask = mock(Task.class);
            when(taskFactory.createTask(inputDTO)).thenReturn(newTask);

            TaskID taskID = mock(TaskID.class);
            when(newTask.getTaskID()).thenReturn(taskID);
            when(taskID.toString()).thenReturn("Project_2022_3&sprint6&task1");
            when(taskRepo.existsById("Project_2022_3&sprint6&task1")).thenReturn(true);
            //Act
            underTest.createAndSaveTask(inputDTO);
        });
    }

    @Test
    void shouldFailToCreateATaskToAFinishedSprint() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TaskDTO inputDTO = mock(TaskDTO.class);
            Task newTask = mock(Task.class);
            when(taskFactory.createTask(inputDTO)).thenReturn(newTask);

            TaskID taskID = mock(TaskID.class);
            when(newTask.getTaskID()).thenReturn(taskID);
            when(taskID.toString()).thenReturn("Project_2022_3&sprint6&task1");
            when(taskRepo.existsById("Project_2022_3&sprint6&task1")).thenReturn(false);

            ResourceID resourceID = mock(ResourceID.class);
            when(newTask.getResponsible()).thenReturn(resourceID);
            when(resourceRepo.existsById(resourceID)).thenReturn(true);

            SprintID sprintID = mock(SprintID.class);
            when(taskID.getTaskContainerID()).thenReturn(sprintID);
            Sprint optionalSprint = mock(Sprint.class);
            when(sprintRepo.findBySprintID(sprintID)).thenReturn(Optional.of(optionalSprint));
            when(optionalSprint.getEndDate()).thenReturn(LocalDate.now());
            //Act
            underTest.createAndSaveTask(inputDTO);
        });
    }

    @Test
    void shouldFailToCreateATaskToAFinishedUserStory() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TaskDTO inputDTO = mock(TaskDTO.class);
            Task newTask = mock(Task.class);
            when(taskFactory.createTask(inputDTO)).thenReturn(newTask);

            TaskID taskID = mock(TaskID.class);
            when(newTask.getTaskID()).thenReturn(taskID);
            when(taskID.toString()).thenReturn("Project_2022_3&as want US002&task1");
            when(taskRepo.existsById("Project_2022_3&as want US002&task1")).thenReturn(false);

            ResourceID resourceID = mock(ResourceID.class);
            when(newTask.getResponsible()).thenReturn(resourceID);
            when(resourceRepo.existsById(resourceID)).thenReturn(true);

            UserStoryID userStoryID = mock(UserStoryID.class);
            when(taskID.getTaskContainerID()).thenReturn(userStoryID);
            UserStory optionalUserStory = mock(UserStory.class);
            when(userStoryRepo.findByUserStoryId(userStoryID)).thenReturn(Optional.of(optionalUserStory));
            when(optionalUserStory.getUsEndDate()).thenReturn(LocalDate.now());
            //Act
            underTest.createAndSaveTask(inputDTO);
        });
    }
}
