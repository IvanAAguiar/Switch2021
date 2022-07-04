package switch2021.project.dataModel.JPA;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.SprintID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SprintJpa")
@Table(name = "sprints")
@Getter
@Setter
@NoArgsConstructor
public class SprintJpa {
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "projectID", column = @Column(name = "ProjectID")),
            @AttributeOverride(name = "sprintName.text", column = @Column(name = "SprintName"))
    })
    private SprintID sprintId;
    private String startDate;
    private String endDate;

    public SprintJpa(SprintID sprintId, String startDate, String endDate) {
        this.sprintId = sprintId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
