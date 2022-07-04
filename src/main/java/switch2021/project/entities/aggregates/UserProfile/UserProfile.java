package switch2021.project.entities.aggregates.UserProfile;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import switch2021.project.utils.Entity;

@Getter
@Setter
public class UserProfile implements Entity<UserProfile> {

    /**
     * Attributes
     **/
    private final UserProfileID userProfileId;

    /**
     * Constructor
     */
    public UserProfile(UserProfileID userProfileId) {
        this.userProfileId = userProfileId;
    }


    /**
     * Override Methods
     **/
    @Override
    public boolean sameIdentityAs(UserProfile other) {
        return other != null && userProfileId.sameValueAs(other.userProfileId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return userProfileId.hashCode();
    }

}
