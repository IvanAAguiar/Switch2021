package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskDTO {
    /**
     * Attributes
     **/
    public String projectId;
    public String sprintName;
    public String usTitle;
    public String systemUserID;
    public String resourceStartDate;
    public String taskTitle;
    public String taskDescription;
    public double taskEffortEstimate;
    public String taskType;
}
