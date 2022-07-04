package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.Objects;


@Embeddable
@NoArgsConstructor
@Getter
public class ResourceID implements ValueObject<ResourceID> {

    @Embedded
    private UserID user;
    @Embedded
    private ProjectID project;
    private LocalDate startDate;

        public ResourceID(UserID user, ProjectID project, LocalDate startDate) {
            this.user = user;
            this.project = project;
            this.startDate = startDate;
        }

    @Override
    public boolean sameValueAs(ResourceID other) {
        return other != null &&
                this.user == other.user &&
                this.project == other.project &&
                this.startDate == other.startDate ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceID)) return false;
        ResourceID that = (ResourceID) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getProject(), getStartDate());
    }

    @Override
    public String toString() {
        return  this.getUser().getEmail().getEmailText() + "&" +
                this.getProject().getCode() + "&" +
                this.getStartDate().toString();
    }
}
