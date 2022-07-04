package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;


@Getter
@AllArgsConstructor
public class OutputTypologyDTO extends RepresentationModel<OutputTypologyDTO> {

    /**
     * Attributes
     */

    public String typology;

}