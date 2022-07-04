package switch2021.project.dataModel.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ResourceIDJpa implements Serializable {

    @Embedded
    private UserID user;
    @Embedded
    private ProjectID project;
    private String startDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceIDJpa)) return false;
        ResourceIDJpa idJpa = (ResourceIDJpa) o;
        return Objects.equals(user, idJpa.user) && Objects.equals(project, idJpa.project) && Objects.equals(startDate
                , idJpa.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, project, startDate);
    }
}


