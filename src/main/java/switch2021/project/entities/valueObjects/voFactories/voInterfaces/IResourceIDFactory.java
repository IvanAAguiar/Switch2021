package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.entities.valueObjects.vos.ResourceID;

public interface IResourceIDFactory {
    ResourceID create(String systemUserID, String projectID, String startDate);
}
