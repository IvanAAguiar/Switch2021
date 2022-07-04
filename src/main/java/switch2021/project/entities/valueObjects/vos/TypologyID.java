package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Locale;
import java.util.Objects;

/**
 * Typology's ID is a description and contents for itself
 * a unique value.
 */

@Getter
@Embeddable
@NoArgsConstructor
@Setter
public class TypologyID implements ValueObject<TypologyID> {

    /**
     * Attributes
     */
    @Embedded
    private Description description;


    /**
     * Constructor
     */
    public TypologyID(Description description) {
        this.description = description;
    }


    /**
     * Methods
     */
    public boolean hasDescription(String description) {
        return Objects.equals(this.description.getText(), description);
    }


    /**
     * Override Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypologyID that = (TypologyID) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean sameValueAs(TypologyID other) {
        return this.description.getText().equals(other.getDescription().getText());
    }
}
