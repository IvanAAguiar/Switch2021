package switch2021.project.dataModel.REST;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectRestDTO extends RepresentationModel<ProjectRestDTO> {

    /**
     * Attributes
     **/
    public String projectCode;
    public String projectName;
    public String projectDescription;
    public String projectBusinessSector;
    public String typologyDescription;
    public String customerName;
    public String startDate;
    public String endDate;
    public String projectNumberOfPlannedSprints;
    public String projectBudget;
    public String status;
    public String projectSprintDuration;

    public ProjectRestDTO(String projectName, String projectDescription, String projectBusinessSector, String startDate,
                          String projectNumberOfPlannedSprints, String projectBudget, String projectSprintDuration, String typologyDescription,
                          String customerName) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectBusinessSector = projectBusinessSector;
        this.startDate = startDate;
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
        this.projectBudget = projectBudget;
        this.projectSprintDuration = projectSprintDuration;
        this.typologyDescription = typologyDescription;
        this.customerName = customerName;
    }
}
