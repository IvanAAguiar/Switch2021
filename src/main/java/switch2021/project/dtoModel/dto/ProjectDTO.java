package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    /**
     * Attributes
     **/
    public String code;
    public String projectName;
    public String description;
    public String businessSector;
    public String typology;
    public String customer;
    public String startDate;
    public String endDate;
    public String numberOfSprints;
    public String budget;
    public String projectStatus;
    public String sprintDuration;

    public ProjectDTO(String projectName, String description, String businessSector, String startDate,
                      String numberOfSprints, String budget, String sprintDuration, String typology, String customer) {
        this.projectName = projectName;
        this.description = description;
        this.businessSector = businessSector;
        this.startDate = startDate;
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
        this.sprintDuration = sprintDuration;
        this.typology = typology;
        this.customer = customer;
    }
}
