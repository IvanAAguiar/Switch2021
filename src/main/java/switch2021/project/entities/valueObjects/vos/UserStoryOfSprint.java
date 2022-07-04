package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

@Getter
public class UserStoryOfSprint implements ValueObject<UserStoryOfSprint> {

    /**
     * Attributes
     */
    private final UserStoryID userStoryId;
    @Setter
    private UserStoryOfSprintStatus userStoryOfSprintStatus;
    private final String sprintName;

    /**
     * Constructor
     */
    public UserStoryOfSprint(UserStoryID userStoryId, UserStoryOfSprintStatus userStoryOfSprintStatus,
                             String sprintName) {
        this.userStoryId = userStoryId;
        this.userStoryOfSprintStatus = userStoryOfSprintStatus;
        this.sprintName = sprintName;
    }

    @Override
    public boolean sameValueAs(UserStoryOfSprint other) {
        return other != null && this.userStoryId.equals(other.userStoryId)
                && this.userStoryOfSprintStatus.equals(other.userStoryOfSprintStatus);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserStoryOfSprint that = (UserStoryOfSprint) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryId, userStoryOfSprintStatus);
    }
}
