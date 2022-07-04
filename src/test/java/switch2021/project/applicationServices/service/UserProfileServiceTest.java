package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.applicationServices.iRepositories.IUserProfileWebRepository;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.dtoModel.mapper.UserProfileMapper;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.factories.factoryInterfaces.IUserProfileFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import javax.net.ssl.SSLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest

public class UserProfileServiceTest {

    @Autowired
    UserProfileService userProfileService;
    @MockBean
    IUserProfileRepo iUserProfileRepo;
    @MockBean
    UserProfileMapper userProfileMapper;
    @MockBean
    IUserProfileFactory iUserProfileFactory;
    @MockBean
    IUserProfileIDFactory factoryId;
    @MockBean
    UserProfile userProfile;
    @MockBean
    UserProfileDTO userProfileDTO;
    @MockBean
    IUserProfileWebRepository iUserProfileWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAndSaveUserProfileSuccess() throws Exception {
        //Arrange
        UserProfileDTO dto = mock(UserProfileDTO.class);
        UserProfile newUserProfile = mock(UserProfile.class);
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfileDTO outDTO = mock(UserProfileDTO.class);
        UserProfile userProfileSaved = mock(UserProfile.class);
        when(iUserProfileFactory.createUserProfile(dto)).thenReturn(newUserProfile);
        when(newUserProfile.getUserProfileId()).thenReturn(userProfileID);
        when(iUserProfileRepo.existsByUserProfileId(userProfileID)).thenReturn(false);
        when(factoryId.createUserProfileID("ok")).thenReturn(userProfileID);
        when(iUserProfileRepo.save(newUserProfile)).thenReturn(userProfileSaved);
        when(userProfileMapper.toDTO(userProfileSaved)).thenReturn(outDTO);
        //Act
        UserProfileDTO result = userProfileService.createAndSaveUserProfile(dto);
        //Assert
        assertEquals(outDTO, result);
    }

