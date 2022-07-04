package switch2021.project.dtoModel.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Relation(collectionRelation = "Tasks")
public class OutputTaskDTO extends RepresentationModel<OutputTaskDTO> {

    public String taskID;
    public String taskTitle;
    public String sprintNameOrUsTitle;
    public String projectID;
    public String resourceID;
    public String userID;
    public String resourceStartDate;
    public String description;
    public String type;
    public String status;
    public double effortEstimate;
    public String taskStartDate;
    public String taskEndDate;
    public List<TaskEffortDTO> registeredEfforts;
    public double hoursSpent;
    public double percentageOfExecution;
}
