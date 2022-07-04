package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "User Stories")
public class OutputUserStoryDTO extends RepresentationModel<OutputUserStoryDTO> {

    /**
     * Attributes
     **/
    public String id;
    public String projectID;
    public String usTitle;
    public int priority;
    public String description;
    public double timeEstimate;
    public String parentUserStory;
    public String usStartDate;
    public String usEndDate;
    public String usRefined;
    public String status;

    public OutputUserStoryDTO(String id, String projectID, String usTitle, int priority, String description,
                              double timeEstimate) {
        this.id = id;
        this.projectID = projectID;
        this.usTitle = usTitle;
        this.priority = priority;
        this.description = description;
        this.timeEstimate = timeEstimate;
    }
}
