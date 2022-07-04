package switch2021.project.dataModel.JPA;

import lombok.*;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "UserStoryOfSprintJpa")
@NoArgsConstructor
@Table(name = "userStoriesOfSprint")
public class UserStoryOfSprintJpa implements Serializable {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "projectID", column = @Column(name = "ProjectID")),
            @AttributeOverride(name = "usTitle", column = @Column(name = "UsTitle"))
    })
    private UserStoryID userStoryId;
    private String sprintName;
    private String status;

    public UserStoryOfSprintJpa(UserStoryID userStoryId, String status, String sprintName) {
        this.userStoryId = userStoryId;
        this.status = status;
        this.sprintName = sprintName;
    }
}
