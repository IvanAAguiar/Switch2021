package switch2021.project.applicationServices.service;

import lombok.SneakyThrows;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.OutputProjectRoleDTO;
import switch2021.project.dtoModel.dto.DefineRoleOfResourceDTO;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.dtoModel.dto.OutputStatusDTO;
import switch2021.project.dtoModel.mapper.ProjectRoleMapper;
import switch2021.project.dtoModel.mapper.ResourceMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.factories.factories.ResourceFactory;
import switch2021.project.entities.factories.factoryInterfaces.IResourceFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ResourceServiceTest {


    @MockBean
    IProjectRepo projRepo;
    @MockBean
    IResourceRepo resRepo;
    @MockBean
    IUserRepo userRepo;
    @MockBean
    ResourceMapper mapper;
    @MockBean
    ProjectRoleMapper roleMapper;
    @MockBean
    ManagementResourcesService manageSrv;
    @MockBean
    IResourceFactory resourceFactory;
    @MockBean
    IResourceIDFactory iResourceIDFactory;
    @MockBean
    IProjectIDFactory projectIDFactory;
    @MockBean
    IUserIDFactory userIDFactory;
    @InjectMocks
    ResourceService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void CreateAndSaveResourceSuccess() {
//        //Arrange
//        CreateResourceDTO dto = mock(CreateResourceDTO.class);
//
//        String email = "tdc@mymail.com";
//        String code = "Project_2022_3";
//        String data = "2022-03-10";
//        String data2 = "2023-03-10";
//        String role = "ScrumMaster";
//        Double percentage = 0.9;
//
//        when(dto.getSystemUserID()).thenReturn(email);
//        when(dto.getProjectId()).thenReturn(code);
//        when(dto.getStartDate()).thenReturn(data);
//        when(dto.getStartDate()).thenReturn(data2);
//        when(dto.getPercentageOfAllocation()).thenReturn(percentage);
//        when(dto.getProjectRole()).thenReturn(role);
//
//
//        UserID userId = mock(UserID.class);
//        when(userIDFactory.createUserID(email)).thenReturn(userId);
//        when(userRepo.existsById(userId)).thenReturn(true);
//
//        ProjectID projID = mock(ProjectID.class);
//        when(projectIDFactory.create(code)).thenReturn(projID);
//        when(projRepo.existsById(projID)).thenReturn(true);
//
//        List<Resource> projectTeamList = new ArrayList<>();
//        Resource x = mock(Resource.class);
//        projectTeamList.add(x);
//        when(resRepo.findAllByProject(projID)).thenReturn(projectTeamList);
//
//        when(manageSrv.validateProjectRole(projectTeamList, dto.getStartDate(), dto.getEndDate(), dto.getProjectRole())).thenReturn(true);
//
//        Project project = mock(Project.class);
//        when(projRepo.findById(projID)).thenReturn(Optional.of(project));
//
//        when(project.isActiveInThisDate(any())).thenReturn(true);
//
//        List<Resource> resourceAllocatedProjects = new ArrayList<>();
//        when(resRepo.findAllByUser(userId)).thenReturn(resourceAllocatedProjects);
//
//        when(manageSrv.validateAllocation(projectTeamList, dto.getStartDate(), dto.getEndDate(), percentage)).thenReturn(true);
//
//        Resource newResource = mock(Resource.class);
//        ResourceID id = mock(ResourceID.class);
//        newResource.setId(id);
//        when(resourceFactory.createResource(dto)).thenReturn(newResource);
//
//        List<Resource> resources = new ArrayList<>();
//        ProjectID projID2 = mock(ProjectID.class);
//        when(resRepo.findAllByProject(projID2)).thenReturn(resources);
//
//        //jumping
//
//        when(resRepo.existsById(id)).thenReturn(true);
//
//        Resource saved = mock(Resource.class);
//        when(resRepo.save(newResource)).thenReturn(saved);
//
//        OutputResourceDTO expected = any();
//        when(mapper.toDto(saved)).thenReturn(expected);
//
//        OutputResourceDTO result = service.createAndSaveResource(dto);
//
//        assertEquals(expected, result);
//
//
//
//    }


    @SneakyThrows
    @Test
    void ShowResourceRequestedSuccess() {
        //Arrange
        ResourceID resID = mock(ResourceID.class);
        when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
        when(projRepo.existsById(any())).thenReturn(true);
        when(userRepo.existsById(any())).thenReturn(true);
        Resource resource = mock(Resource.class);
        when(resRepo.findById(resID)).thenReturn(Optional.of(resource));
        OutputResourceDTO outputDTO = mock(OutputResourceDTO.class);
        when(mapper.toDto(resource)).thenReturn(outputDTO);
        //Act
        OutputResourceDTO result = service.showResourceRequested("tdc@mymail.com&Project_2022_3&2022-03-10");
        //Assert
        assertEquals(outputDTO, result);
    }

    @Test
    void ShowResourceRequestedNoProject() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(projRepo.existsById(any())).thenReturn(false);
            when(userRepo.existsById(any())).thenReturn(true);
            Resource resource = mock(Resource.class);
            when(resRepo.findById(resID)).thenReturn(Optional.of(resource));
            OutputResourceDTO outputDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(resource)).thenReturn(outputDTO);
            //Act
            service.showResourceRequested("tdc@mymail.com&Project_2022_3&2022-03-10");
        });
    }

    @Test
    void ShowResourceRequestedNoUser() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(projRepo.existsById(any())).thenReturn(true);
            when(userRepo.existsById(any())).thenReturn(false);
            Resource resource = mock(Resource.class);
            when(resRepo.findById(resID)).thenReturn(Optional.of(resource));
            OutputResourceDTO outputDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(resource)).thenReturn(outputDTO);
            //Act
            service.showResourceRequested("tdc@mymail.com&Project_2022_3&2022-03-10");
        });
    }

    @Test
    void ShowResourceRequestedNoResource() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(projRepo.existsById(any())).thenReturn(true);
            when(userRepo.existsById(any())).thenReturn(false);
            Resource resource = mock(Resource.class);
            when(resRepo.findById(resID)).thenReturn(null);
            OutputResourceDTO outputDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(resource)).thenReturn(outputDTO);
            //Act
            service.showResourceRequested("tdc@mymail.com&Project_2022_3&2022-03-10");
        });
    }

    @Test
    void showCurrentProjectTeamSuccess() {
        //Arrange
        String projectId = "projID";
        ProjectID projectID = mock(ProjectID.class);
        CollectionModel<OutputResourceDTO> outputResourcesDTOs = CollectionModel.empty();
        Resource res = mock(Resource.class);
        when(projectIDFactory.create(projectId)).thenReturn(projectID);
        when(projRepo.existsById(projectID)).thenReturn(true);
        List<Resource> resources = new ArrayList<>();
        when(resRepo.findAllByProject(projectID)).thenReturn(resources);
        List<Resource> projectTeam = new ArrayList<>();
        when(manageSrv.currentResourcesByDate(resources)).thenReturn(projectTeam);
        projectTeam.add(res);
        when(mapper.toCollectionDto(projectTeam)).thenReturn(outputResourcesDTOs);
        //Act
        CollectionModel<OutputResourceDTO> result = service.showCurrentProjectTeam(projectId);
        //Assert
        assertEquals(outputResourcesDTOs, result);
    }

    @Test
    void ShowCurrentProjectTeamNoResources() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            String projectId = "projID";
            ProjectID projectID = mock(ProjectID.class);
            CollectionModel<OutputResourceDTO> outputResourcesDTOs = CollectionModel.empty();
            when(projectIDFactory.create(projectId)).thenReturn(projectID);
            when(projRepo.existsById(projectID)).thenReturn(true);
            List<Resource> resources = new ArrayList<>();
            when(resRepo.findAllByProject(projectID)).thenReturn(resources);
            List<Resource> projectTeam = new ArrayList<>();
            when(manageSrv.currentResourcesByDate(resources)).thenReturn(projectTeam);
            when(mapper.toCollectionDto(projectTeam)).thenReturn(outputResourcesDTOs);
            //Act
            CollectionModel<OutputResourceDTO> result = service.showCurrentProjectTeam(projectId);
        });
    }

    @Test
    void showProjectTeamSuccess() {
        //Arrange
        ProjectID projID = mock(ProjectID.class);
        when(projectIDFactory.create(anyString())).thenReturn(projID);
        List<OutputResourceDTO> resourcesDto = new ArrayList<>();
        when(projRepo.existsById(projID)).thenReturn(true);
        List<Resource> projectTeam = new ArrayList<>();
        when(resRepo.findAllByProject(projID)).thenReturn(projectTeam);
        Resource x = mock(Resource.class);
        projectTeam.add(x);
        OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
        when(mapper.toDto(any())).thenReturn(outDTO);
        resourcesDto.add(outDTO);
        //Act
        List<OutputResourceDTO> result = service.showProjectTeam("ok");
        //Assert
        assertEquals(resourcesDto, result);
    }

    @Test
    void ShowProjectTeamNoResources() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ProjectID projID = mock(ProjectID.class);
            when(projectIDFactory.create(anyString())).thenReturn(projID);
            when(projRepo.existsById(projID)).thenReturn(true);
            when(resRepo.findAllByProject(projID)).thenReturn(null);
            OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(any())).thenReturn(outDTO);
            //Act
            service.showProjectTeam("ok");
        });
    }

    @Test
    void ShowProjectTeamNoProject() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ProjectID projID = mock(ProjectID.class);
            when(projectIDFactory.create(anyString())).thenReturn(projID);
            when(projRepo.existsById(projID)).thenReturn(false);
            List<Resource> projectTeam = new ArrayList<>();
            when(resRepo.findAllByProject(projID)).thenReturn(projectTeam);
            OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(any())).thenReturn(outDTO);
            //Act
            service.showProjectTeam("ok");
        });
    }

    @Test
    void getAllResourcesSuccess() {
        //Arrange
        Resource x = mock(Resource.class);
        List<Resource> allResources = new ArrayList<>();
        allResources.add(x);
        when(resRepo.findAll()).thenReturn(allResources);
        List<OutputResourceDTO> allResDto = new ArrayList<>();
        OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
        when(mapper.toDto(any())).thenReturn(outDTO);
        allResDto.add(outDTO);
        //Act
        List<OutputResourceDTO> result = service.showAllResources();
        //Assert
        assertEquals(allResDto, result);
    }

    @Test
    void getAllResourceNonExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            List<Resource> allResources = new ArrayList<>();
            when(resRepo.findAll()).thenReturn(allResources);
            OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(any())).thenReturn(outDTO);

            //Act
            service.showAllResources();
        });
    }
