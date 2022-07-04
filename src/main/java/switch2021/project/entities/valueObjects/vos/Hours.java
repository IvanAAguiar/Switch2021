package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.utils.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Hours implements ValueObject<Hours> {

    /**
     * Attributes
     **/
    @Column(name = "effortHours")
    private int effortHours;
    private static final int MINHOUR = 0;
    private static final int MAXHOUR = 23;

    /**
     * Constructor (without SINGLETON)
     **/

    public Hours(int effortHours) {
        checkWorkTimeRules(effortHours);
        this.effortHours = effortHours;
    }

    /**
     * Methods
     **/

    private void checkWorkTimeRules(int effortHours) {
        if (effortHours < MINHOUR || effortHours > MAXHOUR)
            throw new IllegalArgumentException("Not valid work time values." + "Hour interval: [" + MINHOUR + " - " + MAXHOUR + "]");
    }

    /**
     * Override Methods
     */
    @Override
    public boolean sameValueAs(final Hours other) {
        return other != null && this.effortHours == other.effortHours;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Hours that = (Hours) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effortHours);
    }
}
