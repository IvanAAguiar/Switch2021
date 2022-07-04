package switch2021.project.entities.aggregates.Sprint;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;
import switch2021.project.utils.Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Sprint implements Entity<Sprint> {

    /**
     * Attributes of Sprint
     **/
    private SprintID sprintID;
//    private List<UserStoryOfSprint> scrumBoard;
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructor of Sprint
     **/
    public Sprint(SprintID sprintID) {
        this.sprintID = sprintID;
    }

    public Sprint(SprintID sprintID, LocalDate startDate, LocalDate endDate) {
        this.sprintID = sprintID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Method to change Sprint EndDate
     **/

    public void changeEndDate(int sprintDurationInDays) {
        this.endDate = LocalDate.now().plusDays(sprintDurationInDays - 1L);
    }

    public boolean hasSprintID(String sprID) {
        return Objects.equals(this.sprintID.toString(), sprID);
    }


    /**
     * Check if this Sprint is the current Sprint
     */

    public boolean isCurrentSprint() {
        if (this.endDate == null) {
            throw new NullPointerException();
        }
        return ((this.startDate.isBefore(endDate) || this.startDate.equals(endDate))
                && (this.endDate.isAfter(startDate) || this.endDate.equals(startDate)));
    }


    @Override
    public boolean sameIdentityAs(Sprint other) {
        return other != null && sprintID.sameValueAs(other.sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        Sprint sprint = (Sprint) o;
        return sameIdentityAs(sprint);
    }

}








