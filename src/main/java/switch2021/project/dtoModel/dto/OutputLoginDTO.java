package switch2021.project.dtoModel.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class OutputLoginDTO extends RepresentationModel {
    public String email;
    public String username;
    public String token;
}
