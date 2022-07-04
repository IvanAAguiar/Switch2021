package switch2021.project.applicationServices.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryOfSprintRepo;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.dtoModel.mapper.SprintMapper;
import switch2021.project.dtoModel.mapper.UserStoryOfSprintMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.valueObjects.voFactories.voFactories.SprintIDFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.interfaceAdapters.repositories.UserStoryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SprintServiceTest {

    @InjectMocks
    SprintService sprintService;
    @Mock
    private ISprintRepo sprintRepo;
    @Mock
    private SprintMapper sprintMapper;
    @Mock
    private StartSprintDTO startSprintDTO;
    @Mock
    private IProjectRepo projRepo;
    @Mock
    private IUserStoryOfSprintRepo userStoryOfSprintRepo;
    @Mock
    private UserStoryOfSprintMapper userStoryOfSprintMapper;
    @Mock
    private Sprint sprint;
    @Mock
    private OutputSprintDTO outputSprintDTO;
    @Mock
    private OutSprintDTO outSprintDTO;
    @Mock
    private Project project;
    @Mock
    private SprintDuration sprintDurationClass;
    @Mock
    private UserStoryOfSprint userStoryOfSprint;
    @Mock
    private UserStoryOfSprintDTO userStoryOfSprintDTO;
    @Mock
    private SprintIDFactory sprintIDFactory;

    @Test
    @DisplayName("Show all sprints")
    void showAllSprints() {
        //Arrange
        List<Sprint> expected = new ArrayList<>();
        CollectionModel<OutputSprintDTO> expectedDto = CollectionModel.empty();
        when(sprintRepo.findAllSprints()).thenReturn(expected);
        when(sprintMapper.toCollectionDto(expected)).thenReturn(expectedDto);

        //Act
        CollectionModel<OutputSprintDTO> result = sprintService.showAllSprints();

        //Assert
        assertEquals(expectedDto, result);

    }

    @Test
    @SneakyThrows
    @DisplayName("DeleteSprint fail")
    void deleteSprints_fail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "Project_2022_3&Sprint20";
            String projectID = "Project_2022_3";
            String sprintName = "Sprint20";
            SprintID sprintID = mock(SprintID.class);
            when(sprintIDFactory.create(projectID, sprintName)).thenReturn(sprintID);
            when(sprintRepo.existsSprintByID(sprintID)).thenReturn(false);
            //Act
            sprintService.deleteSprint(id);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all Sprints by ProjectId")
    void showAllSprintsByProjectId_success() {
        //Arrange
        List<Sprint> expected = new ArrayList<>();
        when(projRepo.existsById(any())).thenReturn(true);
        CollectionModel<OutputSprintDTO> expectedDto = CollectionModel.empty();
        when(sprintRepo.findAllByProjectID(any())).thenReturn(expected);
        when(sprintMapper.toCollectionDto(expected)).thenReturn(expectedDto);

        //Act
        CollectionModel<OutputSprintDTO> result = sprintService.showSprintsOfAProject("id");

        //Assert
        assertEquals(expectedDto, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all Sprints by ProjectId")
    void showAllSprintsByProjectId_success2() {
        //Arrange
        List<Sprint> expected = new ArrayList<>();
        when(projRepo.existsById(any())).thenReturn(true);
        CollectionModel<OutSprintDTO> expectedDto = CollectionModel.empty();
        when(sprintRepo.findAllByProjectID(any())).thenReturn(expected);
        when(sprintMapper.toCollectionsDto(expected)).thenReturn(expectedDto);

        //Act
        CollectionModel<OutSprintDTO> result = sprintService.showSprintsInProject("id");

        //Assert
        assertEquals(expectedDto, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all sprints fail")
    void showAllSprintsByProjectId_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            when(projRepo.existsById(any())).thenReturn(false);
            //Act
            sprintService.showSprintsOfAProject("id");
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all sprints fail")
    void showAllSprintsByProjectId_fail2() {
        assertThrows(Exception.class, () -> {
            //Arrange
            when(projRepo.existsById(any())).thenReturn(false);
            //Act
            sprintService.showSprintsInProject("id");
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all Sprints by ProjectId")
    void showScrumBoard_success() {
        //Arrange
        SprintID id = mock(SprintID.class);
        String sprintID = "Project_2022_1&1";
        String proj = "Project_2022_1";
        when(sprintIDFactory.create(proj, "1")).thenReturn(id);
        Description descriptionID = mock(Description.class);
        when(id.getSprintName()).thenReturn(descriptionID);
        when(descriptionID.getText()).thenReturn("1");
        when(sprintRepo.existsSprintByID(id)).thenReturn(true);

        List<UserStoryOfSprint> usList = new ArrayList<>();
        CollectionModel<UserStoryOfSprintDTO> expectedDto = CollectionModel.empty();
        when(userStoryOfSprintRepo.findAllUserStoriesBySprintID(id)).thenReturn(usList);
        when(userStoryOfSprintMapper.toCollectionDTO(any())).thenReturn(expectedDto);

        //Act
        CollectionModel<UserStoryOfSprintDTO> result = sprintService.showScrumBoard(sprintID);

        //Arrange
        assertEquals(expectedDto, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all Sprints by ProjectId")
    void showScrumBoard_success2() {
        //Arrange
        SprintID id = mock(SprintID.class);
        String sprintID = "Project_2022_1&1";
        String proj = "Project_2022_1";
        when(sprintIDFactory.create(proj, "1")).thenReturn(id);
        Description descriptionID = mock(Description.class);
        when(id.getSprintName()).thenReturn(descriptionID);
        when(descriptionID.getText()).thenReturn("1");
        when(sprintRepo.existsSprintByID(id)).thenReturn(true);

        List<UserStoryOfSprint> usList = new ArrayList<>();
        CollectionModel<UserStoryOfSprintDTO> expectedDto = CollectionModel.empty();
        when(userStoryOfSprintRepo.findAllUserStoriesBySprintID(id)).thenReturn(usList);
        when(userStoryOfSprintMapper.model2CollectionDTO(any())).thenReturn(expectedDto);

        //Act
        CollectionModel<UserStoryOfSprintDTO> result = sprintService.showScrumBoardOfSprint(sprintID);

        //Arrange
        assertEquals(expectedDto, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Show all Sprints by ProjectId fail")
    void showScrumBoard_fail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            SprintID id = mock(SprintID.class);
            String sprintID = "Project_2022_1&20";
            String proj = "Project_2022_1";
            when(sprintIDFactory.create(proj, "1")).thenReturn(id);
            Description descriptionID = mock(Description.class);
            when(id.getSprintName()).thenReturn(descriptionID);
            when(descriptionID.getText()).thenReturn("1");
            when(sprintRepo.existsSprintByID(id)).thenReturn(true);
            //Act
            sprintService.showScrumBoard(sprintID);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Validate dates success")
    void validateDates_success() {
        //Arrange
        String sprintId = "Project_2020_1&1";
        String date = "2012-01-01";
        LocalDate startDate = LocalDate.parse("2010-01-01");
        LocalDate endDate = LocalDate.parse("2030-01-01");
        Optional<Project> optional = Optional.of(project);
        long expected = 11;

        when(projRepo.findById(any())).thenReturn(optional);
        when(project.getSprintDuration()).thenReturn(sprintDurationClass);
        when(sprintDurationClass.getSprintDurationDays()).thenReturn(expected);
        when(project.getStartDate()).thenReturn(startDate);
        when(project.getEndDate()).thenReturn(endDate);

        //Act
        long result = sprintService.validateSprintStartDate(sprintId, date);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Validate dates project not found")
    void validateDates_fail_project_not_found() {
        assertThrows(Exception.class, () -> {
            //Arrange
            String sprintId = "Project_2020_1&1";
            String date = "2022-01-01";
            LocalDate startDate = LocalDate.parse("2010-01-01");
            LocalDate endDate = LocalDate.parse("2030-01-01");
            Optional<Project> optional = Optional.empty();
            long expected = 11;

            when(projRepo.findById(any())).thenReturn(optional);
            when(project.getSprintDuration()).thenReturn(sprintDurationClass);
            when(sprintDurationClass.getSprintDurationDays()).thenReturn(expected);
            when(project.getStartDate()).thenReturn(startDate);
            when(project.getEndDate()).thenReturn(endDate);

            //Act
            sprintService.validateSprintStartDate(sprintId, date);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Validate end date fail")
    void validateDates_endDate_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            String sprintId = "Project_2020_1&1";
            String date = "2031-01-01";
            LocalDate startDate = LocalDate.parse("2010-01-01");
            LocalDate endDate = LocalDate.parse("2030-01-01");
            Optional<Project> optional = Optional.of(project);
            long expected = 11;

            when(projRepo.findById(any())).thenReturn(optional);
            when(project.getSprintDuration()).thenReturn(sprintDurationClass);
            when(sprintDurationClass.getSprintDurationDays()).thenReturn(expected);
            when(project.getStartDate()).thenReturn(startDate);
            when(project.getEndDate()).thenReturn(endDate);

            //Act
            sprintService.validateSprintStartDate(sprintId, date);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Validate start date fail")
    void validateDates_startDate_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            String sprintId = "Project_2020_1&1";
            String date = "2009-01-01";
            LocalDate startDate = LocalDate.parse("2010-01-01");
            LocalDate endDate = LocalDate.parse("2030-01-01");
            Optional<Project> optional = Optional.of(project);
            long expected = 11;

            when(projRepo.findById(any())).thenReturn(optional);
            when(project.getSprintDuration()).thenReturn(sprintDurationClass);
            when(sprintDurationClass.getSprintDurationDays()).thenReturn(expected);
            when(project.getStartDate()).thenReturn(startDate);
            when(project.getEndDate()).thenReturn(endDate);

            //Act
            sprintService.validateSprintStartDate(sprintId, date);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Start a Sprint")
    void startASprint() {
        //Arrange
        String sprintId = "Project_2020_1&1";
        String date = "2012-01-01";
        LocalDate startDate = LocalDate.parse("2010-01-01");
        LocalDate endDate = LocalDate.parse("2030-01-01");
        Optional<Project> optional = Optional.of(project);
        Optional<Sprint> optionalSprint = Optional.of(sprint);
        long sprintDuration = 11;

        when(startSprintDTO.getStartDate()).thenReturn(date);
        when(projRepo.findById(any())).thenReturn(optional);
        when(project.getSprintDuration()).thenReturn(sprintDurationClass);
        when(sprintDurationClass.getSprintDurationDays()).thenReturn(sprintDuration);
        when(project.getStartDate()).thenReturn(startDate);
        when(project.getEndDate()).thenReturn(endDate);
        when(sprintRepo.findBySprintID(any())).thenReturn(optionalSprint);
        doNothing().when(sprint).setStartDate(any());
        doNothing().when(sprint).setEndDate(any());
        when(sprintRepo.save(sprint)).thenReturn(sprint);
        when(sprintMapper.toDTO(sprint)).thenReturn(outputSprintDTO);

        //Act
        OutputSprintDTO result = sprintService.startSprint(sprintId, startSprintDTO);

        //Assert
        assertEquals(outputSprintDTO, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Start a Sprint fail")
    void startASprint_fail() {
        //Arrange
        assertThrows(Exception.class, () -> {
            String sprintId = "Project_2020_1_1";
            String date = "2012-01-01";
            LocalDate startDate = LocalDate.parse("2010-01-01");
            LocalDate endDate = LocalDate.parse("2030-01-01");
            Optional<Project> optional = Optional.of(project);
            Optional<Sprint> optionalSprint = Optional.empty();
            long sprintDuration = 11;

            when(startSprintDTO.getStartDate()).thenReturn(date);
            when(projRepo.findById(any())).thenReturn(optional);
            when(project.getSprintDuration()).thenReturn(sprintDurationClass);
            when(sprintDurationClass.getSprintDurationDays()).thenReturn(sprintDuration);
            when(project.getStartDate()).thenReturn(startDate);
            when(project.getEndDate()).thenReturn(endDate);
            when(sprintRepo.findBySprintID(any())).thenReturn(optionalSprint);


            //Act
            sprintService.startSprint(sprintId, startSprintDTO);
        });
    }

    @Test
    //@SneakyThrows
    @DisplayName("Change scrum board category success")
    void changeScrumBoardCategory_success() throws Exception {
        //Arrange
        String id = "Project_2022_1&1";
        UserStoryOfSprintDTO userStoryDTO = mock(UserStoryOfSprintDTO.class);
        SprintID sprintID = mock(SprintID.class);
        String proj = "Project_2022_1";
        when(sprintIDFactory.create(proj, "1")).thenReturn(sprintID);
        Description descriptionID = mock(Description.class);
        when(sprintID.getSprintName()).thenReturn(descriptionID);
        when(descriptionID.getText()).thenReturn("1");
        when(sprintRepo.existsSprintByID(sprintID)).thenReturn(true);
        List<UserStoryOfSprint> userStoryOfSprintList = new ArrayList<>();

        userStoryOfSprintList.add(userStoryOfSprint);
        UserStoryID userStoryID = mock(UserStoryID.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle title = mock(UsTitle.class);

        when(userStoryOfSprintRepo.findAllUserStoriesBySprintID(sprintID)).thenReturn(userStoryOfSprintList);
        when(userStoryOfSprint.getUserStoryId()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn(proj);
        when(userStoryDTO.getProjectId()).thenReturn(proj);
        when(userStoryID.getUsTitle()).thenReturn(title);
        when(title.getTitleUs()).thenReturn("title");
        when(userStoryDTO.getUsTitle()).thenReturn("title");
        when(userStoryOfSprint.getSprintName()).thenReturn("1");
        when(userStoryDTO.getStatus()).thenReturn("Todo");
        doNothing().when(userStoryOfSprint).setUserStoryOfSprintStatus(any());
        when(userStoryOfSprintRepo.save(any())).thenReturn(userStoryOfSprint);
        when(userStoryOfSprintMapper.toDTO(userStoryOfSprint)).thenReturn(userStoryOfSprintDTO);

        //Act
        UserStoryOfSprintDTO result = sprintService.changeStatusScrumBoard(id, userStoryDTO);

        //Assert
        assertEquals(userStoryOfSprintDTO, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Change scrum board category fail no sprint")
    void changeScrumBoardCategory_sprintFail() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            String id = "Project_2022_1&1";
            UserStoryOfSprintDTO userStoryDTO = mock(UserStoryOfSprintDTO.class);
            SprintID sprintID = mock(SprintID.class);
            String proj = "Project_2022_1";
            when(sprintIDFactory.create(proj, "1")).thenReturn(sprintID);
            Description descriptionID = mock(Description.class);
            when(sprintID.getSprintName()).thenReturn(descriptionID);
            when(descriptionID.getText()).thenReturn("1");
            when(sprintRepo.existsSprintByID(sprintID)).thenReturn(false);
            //Act
            sprintService.changeStatusScrumBoard(id, userStoryDTO);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Change scrum board category fail no user Story")
    void changeScrumBoardCategory_fail() {
        assertThrows(Exception.class, () -> {
            //Arrange
            String sprintId = "Project_2020_1&1";
            String proj = "Project_2022_1";
            SprintID sprintID = mock(SprintID.class);
            when(sprintIDFactory.create("Project_2022_1", "1")).thenReturn(sprintID);
            List<UserStoryOfSprint> userStoryOfSprintList = new ArrayList<>();
            userStoryOfSprintList.add(userStoryOfSprint);
            UserStoryID userStoryID = mock(UserStoryID.class);
            ProjectID projectID = mock(ProjectID.class);
            UserStoryOfSprintDTO dto = mock(UserStoryOfSprintDTO.class);
            when(userStoryOfSprintRepo.findAllUserStoriesBySprintID(any())).thenReturn(userStoryOfSprintList);
            when(userStoryOfSprint.getUserStoryId()).thenReturn(userStoryID);
            when(userStoryID.getProjectID()).thenReturn(projectID);
            when(projectID.getCode()).thenReturn(proj);
            when(dto.getProjectId()).thenReturn("not");
            //Act
            sprintService.changeStatusScrumBoard(sprintId, dto);
        });
    }

    @Test
    public void addUserStoryToSpringBacklogFail() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            String id = "idSprint";
            SprintID sprintID = mock(SprintID.class);
            UserStoryID usID = mock(UserStoryID.class);
            UserStoryIdDTO inputDto = mock(UserStoryIdDTO.class);
            UserStory userStory = mock(UserStory.class);
            UserStoryRepository usRepo = mock(UserStoryRepository.class);
            when(usRepo.findByUserStoryId(usID)).thenReturn(Optional.of(userStory));
            when(sprintRepo.findBySprintID(sprintID)).thenReturn(Optional.empty());
            //Act
            sprintService.addUserStoryToSprintBacklog(id, inputDto);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("Show sprint by id success")
    void showSprintById_success() {
        //Arrange
        Optional<Sprint> opSprint = Optional.of(sprint);
        when(sprintRepo.findBySprintID(any())).thenReturn(opSprint);
        when(sprintMapper.model2DTO(sprint)).thenReturn(outSprintDTO);
        //Act
        OutSprintDTO result = sprintService.showSprintById("Project_2022_1&1");
        //Assert
        assertEquals(outSprintDTO,result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Show sprint by id fail")
    void showSprintById_fail() {
        assertThrows(Exception.class, () -> {
            Optional<Sprint> opSprint = Optional.empty();
            when(sprintRepo.findBySprintID(any())).thenReturn(opSprint);
            //Act
            sprintService.showSprintById("Project_2022_1&1");
        });
    }
}



