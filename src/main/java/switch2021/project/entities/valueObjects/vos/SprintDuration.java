package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

@Getter

public class SprintDuration implements ValueObject<SprintDuration> {

    /**
     * Attribute
     **/
    private final long sprintDurationDays;

    /**
     * Constants -
     * Minimum and maximum days per sprint
     **/
    final int MIN = 7;
    final int MAX = 31;

    /**
     * Constructor
     **/
    public SprintDuration (long sprintDurationDays) {
        checkRules(sprintDurationDays);
        this.sprintDurationDays = sprintDurationDays;
    }

    /**
     * Constructor for WebProjects
     **/
    public SprintDuration (String sprintDurationDays) {
        this.sprintDurationDays = Long.parseLong(sprintDurationDays);
    }
    /**
     * Validate whether input parameter is within the permitted interval of days
     */
    private void checkRules(long sprintDuration) {
        if(sprintDuration < MIN || sprintDuration > MAX) {
            throw new NullPointerException("The sprint duration must be within " + MIN + " and " +  MAX + " days");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintDuration that = (SprintDuration) o;
        return sprintDurationDays == that.sprintDurationDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintDurationDays, MIN, MAX);
    }

    @Override
    public boolean sameValueAs(SprintDuration other) {
        return false;
    }
}
