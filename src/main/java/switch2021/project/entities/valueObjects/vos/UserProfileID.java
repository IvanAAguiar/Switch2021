package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class UserProfileID implements ValueObject<UserProfileID> {

    /**
     * Attributes
     * */
    @Embedded
    private Description userProfileName;

    /**
     * Constructor
     */
    public UserProfileID(Description userProfileName) {
        this.userProfileName = userProfileName;
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean sameValueAs(final UserProfileID other) {
        return other != null && this.userProfileName.equals(other.userProfileName);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserProfileID that = (UserProfileID) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return userProfileName.hashCode();
    }
}
