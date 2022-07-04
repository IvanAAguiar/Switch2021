package switch2021.project.entities.aggregates.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.TaskStatus;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;
import switch2021.project.utils.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Task implements Entity<Task> {

    /**
     * Attributes
     */
    private TaskID taskID;
    private Description description;
    private TaskTypeEnum type;
    private TaskStatus status;
    private EffortEstimate effortEstimate;
    private LocalDate startDate;
    private LocalDate endDate;
    private ResourceID responsible;
    private List<TaskEffort> registeredEfforts;
    private List<TaskID> precedenceList;

    /**
     * Constructor
     */
    public Task(TaskID taskID, Description description, EffortEstimate effortEstimate, TaskTypeEnum type,
                ResourceID responsible) {
        this.taskID = taskID;
        this.description = description;
        this.effortEstimate = effortEstimate;
        this.type = type;
        this.status = TaskStatus.PLANNED;
        this.responsible = responsible;
        this.registeredEfforts = new ArrayList<>();
        this.precedenceList = new ArrayList<>();
    }

    /**
     * Methods to iterate with attributes
     */
    public boolean hasResponsible(ResourceID resp) {
        return Objects.equals(this.responsible, resp);
    }

    public void toAddEffort(TaskEffort taskEffort) {
        if (this.registeredEfforts.isEmpty()) {
            this.status = TaskStatus.RUNNING;
            this.startDate = taskEffort.getEffortDate().getEffortDate();
        }
        if (this.status.equals(TaskStatus.FINISHED) || this.status.equals(TaskStatus.BLOCKED) || this.status.equals(TaskStatus.CANCELLED)) {
            throw new IllegalArgumentException("The Task is" + getStatus().toString());
        }
        this.registeredEfforts.add(taskEffort);
    }

    /**
     * Methods Override
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task that = (Task) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return taskID.hashCode();
    }

    @Override
    public boolean sameIdentityAs(Task other) {
        return other != null && taskID.sameValueAs(other.taskID);
    }
}
