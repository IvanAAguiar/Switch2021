package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import org.apache.commons.lang3.builder.EqualsBuilder;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
public class UserStoryID implements ValueObject<UserStoryID>, TaskContainerID {

    /**
     * Attributes
     **/
    @Embedded
    private ProjectID projectID;
    @Embedded
    private UsTitle usTitle;


    /**
     * Constructor
     **/
    public UserStoryID(ProjectID projectID, UsTitle usTitle) {
        checkUsID(projectID, usTitle);
        this.projectID = projectID;
        this.usTitle = usTitle;
    }

    public UserStoryID(String userStoryIdString) {
        String[] values = userStoryIdString.split("_");// Project_2022_1_As a PO, want to test this string
        this.projectID = new ProjectID(values[2]);
        this.usTitle = new UsTitle(values[3]);
    }


    /**
     * Methods
     **/
    private void checkUsID(ProjectID projectID, UsTitle usTitle) {
        if (projectID.getCode().trim().isEmpty() || usTitle.getTitleUs().trim().isEmpty()) {
            throw new IllegalArgumentException("Not valid projectCode and/or US title inserted");
        }
    }


    /**
     * Override
     **/
    @Override
    public String toString() {
        return projectID.getCode() + "&" + usTitle.getTitleUs();
    }

    @Override
    public boolean sameValueAs(final UserStoryID other) {
        return other != null && new EqualsBuilder().
                append(this.projectID, other.projectID).
                append(this.usTitle, other.usTitle).
                isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserStoryID that = (UserStoryID) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, usTitle);
    }
}
