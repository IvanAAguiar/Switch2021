package switch2021.project.entities.factories.factories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.applicationServices.iRepositories.ITaskRepo;
import switch2021.project.applicationServices.service.TaskService;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.NewSprintDTO;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.factories.factoryInterfaces.IResourceFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITaskIDFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskFactoryTest {

/*

    @MockBean
    ITaskIDFactory taskIDFactory;

    @MockBean
    Name name;

    @MockBean
    TaskID taskID;

    @MockBean
    Description description;

    @MockBean
    EffortEstimate effort;

    @MockBean
    ResourceID resID;

    @MockBean
    UserStoryID usID;

    @MockBean
    SprintID sprintID;

    @InjectMocks
    private TaskFactory taskFactory;


    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }

@Autowired
private TaskFactory taskFactory;
    @Autowired
    private ITaskRepo taskRepo;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ITaskIDFactory taskIDFactory;
    @Autowired
    private SprintFactory sprintFactory;
    @Autowired
    private ISprintRepo sprintRepo;
    @Autowired
    private IResourceFactory resourceFactory;
    @Autowired
    private IResourceRepo resourceRepo;

    @Test
    @DisplayName("createTask - with success")
    void createTaskWithSuccess() {
        //Arrange
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.projectId = "Project_2022_3";
        taskDTO.sprintName = "9";
        taskDTO.usTitle = null;
        taskDTO.systemUserID = "tdc@mymail.com";
        taskDTO.resourceStartDate="2022-03-10";
        taskDTO.taskTitle="name";
        taskDTO.taskDescription="description";
        taskDTO.taskEffortEstimate = 20.0;
        taskDTO.taskType = "TEST";

        Task newTask = taskFactory.createTask(taskDTO);
        //Act
        Task savedTask = taskRepo.save(newTask);
        //Assert
        assertNotNull(savedTask);
        assertEquals("TEST", savedTask.getType().toString());
    }

    @Test
    @DisplayName("createTask - with success")
    void serviceCreateTaskWithSuccess() {
        //Arrange
        CreateResourceDTO resourceDTO = new CreateResourceDTO("tdc@mymail.com", "Project_2022_3", "TeamMember", "2022" +
                "-03-10", "2022-11-30", 10, 0.5);
        Resource newResource = resourceFactory.createResource(resourceDTO);
        resourceRepo.save(newResource);
        NewSprintDTO sprintDTO = new NewSprintDTO("Project_2022_3", "9", "2022-03-10");
        Sprint newSprint = sprintFactory.createSprint(sprintDTO);
        sprintRepo.save(newSprint);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.projectId = "Project_2022_3";
        taskDTO.sprintName = "9";
        taskDTO.usTitle = null;
        taskDTO.systemUserID = "tdc@mymail.com";
        taskDTO.resourceStartDate = "2022-03-10";
        taskDTO.taskTitle = "name2";
        taskDTO.taskDescription = "description";
        taskDTO.taskEffortEstimate = 20.0;
        taskDTO.taskType = "TEST";
        //Act
        OutputTaskDTO outputTaskDTO = taskService.createAndSaveTask(taskDTO);
        //Assert
        assertNotNull(outputTaskDTO);
        assertEquals("TEST", outputTaskDTO.type);
    }

    @Test
    void serviceGetTaskByIDWithSuccess() {
        //Arrange
        CreateResourceDTO resourceDTO = new CreateResourceDTO("tdc@mymail.com", "Project_2022_3", "TeamMember", "2022" +
                "-03-10", "2022-11-30", 10, 0.5);
        Resource newResource = resourceFactory.createResource(resourceDTO);
        resourceRepo.save(newResource);
        NewSprintDTO sprintDTO = new NewSprintDTO("Project_2022_3", "9", "2022-03-10");
        Sprint newSprint = sprintFactory.createSprint(sprintDTO);
        sprintRepo.save(newSprint);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.projectId = "Project_2022_3";
        taskDTO.sprintName = "9";
        taskDTO.usTitle = null;
        taskDTO.systemUserID = "tdc@mymail.com";
        taskDTO.resourceStartDate = "2022-03-10";
        taskDTO.taskTitle = "name3";
        taskDTO.taskDescription = "description";
        taskDTO.taskEffortEstimate = 20.0;
        taskDTO.taskType = "TEST";

        taskService.createAndSaveTask(taskDTO);
        //Act
        OutputTaskDTO outputTaskDTO = taskService.getTaskById("Project_2022_3&9&name3");
        //Assert
        assertNotNull(outputTaskDTO);
        assertEquals("TEST", outputTaskDTO.type);
        assertEquals(20.0, outputTaskDTO.effortEstimate);
    }*/
}