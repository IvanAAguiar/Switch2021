package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
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
import switch2021.project.entities.valueObjects.vos.Customer;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import javax.net.ssl.SSLException;
import java.time.LocalDate;
import java.util.*;


@Service
public class ProjectService {

    @Autowired
    private IProjectRepo projRepo;
    @Autowired
    private ITypologyRepo iTypologyRepo;
    @Autowired
    private IProjectFactory iProjectFactory;
    @Autowired
    private ProjectMapper projMapper;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IUserIDFactory userIDFactory;
    @Autowired
    private IResourceRepo resRepo;
    @Autowired
    private ManagementResourcesService resService;
    @Autowired
    private IProjectWebRepository iProjectWebRepository;
    @Autowired
    private ITypologyIDFactory typologyIDFactory;
    @Autowired
    private IProjectIDFactory projectIDFactory;
    @Autowired
    private IDescriptionFactory descriptionFactory;
    @Autowired
    private INumberOfSprintsFactory numberOfSprintsFactory;
    @Autowired
    private IBudgetFactory budgetFactory;
    @Autowired
    private ISprintDurationFactory sprintDurationFactory;
    @Autowired
    private StatusMapper statusMapper;


    /**
     * Methods to execute controller requests to Project Aggregate
     */
    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) throws Exception {
        Project newProject;

        if (iTypologyRepo.existsByTypologyId(typologyIDFactory.createIdWithString(projDTO.getTypology()))) {
            newProject = iProjectFactory.createProject(projDTO);
            ProjectID projID = generatedProjectId();

            newProject.setProjectCode(projID);
        } else {
            throw new IllegalArgumentException("Typology does not exist");
        }
        if (!projRepo.existsById(newProject.getProjectCode())) {
            Project savedProject = projRepo.save(newProject);
            return projMapper.model2Dto(savedProject);
        } else {
            throw new IllegalArgumentException("Project already exist.");
        }

    }

    private ProjectID generatedProjectId() {
        String format = "Project_" + LocalDate.now().getYear() + "_";
        int sequenceNumber = projRepo.findAll().size() + 1;
        String id = format + sequenceNumber;
        return projectIDFactory.create(id);
    }

    /**
     * US008
     */

    public OutputProjectDTO updateProjectPartially(String id, EditProjectInfoDTO editProjectInfoDTO) {

        ProjectID projID = projectIDFactory.create(id);

        Optional<Project> opProject = projRepo.findById(projID);

        if (opProject.isPresent()) {
            Project proj = opProject.get();
            proj.setProjectName(descriptionFactory.createDescription(editProjectInfoDTO.getProjectName()));
            proj.setDescription(descriptionFactory.createDescription(editProjectInfoDTO.getDescription()));
            proj.setStartDate(LocalDate.parse(editProjectInfoDTO.getStartDate()));
            proj.setNumberOfSprints(numberOfSprintsFactory.create(Integer.parseInt(editProjectInfoDTO.getNumberOfSprints())));
            proj.setBudget(budgetFactory.create(Integer.parseInt(editProjectInfoDTO.getBudget())));
            proj.setSprintDuration(sprintDurationFactory.create(Integer.parseInt(editProjectInfoDTO.getSprintDuration())));


            proj.setProjectStatus(ProjectStatusEnum.valueOf(editProjectInfoDTO.getProjectStatus().toUpperCase()));
            proj.setCustomer(Customer.create(editProjectInfoDTO.getCustomer()));
            proj.setEndDate(LocalDate.parse(editProjectInfoDTO.getEndDate()));

            TypologyID typoId = typologyIDFactory.createIdWithString(editProjectInfoDTO.getTypology());

            if (iTypologyRepo.existsByTypologyId(typoId)) {
                proj.setTypologyId(typoId);
            }

            Project savedProject = projRepo.save(proj);

            return projMapper.model2Dto(savedProject);
        }

        throw new IllegalArgumentException("Project does not exist.");
    }

    public Map<String, CollectionModel<PartialProjectDTO>> getAllProjects() throws SSLException {

        List<Project> projects = projRepo.findAll();
        List<Project> projectsWeb = iProjectWebRepository.findAll();

        CollectionModel<PartialProjectDTO> outputProjectDTOS = projMapper.toCollectionDto2(projectsWeb, true);
        CollectionModel<PartialProjectDTO> outputProjectDTOS2 = projMapper.toCollectionDto2(projects, false);

        Map<String, CollectionModel<PartialProjectDTO>> mapProjects = new HashMap<>();
        mapProjects.put("internalProjects", outputProjectDTOS2);
        mapProjects.put("externalProjects", outputProjectDTOS);

        return mapProjects;
    }

    public OutputProjectDTO showProject(String id) throws Exception {

        ProjectID projID = projectIDFactory.create(id);

        Optional<Project> foundProject = projRepo.findById(projID);

        Project project = foundProject.flatMap(proj -> foundProject).orElse(null);

        if (project == null) {
            throw new IllegalArgumentException("Project does not exist");
        }

        return projMapper.model2Dto(project);
    }

    public CollectionModel<OutputProjectDTO> getCurrentProjectsByUser(String UserId) {
        UserID uId = userIDFactory.createUserID(UserId);
        Optional<User> foundUser = userRepo.findByUserId(uId);

        User user = foundUser.flatMap(u -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("User dos not exist");
        }
        List<Resource> userResources = resRepo.findAllByUser(uId);
        List<Resource> currentUserResources = resService.currentResourcesByDate(userResources);
        List<ProjectID> resourceProjects = resService.listProjectsOfResources(currentUserResources);
        List<Project> projects = new ArrayList<>();

        for (ProjectID x : resourceProjects) {
            Optional<Project> y = projRepo.findById(x);
            if (y.isEmpty()) {
                throw new NullPointerException("User is not allocated in any project!");
            }
            projects.add(y.flatMap(z -> y).orElse(null));
        }
        return projMapper.toCollectionDto(projects, false);
    }

    public boolean deleteProjectRequest(String id) {

        ProjectID projectId = projectIDFactory.create(id);

        if (!projRepo.delete(projectId)) {
            throw new IllegalArgumentException("Project does not exist");
        }

        return true;
    }

    public CollectionModel<OutputStatusDTO> getProjectStatus() {
        List<String> projectStatusEnum = ProjectStatusEnum.getProjectStatus();
        return statusMapper.toCollectionDto(projectStatusEnum);
    }
}
