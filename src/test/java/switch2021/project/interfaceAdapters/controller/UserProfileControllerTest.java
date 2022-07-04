package switch2021.project.interfaceAdapters.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.UserProfileService;
import switch2021.project.dtoModel.dto.UserProfileDTO;

import javax.net.ssl.SSLException;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserProfileControllerTest {

    @Autowired
    UserProfileController ctrl;

    @MockBean
    UserProfileService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllProfilesSuccess() throws SSLException {
        //Arrange
        Map<String, CollectionModel<UserProfileDTO>> profilesDTO = new HashMap<>();
        when(service.getAllProfiles()).thenReturn(profilesDTO);
        //Act
        ResponseEntity<?> response = ctrl.getAllProfiles();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getAllProfilesCatchException() throws SSLException {
        //Arrange
        when(service.getAllProfiles()).thenThrow();
        //Act
        ResponseEntity<?> response = ctrl.getAllProfiles();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void getProfileRequestedSuccess() {
        //Arrange
        UserProfileDTO test = mock(UserProfileDTO.class);
        String x = "Fixe";
        test.userProfileName = x;
        when(service.findUserProfileRequested(x)).thenReturn(test);
        //Act
        ResponseEntity<?> response = ctrl.showUserProfileRequested(x);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getProfileRequestedCatchException() {
        //Arrange
        when(service.findUserProfileRequested(anyString())).thenThrow();
        //Act
        ResponseEntity<?> response = ctrl.showUserProfileRequested("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @SneakyThrows
    @Test
    void testCreateProfile() {
        //Arrange
        UserProfileDTO test = mock(UserProfileDTO.class);
        test.userProfileName = "Adeus";
        UserProfileDTO outTest= mock(UserProfileDTO.class);
        when(service.createAndSaveUserProfile(test)).thenReturn(outTest);
        //Act
        ResponseEntity<?> response = ctrl.createUserProfile(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @SneakyThrows
    @Test
    void testCreateProfileException() {
        //Arrange
        UserProfileDTO test = mock(UserProfileDTO.class);
        test.userProfileName = "Adeus";
        when(service.createAndSaveUserProfile(test)).thenThrow(Exception.class);
        //Act
        ResponseEntity<?> response = ctrl.createUserProfile(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testUpdateProfile() {
        //Arrange
        UserProfileDTO test = mock(UserProfileDTO.class);
        UserProfileDTO outTest= mock(UserProfileDTO.class);
        when(service.editARequestedUserProfile("1", test)).thenReturn(outTest);
        //Act
        ResponseEntity<?> response = ctrl.editAUserProfile("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(202);
    }

    @SneakyThrows
    @Test
    void testUpdateProfileException() {
        //Arrange
        UserProfileDTO test = mock(UserProfileDTO.class);
        when(service.editARequestedUserProfile(anyString(),any())).thenThrow(Exception.class);
        //Act
        ResponseEntity<?> response = ctrl.editAUserProfile("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testDeleteProfile() {
        //Arrange
        doNothing().when(service).deleteARequestedUserProfile("1");
        //Act
        ResponseEntity<?> response = ctrl.deleteAUserProfile("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(202);
    }

    @SneakyThrows
    @Test
    void testDeleteProfileException() {
        //Arrange
        doThrow(IllegalArgumentException.class).when(service).deleteARequestedUserProfile("1");
        //Act
        ResponseEntity<?> response = ctrl.deleteAUserProfile("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }
}
