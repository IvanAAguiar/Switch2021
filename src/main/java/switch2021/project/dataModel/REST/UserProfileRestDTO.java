package switch2021.project.dataModel.REST;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileRestDTO extends RepresentationModel<UserProfileRestDTO> {

    /**
     * Attribute
     */
    private String designation;

}
