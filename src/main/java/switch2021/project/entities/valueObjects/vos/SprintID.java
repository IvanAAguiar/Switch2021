package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.utils.ValueObject;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class SprintID implements ValueObject<SprintID>, TaskContainerID {

    /** Attributes */
    @Embedded
    private ProjectID projectID;
    @Embedded
    private Description sprintName;


    /** Constructor */
    public SprintID(ProjectID projectID, Description sprintName){
        this.projectID = projectID;
        this.sprintName = sprintName;
    }
 
    public SprintID(String sprintIdString){
        String [] values = sprintIdString.split("&");
        this.projectID = new ProjectID(values[0]);
        this.sprintName = new Description(values[1]);
    }

    /** Override Methods */

    @Override
    public String toString() {
        return projectID.getCode() + "&" +  sprintName.getText();
    }

    @Override
    public boolean sameValueAs(final SprintID other) {
        return other != null && new EqualsBuilder().
                append(this.projectID, other.projectID).
                append(this.sprintName, other.sprintName).
                isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SprintID that = (SprintID) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, sprintName);
    }
}
