package switch2021.project.applicationServices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.applicationServices.iRepositories.*;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.dtoModel.mapper.ProjectMapper;
import switch2021.project.dtoModel.mapper.StatusMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.factories.factoryInterfaces.IProjectFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import javax.net.ssl.SSLException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectServiceTest {

    @Mock
    private IProjectRepo projRepo;
    @Mock
    private ITypologyRepo iTypologyRepo;
    @Mock
    private IProjectFactory iProjectFactory;
    @Mock
    private ProjectMapper projMapper;
    @Mock
    private IUserRepo userRepo;
    @Mock
    private IUserIDFactory userIDFactory;
    @Mock
    private IResourceRepo resRepo;
    @Mock
    private ManagementResourcesService resService;
    @Mock
    private IProjectWebRepository iProjectWebRepository;
    @Mock
    private ITypologyIDFactory typologyIDFactory;
    @Mock
    private IProjectIDFactory projectIDFactory;
    @Mock
    private IDescriptionFactory descriptionFactory;
    @Mock
    private INumberOfSprintsFactory numberOfSprintsFactory;
    @Mock
    private IBudgetFactory budgetFactory;
    @Mock
    private ISprintDurationFactory sprintDurationFactory;
    @Mock
    private StatusMapper statusMapper;

    private static MockedStatic<ProjectStatusEnum> statusEnum;
    private static MockedStatic<Customer> customer;

    @BeforeEach
    public void init() {
        statusEnum = mockStatic(ProjectStatusEnum.class);
        customer = mockStatic(Customer.class);
    }

    @AfterEach
    public void close() {
        statusEnum.close();
        customer.close();
    }

    @InjectMocks
    ProjectService projectService;


    /**
     * Unitary tests to createAndSaveProject() method.
     */

    @Test
    void createProjectWithTypoNotExist() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            ProjectDTO projDto = mock(ProjectDTO.class);
            TypologyID typoId = mock(TypologyID.class);
            String typo = "not exist";
            when(projDto.getTypology()).thenReturn(typo);
            when(typologyIDFactory.createIdWithString(typo)).thenReturn(typoId);
            when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(false);
            //Act
            projectService.createAndSaveProject(projDto);
        });
    }

    @Test
    void createProjectWhenProjectAlreadyExist() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            ProjectDTO projDto = mock(ProjectDTO.class);
            TypologyID typoId = mock(TypologyID.class);
            String typo = "exist";
            Project newProject = mock(Project.class);
            ProjectID projId = mock(ProjectID.class);
            String format = "Project_2022_";
            List<Project> allProjects = new ArrayList<>();
            int number = 1;
            when(projDto.getTypology()).thenReturn(typo);
            when(typologyIDFactory.createIdWithString(typo)).thenReturn(typoId);
            when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(true);
            when(iProjectFactory.createProject(projDto)).thenReturn(newProject);
            when(projRepo.findAll()).thenReturn(allProjects);
            when(projectIDFactory.create(format + number)).thenReturn(projId);
            when(newProject.getProjectCode()).thenReturn(projId);
            when(projRepo.existsById(projId)).thenReturn(true);
            //Act
            projectService.createAndSaveProject(projDto);
        });
    }

    @Test
    void createAndSaveProjectSuccess() throws Exception {
        //Arrange
        ProjectDTO projDto = mock(ProjectDTO.class);
        TypologyID typoId = mock(TypologyID.class);
        String typo = "exist";
        Project newProject = mock(Project.class);
        ProjectID projId = mock(ProjectID.class);
        String format = "Project_2022_";
        List<Project> allProjects = new ArrayList<>();
        int number = 1;
        String id = format + number;
        Project savedProject = mock(Project.class);
        OutputProjectDTO outDto = mock(OutputProjectDTO.class);

        when(projDto.getTypology()).thenReturn(typo);
        when(typologyIDFactory.createIdWithString(typo)).thenReturn(typoId);
        when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(true);
        when(iProjectFactory.createProject(projDto)).thenReturn(newProject);
        when(projRepo.findAll()).thenReturn(allProjects);
        when(projectIDFactory.create(id)).thenReturn(projId);
        doAnswer(invocationOnMock -> {
            ProjectID arg = invocationOnMock.getArgument(0);
            when(projId.getCode()).thenReturn(id);
            assertEquals(id, arg.getCode());
            return null;
        }).when(newProject).setProjectCode(projId);
        when(newProject.getProjectCode()).thenReturn(projId);
        when(projRepo.existsById(projId)).thenReturn(false);
        when(projRepo.save(newProject)).thenReturn(savedProject);
        when(projMapper.model2Dto(savedProject)).thenReturn(outDto);
        //Act
        OutputProjectDTO result = projectService.createAndSaveProject(projDto);
        //Assert
        assertEquals(outDto, result);
    }

    /**
     * Unitary tests to updateProjectPartially() method.
     */

    @Test
    void updateProjectWithProjectNotExist() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "not exist";
            EditProjectInfoDTO dto = mock(EditProjectInfoDTO.class);
            ProjectID projId = mock(ProjectID.class);
            Optional<Project> opt = Optional.empty();
            when(projectIDFactory.create(id)).thenReturn(projId);
            when(projRepo.findById(projId)).thenReturn(opt);
            //Act
            projectService.updateProjectPartially(id, dto);
        });
    }

    @Test
    void updateProjectWithTypoNotExist() {
        //Arrange
        String id = "exist";
        EditProjectInfoDTO dto = mock(EditProjectInfoDTO.class);
        ProjectID projId = mock(ProjectID.class);
        Project proj = mock(Project.class);
        Optional<Project> opt = Optional.of(proj);
        TypologyID typoId = mock(TypologyID.class);
        Project savedProj = mock(Project.class);
        OutputProjectDTO expected = mock(OutputProjectDTO.class);
        Description description = mock(Description.class);
        when(projectIDFactory.create(id)).thenReturn(projId);
        when(projRepo.findById(projId)).thenReturn(opt);

        String resGet = "get something";

        when(dto.getProjectName()).thenReturn(resGet);
        when(dto.getDescription()).thenReturn(resGet);
        when(descriptionFactory.createDescription(resGet)).thenReturn(description);
        doNothing().when(proj).setProjectName(description);
        doNothing().when(proj).setDescription(description);

        String date = "2022-06-30";
        when(dto.getStartDate()).thenReturn(date);
        doNothing().when(proj).setStartDate(LocalDate.parse(date));

        String number = "1";
        NumberOfSprints num = mock(NumberOfSprints.class);
        when(dto.getNumberOfSprints()).thenReturn(number);
        when(numberOfSprintsFactory.create(Integer.parseInt(number))).thenReturn(num);
        doNothing().when(proj).setNumberOfSprints(num);

        Budget budget = mock(Budget.class);
        when(dto.getBudget()).thenReturn(number);
        when(budgetFactory.create(Integer.parseInt(number))).thenReturn(budget);
        doNothing().when(proj).setBudget(budget);

        SprintDuration duration = mock(SprintDuration.class);
        when(dto.getSprintDuration()).thenReturn(number);
        when(sprintDurationFactory.create(Integer.parseInt(number))).thenReturn(duration);
        doNothing().when(proj).setSprintDuration(duration);

        String status = "Planned";
        when(dto.getProjectStatus()).thenReturn(status);
        statusEnum.when(() -> ProjectStatusEnum.valueOf(status)).thenReturn(null);
        doNothing().when(proj).setProjectStatus(null);

        Customer cust = mock(Customer.class);
        when(dto.getCustomer()).thenReturn(resGet);
        customer.when(() -> Customer.create(resGet)).thenReturn(cust);
        doNothing().when(proj).setCustomer(cust);

        when(dto.getEndDate()).thenReturn(date);
        doNothing().when(proj).setEndDate(LocalDate.parse(date));

        when(dto.getTypology()).thenReturn(resGet);
        when(typologyIDFactory.createIdWithString(resGet)).thenReturn(typoId);

        when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(false);
        when(projRepo.save(proj)).thenReturn(savedProj);
        when(projMapper.model2Dto(savedProj)).thenReturn(expected);

        //Act
        OutputProjectDTO result = projectService.updateProjectPartially(id, dto);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void updateProjectWithTypoExist() {
        //Arrange
        String id = "exist";
        EditProjectInfoDTO dto = mock(EditProjectInfoDTO.class);
        ProjectID projId = mock(ProjectID.class);
        Project proj = mock(Project.class);
        Optional<Project> opt = Optional.of(proj);
        TypologyID typoId = mock(TypologyID.class);
        Project savedProj = mock(Project.class);
        OutputProjectDTO expected = mock(OutputProjectDTO.class);
        Description description = mock(Description.class);
        when(projectIDFactory.create(id)).thenReturn(projId);
        when(projRepo.findById(projId)).thenReturn(opt);

        String resGet = "get something";

        when(dto.getProjectName()).thenReturn(resGet);
        when(dto.getDescription()).thenReturn(resGet);
        when(descriptionFactory.createDescription(resGet)).thenReturn(description);
        doNothing().when(proj).setProjectName(description);
        doNothing().when(proj).setDescription(description);

        String date = "2022-06-30";
        when(dto.getStartDate()).thenReturn(date);
        doNothing().when(proj).setStartDate(LocalDate.parse(date));

        String number = "1";
        NumberOfSprints num = mock(NumberOfSprints.class);
        when(dto.getNumberOfSprints()).thenReturn(number);
        when(numberOfSprintsFactory.create(Integer.parseInt(number))).thenReturn(num);
        doNothing().when(proj).setNumberOfSprints(num);

        Budget budget = mock(Budget.class);
        when(dto.getBudget()).thenReturn(number);
        when(budgetFactory.create(Integer.parseInt(number))).thenReturn(budget);
        doNothing().when(proj).setBudget(budget);

        SprintDuration duration = mock(SprintDuration.class);
        when(dto.getSprintDuration()).thenReturn(number);
        when(sprintDurationFactory.create(Integer.parseInt(number))).thenReturn(duration);
        doNothing().when(proj).setSprintDuration(duration);

        String status = "Planned";
        when(dto.getProjectStatus()).thenReturn(status);
        statusEnum.when(() -> ProjectStatusEnum.valueOf(status)).thenReturn(null);
        doNothing().when(proj).setProjectStatus(null);

        Customer cust = mock(Customer.class);
        when(dto.getCustomer()).thenReturn(resGet);
        customer.when(() -> Customer.create(resGet)).thenReturn(cust);
        doNothing().when(proj).setCustomer(cust);

        when(dto.getEndDate()).thenReturn(date);
        doNothing().when(proj).setEndDate(LocalDate.parse(date));

        when(dto.getTypology()).thenReturn(resGet);
        when(typologyIDFactory.createIdWithString(resGet)).thenReturn(typoId);

        when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(true);
        doNothing().when(proj).setTypologyId(typoId);

        when(projRepo.save(proj)).thenReturn(savedProj);
        when(projMapper.model2Dto(savedProj)).thenReturn(expected);

        //Act
        OutputProjectDTO result = projectService.updateProjectPartially(id, dto);
        //Assert
        assertEquals(expected, result);
        verify(proj, times(1)).setProjectName(description);
        verify(proj, times(1)).setDescription(description);
        verify(proj, times(1)).setStartDate(LocalDate.parse(date));
        verify(proj, times(1)).setNumberOfSprints(num);
        verify(proj, times(1)).setBudget(budget);
        verify(proj, times(1)).setSprintDuration(duration);
        verify(proj, times(1)).setProjectStatus(null);
        verify(proj, times(1)).setCustomer(cust);
        verify(proj, times(1)).setEndDate(LocalDate.parse(date));
        verify(proj, times(1)).setTypologyId(typoId);
    }

    /**
     * Unitary tests to getAllProjects() method.
     */

    @Test
    void getAllProjectsSuccess() throws SSLException {
        //Arrange
        List<Project> projects = new ArrayList<>();
        List<Project> projectsWeb = new ArrayList<>();
        CollectionModel<PartialProjectDTO> outDtoList = CollectionModel.empty();
        CollectionModel<PartialProjectDTO> outDtoWebList = CollectionModel.empty();

        when(projRepo.findAll()).thenReturn(projects);
        when(iProjectWebRepository.findAll()).thenReturn(projectsWeb);
        when(projMapper.toCollectionDto2(projects, false)).thenReturn(outDtoList);
        when(projMapper.toCollectionDto2(projectsWeb, true)).thenReturn(outDtoWebList);
        Map<String, CollectionModel<PartialProjectDTO>> expected = new HashMap<>();
        expected.put("internalProjects", outDtoList);
        expected.put("externalProjects", outDtoWebList);
        //Act
        Map<String, CollectionModel<PartialProjectDTO>> result = projectService.getAllProjects();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unitary tests to getProject() method.
     */

    @Test
    void getProjectWithProjectNotExist() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "not exist";
            ProjectID projId = mock(ProjectID.class);
            Optional<Project> foundedProject = Optional.empty();

            when(projectIDFactory.create(id)).thenReturn(projId);
            when(projRepo.findById(projId)).thenReturn(foundedProject);
            //Act
            projectService.showProject(id);
        });
    }

    @Test
    void getProjectSuccess() throws Exception {
        //Arrange
        String id = "not exist";
        ProjectID projId = mock(ProjectID.class);
        Project proj = mock(Project.class);
        Optional<Project> foundedProject = Optional.of(proj);
        OutputProjectDTO expected = mock(OutputProjectDTO.class);

        when(projectIDFactory.create(id)).thenReturn(projId);
        when(projRepo.findById(projId)).thenReturn(foundedProject);
        when(projMapper.model2Dto(proj)).thenReturn(expected);
        //Act
        OutputProjectDTO result = projectService.showProject(id);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unitary tests to getCurrentProjectsByUser() method.
     */

    @Test
    void getCurrentProjectsByUserNotExist() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "not exist";
            Email email = mock(Email.class);
            UserID userID = mock(UserID.class);
            when(email.getEmailText()).thenReturn(id);
            when(userID.getEmail()).thenReturn(email);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.empty());
            //Act
            projectService.getCurrentProjectsByUser(id);
        });
    }

    @Test
    void getCurrentProjectsByResourceProjectNotExist() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "not exist";
            UserID userID = mock(UserID.class);
            User user = mock(User.class);
            Resource resource = mock(Resource.class);
            ProjectID projId = mock(ProjectID.class);
            List<Resource> userResources = List.of(resource);
            List<ProjectID> resourceProjects = List.of(projId);

            when(userIDFactory.createUserID(id)).thenReturn(userID);
            when(user.getUserId()).thenReturn(userID);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));
            when(userRepo.existsById(userID)).thenReturn(true);
            when(resRepo.findAllByUser(userID)).thenReturn(userResources);
            when(resService.currentResourcesByDate(userResources)).thenReturn(userResources);
            when(resService.listProjectsOfResources(userResources)).thenReturn(resourceProjects);
            when(projRepo.findById(projId)).thenReturn(Optional.empty());
            //Act
            projectService.getCurrentProjectsByUser(id);
        });
    }

    @Test
    void getCurrentProjectsByUserSuccess() {
        //Arrange
        String id = "not exist";
        UserID userID = mock(UserID.class);
        User user = mock(User.class);
        Resource resource = mock(Resource.class);
        ProjectID projId = mock(ProjectID.class);
        Project project = mock(Project.class);
        OutputProjectDTO dto = mock(OutputProjectDTO.class);
        List<Resource> userResources = List.of(resource);
        List<ProjectID> resourceProjects = List.of(projId);
        List<Project> projectList = List.of(project);

        when(userIDFactory.createUserID(id)).thenReturn(userID);
        when(user.getUserId()).thenReturn(userID);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));
        when(userRepo.existsById(userID)).thenReturn(true);
        when(resRepo.findAllByUser(userID)).thenReturn(userResources);
        when(resService.currentResourcesByDate(userResources)).thenReturn(userResources);
        when(resService.listProjectsOfResources(userResources)).thenReturn(resourceProjects);
        when(projRepo.findById(projId)).thenReturn(Optional.of(project));
        when(projMapper.toCollectionDto(projectList, false)).thenReturn(CollectionModel.of(List.of(dto)));

        CollectionModel<OutputProjectDTO> expected = CollectionModel.of(List.of(dto));

        //Act
        CollectionModel<OutputProjectDTO> result = projectService.getCurrentProjectsByUser(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void deleteProjectNotExist() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "not exist";
            ProjectID projectID = mock(ProjectID.class);

            when(projectIDFactory.create(id)).thenReturn(projectID);
            when(projRepo.delete(projectID)).thenReturn(false);
            //Act
            projectService.deleteProjectRequest(id);
        });
    }

    @Test
    void deleteProjectSuccess() {
        //Arrange
        String id = "not exist";
        ProjectID projectID = mock(ProjectID.class);

        when(projectIDFactory.create(id)).thenReturn(projectID);
        when(projRepo.delete(projectID)).thenReturn(true);
        //Act
        boolean result = projectService.deleteProjectRequest(id);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldGetProjectStatus() {
        //Arrange
        CollectionModel<OutputStatusDTO> outputProjectDTOS = CollectionModel.of(new ArrayList<>());
        when(statusMapper.toCollectionDto(any())).thenReturn(outputProjectDTOS);
        ProjectStatusEnum[] statusValues = {ProjectStatusEnum.PLANNED, ProjectStatusEnum.INCEPTION};

        when(ProjectStatusEnum.values()).thenReturn(statusValues);
        //Act
        CollectionModel<OutputStatusDTO> result = projectService.getProjectStatus();
        //Assert
        assertEquals(outputProjectDTOS,result);
    }

}