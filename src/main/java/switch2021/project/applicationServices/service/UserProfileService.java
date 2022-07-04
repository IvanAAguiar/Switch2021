package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.applicationServices.iRepositories.IUserProfileWebRepository;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.dtoModel.mapper.UserProfileMapper;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.factories.factoryInterfaces.IUserProfileFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserProfileService {

    /**
     * Attributes
     **/
    @Autowired
    private IUserProfileRepo iUserProfileRepo;
    @Autowired
    private IUserProfileFactory iUserProfileFactory;
    @Autowired
    private IUserProfileIDFactory factoryId;
    @Autowired
    private UserProfileMapper userProfileMapper;
    @Autowired
    private IUserProfileWebRepository iUserProfileWebRepository;

    /**
     * Create and save a User Profile
     */
    public UserProfileDTO createAndSaveUserProfile(UserProfileDTO dto) throws Exception {
        UserProfile newUserProfile = iUserProfileFactory.createUserProfile(dto);

        if(iUserProfileRepo.existsByUserProfileId(newUserProfile.getUserProfileId())) {
            throw new Exception ("This User Profile already exists!");
        }
        UserProfile userProfileSaved = iUserProfileRepo.save(newUserProfile);
        return  userProfileMapper.toDTO(userProfileSaved);
    }

    /**
     * To get all profiles
     */
    public Map<String, CollectionModel<UserProfileDTO>> getAllProfiles() throws SSLException {

        List<UserProfile> userProfiles = iUserProfileRepo.findAll();
        List<UserProfile> userProfileWeb = iUserProfileWebRepository.findAll();

        CollectionModel<UserProfileDTO> outputUserProfileList_DTO = userProfileMapper.toCollectionDTO(userProfileWeb, true);
        CollectionModel<UserProfileDTO> outputUserProfileDTO_List = userProfileMapper.toCollectionDTO(userProfiles, false);

        Map<String, CollectionModel<UserProfileDTO>> mapProfiles = new HashMap<>();
        mapProfiles.put("internalUserProfiles", outputUserProfileDTO_List);
        mapProfiles.put("externalUserProfiles", outputUserProfileList_DTO);

        return mapProfiles;

    }

    /**
     * Find a requested user profile
     */

    public UserProfileDTO findUserProfileRequested(String id) throws Exception {
        UserProfileID profileID = factoryId.createUserProfileID(id);
        Optional<UserProfile> userProfile = iUserProfileRepo.findByUserProfileID(profileID);

       UserProfileDTO dto = null;

        if(userProfile.isPresent()){
            dto = userProfileMapper.toDTO(userProfile.get());
        }

        if(userProfile.isEmpty()){
            throw new Exception("We can not find the User Profile requested");
        }

        return dto;

    }

    /**
     * To edit a profile
     */
    public UserProfileDTO editARequestedUserProfile(String id, UserProfileDTO inDto) throws Exception {

        UserProfileID profileId = factoryId.createUserProfileID(id);

        Optional<UserProfile> requested = iUserProfileRepo.findByUserProfileID(profileId);

        if(requested.isEmpty()){
            throw new Exception("Profile does not exist.");
        }

        iUserProfileRepo.deleteById(profileId);

        return createAndSaveUserProfile (inDto);
    }

    /**
     * To delete a profile
     */
    public void deleteARequestedUserProfile(String id) throws Exception {
        UserProfileID profileId = factoryId.createUserProfileID(id);

        if(!iUserProfileRepo.existsByUserProfileId(profileId)){
            throw new Exception("User profile does not exist.");
        }
        iUserProfileRepo.deleteById(profileId);
    }
}

