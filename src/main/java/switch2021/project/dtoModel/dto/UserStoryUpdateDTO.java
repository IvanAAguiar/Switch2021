package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class UserStoryUpdateDTO extends RepresentationModel<UserStoryUpdateDTO> {

   public int priority;
   public String description;
   public double timeEstimate;
   public String usStartDate;
   public String usEndDate;
}
