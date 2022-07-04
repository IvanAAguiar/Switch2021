package switch2021.project.dataModel.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TaskJpa")
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskJpa {
    @Id
    private String taskID;
    @Embedded
    ProjectID projectID;
    @Embedded
    private UsTitle usTitle;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "SprintName"))
    private Description sprintName;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "TaskTitle"))
    private Description taskTitle;
    private String taskDescription;
    private String taskType;
    private String taskStatus;
    private double taskEffortEstimate;
    private String taskStartDate;
    private String taskEndDate;
    @Embedded
    private UserID resourceUserID;
    private String resourceStartDate;
    @ElementCollection
    @CollectionTable(name = "efforts")
    private List<TaskEffort> taskEffortList;
    @ElementCollection
    @CollectionTable(name = "tasks_precedences")
    private List<TaskID> taskPrecedenceList;
}
