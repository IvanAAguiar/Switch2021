package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class OutSprintDTO extends RepresentationModel<OutSprintDTO> {

    /**
     * Attributes
     */

    public String projectID;
    public String sprintID;
    public String name;
    public String startDate;
    public String endDate;
}
