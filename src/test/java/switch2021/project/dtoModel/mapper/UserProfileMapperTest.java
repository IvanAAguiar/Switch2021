package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileMapperTest {

    @Test
    @DisplayName("toOutputDTO test")
    public void toOuputDTOSuccess() {
        //Arrange
        UserProfileMapper userProfileMapper = new UserProfileMapper();
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileID userProfileID = mock(UserProfileID.class);
        Description description = mock(Description.class);
        //Act
        when(userProfile.getUserProfileId()).thenReturn(userProfileID);
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        UserProfileDTO outputUserProfileDTO = userProfileMapper.toDTO(userProfile);
        //Assert
        assertEquals(userProfile.getUserProfileId().getUserProfileName().getText(), outputUserProfileDTO.userProfileName);
    }

    @Test
    @DisplayName("Test UserProfileDTO")
    public void userProfileDTO(){
        //Arrange
        UserProfileMapper userProfileMapper = new UserProfileMapper();
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileID userProfileID = mock(UserProfileID.class);
        Description description = mock(Description.class);
        //Act
        when(userProfile.getUserProfileId()).thenReturn(userProfileID);
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        UserProfileDTO outputUserProfileDTO = userProfileMapper.toDTO(userProfile);
        //Assert
        assertEquals(outputUserProfileDTO.getUserProfileName(), "User Profile");
    }

    @Test
    public void toCollectionDTO() {
        //Arrange
        UserProfileMapper userProfileMapper = new UserProfileMapper();
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileID userProfileID = mock(UserProfileID.class);
        Description description = mock(Description.class);
        when(userProfile.getUserProfileId()).thenReturn(userProfileID);
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        List<UserProfile> profiles = new ArrayList<>();
        profiles.add(userProfile);
        //Act
        CollectionModel<UserProfileDTO> result = userProfileMapper.toCollectionDTO(profiles, true);
        //Assert
        assertEquals(1, result.getContent().size());
    }
}