//
//    @Test
//    void defineProjectRoleSuccess() {
//        String id = "tcz@mymail.com&Project_2022_3&2022-03-10";
//        ProjectID projId = mock(ProjectID.class);
//        DefineRoleOfResourceDTO dto = mock(DefineRoleOfResourceDTO.class);
//
//        String email = "tcz@mymail.com";
//        String projid = "Project_2022_3";
//        String data = "2022-03-10";
//        String data2 = "2023-03-10";
//        Double percentage = 0.9;
//
//        ResourceID resID = mock(ResourceID.class);
//        when(iResourceIDFactory.create(email, projid, data)).thenReturn(resID);
//
//        List<Resource> resourcesByProject = new ArrayList<>();
//        when(resRepo.findAllByProject(any())).thenReturn(resourcesByProject);
//
//        Resource opFoundResource = mock(Resource.class);
//        when(resRepo.findById(resID)).thenReturn(Optional.of(opFoundResource));
//
//        Resource opFoundByRole = mock(Resource.class);
//        when(opFoundResource.hasProjectRole(anyString())).thenReturn(true);
//        when(opFoundResource.isActiveToThisDate(any())).thenReturn(true);
//
//        when(resourceFactory.createResourceByAnotherResource(resID,dto)).thenReturn(opFoundByRole);
//
//        when(projectIDFactory.create(id)).thenReturn(projId);
//        Project opProject = mock(Project.class);
//        when(projRepo.findById(projId)).thenReturn(Optional.of(opProject));
//
//        when(opProject.isActiveInThisDate(any())).thenReturn(true);
//
//        UserID userID = mock(UserID.class);
//        List<Resource> resourceAllocatedProjects = new ArrayList<>();
//        when(resRepo.findAllByUser(any())).thenReturn(resourceAllocatedProjects);
//
//
//
//        when(manageSrv.validateAllocation(resourceAllocatedProjects, data, data2, percentage)).thenReturn(true);
//
//        Resource expectedRes = mock(Resource.class);
//
//        when(resRepo.save(expectedRes)).thenReturn(expectedRes);
//
//        OutputResourceDTO expected = mock(OutputResourceDTO.class);
//        when(mapper.toDto(expectedRes)).thenReturn(expected);
//
//        OutputResourceDTO result = service.defineProjectRole(id, dto);
//
//        assertEquals(expected, result);
//
//    }

    @Test
    void deleteResourceTestSuccess() {
        //Assert
        assertDoesNotThrow(() -> {
            //Arrange
            String res = "tdc@mymail.com&Project_2022_3&2022-03-10";
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(resRepo.existsById(resID)).thenReturn(true);
            when(resRepo.deleteByResourceID(resID)).thenReturn(true);
            //Act
            service.deleteResourceRequest(res);
        });
    }

    @Test
    void deleteResourceNonExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            String res = "tdc@mymail.com&Project_2022_3&2022-03-10";
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(resRepo.existsById(resID)).thenReturn(false);
            when(resRepo.deleteByResourceID(resID)).thenReturn(true);
            //Act
            service.deleteResourceRequest(res);
        });
    }

    @Test
    public void findProjectRoles() {
        //Arrange
        List<String> roles = ProjectRole.getProjectRole();
        List<OutputProjectRoleDTO> dtos = new ArrayList<>();
        OutputProjectRoleDTO teamMember = mock(OutputProjectRoleDTO.class);
        OutputProjectRoleDTO scrumMaster = mock(OutputProjectRoleDTO.class);
        OutputProjectRoleDTO productOwner = mock(OutputProjectRoleDTO.class);
        OutputProjectRoleDTO projectManager = mock(OutputProjectRoleDTO.class);
        dtos.add(teamMember);
        dtos.add(scrumMaster);
        dtos.add(productOwner);
        dtos.add(projectManager);
        when(roleMapper.toCollectionDto(roles)).thenReturn(CollectionModel.of(dtos));
        //Act
        CollectionModel<OutputProjectRoleDTO> result = service.findProjectRoles();
        //Assert
        assertEquals(CollectionModel.of(dtos), result);
    }

}