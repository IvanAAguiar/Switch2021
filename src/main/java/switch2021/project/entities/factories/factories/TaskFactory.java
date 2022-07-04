package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IEffortEstimateFactory;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.factories.factoryInterfaces.ITaskFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.EffortEstimate;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.TaskID;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

@Component
public class TaskFactory implements ITaskFactory {

    @Autowired
    private IDescriptionFactory descriptionFactory;
    @Autowired
    private IEffortEstimateFactory effortEstimateFactory;
    @Autowired
    private ITaskIDFactory taskIDFactory;
    @Autowired
    private ISprintIDFactory sprintIDFactory;
    @Autowired
    private IUserStoryIDFactory userStoryIDFactory;
    @Autowired
    private IResourceIDFactory resourceIDFactory;

    @Override
    public Task createTask(TaskDTO taskDTO) {
        TaskContainerID taskContainerID;
        if(taskDTO.usTitle==null && taskDTO.sprintName!=null){
            taskContainerID = sprintIDFactory.create(taskDTO.projectId, taskDTO.sprintName);
        } else {
            taskContainerID= userStoryIDFactory.create(taskDTO.projectId,taskDTO.usTitle);
        }
        TaskID taskID = taskIDFactory.createTaskID(taskContainerID, taskDTO.taskTitle);
        Description description = descriptionFactory.createDescription(taskDTO.taskDescription);
        EffortEstimate effortEstimate = effortEstimateFactory.create(taskDTO.taskEffortEstimate);
        TaskTypeEnum taskType = TaskTypeEnum.valueOf(taskDTO.taskType);
        ResourceID resourceID = resourceIDFactory.create(taskDTO.systemUserID, taskDTO.projectId, taskDTO.resourceStartDate);

        return new Task(taskID, description, effortEstimate, taskType, resourceID);
    }
}
