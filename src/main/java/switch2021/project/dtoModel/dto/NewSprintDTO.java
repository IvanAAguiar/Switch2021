package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class NewSprintDTO extends RepresentationModel<NewSprintDTO> {

    /** Attributes*/

    public String projectID;
    public String name;
    public String startDate;

}
