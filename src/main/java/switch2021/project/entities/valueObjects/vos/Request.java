package switch2021.project.entities.valueObjects.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Embeddable
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request implements ValueObject<Request> {

    /**
     * Class attributes
     **/
    private LocalDate requestDate;
    @Embedded
    private UserProfileID profileIdRequested;


    /**
     * Constructor
     */
    public Request(UserProfileID profileId) {
        this.requestDate = LocalDate.now();
        this.profileIdRequested = profileId;
    }


    /**
     * Override Methods
     */
    @Override
    public boolean sameValueAs(final Request other) {
        return other != null && this.requestDate.equals(other.requestDate)
                && this.profileIdRequested.equals(other.profileIdRequested);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Request that = (Request) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, profileIdRequested);
    }
}





