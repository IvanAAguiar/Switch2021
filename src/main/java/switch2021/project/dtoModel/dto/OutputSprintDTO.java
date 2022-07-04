package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class OutputSprintDTO extends RepresentationModel<OutputSprintDTO> {

    /**
     * Attributes
     */

    public String projectID;
    public String sprintID;
    public String name;

}

