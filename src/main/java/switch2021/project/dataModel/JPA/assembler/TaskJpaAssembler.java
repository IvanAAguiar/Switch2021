package switch2021.project.dataModel.JPA.assembler;


import org.springframework.stereotype.Component;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.dataModel.JPA.TaskJpa;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.TaskStatus;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import java.time.LocalDate;
import java.util.List;

@Component
public class TaskJpaAssembler {

    public TaskJpa toData(Task task) {
        String taskDescription = task.getDescription().getText();
        String taskType = null;
        if (!(task.getType() == null)) {
            taskType = task.getType().toString();
        }
        String taskStatus = null;
        if (!(task.getStatus() == null)) {
            taskStatus = task.getStatus().toString();
        }
        double taskEffortEstimate = task.getEffortEstimate().getEffortHours();
        String taskStartDate = null;
        if (!(task.getStartDate() == null)) {
            taskStartDate = task.getStartDate().toString();
        }
        String taskEndDate = null;
        if (!(task.getEndDate() == null)) {
            taskEndDate = task.getEndDate().toString();
        }
        List<TaskID> taskPrecedenceList = task.getPrecedenceList();
        List<TaskEffort> taskEffortList = task.getRegisteredEfforts();

        String taskID = task.getTaskID().toString();
        Description taskTitle = task.getTaskID().getTaskTitle();
        TaskContainerID sprintOrUsID = task.getTaskID().getTaskContainerID();
        ProjectID projectID = null;
        UsTitle usTitle = null;
        Description sprintName = null;
        if (sprintOrUsID instanceof UserStoryID) {
            projectID = ((UserStoryID) sprintOrUsID).getProjectID();
            usTitle = ((UserStoryID) sprintOrUsID).getUsTitle();
        }
        if (sprintOrUsID instanceof SprintID) {
            projectID = ((SprintID) sprintOrUsID).getProjectID();
            sprintName = ((SprintID) sprintOrUsID).getSprintName();
        }

        ResourceID taskResponsibleID = task.getResponsible();
        UserID userId = taskResponsibleID.getUser();
        String resourceStartDate = null;
        if (!(taskResponsibleID.getStartDate() == null)) {
            resourceStartDate = taskResponsibleID.getStartDate().toString();
        }
        return new TaskJpa(taskID, projectID, usTitle, sprintName, taskTitle, taskDescription, taskType,taskStatus, taskEffortEstimate,
                taskStartDate, taskEndDate, userId, resourceStartDate, taskEffortList, taskPrecedenceList);
    }

    public Task toDomain(TaskJpa taskJpa) {
        Description taskDescription = new Description(taskJpa.getTaskDescription());
        TaskStatus taskStatus = null;
        if (!(taskJpa.getTaskStatus() == null)) {
            taskStatus = TaskStatus.valueOf(taskJpa.getTaskStatus());
        }
        TaskTypeEnum taskType = null;
        if (!(taskJpa.getTaskType() == null)) {
            taskType = TaskTypeEnum.valueOf(taskJpa.getTaskType());
        }
        EffortEstimate taskEffortEstimate = new EffortEstimate(taskJpa.getTaskEffortEstimate());
        LocalDate taskStartDate = null;
        if (!(taskJpa.getTaskStartDate() == null)) {
            taskStartDate = LocalDate.parse(taskJpa.getTaskStartDate());
        }
        LocalDate taskEndDate = null;
        if (!(taskJpa.getTaskEndDate() == null)) {
            taskEndDate = LocalDate.parse(taskJpa.getTaskEndDate());
        }
        List<TaskEffort> taskEffortList = taskJpa.getTaskEffortList();
        List<TaskID> taskPrecedenceList = taskJpa.getTaskPrecedenceList();

        ProjectID projectID = taskJpa.getProjectID();
        UsTitle usTitle = taskJpa.getUsTitle();
        Description sprintName = taskJpa.getSprintName();

        TaskContainerID sprintOrUsID = null;
        if (usTitle == null && sprintName != null) {
            sprintOrUsID = new SprintID(projectID, sprintName);
        }
        if (usTitle != null && sprintName == null) {
            sprintOrUsID = new UserStoryID(projectID, usTitle);
        }

        Description taskTitle = taskJpa.getTaskTitle();
        TaskID taskID = new TaskID(sprintOrUsID, taskTitle);

        UserID resourceUserID = taskJpa.getResourceUserID();
        LocalDate resourceStartDate = null;
        if (!(taskJpa.getResourceStartDate() == null)) {
            resourceStartDate = LocalDate.parse(taskJpa.getResourceStartDate());
        }
        ResourceID responsible = new ResourceID(resourceUserID, projectID, resourceStartDate);

        return new Task(taskID, taskDescription, taskType, taskStatus, taskEffortEstimate, taskStartDate, taskEndDate,
                responsible, taskEffortList, taskPrecedenceList);
    }
}
