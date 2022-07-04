package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

@Component
public class UserProfileIDFactory implements IUserProfileIDFactory {


    @Autowired
    private IDescriptionFactory descriptionFactory;


    @Override
    public UserProfileID createUserProfileID(String userProfileName) {
        Description description = descriptionFactory.createDescription(userProfileName);
        return new UserProfileID(description);
    }
}
