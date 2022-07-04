package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Date {

    /**
     * Attributes
     **/

    @Column(name = "effortDate")
    private LocalDate effortDate;


    /**
     * Constructor (without SINGLETON)
     **/
    public Date(LocalDate effortDate) {
        checkWorkDateRules(effortDate);
        this.effortDate = effortDate;
    }


    /**
     * Methods
     **/
    private void checkWorkDateRules(LocalDate effortDate) {
        if (effortDate == null) {
            this.effortDate = LocalDate.now();
        } else if (effortDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid workHours value.");
        } else {
            this.effortDate = effortDate;
        }
    }
}
