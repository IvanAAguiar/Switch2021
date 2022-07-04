package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ISprintIDFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.SprintID;

@Component
public class SprintIDFactory implements ISprintIDFactory{

    /**
     * Attributes
     */
    @Autowired
    private IProjectIDFactory projectIDFactory;
    @Autowired
    private IDescriptionFactory descriptionFactory;

    /**
     * Method
     */
    public SprintID create(String code, String description) {
        ProjectID projectID = projectIDFactory.create(code);
        Description sprintName = descriptionFactory.createDescription(description);
        return new SprintID(projectID, sprintName);
    }

}
