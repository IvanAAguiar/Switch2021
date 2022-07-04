package switch2021.project.entities.factories.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dtoModel.dto.NewUserInfoDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.aggregates.User.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserFactoryTest {

    @MockBean
    IEmailFactory emailFactory;
    @MockBean
    IUserIDFactory idFactory;
    @MockBean
    INameFactory nameFactory;
    @MockBean
    IFunctionFactory functionFactory;
    @MockBean
    IPasswordFactory passwordFactory;
    @MockBean
    IPhotoFactory photoFactory;
    @MockBean
    IUserProfileIDFactory userProfileIDFactory;
    @InjectMocks
    UserFactory underTest;

    @BeforeEach
    public void setUp()/* throws Exception*/ {
        MockitoAnnotations.openMocks(this);
    }

/*    @Test
    void itShouldCreateASystemUser() {
        //S.U.T. {SystemUserFactory, SystemUser}
        //Arrange
        NewUserInfoDTO infoDTODouble = mock(NewUserInfoDTO.class);

        Name nameDouble = mock(Name.class);
        UserID idDouble = new UserID(new Email("qqefr@adsdsa.com")) mock(UserID.class);
        Password passwordDouble = mock(Password.class);
        Function functionDouble = mock(Function.class);
        Photo photoDouble = mock(Photo.class);
        UserProfileID userProfileIDDouble = mock(UserProfileID.class);
        Description descriptionDouble = mock(Description.class);

        when(nameFactory.createName(any())).thenReturn(nameDouble);
        when(idFactory.createUserID(any())).thenReturn(idDouble);
        when(functionFactory.createFunction(any())).thenReturn(functionDouble);
        when(photoFactory.createPhoto(any())).thenReturn(photoDouble);

        when(passwordFactory.createPassword(any())).thenReturn(passwordDouble);
        when(passwordDouble.getPwd()).thenReturn("Qwerty_1");

        when(userProfileIDFactory.createUserProfileID("Visitor")).thenReturn(userProfileIDDouble);
        when(userProfileIDDouble.getUserProfileName()).thenReturn(descriptionDouble);
        when(descriptionDouble.getText()).thenReturn("Visitor");
        //Act
        User isCreated = underTest.createUser(infoDTODouble);
        //Assert
        assertNotNull(isCreated);
        assertEquals("Visitor",isCreated.getAssignedProfiles().get(0).getUserProfileName().getText());
    }
 */
}
