package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.OutputUserDTO;
import switch2021.project.dtoModel.dto.PartialUserDTO;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.vos.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserMapperTest {

    @Test
    public void toDTOSuccess() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        OutputUserDTO dto = new OutputUserDTO("User Name", "user@email.com",
                "Function", "photo.png", "True", new ArrayList<>());
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);
        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("photo.png");
        when(user.isActive()).thenReturn(true);
        //Act
        OutputUserDTO result = userMapper.toDto(user);
        //Assert
        assertEquals(dto, result.removeLinks());
        assertEquals(dto.getUserName(),result.getUserName());
        assertEquals(dto.getEmail(),result.getEmail());
        assertEquals(dto.getFunction(),result.getFunction());
        assertEquals(dto.getPhoto(),result.getPhoto());
        assertEquals(dto.isActive,result.isActive);
        assertEquals(dto.assignedIdProfiles,result.assignedIdProfiles);
    }

    @Test
    public void toCollection() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        OutputUserDTO dto = new OutputUserDTO();
        dto.userName = "User Name";
        dto.email = "user@email.com";
        dto.function = "Function";
        dto.photo = "photo.png";
        dto.isActive = "true";
        dto.assignedIdProfiles = new ArrayList<>();
        dto.assignedIdProfiles.add("Visitor");
        List<UserProfileID> profiles = new ArrayList<>();
        UserProfileID profileID = mock(UserProfileID.class);
        profiles.add(profileID);
        UserProfile profile = mock(UserProfile.class);
        Description des = mock(Description.class);
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);
        when(des.getText()).thenReturn("Visitor");
        when(profileID.getUserProfileName()).thenReturn(des);
        when(profile.getUserProfileId()).thenReturn(profileID);
        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(user.getAssignedIdProfiles()).thenReturn(profiles);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("photo.png");
        when(user.isActive()).thenReturn(true);
        List<User> users = new ArrayList<>();
        users.add(user);
        //Act
        CollectionModel<OutputUserDTO> result = userMapper.toCollectionDTO(users);
        //Assert
        assertEquals(1, result.getContent().size());
    }

    @Test
    public void toDTOPartial() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        OutputUserDTO dto = new OutputUserDTO();
        dto.userName = "User Name";
        dto.email = "user@email.com";
        dto.function = "Function";
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);

        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        //Act
        PartialUserDTO result = userMapper.toDtoPartial(user);
        //Assert
        assertEquals(dto.getUserName(), result.getUserName());
        assertEquals(dto.getFunction(), result.getFunction());
        assertEquals(dto.getEmail(), result.getEmail());
    }

    @Test
    public void toCollectionDTOPartial() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        PartialUserDTO dto = new PartialUserDTO();
        dto.userName = "User Name";
        dto.email = "user@email.com";
        dto.function = "Function";
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);

        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        List<User> users = new ArrayList<>();
        users.add(user);
        //Act
        CollectionModel<PartialUserDTO> result = userMapper.toCollectionDTOPartial(users);
        //Assert
        assertEquals(1, result.getContent().size());
    }
}
