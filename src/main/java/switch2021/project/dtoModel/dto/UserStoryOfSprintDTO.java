package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class UserStoryOfSprintDTO extends RepresentationModel<UserStoryOfSprintDTO> {

    public String usTitle;
    public String projectId;
    public String sprintName;
    public String status;

}
