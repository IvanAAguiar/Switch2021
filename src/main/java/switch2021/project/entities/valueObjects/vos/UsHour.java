package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

@Getter
public class UsHour implements ValueObject<UsHour> {


    /**
     * Attributes
     **/
    private final double usHours;
    private static final int MINHOUR = 0;

    /**
     * Constructor
     **/
    public UsHour(double usHours) {
        checkWorkTimeRules(usHours);
        this.usHours = usHours;
    }

    /**
     * Methods
     **/
    private void checkWorkTimeRules(double usHours) {
        if (usHours < MINHOUR)
            throw new IllegalArgumentException("Not valid work time values." + "Hour interval: [" + MINHOUR + "]");
    }

    /**
     * Override
     **/

    @Override
    public boolean sameValueAs(final UsHour other) {
        return other !=null && this.usHours==(other.usHours);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UsHour usHour = (UsHour) o;
        return sameValueAs(usHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usHours);
    }
}
