package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Relation(collectionRelation = "User")
public class UpdateUserProfilesDTO extends RepresentationModel<UpdateUserProfilesDTO> {

    /**
     * Attributes
     **/
    public String[] profilesId;
}