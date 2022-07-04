package switch2021.project.entities.aggregates.Resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.CostPerHour;
import switch2021.project.entities.valueObjects.vos.PercentageOfAllocation;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;
import switch2021.project.utils.Entity;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements Entity<Resource> {

    /**
     * Attributes
     **/
    private ResourceID id;
    private LocalDate endDate;
    private PercentageOfAllocation allocation;
    private CostPerHour cost;
    private ProjectRole role;



    public boolean isActiveToThisDate(LocalDate date) {
        boolean result = false;

        if(this.id.getStartDate().isBefore(date) || this.id.getStartDate().isEqual(date)){
            if(this.endDate.isEqual(date) || this.endDate.isAfter(date)){
                result = true;
            }
        }
        return result;
    }

    public boolean hasProjectRole(String role) {
        return this.role.equals(ProjectRole.valueOf(role));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource that = (Resource) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean sameIdentityAs(Resource other) {
        return other != null && this.id.sameValueAs(other.id);
    }
}
