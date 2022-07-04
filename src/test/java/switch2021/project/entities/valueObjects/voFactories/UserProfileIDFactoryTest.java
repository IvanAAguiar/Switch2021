package switch2021.project.entities.valueObjects.voFactories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.entities.valueObjects.voFactories.voFactories.DescriptionFactory;
import switch2021.project.entities.valueObjects.voFactories.voFactories.UserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserProfileIDFactoryTest {

    @InjectMocks
    private UserProfileIDFactory userProfileIDFactory;

    @Mock
    private DescriptionFactory descriptionFactory;
    @Mock
    private Description description;
    @Mock
    private UserProfileID userProfileID;

    @Test
    @DisplayName("Test to create a User Profile, with success")
    public void createUserProfileID(){
        //Arrange
        String des = "User Profile";
        description = new Description(des);
        when(descriptionFactory.createDescription(des)).thenReturn(description);
        //Act
        userProfileID = userProfileIDFactory.createUserProfileID(des);
        //Arrange
        assertEquals(des, userProfileID.getUserProfileName().getText());
    }
}
