package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import switch2021.project.utils.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//@ToString
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Description implements ValueObject<Description> {

    /**
     * Attributes
     **/
    @Column(name = "text")
    private String text;
    private static final int MINDESCRIPTIONLENGTH = 1;
    private static final int MAXDESCRIPTIONLENGTH = 1000;

    /**
     * Constructor
     **/
    public Description (String description) {
        if (description == null)
            throw new NullPointerException("Description field requires at least " + MINDESCRIPTIONLENGTH + " characters");
        if (description.trim().length() < MINDESCRIPTIONLENGTH)
            throw new IllegalArgumentException("Description field requires at least " + MINDESCRIPTIONLENGTH + " characters");
        if (description.trim().length() > MAXDESCRIPTIONLENGTH)
            throw new IllegalArgumentException("Description field cannot have more than " + MAXDESCRIPTIONLENGTH + " characters");
        this.text = description;
    }

    @Override
    public boolean sameValueAs(final Description other) {
        return other != null && this.text.equals(other.text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Description that = (Description) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
