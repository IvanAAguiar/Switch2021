package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.vos.ProjectID;

@Component
public class ProjectIDFactory implements IProjectIDFactory {
    @Override
    public ProjectID create(String projectID) {
        return new ProjectID(projectID);
    }
}
