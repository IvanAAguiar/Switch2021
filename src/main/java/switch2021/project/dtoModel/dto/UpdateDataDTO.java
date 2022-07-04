package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateDataDTO extends RepresentationModel<UpdateDataDTO> {

    /**
     * Attributes
     */
    public String userName;
    public String function;
    public String photo;
    public String oldPassword;
    public String newPassword;

}
