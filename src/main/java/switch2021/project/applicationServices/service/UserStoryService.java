package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.dtoModel.dto.OutputUserStoryDTO;
import switch2021.project.dtoModel.dto.UpdateUserStoryDTO;
import switch2021.project.dtoModel.dto.UserStoryDTO;
import switch2021.project.dtoModel.dto.UserStoryUpdateDTO;
import switch2021.project.entities.factories.factoryInterfaces.IUserStoryFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsHourFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsPriorityFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserStoryIDFactory;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.dtoModel.mapper.UserStoryMapper;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryStatusEnum;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserStoryService {

    /**
     * Attributes
     **/
    @Autowired
    private IUserStoryRepo iUserStoryRepo;
    @Autowired
    private IProjectRepo iProjectRepo;
    @Autowired
    private UserStoryMapper userStoryMapper;
    @Autowired
    private IUserStoryFactory iUserStoryFactory;
    @Autowired
    private IUserStoryIDFactory usIdFactory;
    @Autowired
    private IProjectIDFactory iProjectIDFactory;
    @Autowired
    private IUsHourFactory usHourFactory;
    @Autowired
    private IUsPriorityFactory priorityFactory;

    /**
     * Create and save a User Story (US009)
     */
    public OutputUserStoryDTO createAndSaveUserStory(UserStoryDTO userStoryDTO) {
        ProjectID projID = createProjectId(userStoryDTO.getProjectID());

        if (!iProjectRepo.existsById(projID)) {
            throw new NullPointerException("Project does not exist");
        }

        UserStory newUserStory = iUserStoryFactory.createUserStory(userStoryDTO);

        if (iUserStoryRepo.existsUserStoryByID(newUserStory.getUserStoryID())) {
            throw new IllegalArgumentException("User story already exist.");
        }
        UserStory usSaved = iUserStoryRepo.save(newUserStory);
        return userStoryMapper.toDto(usSaved);
    }

    public OutputUserStoryDTO showAUserStory(String id) {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(usId);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }
        return userStoryMapper.toDto(userStory);
    }

    public CollectionModel<OutputUserStoryDTO> getAllUserStories() {
        List<UserStory> allUserStories = iUserStoryRepo.findAll();
        return userStoryMapper.toCollectionDto(allUserStories);
    }


    /**
     * Consult a Product Backlog of a Project (US018)
     */
    public CollectionModel<OutputUserStoryDTO> consultProductBacklog(String projectId) throws Exception {
        ProjectID projID = createProjectId(projectId);

        if (!iProjectRepo.existsById(projID)) {
            throw new Exception("Project does not exist");
        }
        List<UserStory> productBacklog = iUserStoryRepo.findProductBacklog(projectId);
        return userStoryMapper.toCollectionDto(productBacklog);
    }


    /**
     * Update data of a User Story (US019 and US021)
     */
    public OutputUserStoryDTO updateUSData(String id, UpdateUserStoryDTO updateDTO) {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(usId);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }
        if (updateDTO.getPriority() != 0) {
            userStory.updatePriority(priorityFactory.create(updateDTO.getPriority()));
        }
        if (updateDTO.getTimeEstimate() != 0) {
            userStory.updateTimeEstimate(usHourFactory.create(updateDTO.getTimeEstimate()));
        }
        UserStory updated = iUserStoryRepo.save(userStory);
        return userStoryMapper.toDto(updated);
    }

    public OutputUserStoryDTO startUserStory(String id) throws Exception {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(usId);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }

        if (userStory.getUsEndDate() != null) {
            throw new IllegalArgumentException("This User Story is already closed!");
        } else if (userStory.getUsStartDate() != null) {
            throw new IllegalArgumentException("This User Story is already started!");
        } else {
            userStory.startUserStory();
        }

        UserStory updated = iUserStoryRepo.save(userStory);
        return userStoryMapper.toDto(updated);
    }

    public OutputUserStoryDTO cancelUserStory(String id) {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(usId);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }

        if (userStory.getUsEndDate() != null) {
            throw new IllegalArgumentException("This User Story is already closed!");
        } else {
            userStory.cancelUserStory();
        }

        UserStory updated = iUserStoryRepo.save(userStory);
        return userStoryMapper.toDto(updated);
    }

    public OutputUserStoryDTO finishUserStory(String id) throws IllegalArgumentException {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(usId);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }

        if (userStory.getUsEndDate() != null) {
            throw new IllegalArgumentException("This User Story is already closed!");
        } else {
            userStory.finishUserStory();
        }

        UserStory updated = iUserStoryRepo.save(userStory);
        return userStoryMapper.toDto(updated);
    }


    /**
     * Refine a board user story of the Product Backlog (US020)
     */
    public CollectionModel<OutputUserStoryDTO> refineUserStory(String id, UserStoryDTO inDto) {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(usId);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }
        List<UserStory> userStories = new ArrayList<>();

        //Create new User Story
        UserStory refinedUs = iUserStoryFactory.createUserStory(inDto);
        refinedUs.assignParentUserStory(userStory);
        UserStory savedRefinedUs = iUserStoryRepo.save(refinedUs);

        //Update old User Story
        userStories.add(savedRefinedUs);
        userStory.refinedUs();
        UserStory updated = iUserStoryRepo.save(userStory);

        //Add User Stories to show as response
        userStories.add(updated);
        return userStoryMapper.toCollectionDto(userStories);
    }


    /**
     * Delete User Story
     */
    public void deleteAUserStory(String id) {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);

        if (!iUserStoryRepo.existsUserStoryByID(usId)) {
            throw new IllegalArgumentException("User Story does not exist");
        }
        iUserStoryRepo.deleteByUserStoryId(usId);
    }


    /**
     * Create User Story ID method
     */
    public UserStoryID createUserStoryIdByStringInputFromController(String id) {
        String[] x = id.split("&");
        String pId = x[0];
        String uTitle = x[1];
        return usIdFactory.create(pId, uTitle);
    }

    /**
     * Create Project ID method
     */
    public  ProjectID createProjectId(String projectId) {
        return iProjectIDFactory.create(projectId);
    }

    public OutputUserStoryDTO updateUserStoryPartially(String id, UserStoryUpdateDTO userStoryUpdateDTO) {
        UserStoryID userStoryID = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(userStoryID);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }

        userStory.setPriority(new UsPriority(userStoryUpdateDTO.priority));
        userStory.setDescription(new Description(userStoryUpdateDTO.description));
        userStory.setTimeEstimate(new UsHour(userStoryUpdateDTO.timeEstimate));
        if(userStoryUpdateDTO.usStartDate != null && !userStoryUpdateDTO.usStartDate.isEmpty())
            userStory.setUsStartDate(LocalDate.parse(userStoryUpdateDTO.usStartDate));
        if(userStoryUpdateDTO.usEndDate != null && !userStoryUpdateDTO.usEndDate.isEmpty())
            userStory.setUsEndDate(LocalDate.parse(userStoryUpdateDTO.usEndDate));

        if (userStory.getUsEndDate() != null && userStory.getUsStartDate() != null) {
           userStory.setUsStatus(UserStoryStatusEnum.FINISHED);
        }
        if (userStory.getUsStartDate() != null && userStory.getUsEndDate() == null) {
            userStory.setUsStatus(UserStoryStatusEnum.IN_PROGRESS);
        }
        iUserStoryRepo.save(userStory);
        return userStoryMapper.toDto(userStory);
    }
}

