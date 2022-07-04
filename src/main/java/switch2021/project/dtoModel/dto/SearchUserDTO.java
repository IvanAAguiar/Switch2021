package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchUserDTO extends RepresentationModel<SearchUserDTO> {
    public String name;
    public String function;
    public String profile;
}
