package switch2021.project.dtoModel.dto;

import org.springframework.hateoas.RepresentationModel;

public class OutputResourceDTO extends RepresentationModel<OutputResourceDTO> {

    public String resourceID;
    public String user;
    public String project;
    public String role;
    public String startDate;
    public String endDate;
    public double allocation;
    public double cost;
}
