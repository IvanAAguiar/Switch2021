package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class UpdateUserStoryDTO extends RepresentationModel<UpdateUserStoryDTO> {

    /**
     * Attributes
     **/
    public int priority;
    public double timeEstimate;
}
