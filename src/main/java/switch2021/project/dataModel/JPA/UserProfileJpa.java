package switch2021.project.dataModel.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="profiles")
@Getter
public class UserProfileJpa {

    /** Attribute **/
    @EmbeddedId
    private UserProfileID userProfileID;
}


