package switch2021.project.dataModel.JPA.assembler;

import org.springframework.stereotype.Service;
import switch2021.project.dataModel.JPA.UserProfileJpa;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;

@Service
public class UserProfileJpaAssembler {

    public UserProfileJpa toData (UserProfile userProfile) {
        return new UserProfileJpa(userProfile.getUserProfileId());
    }

    public UserProfile toDomain (UserProfileJpa userProfileJPA) {
        return new UserProfile(userProfileJPA.getUserProfileID());
    }
}
