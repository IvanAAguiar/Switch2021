package switch2021.project.dtoModel.dto;


import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "Projects")
public class OutputProjectDTO extends RepresentationModel<OutputProjectDTO> {

    public String code;
    public String projectName;
    public String description;
    public String customer;
    public String businessSector;
    public String typo;
    public String numberOfSprints;
    public String budget;
    public String status;
    public String startDate;
    public String endDate;
    public String sprintDuration;

    public OutputProjectDTO(String code,String projectName,String description,String businessSector,String startDate,
                            String numberOfSprints,String budget,String status, String sprintDuration) {
        this.code = code;
        this.projectName = projectName;
        this.description = description;
        this.businessSector = businessSector;
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
        this.status = status;
        this.startDate = startDate;
        this.sprintDuration = sprintDuration;
    }
}
