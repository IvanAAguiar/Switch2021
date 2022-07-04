package switch2021.project.dtoModel.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "Projects")
public class PartialProjectDTO extends RepresentationModel<PartialProjectDTO> {

    /**
     * Attributes
     **/
    public String code;
    public String projectName;
    public String description;
    public String startDate;
    public String projectStatus;

    public PartialProjectDTO(String code, String projectName, String description, String startDate, String projectStatus) {
        this.code = code;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.projectStatus = projectStatus;
    }
}
