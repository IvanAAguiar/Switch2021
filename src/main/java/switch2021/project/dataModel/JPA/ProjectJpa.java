package switch2021.project.dataModel.JPA;

import lombok.*;
import switch2021.project.entities.valueObjects.vos.ProjectID;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "projects")
@Table/*(name = "Projects", uniqueConstraints = {@UniqueConstraint(name = "ProjectCodeUnique", columnNames = "id")} )*/
public class ProjectJpa {

    @EmbeddedId
    @Column(name = "project_code")
    private ProjectID projectCode;
    private String name;
    private String description;
    private String businessSector;
    private String startDate;
    private int numberOfSprints;
    private long sprintDuration;
    private double budget;
    private String endDate;
    private String typology;
    private String customer;
    private String status;
}
