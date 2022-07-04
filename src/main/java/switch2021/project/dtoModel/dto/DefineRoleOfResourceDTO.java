package switch2021.project.dtoModel.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DefineRoleOfResourceDTO {

    private final String role;
    private final String startDate;
    private final String endDate;
    private final double costPerHour;
    private final double percentageOfAllocation;
}
