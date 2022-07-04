package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;

import java.time.LocalDate;

@Component
public class ResourceIDFactory implements IResourceIDFactory {

    /**
     * Attributes
     */
    @Autowired
    private IProjectIDFactory projectIDFactory;
    @Autowired
    private IUserIDFactory userIDFactory;

    @Override
    public ResourceID create(String systemUserID, String projID, String startDat) {
        UserID sysUserID = userIDFactory.createUserID(systemUserID);
        ProjectID projectID = projectIDFactory.create(projID);
        LocalDate startDate = LocalDate.parse(startDat);
        return new ResourceID(sysUserID, projectID, startDate);
    }
}
