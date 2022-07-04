package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    IUserFactory userFactory;
    @MockBean
    IUserRepo userRepo;
    @MockBean
    UserMapper userMapper;
    @MockBean
    IUserIDFactory userIDFactory;
    @MockBean
    IUserProfileIDFactory profileIDFactory;
    @MockBean
    IUserProfileRepo profileRepo;

    @InjectMocks
    UserService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAUser() throws Exception {
        //Arrange
        NewUserInfoDTO infoDTO = mock(NewUserInfoDTO.class);
        User newUser = mock(User.class);
        when(userFactory.createUser(infoDTO)).thenReturn(newUser);

        UserID userID = mock(UserID.class);
        when(newUser.getUserId()).thenReturn(userID);
        when(userRepo.existsById(userID)).thenReturn(false);

        User savedUser = mock(User.class);
        when(userRepo.save(newUser)).thenReturn(savedUser);
        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);

        when(userMapper.toDto(savedUser)).thenReturn(outputUserDTO);
        //Act
        OutputUserDTO result = underTest.createAndSaveUser(infoDTO);
        //Assert
        assertEquals(outputUserDTO, result);
    }

    @Test
    void shouldFailToCreateAUserThatAlreadyExists() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            NewUserInfoDTO infoDTO = mock(NewUserInfoDTO.class);
            User newUser = mock(User.class);
            when(userFactory.createUser(infoDTO)).thenReturn(newUser);

            UserID userID = mock(UserID.class);
            when(newUser.getUserId()).thenReturn(userID);
            when(userRepo.existsById(userID)).thenReturn(true);
            //Act
            underTest.createAndSaveUser(infoDTO);
        });
    }

    @Test
    void shouldGetAllUsers() {
        //Arrange
        List<User> usersList = new ArrayList<>();
        when(userRepo.findAll()).thenReturn(usersList);
        List<PartialUserDTO> dtos = new ArrayList<>();
        CollectionModel<PartialUserDTO> collection = CollectionModel.of(dtos);
        when(userMapper.toCollectionDTOPartial(usersList)).thenReturn(collection);
        //Act
        CollectionModel<PartialUserDTO> result = underTest.findAllUsers();
        //Assert
        assertEquals(collection, result);
    }

    @Test
    void shouldGetAUserById() {
        //Arrange
        String id = "tdc@mymail.com";
        UserID userID = mock(UserID.class);
        when(userIDFactory.createUserID(id)).thenReturn(userID);
        User foundUser = mock(User.class);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(foundUser));

        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);
        when(userMapper.toDto(foundUser)).thenReturn(outputUserDTO);
        //Act
        OutputUserDTO result = underTest.findUserById(id);
        //Assert
        assertEquals(outputUserDTO, result);
    }

    @Test
    void shouldNotFindAUser() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.empty());
            //Act
            underTest.findUserById(id);
        });
    }

    @Test
    void shouldSearchUserByName() {
        //Arrange
        SearchUserDTO inDto = mock(SearchUserDTO.class);
        List<User> usersFounded = new ArrayList<>();
        List<OutputUserDTO> dtos = new ArrayList<>();
        when(inDto.getName()).thenReturn("Tino das Cruzes");
        when(inDto.getFunction()).thenReturn("");
        when(inDto.getProfile()).thenReturn("");

        CollectionModel<OutputUserDTO> collection = CollectionModel.of(dtos);

        when(userMapper.toCollectionDTO(usersFounded)).thenReturn(collection);
        //Act
        CollectionModel<OutputUserDTO> result = underTest.searchUsersByParams(inDto);
        //Assert
        assertEquals(collection, result);
    }

    @Test
    void shouldSearchUserByFunction() {
        //Arrange
        SearchUserDTO inDto = mock(SearchUserDTO.class);
        List<User> usersFounded = new ArrayList<>();
        List<OutputUserDTO> dtos = new ArrayList<>();
        when(inDto.getName()).thenReturn("");
        when(inDto.getFunction()).thenReturn("Anything");
        when(inDto.getProfile()).thenReturn("");

        CollectionModel<OutputUserDTO> collection = CollectionModel.of(dtos);

        when(userMapper.toCollectionDTO(usersFounded)).thenReturn(collection);
        //Act
        CollectionModel<OutputUserDTO> result = underTest.searchUsersByParams(inDto);
        //Assert
        assertEquals(collection, result);
    }

    @Test
    void shouldSearchUserByProfile() {
        //Arrange
        SearchUserDTO inDto = mock(SearchUserDTO.class);
        List<User> usersFounded = new ArrayList<>();
        List<OutputUserDTO> dtos = new ArrayList<>();
        when(inDto.getName()).thenReturn("");
        when(inDto.getFunction()).thenReturn("");

        when(inDto.getProfile()).thenReturn("User");
        UserProfileID profileId = mock(UserProfileID.class);
        when(profileIDFactory.createUserProfileID("User")).thenReturn(profileId);
        when(profileRepo.existsByUserProfileId(profileId)).thenReturn(true);

        CollectionModel<OutputUserDTO> collection = CollectionModel.of(dtos);

        when(userMapper.toCollectionDTO(usersFounded)).thenReturn(collection);
        //Act
        CollectionModel<OutputUserDTO> result = underTest.searchUsersByParams(inDto);
        //Assert
        assertEquals(collection, result);
    }

    @Test
    void shouldNotSearchUserByProfileUnknown() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            SearchUserDTO inDto = mock(SearchUserDTO.class);
            when(inDto.getName()).thenReturn("");
            when(inDto.getFunction()).thenReturn("");

            when(inDto.getProfile()).thenReturn("User");
            UserProfileID profileId = mock(UserProfileID.class);
            when(profileIDFactory.createUserProfileID("User")).thenReturn(profileId);
            when(profileRepo.existsByUserProfileId(profileId)).thenReturn(false);
            //Act
            underTest.searchUsersByParams(inDto);
        });
    }

    @Test
    void shouldUpdateUserPassword() {
        //Arrange
        String id = "tdc@mymail.com";
        UserID userID = mock(UserID.class);
        when(userIDFactory.createUserID(id)).thenReturn(userID);
        User user = mock(User.class);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

        UpdateDataDTO updateDataDTO = mock(UpdateDataDTO.class);
        String newPassword = "Qwerty1!";
        String oldPassword = "Qwerty2!";
        when(updateDataDTO.getNewPassword()).thenReturn(newPassword);
        when(updateDataDTO.getOldPassword()).thenReturn(oldPassword);

        User updatedUser = mock(User.class);
        when(userRepo.save(user)).thenReturn(updatedUser);
        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);
        when(userMapper.toDto(updatedUser)).thenReturn(outputUserDTO);
        //Act
        OutputUserDTO result = underTest.updatePersonalData(id, updateDataDTO);
        //Assert
        assertEquals(outputUserDTO, result);
    }

    @Test
    void shouldUpdatePersonalData() {
        //Arrange
        String id = "tdc@mymail.com";
        UserID userID = mock(UserID.class);
        when(userIDFactory.createUserID(id)).thenReturn(userID);
        User user = mock(User.class);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

        UpdateDataDTO updateDataDTO = mock(UpdateDataDTO.class);
        when(updateDataDTO.getNewPassword()).thenReturn(null);
        when(updateDataDTO.getOldPassword()).thenReturn(null);
        String userName = "Tino das Cruzes";
        String function = "Not so flexible";
        String photo = "photo.jpeg";
        when(updateDataDTO.getUserName()).thenReturn(userName);
        when(updateDataDTO.getFunction()).thenReturn(function);
        when(updateDataDTO.getPhoto()).thenReturn(photo);

        User updatedUser = mock(User.class);
        when(userRepo.save(user)).thenReturn(updatedUser);
        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);
        when(userMapper.toDto(updatedUser)).thenReturn(outputUserDTO);
        //Act
        OutputUserDTO result = underTest.updatePersonalData(id, updateDataDTO);
        //Assert
        assertEquals(outputUserDTO, result);
    }

    @Test
    void shouldAssignUserProfile() {
        //Arrange
        String id = "tdc@mymail.com";
        UserID userID = mock(UserID.class);
        when(userIDFactory.createUserID(id)).thenReturn(userID);
        User user = mock(User.class);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

        UpdateUserProfileDTO profileDTO = mock(UpdateUserProfileDTO.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(profileDTO.getProfileId()).thenReturn("User");
        when(profileIDFactory.createUserProfileID("User")).thenReturn(profileID);

        when(profileRepo.existsByUserProfileId(profileID)).thenReturn(true);
        when(user.hasProfile(profileID)).thenReturn(false);

        User updatedUser = mock(User.class);
        when(userRepo.save(user)).thenReturn(updatedUser);
        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);
        when(userMapper.toDto(updatedUser)).thenReturn(outputUserDTO);
        //Act
        OutputUserDTO result = underTest.assignUserProfile(id, profileDTO);
        //Assert
        assertEquals(outputUserDTO, result);
    }

    @Test
    void shouldNotAssignUserProfileUnknown() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            UpdateUserProfileDTO profileDTO = mock(UpdateUserProfileDTO.class);
            UserProfileID profileID = mock(UserProfileID.class);
            when(profileDTO.getProfileId()).thenReturn("User");
            when(profileIDFactory.createUserProfileID("User")).thenReturn(profileID);

            when(profileRepo.existsByUserProfileId(profileID)).thenReturn(false);
            //Act
            underTest.assignUserProfile(id, profileDTO);
        });
    }

    @Test
    void shouldNotAssignUserProfileAlreadyAssigned() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            UpdateUserProfileDTO profileDTO = mock(UpdateUserProfileDTO.class);
            UserProfileID profileID = mock(UserProfileID.class);
            when(profileDTO.getProfileId()).thenReturn("User");
            when(profileIDFactory.createUserProfileID("User")).thenReturn(profileID);

            when(profileRepo.existsByUserProfileId(profileID)).thenReturn(true);
            when(user.hasProfile(profileID)).thenReturn(true);
            //Act
            underTest.assignUserProfile(id, profileDTO);
        });
    }

    @Test
    void shouldRemoveUserProfile() {
        //Arrange
        String id = "tdc@mymail.com";
        UserID userID = mock(UserID.class);
        when(userIDFactory.createUserID(id)).thenReturn(userID);
        User user = mock(User.class);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

        UpdateUserProfileDTO profileDTO = mock(UpdateUserProfileDTO.class);
        UserProfileID profileID = mock(UserProfileID.class);
        when(profileDTO.getProfileId()).thenReturn("User");
        when(profileIDFactory.createUserProfileID("User")).thenReturn(profileID);

        when(profileRepo.existsByUserProfileId(profileID)).thenReturn(true);
        when(user.hasProfile(profileID)).thenReturn(true);

        User updatedUser = mock(User.class);
        when(userRepo.save(user)).thenReturn(updatedUser);
        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);
        when(userMapper.toDto(updatedUser)).thenReturn(outputUserDTO);
        //Act
        OutputUserDTO result = underTest.removeUserProfile(id, profileDTO);
        //Assert
        assertEquals(outputUserDTO, result);
    }

    @Test
    void shouldNotRemoveUserProfileVisitor() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            UpdateUserProfileDTO profileDTO = mock(UpdateUserProfileDTO.class);
            when(profileDTO.getProfileId()).thenReturn("visitor");
            //Act
            underTest.removeUserProfile(id, profileDTO);
        });
    }

    @Test
    void shouldNotRemoveUserProfileUnknown() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            UpdateUserProfileDTO profileDTO = mock(UpdateUserProfileDTO.class);
            UserProfileID profileID = mock(UserProfileID.class);
            when(profileDTO.getProfileId()).thenReturn("User");
            when(profileIDFactory.createUserProfileID("User")).thenReturn(profileID);

            when(profileRepo.existsByUserProfileId(profileID)).thenReturn(false);
            //Act
            underTest.removeUserProfile(id, profileDTO);
        });
    }

    @Test
    void shouldNotRemoveUserProfileNotAssigned() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            UpdateUserProfileDTO profileDTO = mock(UpdateUserProfileDTO.class);
            UserProfileID profileID = mock(UserProfileID.class);
            when(profileDTO.getProfileId()).thenReturn("User");
            when(profileIDFactory.createUserProfileID("User")).thenReturn(profileID);

            when(profileRepo.existsByUserProfileId(profileID)).thenReturn(true);
            when(user.hasProfile(profileID)).thenReturn(false);
            //Act
            underTest.removeUserProfile(id, profileDTO);
        });
    }

    @Test
    void shouldActivateUser() {
        //Arrange
        String id = "tdc@mymail.com";
        UserID userID = mock(UserID.class);
        when(userIDFactory.createUserID(id)).thenReturn(userID);
        User user = mock(User.class);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

        when(user.isActive()).thenReturn(false);

        User updatedUser = mock(User.class);
        when(userRepo.save(user)).thenReturn(updatedUser);
        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);
        when(userMapper.toDto(updatedUser)).thenReturn(outputUserDTO);
        //Act
        OutputUserDTO result = underTest.activateUser(id);
        //Assert
        assertEquals(outputUserDTO, result);
    }

    @Test
    void shouldNotActivateUser() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            when(user.isActive()).thenReturn(true);
            //Act
            underTest.activateUser(id);
        });
    }

    @Test
    void shouldInactivateUser() {
        //Arrange
        String id = "tdc@mymail.com";
        UserID userID = mock(UserID.class);
        when(userIDFactory.createUserID(id)).thenReturn(userID);
        User user = mock(User.class);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

        when(user.isActive()).thenReturn(true);

        User updatedUser = mock(User.class);
        when(userRepo.save(user)).thenReturn(updatedUser);
        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);
        when(userMapper.toDto(updatedUser)).thenReturn(outputUserDTO);
        //Act
        OutputUserDTO result = underTest.inactivateUser(id);
        //Assert
        assertEquals(outputUserDTO, result);
    }

    @Test
    void shouldNotInactivateUser() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            when(user.isActive()).thenReturn(false);
            //Act
            underTest.inactivateUser(id);
        });
    }

    @Test
    void shouldCreateAndAddRequest() {
        //Arrange
        String id = "tdc@mymail.com";
        UserID userID = mock(UserID.class);
        when(userIDFactory.createUserID(id)).thenReturn(userID);
        User user = mock(User.class);
        when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

        UserProfileID profileID = mock(UserProfileID.class);
        RequestDTO requestDTO = mock(RequestDTO.class);
        when(requestDTO.getProfileId()).thenReturn("User");
        when(profileIDFactory.createUserProfileID("User")).thenReturn(profileID);

        when(profileRepo.existsByUserProfileId(profileID)).thenReturn(true);

        User updatedUser = mock(User.class);
        when(userRepo.save(user)).thenReturn(updatedUser);
        //Act
        boolean result = underTest.createAndAddRequest(id, requestDTO);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldNotCreateAndAddRequest() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            UserProfileID profileID = mock(UserProfileID.class);
            RequestDTO requestDTO = mock(RequestDTO.class);
            when(requestDTO.getProfileId()).thenReturn("User");
            when(profileIDFactory.createUserProfileID("User")).thenReturn(profileID);

            when(profileRepo.existsByUserProfileId(profileID)).thenReturn(false);
            //Act
            underTest.createAndAddRequest(id, requestDTO);
        });
    }

    @Test
    void shouldDeleteUser() {
        //Assert
        Assertions.assertDoesNotThrow(() -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            User user = mock(User.class);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.of(user));

            when(user.getUserId()).thenReturn(userID);
            //Act
            underTest.deleteUser(id);
        });
    }

    @Test
    void shouldNotDeleteUserNonExistent() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String id = "tdc@mymail.com";
            UserID userID = mock(UserID.class);
            when(userIDFactory.createUserID(id)).thenReturn(userID);
            when(userRepo.findByUserId(userID)).thenReturn(Optional.empty());
            //Act
            underTest.deleteUser(id);
        });
    }

    @Test
    void shouldReturnUserStatusList() {
        //Arrange
        List<User> userList = new ArrayList<>();
        User user = mock(User.class);
        userList.add(user);
        List<OutputUserDTO> outputUserDTOList = new ArrayList<>();
        OutputUserDTO outputUserDTO = mock(OutputUserDTO.class);
        outputUserDTOList.add(outputUserDTO);
        CollectionModel<OutputUserDTO> userDtoList = CollectionModel.of(outputUserDTOList);
        when(userRepo.findAll()).thenReturn(userList);
        when(userMapper.toCollectionDTO(userList)).thenReturn(userDtoList);
        //Act
        CollectionModel<OutputUserDTO> result = underTest.showUserStatus();
        //Assert
        assertEquals(userDtoList,result);
    }
}
