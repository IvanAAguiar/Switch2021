package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "User")
public class OutputUserDTO extends RepresentationModel<OutputUserDTO> {

    /**
     * Attributes
     */
    public String userName;
    public String email;
    public String function;
    public String photo;
    public String isActive;
    public List<String> assignedIdProfiles;
}
