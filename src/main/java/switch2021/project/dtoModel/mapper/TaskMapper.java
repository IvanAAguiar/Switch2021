package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskEffortDTO;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.interfaceAdapters.controller.TaskController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskMapper {

    public OutputTaskDTO toDto(Task task) {
        OutputTaskDTO outputTaskDTO = new OutputTaskDTO();
        outputTaskDTO.taskID = task.getTaskID().toString();
        outputTaskDTO.taskTitle = task.getTaskID().getTaskTitle().getText();

        TaskContainerID sprintOrUsID = task.getTaskID().getTaskContainerID();
        ProjectID projectID;
        UsTitle usTitle;
        Description sprintName;
        if (sprintOrUsID instanceof UserStoryID) {
            projectID = ((UserStoryID) sprintOrUsID).getProjectID();
            usTitle = ((UserStoryID) sprintOrUsID).getUsTitle();
            outputTaskDTO.sprintNameOrUsTitle = usTitle.getTitleUs();
            outputTaskDTO.projectID = projectID.getCode();
        }
        if (sprintOrUsID instanceof SprintID) {
            projectID = ((SprintID) sprintOrUsID).getProjectID();
            sprintName = ((SprintID) sprintOrUsID).getSprintName();
            outputTaskDTO.sprintNameOrUsTitle = sprintName.getText();
            outputTaskDTO.projectID = projectID.getCode();
        }
        outputTaskDTO.resourceID = task.getResponsible().toString();
        outputTaskDTO.userID = task.getResponsible().getUser().getEmail().getEmailText();
        outputTaskDTO.resourceStartDate = task.getResponsible().getStartDate().toString();
        outputTaskDTO.description = task.getDescription().getText();
        outputTaskDTO.type = task.getType().toString();
        outputTaskDTO.status = task.getStatus().toString();
        outputTaskDTO.effortEstimate = task.getEffortEstimate().getEffortHours();
        if (task.getStartDate() != null) {
            outputTaskDTO.taskStartDate = task.getStartDate().toString();
        }
        if (task.getEndDate() != null) {
            outputTaskDTO.taskEndDate = task.getEndDate().toString();
        }
        outputTaskDTO.registeredEfforts = effortsToDTO(task);
        outputTaskDTO.hoursSpent = Math.round(getHoursSpent(task.getRegisteredEfforts()) * 100.0) / 100.0;
        outputTaskDTO.percentageOfExecution =
                Math.round(((outputTaskDTO.hoursSpent / outputTaskDTO.effortEstimate) * 100) * 10.0) / 10.0;
        /*
         * Add HATEOAS to OUTPUT DTO
         */
        //Add self relation
        outputTaskDTO.add(linkTo(methodOn(TaskController.class).getById(outputTaskDTO.taskID)).withSelfRel());
        //Add register effort
        outputTaskDTO.add(linkTo(methodOn(TaskController.class).registerEffort(outputTaskDTO.taskID,
                new TaskEffortDTO())).withRel("Register Effort"));
        //Show all tasks in a Sprint/User Story
        outputTaskDTO.add(linkTo(methodOn(TaskController.class).getTasksByTaskContainerID(sprintOrUsID.toString())).withRel("ShowFilteredTasks"));

        return outputTaskDTO;
    }

    public CollectionModel<OutputTaskDTO> toCollectionDto(List<Task> taskList) {
        CollectionModel<OutputTaskDTO> tasks = CollectionModel.of(taskList.stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
        /*
         * Add HATEOAS to OUTPUT DTO COLLECTION
         */
        //Add self relation
        tasks.add(linkTo(methodOn(TaskController.class).getAll()).withSelfRel());

        return tasks;
    }

    private List<TaskEffortDTO> effortsToDTO(Task task) {
        return task.getRegisteredEfforts().stream()
                .map(this::effortToDTO)
                .collect(Collectors.toList());
    }

    public TaskEffortDTO effortToDTO(TaskEffort taskEffort) {
        TaskEffortDTO dto = new TaskEffortDTO();
        dto.effortHours = taskEffort.getEffortHours().getEffortHours();
        dto.effortMinutes = taskEffort.getEffortMinutes().getEffortMinutes();
        dto.effortDate = taskEffort.getEffortDate().getEffortDate().toString();
        dto.comment = taskEffort.getComment().getText();
        dto.attachment = taskEffort.getAttachment().getExtension();

        return dto;
    }

    private double getHoursSpent(List<TaskEffort> taskEffortList) {
        double x = 0;
        for (TaskEffort i : taskEffortList) {
            x = x + effortInHours(i);
        }
        return x;
    }

    private double effortInHours(TaskEffort effort) {
        return (double) effort.getEffortHours().getEffortHours()
                + ((double) effort.getEffortMinutes().getEffortMinutes() / 60);
    }
}
