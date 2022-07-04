package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import switch2021.project.utils.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
public class UsTitle implements ValueObject<UsTitle> {

    /**
     * Attributes
     **/
    @Column(name = "US_title")
    private String titleUs; //As a //I want to

    /**
     * Constructor
     **/
    public UsTitle(String titleUs) {
        checkUsTile(titleUs);
        this.titleUs = titleUs;
    }

    /**
     * Methods
     **/
    private void checkUsTile(String usTitle) {
        if (usTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if (!usTitle.toUpperCase().startsWith("AS")) {
            throw new IllegalArgumentException("Title need to begin with - as");
        }
        if (!usTitle.contains("want") && !usTitle.contains("Want")) {
            throw new IllegalArgumentException("Title don't contain the word want");
        }
    }

    /**
     * Override
     **/
    @Override
    public boolean sameValueAs(final UsTitle other) {
        return other != null && Objects.equals(this.titleUs, other.titleUs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UsTitle usTitle = (UsTitle) o;
        return sameValueAs(usTitle);
    }

    @Override
    public int hashCode() {
        return titleUs.hashCode();
    }
}




