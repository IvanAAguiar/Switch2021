package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.dtoModel.mapper.UserMapper;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.factories.factoryInterfaces.IUserFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    /**
     * Attributes
     */
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserFactory userFactory;
    @Autowired
    private IUserIDFactory userIDFactory;
    @Autowired
    private IUserProfileRepo profileRepo;
    @Autowired
    private IUserProfileIDFactory profileIDFactory;

    /**
     * Register User (US001)
     */
    public OutputUserDTO createAndSaveUser(NewUserInfoDTO infoDTO) throws Exception {
        User newUser = userFactory.createUser(infoDTO);

        if (userRepo.existsById(newUser.getUserId())) {
            throw new Exception("System User Already exists!");
        }
        return userMapper.toDto(userRepo.save(newUser));
    }

    /**
     * Find All Users
     */
    public CollectionModel<PartialUserDTO> findAllUsers() {
        List<User> usersList = userRepo.findAll();
        return userMapper.toCollectionDTOPartial(usersList);
    }

    /**
     * Find User, by ID
     */
    public OutputUserDTO findUserById(String id) {
        User user = getUser(id);
        return userMapper.toDto(user);
    }

    /**
     * Search User By Parameters
     */
    public CollectionModel<OutputUserDTO> searchUsersByParams(SearchUserDTO inDto) {
        List<User> allFounded = new ArrayList<>();
        List<User> usersFounded = new ArrayList<>();

        if (!inDto.getName().isEmpty() || !inDto.getName().isBlank())
            allFounded.addAll(userRepo.findAllByNameContains(inDto.getName()));

        if (!inDto.getFunction().isEmpty() || !inDto.getFunction().isBlank())
            allFounded.addAll(userRepo.findAllByFunctionContains(inDto.getFunction()));

        if (!inDto.getProfile().isEmpty() || !inDto.getProfile().isBlank()) {
            UserProfileID profileId = profileIDFactory.createUserProfileID(inDto.getProfile());
            checkUserProfileExist(profileId);
            allFounded.addAll(userRepo.findAllByUserProfileContains(profileId));
        }

        allFounded.forEach(user -> {
            if (!usersFounded.contains(user)) {
                usersFounded.add(user);
            }
        });
        return userMapper.toCollectionDTO(usersFounded);
    }

    /**
     * Update Personal Data and Change Password (US010 and US011)
     */
    public OutputUserDTO updatePersonalData(String id, UpdateDataDTO updateDataDTO) {
        User user = getUser(id);

        if (updateDataDTO.getNewPassword() != null && updateDataDTO.getOldPassword() != null) {
            user.updatePassword(updateDataDTO.getOldPassword(), updateDataDTO.getNewPassword());
        } else {
            user.editPersonalData(updateDataDTO.getUserName(), updateDataDTO.getFunction(), updateDataDTO.getPhoto());
        }
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    /**
     * Update assigned profiles (US006)
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public OutputUserDTO assignUserProfile(String id, UpdateUserProfileDTO profileDTO) {
        User user = getUser(id);
        UserProfileID profileID = profileIDFactory.createUserProfileID(profileDTO.getProfileId());
        //Validate if exist the profile
        checkUserProfileExist(profileID);
        //Validate if the user has the user profile assigned
        if (!user.hasProfile(profileID)) {
            user.toAssignProfile(profileID);
        } else {
            throw new IllegalArgumentException("This user profile was already assigned!");
        }
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public OutputUserDTO assignUserProfiles(String id, UpdateUserProfilesDTO profileDTO) {
        User user = getUser(id);

        user.clearProfiles();

        for(String profile : profileDTO.profilesId){
            UserProfileID profileID = profileIDFactory.createUserProfileID(profile);
            //Validate if exist the profile
            if (!profileRepo.existsByUserProfileId(profileID)) {
                throw new IllegalArgumentException("This user profile does not exist!");
            }

            user.toAssignProfile(profileID);

        }


        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    public OutputUserDTO removeUserProfile(String id, UpdateUserProfileDTO profileDTO) {
        UserProfileID profileID;
        User user = getUser(id);
        //Validate if the profile is Visitor, all Users must have the visitor Profile.
        if (!profileDTO.getProfileId().equalsIgnoreCase("visitor")) {
            profileID = profileIDFactory.createUserProfileID(profileDTO.getProfileId());
        } else {
            throw new IllegalArgumentException("The user profile 'Visitor' can not be removed!");
        }
        //Validate if exist the profile
        checkUserProfileExist(profileID);
        //Validate if the user has the user profile assigned
        if (user.hasProfile(profileID)) {
            user.removeProfile(profileID);
        } else {
            throw new IllegalArgumentException("This user profile was not assigned!");
        }
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }


    /**
     * Active and Inactive User (US002, US025 and US026)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public OutputUserDTO activateUser(String id) {
        User user = getUser(id);
        if (user.isActive()) {
            throw new IllegalArgumentException("This user is already activated");
        }
        user.activateStatus();
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    public OutputUserDTO inactivateUser(String id) {
        User user = getUser(id);
        if (!user.isActive()) {
            throw new IllegalArgumentException("This user is already inactivated");
        }
        user.inactivateStatus();
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    /**
     * Create a Request to assign a user profile to a user (US003)
     */
    public boolean createAndAddRequest(String id, RequestDTO requestDTO) {
        UserProfileID profileID = profileIDFactory.createUserProfileID(requestDTO.getProfileId());
        User user = getUser(id);
        checkUserProfileExist(profileID);
        user.createProfileRequest(profileID);
        userRepo.save(user);
        return true;
    }

    /**
     * Delete User
     */
    public void deleteUser(String id) {
        User user = getUser(id);
        userRepo.delete(user.getUserId());
    }

    public User getUser(String id) {
        UserID userID = userIDFactory.createUserID(id);
        Optional<User> foundUser = userRepo.findByUserId(userID);

        User user = foundUser.flatMap(found -> foundUser).orElse(null);

        if (user == null) {
            throw new NullPointerException("This User does not exist!");
        }
        return user;
    }

    public void checkUserProfileExist(UserProfileID profileID) {
        if (!profileRepo.existsByUserProfileId(profileID)) {
            throw new NullPointerException("This user profile does not exist!");
        }
    }

    public CollectionModel<OutputUserDTO> showUserStatus() {
        List<User> users = userRepo.findAll();
        CollectionModel<OutputUserDTO> usersStatusList = userMapper.toCollectionDTO(users);
        for(OutputUserDTO user : usersStatusList) {
            user.assignedIdProfiles = new ArrayList<>();
            user.function = "";
        }

        return usersStatusList;
    }

}