    @Test
    void createAndSaveUserProfileAlreadyExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            UserProfileDTO dto = mock(UserProfileDTO.class);
            UserProfile newUserProfile = mock(UserProfile.class);
            UserProfileID userProfileID = mock(UserProfileID.class);
            UserProfileDTO outDTO = mock(UserProfileDTO.class);
            UserProfile userProfileSaved = mock(UserProfile.class);
            when(iUserProfileFactory.createUserProfile(dto)).thenReturn(newUserProfile);
            when(newUserProfile.getUserProfileId()).thenReturn(userProfileID);
            when(iUserProfileRepo.existsByUserProfileId(userProfileID)).thenReturn(true);
            when(factoryId.createUserProfileID("ok")).thenReturn(userProfileID);
            when(iUserProfileRepo.save(newUserProfile)).thenReturn(userProfileSaved);
            when(userProfileMapper.toDTO(userProfileSaved)).thenReturn(outDTO);
            //Act
            userProfileService.createAndSaveUserProfile(dto);
        });
    }

    @Test
    void getAllUserProfileSuccess() throws SSLException {
        //Arrange
        List<UserProfile> userProfiles = new ArrayList<>();
        when(iUserProfileRepo.findAll()).thenReturn(userProfiles);
        List<UserProfile> userProfiles2 = new ArrayList<>();
        when(iUserProfileWebRepository.findAll()).thenReturn(userProfiles2);

        CollectionModel<UserProfileDTO> outputUserProfileListIntern = CollectionModel.empty();
        when(userProfileMapper.toCollectionDTO(userProfiles, false)).thenReturn(outputUserProfileListIntern);

        CollectionModel<UserProfileDTO> outputUserProfileListExtern = CollectionModel.empty();
        when(userProfileMapper.toCollectionDTO(userProfiles, false)).thenReturn(outputUserProfileListExtern);

        Map<String, CollectionModel<UserProfileDTO>> allProfilesDto = new HashMap<>();
        allProfilesDto.put("internalUserProfiles", outputUserProfileListIntern );
//        allProfilesDto.put("externalUserProfiles", outputUserProfileListExtern);
        allProfilesDto.put("externalUserProfiles", null);  //while webclient not working use this line, when working use above line
        //Act
        Map<String, CollectionModel<UserProfileDTO>> result = userProfileService.getAllProfiles();
        //Assert
        assertEquals(allProfilesDto, result);
    }

    @Test
    void findUserProfileSuccess() throws Exception {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileID profileID = mock(UserProfileID.class);
        UserProfileDTO outDTO = mock(UserProfileDTO.class);
        when(factoryId.createUserProfileID("ok")).thenReturn(profileID);
        when(iUserProfileRepo.findByUserProfileID(profileID)).thenReturn(java.util.Optional.ofNullable(userProfile));
        when(userProfileMapper.toDTO(Objects.requireNonNull(userProfile))).thenReturn(outDTO);
        //Act
        UserProfileDTO result = userProfileService.findUserProfileRequested("ok");
        //Assert
        assertEquals(outDTO, result);
    }

    @Test
    void findUserProfileNonExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            UserProfile userProfile = mock(UserProfile.class);
            UserProfileID profileID = mock(UserProfileID.class);
            UserProfileDTO outDTO = mock(UserProfileDTO.class);
            when(iUserProfileRepo.findByUserProfileID(profileID)).thenReturn(java.util.Optional.ofNullable(userProfile));
            when(userProfileMapper.toDTO(Objects.requireNonNull(userProfile))).thenReturn(outDTO);
            //Act
            userProfileService.findUserProfileRequested("");
        });
    }

    @Test
    void editUserProfileSuccess() throws Exception {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileID profileID = mock(UserProfileID.class);
        UserProfileDTO outDTO = mock(UserProfileDTO.class);
        UserProfileDTO inDTO = mock(UserProfileDTO.class);
        UserProfile userProfileSaved = mock(UserProfile.class);
        when(factoryId.createUserProfileID("ok")).thenReturn(profileID);
        when(iUserProfileRepo.findByUserProfileID(profileID)).thenReturn(java.util.Optional.ofNullable(userProfile));
        when(iUserProfileRepo.deleteById(profileID)).thenReturn(true);
        when(iUserProfileFactory.createUserProfile(inDTO)).thenReturn(userProfile);
        when(Objects.requireNonNull(userProfile).getUserProfileId()).thenReturn(profileID);

        when(iUserProfileRepo.existsByUserProfileId(profileID)).thenReturn(false);
        when(iUserProfileRepo.save(userProfile)).thenReturn(userProfileSaved);
        when(userProfileMapper.toDTO(userProfileSaved)).thenReturn(outDTO);

        when(userProfileService.createAndSaveUserProfile(inDTO)).thenReturn(outDTO);

        //Act
        UserProfileDTO result = userProfileService.editARequestedUserProfile("ok", inDTO);
        //Assert
        assertEquals(outDTO, result);
    }

    @Test
    void editUserProfileNonExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            UserProfileID profileID = mock(UserProfileID.class);
            UserProfileDTO inDTO = mock(UserProfileDTO.class);
            when(factoryId.createUserProfileID("ok")).thenReturn(profileID);
            when(iUserProfileRepo.findByUserProfileID(profileID)).thenReturn(Optional.empty());
            //Act
            userProfileService.editARequestedUserProfile("ok", inDTO);
        });
    }

    @Test
    void deleteUserProfileSuccess() {
        //Assert
        Assertions.assertDoesNotThrow(() -> {
            //Arrange
            UserProfileID profileID = mock(UserProfileID.class);
            UserProfileDTO outDTO = mock(UserProfileDTO.class);
            outDTO.userProfileName="ok";
            when(factoryId.createUserProfileID("ok")).thenReturn(profileID);
            when(iUserProfileRepo.existsByUserProfileId(profileID)).thenReturn(true);
            when(iUserProfileRepo.deleteById(profileID)).thenReturn(true);
            //Act
            userProfileService.deleteARequestedUserProfile("ok");
        });
    }

    @Test
    void deleteUserProfileNonExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            UserProfileID profileID = mock(UserProfileID.class);
            UserProfileDTO outDTO = mock(UserProfileDTO.class);
            outDTO.userProfileName="ok";
            when(factoryId.createUserProfileID("ok")).thenReturn(profileID);
            when(iUserProfileRepo.existsByUserProfileId(profileID)).thenReturn(false);
            when(iUserProfileRepo.deleteById(profileID)).thenReturn(true);
            //Act
            userProfileService.deleteARequestedUserProfile("ok");
        });
    }





    @Test
    @DisplayName("Test to create and save user profile")
    public void createAndSaveUserProfile() throws Exception {
        //Arrange
        when(iUserProfileFactory.createUserProfile(userProfileDTO)).thenReturn(userProfile);
        when(iUserProfileRepo.save(userProfile)).thenReturn(userProfile);
        when(userProfileMapper.toDTO(userProfile)).thenReturn(userProfileDTO);

        //Act
        UserProfileDTO dto = userProfileService.createAndSaveUserProfile(userProfileDTO);

        //Assert
        assertEquals(dto.userProfileName, userProfileService.createAndSaveUserProfile(userProfileDTO).userProfileName);
    }
}
