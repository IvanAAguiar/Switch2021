package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;

public interface IUserProfileFactory {

    UserProfile createUserProfile (UserProfileDTO dto);
}
