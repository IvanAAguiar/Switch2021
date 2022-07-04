package switch2021.project.dataModel.JPA;

import lombok.*;
import switch2021.project.entities.valueObjects.vos.Request;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "UserJpa")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class UserJpa {

    @EmbeddedId
    private UserID email;
    private String userName;
    private String function;
    private String photo;
    private String password;
    private String isActive;
    @ElementCollection
    @CollectionTable(name = "user_assignedProfiles")
    private List<UserProfileID> assignedIDProfiles;
    @ElementCollection
    @CollectionTable(name = "requests")
    private List<Request/*Jpa*/> requests;

    public UserJpa(UserID email, String userName, String function, String photo, String password,
                   String isActive, List<UserProfileID> assignedIDProfiles) {
        this.email = email;
        this.userName = userName;
        this.function = function;
        this.photo = photo;
        this.password = password;
        this.isActive = isActive;
        this.assignedIDProfiles = assignedIDProfiles;
        this.requests = new ArrayList<>();
    }
}