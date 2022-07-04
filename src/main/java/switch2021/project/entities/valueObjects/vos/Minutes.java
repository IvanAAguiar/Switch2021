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
public class Minutes implements ValueObject<Minutes> {

    /**
     * Attributes
     **/
    @Column(name = "effortMinutes")
    private int effortMinutes;
    private static final int MINMINUTE = 0;
    private static final int MAXMINUTE = 60;

    /**
     * Constructor (without SINGLETON)
     **/

    public Minutes(int effortMinutes){
        checkMinutesTimeRules(effortMinutes);
        this.effortMinutes = effortMinutes;
    }

    /**
     * Methods
     **/

    private void checkMinutesTimeRules ( int effortMinutes){
        if (effortMinutes < MINMINUTE || effortMinutes >= MAXMINUTE)
            throw new IllegalArgumentException("Not valid work time values." + " Minute interval: [" + MINMINUTE + " - " + MAXMINUTE + "]");
    }
    /**
     * Override Methods
     */
    @Override
    public boolean sameValueAs(final Minutes other) {
        return other != null && this.effortMinutes == other.effortMinutes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Minutes that = (Minutes) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effortMinutes);
    }
}
