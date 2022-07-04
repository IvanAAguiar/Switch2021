package switch2021.project.dtoModel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditProjectInfoDTO {

//    public String code;
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

    public EditProjectInfoDTO(String projectName, String description, String businessSector, String typology,
                              String customer, String startDate, String endDate, String numberOfSprints,
                              String budget, String projectStatus, String sprintDuration) {
        this.projectName = projectName;
        this.description = description;
        this.businessSector = businessSector;
        this.typology = typology;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
        this.projectStatus = projectStatus;
        this.sprintDuration = sprintDuration;
    }
}
