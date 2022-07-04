package switch2021.project.dataModel.JPA;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "ResourceJpa")
@Table(name = "resources")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceJpa {

    @EmbeddedId
    private ResourceIDJpa id;
    private String endDate;
    private double allocation;
    private double cost;
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceJpa)) return false;
        ResourceJpa that = (ResourceJpa) o;
        return Double.compare(that.allocation, allocation) == 0 &&
                Double.compare(that.cost, cost) == 0 &&
                id.equals(that.id) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endDate, allocation, cost, role);
    }
}
