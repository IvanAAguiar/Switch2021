package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.dtoModel.dto.OutputUserStoryDTO;
import switch2021.project.dtoModel.dto.UpdateUserStoryDTO;
import switch2021.project.dtoModel.dto.UserStoryDTO;
import switch2021.project.dtoModel.mapper.UserStoryMapper;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.factories.factoryInterfaces.IUserStoryFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsHourFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsPriorityFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserStoryIDFactory;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UsHour;
import switch2021.project.entities.valueObjects.vos.UsPriority;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserStoryServiceTest {

    @InjectMocks
    UserStoryService service;

    @MockBean
    private IUserStoryFactory iUserStoryFactory;
    @MockBean
    private IUserStoryIDFactory usIdFactory;
    @MockBean
    private IUsHourFactory usHourFactory;
    @MockBean
    private IUsPriorityFactory priorityFactory;
    @MockBean
    private IProjectIDFactory projectIDFactory;
    @MockBean
    private IProjectRepo iProjectRepo;
    @MockBean
    private IUserStoryRepo repo;
    @MockBean
    private UserStoryMapper mapper;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Test to create and save user story - with success")
    public void createAndSaveUserStoryWithSuccessGetProjectID() {
        //Arrange
        UserStoryDTO createUserStoryDTO = mock(UserStoryDTO.class);
        UserStory newUserStory = mock(UserStory.class);
        UserStory savedUserStory = mock(UserStory.class);
        UserStoryID id = mock(UserStoryID.class);
        OutputUserStoryDTO outputUsDto = mock(OutputUserStoryDTO.class);
        when(createUserStoryDTO.getProjectID()).thenReturn("Project_2022_1");
        ProjectID projId = mock(ProjectID.class);
        when(projectIDFactory.create("Project_2022_1")).thenReturn(projId);
        when(iProjectRepo.existsById(projId)).thenReturn(true);
        when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
        when(newUserStory.getUserStoryID()).thenReturn(id);
        when(repo.existsUserStoryByID(id)).thenReturn(false);
        when(repo.save(newUserStory)).thenReturn(savedUserStory);
        when(mapper.toDto(savedUserStory)).thenReturn(outputUsDto);
        when(newUserStory.getUserStoryID()).thenReturn(id);
        when(savedUserStory.getUserStoryID()).thenReturn(id);
        //Act
        OutputUserStoryDTO result = service.createAndSaveUserStory(createUserStoryDTO);
        //Assert
        assertEquals(outputUsDto, result);
    }

    @Test
    @DisplayName("Test to create and save user story - with Fail")
    public void createAndSaveUserStoryWithFailGetProjectID() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            UserStoryDTO createUserStoryDTO = mock(UserStoryDTO.class);
            when(createUserStoryDTO.getProjectID()).thenReturn("Project_2022_1");
            ProjectID projId = mock(ProjectID.class);
            when(projectIDFactory.create("Project_2022_1")).thenReturn(projId);
            when(iProjectRepo.existsById(projId)).thenReturn(false);
            //Act
            service.createAndSaveUserStory(createUserStoryDTO);
        });
    }

    @Test
    @DisplayName("Test to create and save user story - with Fail")
    public void createAndSaveUserStoryWithFailUSExist() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            UserStoryDTO createUserStoryDTO = mock(UserStoryDTO.class);
            when(createUserStoryDTO.getProjectID()).thenReturn("Project_2022_1");
            ProjectID projId = mock(ProjectID.class);
            when(projectIDFactory.create("Project_2022_1")).thenReturn(projId);
            when(iProjectRepo.existsById(projId)).thenReturn(true);
            UserStory newUserStory = mock(UserStory.class);
            UserStoryID id = mock(UserStoryID.class);
            when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
            when(newUserStory.getUserStoryID()).thenReturn(id);
            when(repo.existsUserStoryByID(id)).thenReturn(true);
            //Act
            service.createAndSaveUserStory(createUserStoryDTO);
        });
    }


    @Test
    public void showAUserStorySuccess() {
        //Arrange
        String id = "Project_2022_1&As fulano i want to teste";
        UserStoryID usId = mock(UserStoryID.class);
        OutputUserStoryDTO outputUsDto = mock(OutputUserStoryDTO.class);
        UserStory userStory = mock(UserStory.class);
        when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
        when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
        when(mapper.toDto(userStory)).thenReturn(outputUsDto);
        //Act
        OutputUserStoryDTO result = service.showAUserStory(id);
        //Assert
        assertEquals(outputUsDto, result);
    }

    @Test
    public void showAUserStoryFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.empty());
            //Act
            service.showAUserStory(id);
        });
    }

    @Test
    public void showAllUserStoriesSuccess() {
        //Arrange
        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStory1);
        userStories.add(userStory2);
        OutputUserStoryDTO dto1 = mock(OutputUserStoryDTO.class);
        OutputUserStoryDTO dto2 = mock(OutputUserStoryDTO.class);
        List<OutputUserStoryDTO> dtos = new ArrayList<>();
        dtos.add(dto1);
        dtos.add(dto2);
        CollectionModel<OutputUserStoryDTO> collection = CollectionModel.of(dtos);
        when(repo.findAll()).thenReturn(userStories);
        when(mapper.toCollectionDto(userStories)).thenReturn(collection);
        //Act
        CollectionModel<OutputUserStoryDTO> result = service.getAllUserStories();
        //Assert
        assertEquals(collection, result);
    }

    @Test
    public void consultProductBacklogSuccess() throws Exception {
        //Arrange
        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStory1);
        userStories.add(userStory2);
        OutputUserStoryDTO dto1 = mock(OutputUserStoryDTO.class);
        OutputUserStoryDTO dto2 = mock(OutputUserStoryDTO.class);
        List<OutputUserStoryDTO> dtos = new ArrayList<>();
        dtos.add(dto1);
        dtos.add(dto2);
        CollectionModel<OutputUserStoryDTO> collection = CollectionModel.of(dtos);
        ProjectID projId = mock(ProjectID.class);
        when(projectIDFactory.create("Project_2022_1")).thenReturn(projId);
        when(iProjectRepo.existsById(projId)).thenReturn(true);
        when(repo.findProductBacklog("Project_2022_1")).thenReturn(userStories);
        when(mapper.toCollectionDto(userStories)).thenReturn(collection);
        //Act
        CollectionModel<OutputUserStoryDTO> result = service.consultProductBacklog("Project_2022_1");
        //Assert
        assertEquals(collection, result);
    }

    @Test
    public void consultProductBacklogFail() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ProjectID projId = mock(ProjectID.class);
            when(projectIDFactory.create("Project_2022_1")).thenReturn(projId);
            when(iProjectRepo.existsById(projId)).thenReturn(false);
            //Act
            CollectionModel<OutputUserStoryDTO> result = service.consultProductBacklog("Project_2022_1");
        });
    }

    @Test
    public void updateUSDataSuccessPriority() {
        //Arrange
        UpdateUserStoryDTO dto = mock(UpdateUserStoryDTO.class);
        String id = "Project_2022_1&As fulano i want to teste";
        UserStoryID usId = mock(UserStoryID.class);
        UserStory updated = mock(UserStory.class);
        OutputUserStoryDTO outputUsDto = mock(OutputUserStoryDTO.class);
        UserStory userStory = mock(UserStory.class);
        when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
        when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
        when(dto.getPriority()).thenReturn(2);
        UsPriority priority = mock(UsPriority.class);
        when(priorityFactory.create(2)).thenReturn(priority);
        when(dto.getTimeEstimate()).thenReturn(0.0);
        when(repo.save(userStory)).thenReturn(updated);
        when(mapper.toDto(updated)).thenReturn(outputUsDto);
        //Act
        OutputUserStoryDTO result = service.updateUSData(id, dto);
        //Assert
        assertEquals(outputUsDto, result);
    }

    @Test
    public void updateUSDataSuccessTimeEstimate() {
        //Arrange
        UpdateUserStoryDTO dto = mock(UpdateUserStoryDTO.class);
        String id = "Project_2022_1&As fulano i want to teste";
        UserStoryID usId = mock(UserStoryID.class);
        UserStory updated = mock(UserStory.class);
        OutputUserStoryDTO outputUsDto = mock(OutputUserStoryDTO.class);
        UserStory userStory = mock(UserStory.class);
        when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
        when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
        when(dto.getPriority()).thenReturn(0);
        when(dto.getTimeEstimate()).thenReturn(2.0);
        UsHour usHour = mock(UsHour.class);
        when(usHourFactory.create(2.0)).thenReturn(usHour);
        when(repo.save(userStory)).thenReturn(updated);
        when(mapper.toDto(updated)).thenReturn(outputUsDto);
        //Act
        OutputUserStoryDTO result = service.updateUSData(id, dto);
        //Assert
        assertEquals(outputUsDto, result);
    }

    @Test
    public void updateUSDataFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            UpdateUserStoryDTO dto = mock(UpdateUserStoryDTO.class);
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.empty());
            //Act
            service.updateUSData(id, dto);
        });
    }

    @Test
    public void startUserStorySuccess() throws Exception {
        //Arrange

        String id = "Project_2022_1&As fulano i want to teste";
        UserStoryID usId = mock(UserStoryID.class);
        UserStory updated = mock(UserStory.class);
        OutputUserStoryDTO outputUsDto = mock(OutputUserStoryDTO.class);
        UserStory userStory = mock(UserStory.class);
        when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
        when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
        when(repo.save(userStory)).thenReturn(updated);
        when(mapper.toDto(updated)).thenReturn(outputUsDto);
        //Act
        OutputUserStoryDTO result = service.startUserStory(id);
        //Assert
        assertEquals(outputUsDto, result);
    }

    @Test
    public void startUserStoryFailUSDoesNotExist() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.empty());
            //Act
            service.startUserStory(id);
        });
    }

    @Test
    public void startUserStoryFailUSAlreadyClosed() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            UserStory userStory = mock(UserStory.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
            when(userStory.getUsEndDate()).thenReturn(LocalDate.parse("2022-01-01"));
            //Act
            service.startUserStory(id);
        });
    }

    @Test
    public void startUserStoryFailUSAlreadyStarted() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            UserStory userStory = mock(UserStory.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
            when(userStory.getUsStartDate()).thenReturn(LocalDate.parse("2022-01-01"));
            //Act
            service.startUserStory(id);
        });
    }

    @Test
    public void cancelUSSuccess() {
        //Arrange
        String id = "Project_2022_1&As fulano i want to teste";
        UserStoryID usId = mock(UserStoryID.class);
        UserStory userStory = mock(UserStory.class);
        UserStory updated = mock(UserStory.class);
        OutputUserStoryDTO outputUsDto = mock(OutputUserStoryDTO.class);
        when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
        when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
        when(repo.save(userStory)).thenReturn(updated);
        when(mapper.toDto(updated)).thenReturn(outputUsDto);
        //Act
        OutputUserStoryDTO result = service.cancelUserStory(id);
        //Assert
        assertEquals(outputUsDto, result);
    }

    @Test
    public void cancelUSFailDoesNotExist() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.empty());
            //Act
            service.cancelUserStory(id);
        });
    }

    @Test
    public void cancelUSFailUSCancelled() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            UserStory userStory = mock(UserStory.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
            when(userStory.getUsEndDate()).thenReturn(LocalDate.parse("2022-01-01"));
            //Act
            service.cancelUserStory(id);
        });
    }

    @Test
    public void finishUSSuccess() {
        //Arrange
        String id = "Project_2022_1&As fulano i want to teste";
        UserStoryID usId = mock(UserStoryID.class);
        UserStory userStory = mock(UserStory.class);
        UserStory updated = mock(UserStory.class);
        OutputUserStoryDTO outputUsDto = mock(OutputUserStoryDTO.class);
        when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
        when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
        when(repo.save(userStory)).thenReturn(updated);
        when(mapper.toDto(updated)).thenReturn(outputUsDto);
        //Act
        OutputUserStoryDTO result = service.finishUserStory(id);
        //Assert
        assertEquals(outputUsDto, result);
    }

    @Test
    public void finishUSFailDoesNotExist() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.empty());
            //Act
            service.finishUserStory(id);
        });
    }

    @Test
    public void finishUSFailUSCancelled() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            UserStory userStory = mock(UserStory.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
            when(userStory.getUsEndDate()).thenReturn(LocalDate.parse("2022-01-01"));
            //Act
            service.finishUserStory(id);
        });
    }

    @Test
    public void refineUSSuccess() {
        //Arrange
        UserStoryDTO inDto = mock(UserStoryDTO.class);
        String id = "Project_2022_1&As fulano i want to teste";
        UserStoryID usId = mock(UserStoryID.class);
        UserStory userStory = mock(UserStory.class);
        when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
        when(repo.findByUserStoryId(usId)).thenReturn(Optional.of(userStory));
        List<UserStory> userStories = new ArrayList<>();

        UserStory refinedUs = mock(UserStory.class);
        when(iUserStoryFactory.createUserStory(inDto)).thenReturn(refinedUs);
        UserStory savedRefinedUs = mock(UserStory.class);
        when(repo.save(refinedUs)).thenReturn(savedRefinedUs);
        userStories.add(savedRefinedUs);

        UserStory updated = mock(UserStory.class);
        when(repo.save(userStory)).thenReturn(updated);
        userStories.add(updated);

        OutputUserStoryDTO savedRefinedDto = mock(OutputUserStoryDTO.class);
        OutputUserStoryDTO updatedDto = mock(OutputUserStoryDTO.class);
        List<OutputUserStoryDTO> dtos = new ArrayList<>();
        dtos.add(savedRefinedDto);
        dtos.add(updatedDto);

        CollectionModel<OutputUserStoryDTO> collection = CollectionModel.of(dtos);
        when(mapper.toCollectionDto(userStories)).thenReturn(collection);
        //Act
        CollectionModel<OutputUserStoryDTO> result = service.refineUserStory(id, inDto);
        //Assert
        assertEquals(collection, result);
    }

    @Test
    public void refineUSFailUSDoesNotExist() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            UserStoryDTO inDto = mock(UserStoryDTO.class);
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.empty());
            //Act
            service.refineUserStory(id, inDto);
        });
    }

    @Test
    public void deleteAUserStoryFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "Project_2022_1&As fulano i want to teste";
            UserStoryID usId = mock(UserStoryID.class);
            when(usIdFactory.create("Project_2022_1", "As fulano i want to teste")).thenReturn(usId);
            when(repo.findByUserStoryId(usId)).thenReturn(Optional.empty());
            //Act
            service.deleteAUserStory(id);
        });
    }
}
