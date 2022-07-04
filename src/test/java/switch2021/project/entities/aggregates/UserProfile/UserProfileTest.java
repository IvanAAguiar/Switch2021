package switch2021.project.entities.aggregates.UserProfile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileTest {

    @Test
    @DisplayName("Constructor Test, with success")
    void ConstructorUserProfile_Success() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        Description description = mock(Description.class);
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        //Act
        UserProfile userProfile = new UserProfile(userProfileID);
        //Assert
        assertEquals("User Profile", userProfile.getUserProfileId().getUserProfileName().getText());
    }

    @Test
    @DisplayName("Constructor Test Integration, with success")
    void IntegrationTestConstructorUserProfile_Success() {
        //Arrange
        UserProfileID userProfileID = new UserProfileID(new Description("User Profile"));
        //Act
        UserProfile userProfile = new UserProfile(userProfileID);
        //Assert
        assertEquals ("User Profile", userProfile.getUserProfileId().getUserProfileName().getText());
    }


    @Test
    @DisplayName("Constructor Test, with failure")
    void ConstructorUserProfile_Failure() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        Description description = mock(Description.class);
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("User Profile");
        //Act
        UserProfile userProfile = new UserProfile(userProfileID);
        //Assert
        assertNotEquals("Profile User", userProfile.getUserProfileId().getUserProfileName().getText());
    }

    @Test
    @DisplayName("HashCode Verification")
    public void hashCodeTest() {
        //Arrange
        Description description = new Description("Description");
        Description description1 = new Description("Description 1");
        UserProfileID userProfileID = new UserProfileID(description);
        UserProfile userProfile = new UserProfile(userProfileID);
        UserProfileID userProfileID1 = new UserProfileID(description1);
        UserProfile userProfile1 = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID1);
        //Assert
        assertEquals(userProfile.hashCode(), userProfile1.hashCode());
        assertNotEquals(userProfile2.hashCode(), userProfile1.hashCode());
    }

    @Test
    @DisplayName("Override Verification")
    public void overrideTest() {
        //Arrange
        Description description = new Description("Description");
        Description description1 = new Description("Description 1");
        UserProfileID userProfileID = new UserProfileID(description);
        UserProfile userProfile = new UserProfile(userProfileID);
        UserProfileID userProfileID1 = new UserProfileID(description1);
        UserProfile userProfile1 = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID1);
        //Assert
        assertEquals(userProfile, userProfile1);
        assertNotEquals(userProfile2, userProfile1);
    }

    @Test
    @DisplayName("Same Identify As - True")
    public void sameIdentityAs_true() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile1 = new UserProfile(userProfileID);
        UserProfile userProfile2 = new UserProfile(userProfileID);
        //Act
        when(userProfileID.sameValueAs(userProfileID)).thenReturn(true);
        //Assert
        assertTrue(userProfile1.sameIdentityAs(userProfile2));
    }

    @Test
    @DisplayName("Same Identify As - False")
    public void sameIdentityAs_false() {
        //Arrange
        UserProfileID userProfileID1 = mock(UserProfileID.class);
        UserProfileID userProfileID2 = mock(UserProfileID.class);
        UserProfile userProfile1 = new UserProfile(userProfileID1);
        UserProfile userProfile2 = new UserProfile(userProfileID2);
        //Act
        when(userProfileID1.sameValueAs(userProfileID2)).thenReturn(false);
        //Assert
        assertFalse(userProfile1.sameIdentityAs(userProfile2));
    }

    @Test
    @DisplayName("Same Identify As - Null")
    public void sameIdentityAs_Null() {
        //Arrange
        UserProfileID userProfileID = mock(UserProfileID.class);
        UserProfile userProfile = new UserProfile(userProfileID);
        UserProfile userProfile1 = new UserProfile(null);
        //Act
        when(userProfileID.sameValueAs(null)).thenReturn(false);
        //Assert
        assertFalse(userProfile.sameIdentityAs(userProfile1));
    }
}
