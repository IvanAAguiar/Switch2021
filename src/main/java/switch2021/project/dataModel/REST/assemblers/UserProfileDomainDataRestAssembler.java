package switch2021.project.dataModel.REST.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dataModel.REST.UserProfileRestDTO;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserProfileDomainDataRestAssembler {

    @Autowired
    IUserProfileIDFactory userProfileIDFactory;


    /**
     * @param userProfileRestDTO userProfileRestDTO
     * @return userProfile
     */
    public UserProfile toDomain(UserProfileRestDTO userProfileRestDTO) {

        return new UserProfile(new UserProfileID(new Description(userProfileRestDTO.getDesignation())));
    }


    /**
     * @param userProfileRestDTO userProfileRestDTO
     * @return List of User Profiles
     */

    public List<UserProfile> toDomain(List<UserProfileRestDTO> userProfileRestDTO) {

        return userProfileRestDTO.stream().map(this::toDomain).collect(Collectors.toList());
    }

}
