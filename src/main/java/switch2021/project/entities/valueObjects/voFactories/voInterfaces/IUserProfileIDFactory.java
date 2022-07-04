package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.entities.valueObjects.vos.UserProfileID;

public interface IUserProfileIDFactory {
    UserProfileID createUserProfileID (String userProfileName);
}
