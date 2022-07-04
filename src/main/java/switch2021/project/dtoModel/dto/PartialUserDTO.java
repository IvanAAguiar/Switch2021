package switch2021.project.dtoModel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@NoArgsConstructor
@Relation(collectionRelation = "Users")
public class PartialUserDTO extends RepresentationModel<PartialUserDTO> {

    public String userName;
    public String email;
    public String function;

    public PartialUserDTO(String name, String email, String function){
        this.userName = name;
        this.email = email;
        this.function = function;
    }
}
