package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryOfSprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.dtoModel.mapper.SprintMapper;
import switch2021.project.dtoModel.mapper.UserStoryOfSprintMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.factories.factoryInterfaces.ISprintFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ISprintIDFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SprintService {

    /**
     * Attributes
     */
    @Autowired
    private ISprintRepo sprintRepo;
    @Autowired
    private SprintMapper sprintMapper;
    @Autowired
    private ISprintFactory sprintFactory;
    @Autowired
    private IUserStoryRepo usRepo;
    @Autowired
    private IProjectRepo projRepo;
    @Autowired
    private IUserStoryOfSprintRepo userStoryOfSprintRepo;
    @Autowired
    private UserStoryOfSprintMapper userStoryOfSprintMapper;
    @Autowired
    private IProjectIDFactory projectIDFactory;
    @Autowired
    private ISprintIDFactory sprintIDFactory;

    /**
     * Create and Save a New Sprint
     */

    public OutputSprintDTO createAndSaveSprint(NewSprintDTO inDTO) throws Exception {
        ProjectID projID = projectIDFactory.create(inDTO.getProjectID());
        if (!projRepo.existsById(projID)) {
            throw new NullPointerException("Project does not exist");
        }
        Sprint sprint = sprintFactory.createSprint(inDTO);

        if (!Objects.equals(inDTO.startDate, "")) {
            String sprintId = inDTO.projectID + "&" + inDTO.name;
            validateSprintStartDate(sprintId, inDTO.startDate);
            sprint.setStartDate(LocalDate.parse(inDTO.startDate));
        }
        if (sprintRepo.existsSprintByID(sprint.getSprintID())) {
            throw new IllegalArgumentException("Sprint already exist.");
        }
        Sprint sprintSaved = sprintRepo.save(sprint);

        return sprintMapper.toDTO(sprintSaved);
    }

    /**
     * Find All Sprints
     */

    public CollectionModel<OutputSprintDTO> showAllSprints() {
        List<Sprint> allSprints = sprintRepo.findAllSprints();
        return sprintMapper.toCollectionDto(allSprints);
    }

    public void deleteSprint(String id) {
        SprintID sprintID = createSprintIdByStringInputFromController(id);

        if (!sprintRepo.existsSprintByID(sprintID)) {
            throw new NullPointerException("Sprint does not exist");
        }
        sprintRepo.deleteSprint(sprintID);
    }

    public CollectionModel<OutputSprintDTO> showSprintsOfAProject(String projId) throws Exception {

        if (projRepo.existsById(new ProjectID(projId))) {

            List<Sprint> allSprintsOfAProject = sprintRepo.findAllByProjectID(new ProjectID(projId));

            return sprintMapper.toCollectionDto(allSprintsOfAProject);

        } else {

            throw new Exception("Project does not exist");
        }
    }

    public CollectionModel<OutSprintDTO> showSprintsInProject(String projId) throws Exception {

        if (projRepo.existsById(new ProjectID(projId))) {

            List<Sprint> allSprintsOfAProject = sprintRepo.findAllByProjectID(new ProjectID(projId));

            return sprintMapper.toCollectionsDto(allSprintsOfAProject);

        } else {

            throw new Exception("Project does not exist");
        }
    }

    /**
     * Start a Sprint
     *
     * @param id  - Sprint Id
     * @param dto - Sprint DTO
     * @return - Returns an output dto of the sprint, or a null object in case it fails to save
     * @throws Exception - The start date validation can throw an exception in case the criteria inst met.
     */
    public OutputSprintDTO startSprint(String id, StartSprintDTO dto) throws Exception {

        long sprintDuration = validateSprintStartDate(id, dto.getStartDate());

        SprintID sprintID = createSprintIdByStringInputFromController(id);

        Optional<Sprint> opSprint = sprintRepo.findBySprintID(sprintID);

        if (opSprint.isPresent()) {

            opSprint.get().setStartDate(LocalDate.parse(dto.getStartDate()));
            opSprint.get().setEndDate(LocalDate.parse(dto.getStartDate()).plusDays(sprintDuration));

            sprintRepo.deleteSprint(opSprint.get().getSprintID());

            Sprint savedSprint = sprintRepo.save(opSprint.get());

            return sprintMapper.toDTO(savedSprint);
        }

        throw new Exception("Sprint doesnt exist");
    }

    /**
     * Adds User Story to a specific sprintBacklog(aka ScrumBoard)
     *
     * @param id             Sprint id
     * @param userStoryIdDTO User Story DTO
     * @return Output DTO of Sprint
     * @throws Exception in case of missing parameter return
     */

    @Transactional
    public UserStoryOfSprintDTO addUserStoryToSprintBacklog(String id, UserStoryIdDTO userStoryIdDTO) throws Exception {
        SprintID sprintID = createSprintIdByStringInputFromController(id);

        Optional<Sprint> sprint = sprintRepo.findBySprintID(sprintID);

        UserStoryID userStoryID = new UserStoryID(new ProjectID(userStoryIdDTO.projectID),
                                                  new UsTitle(userStoryIdDTO.title));

        Optional<UserStory> foundUs = usRepo.findByUserStoryId(userStoryID);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }

        if (sprint.isPresent()) {
            UserStoryOfSprint savedSprint =
                    userStoryOfSprintRepo.save(new UserStoryOfSprint(userStory.getUserStoryID(),
                                                                     UserStoryOfSprintStatus.Todo,
                                                                     sprint.get().getSprintID().getSprintName().getText()));

            return userStoryOfSprintMapper.toDTO(savedSprint);
        } else {
            throw new Exception("Sprint or User Story doesn't exist!");
        }

    }

    public CollectionModel<UserStoryOfSprintDTO> showScrumBoard(String id) throws Exception {
        CollectionModel<UserStoryOfSprintDTO> dtoList;
        SprintID sprintID = createSprintIdByStringInputFromController(id);
        if (!sprintRepo.existsSprintByID(sprintID)) {
            throw new NullPointerException("Sprint does not exist!");
        }
        List<UserStoryOfSprint> userStoryOfSprintList = userStoryOfSprintRepo.findAllUserStoriesBySprintID(sprintID);

        dtoList = userStoryOfSprintMapper.toCollectionDTO(userStoryOfSprintList);

        return dtoList;
    }

    public CollectionModel<UserStoryOfSprintDTO> showScrumBoardOfSprint(String id) throws Exception {
        CollectionModel<UserStoryOfSprintDTO> dtoList;
        /*String[] values = id.split("&");
        ProjectID projectID = new ProjectID(values[0]);
        Description sprintName = new Description((values[1]));

        SprintID sprintID = new SprintID(projectID, sprintName);*/
        SprintID sprintID = createSprintIdByStringInputFromController(id);
        List<UserStoryOfSprint> userStoryOfSprintList = userStoryOfSprintRepo.findAllUserStoriesBySprintID(sprintID);

        dtoList = userStoryOfSprintMapper.model2CollectionDTO(userStoryOfSprintList);

        return dtoList;
    }

    public UserStoryOfSprintDTO changeStatusScrumBoard(String id, UserStoryOfSprintDTO userStoryDTO) throws Exception {
        UserStoryOfSprintDTO userStoryOfSprintDTO;
        SprintID sprintID = createSprintIdByStringInputFromController(id);
        if (!sprintRepo.existsSprintByID(sprintID)) {
            throw new NullPointerException("Sprint does not exist!");
        }
        List<UserStoryOfSprint> userStoryOfSprintList = userStoryOfSprintRepo.findAllUserStoriesBySprintID(sprintID);

        for (UserStoryOfSprint userStoryOfSprint : userStoryOfSprintList) {
            if (Objects.equals(userStoryOfSprint.getUserStoryId().getProjectID().getCode(),
                               userStoryDTO.getProjectId())
                    && Objects.equals(userStoryOfSprint.getUserStoryId().getUsTitle().getTitleUs(),
                                      userStoryDTO.getUsTitle())
                    && Objects.equals(userStoryOfSprint.getSprintName(),
                                      sprintID.getSprintName().getText())) {

                userStoryOfSprint.setUserStoryOfSprintStatus(UserStoryOfSprintStatus.valueOf(userStoryDTO.getStatus()));

                UserStoryOfSprint savedSprint = userStoryOfSprintRepo.save(userStoryOfSprint);


                userStoryOfSprintDTO = userStoryOfSprintMapper.toDTO(savedSprint);


                return userStoryOfSprintDTO;
            }
        }

        throw new Exception("User Story not found");
    }

    public long validateSprintStartDate(String id, String date) throws Exception {
        String[] values = id.split("&");
        ProjectID projectID = new ProjectID(values[0]);

        Optional<Project> foundProject = projRepo.findById(projectID);

        Project project = foundProject.flatMap(proj -> foundProject).orElse(null);

        if (project == null) {
            throw new IllegalArgumentException("Project does not exist");
        }

        long sprintDuration = project.getSprintDuration().getSprintDurationDays();

        if (project.getStartDate().isAfter(LocalDate.parse(date))) {
            throw new Exception("Start date cant be before project start date");

        } else if (project.getEndDate() != null
                &&
                project.getEndDate().isBefore(LocalDate.parse(date))) {
            throw new Exception("Start date cant be after project end date");

        } else {
            return sprintDuration;
        }
    }

    public SprintID createSprintIdByStringInputFromController(String id) {
        String[] x = id.split("&");
        String projectID = x[0];
        String sprintName = x[1];
        return sprintIDFactory.create(projectID,sprintName);
    }

    public OutSprintDTO showSprintById (String id) throws Exception {
        SprintID sprintID = new SprintID(id);

        Optional<Sprint> opSprint = sprintRepo.findBySprintID(sprintID);

        Sprint sprint = opSprint.flatMap(sprint1 -> opSprint).orElse(null);

        if(sprint != null) {
            return sprintMapper.model2DTO(sprint);
        }

        else throw new Exception("Sprint doesnt exist");
    }
}