package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
@NoArgsConstructor
public class UserID implements ValueObject<UserID> {

    @Embedded
    private Email email;

    /**
     * Constructor.
     */
    public UserID(Email email) {
        this.email = email;
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean sameValueAs(final UserID other) {
        return other != null && this.email.equals(other.email);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserID that = (UserID) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}