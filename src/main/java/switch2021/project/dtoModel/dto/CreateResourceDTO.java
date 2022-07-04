package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class CreateResourceDTO extends RepresentationModel<CreateResourceDTO> {

    public String systemUserID;
    public String projectId;
    public String projectRole;
    public String startDate;
    public String endDate;
    public double costPerHour;
    public double percentageOfAllocation;
}
