package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Relation(collectionRelation = "User")
public class RequestDTO extends RepresentationModel<RequestDTO> {
    public String profileId;
}


