package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import org.springframework.stereotype.Component;
import switch2021.project.entities.factories.factoryInterfaces.IUserProfileFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

@Component
public class UserProfileFactory implements IUserProfileFactory {

    @Autowired
    private IUserProfileIDFactory idFactory;


    @Override
    public UserProfile createUserProfile(UserProfileDTO dto) {
        UserProfileID userProfileID = idFactory.createUserProfileID(dto.userProfileName);
        return new UserProfile(userProfileID);
    }
}


