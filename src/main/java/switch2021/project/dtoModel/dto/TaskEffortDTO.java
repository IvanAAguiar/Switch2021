package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TaskEffortDTO {
    /**
     * Attributes
     **/
    public int effortHours;
    public int effortMinutes;
    public String effortDate;
    public String comment;
    public String attachment;
}
