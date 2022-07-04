package switch2021.project.dataModel.JPA;

import lombok.*;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import javax.persistence.*;

@Entity(name = "UserStoryJpa")
@Table(name = "userStories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStoryJpa {

    @EmbeddedId
    private UserStoryID id;
    private int priority;
    private String description;
    private double timeEstimate;
    private String usStatus;
    @OneToOne
    private UserStoryJpa parentUserStory;
    private String startDate;
    private String endDate;
    private String  refined;
}
