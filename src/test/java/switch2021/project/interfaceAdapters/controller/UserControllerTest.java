package switch2021.project.interfaceAdapters.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.UserService;
import switch2021.project.dtoModel.dto.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController ctrl;

    @MockBean
    UserService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @SneakyThrows
    @Test
    void testRegisterUser() {
        //Arrange
        NewUserInfoDTO test2 = mock(NewUserInfoDTO.class);
        OutputUserDTO test = mock(OutputUserDTO.class);
        when(service.createAndSaveUser(test2)).thenReturn(test);
        //Act
        ResponseEntity<?> response = ctrl.registerUser(test2);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @SneakyThrows
    @Test
    void testRegisterUserException() {
        //Arrange
        NewUserInfoDTO test = mock(NewUserInfoDTO.class);
        when(service.createAndSaveUser(test)).thenThrow(Exception.class);
        //Act
        ResponseEntity<?> response = ctrl.registerUser(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void getUserSuccess() {
        //Arrange
        OutputUserDTO test = mock(OutputUserDTO.class);
        String x = "1";
        when(test.getEmail()).thenReturn(x);
        when(service.findUserById(x)).thenReturn(test);
        //Act
        ResponseEntity<?> response = ctrl.getUser(x);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getUserException() {
        //Arrange
        doThrow(NullPointerException.class).when(service).findUserById(anyString());
        //Act
        ResponseEntity<?> response = ctrl.getUser("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void getAllUsersSuccess() {
        //Arrange
        when(service.findAllUsers()).thenReturn(CollectionModel.of
                (List.of(new PartialUserDTO[]{})));
        //Act
        ResponseEntity<?> response = ctrl.showAllUsers();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getAllUsersCatchException() {
        //Arrange
        doThrow(IllegalArgumentException.class).when(service).findAllUsers();
        //Act
        ResponseEntity<?> response = ctrl.showAllUsers();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void getUserByParamSuccess() {
        //Arrange
        SearchUserDTO test = mock(SearchUserDTO.class);
        OutputUserDTO xx = mock(OutputUserDTO.class);
        CollectionModel<OutputUserDTO> y = CollectionModel.of
                (List.of(new OutputUserDTO[]{xx}));
        when(service.searchUsersByParams(test)).thenReturn(y);
        //Act
        ResponseEntity<?> response = ctrl.searchUsersByTypedParams(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getUserByParamException() {
        //Arrange
        SearchUserDTO test = mock(SearchUserDTO.class);
        doThrow(IllegalArgumentException.class).when(service).searchUsersByParams(any());
        //Act
        ResponseEntity<?> response = ctrl.searchUsersByTypedParams(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testUpdateUser() {
        //Arrange
        UpdateDataDTO test = mock(UpdateDataDTO.class);
        OutputUserDTO test2 = mock(OutputUserDTO.class);
        when(service.updatePersonalData("1", test)).thenReturn(test2);
        //Act
        ResponseEntity<?> response = ctrl.updatePersonalData("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testUpdateUserException() {
        //Arrange
        UpdateDataDTO test = mock(UpdateDataDTO.class);
        when(service.updatePersonalData(any(), any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.updatePersonalData("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testAssignProfile() {
        //Arrange
        UpdateUserProfileDTO test = mock(UpdateUserProfileDTO.class);
        OutputUserDTO test2 = mock(OutputUserDTO.class);
        when(service.assignUserProfile("1", test)).thenReturn(test2);
        //Act
        ResponseEntity<?> response = ctrl.assignProfile("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void testAssignProfiles() {
        //Arrange
        UpdateUserProfilesDTO test = mock(UpdateUserProfilesDTO.class);
        OutputUserDTO test2 = mock(OutputUserDTO.class);
        when(service.assignUserProfiles("1", test)).thenReturn(test2);
        //Act
        ResponseEntity<?> response = ctrl.assignProfiles("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testAssignProfileException() {
        //Arrange
        UpdateUserProfileDTO test = mock(UpdateUserProfileDTO.class);
        when(service.assignUserProfile(any(), any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.assignProfile("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void testAssignProfilesException() {
        //Arrange
        UpdateUserProfilesDTO test = mock(UpdateUserProfilesDTO.class);
        when(service.assignUserProfiles(any(), any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.assignProfiles("54", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testRemoveProfile() {
        //Arrange
        UpdateUserProfileDTO test = mock(UpdateUserProfileDTO.class);
        OutputUserDTO test2 = mock(OutputUserDTO.class);
        when(service.removeUserProfile("1", test)).thenReturn(test2);
        //Act
        ResponseEntity<?> response = ctrl.removeProfile("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testRemoveProfileException() {
        //Arrange
        UpdateUserProfileDTO test = mock(UpdateUserProfileDTO.class);
        when(service.removeUserProfile(any(), any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.removeProfile("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testActivate() {
        //Arrange
        OutputUserDTO test2 = mock(OutputUserDTO.class);
        when(service.activateUser("1")).thenReturn(test2);
        //Act
        ResponseEntity<?> response = ctrl.activateUser("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testActivateUserException() {
        //Arrange
        when(service.activateUser(any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.activateUser("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testInactivate() {
        //Arrange
        OutputUserDTO test2 = mock(OutputUserDTO.class);
        when(service.inactivateUser("1")).thenReturn(test2);
        //Act
        ResponseEntity<?> response = ctrl.inactivateUser("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testInactivateUserException() {
        //Arrange
        when(service.inactivateUser(any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.inactivateUser("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testRequestUserProfile() {
        //Arrange
        RequestDTO test = mock(RequestDTO.class);
        when(service.createAndAddRequest("1", test)).thenReturn(true);
        //Act
        ResponseEntity<?> response = ctrl.requestUserProfile("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testRequestUserProfileException() {
        //Arrange
        RequestDTO test = mock(RequestDTO.class);
        when(service.createAndAddRequest(any(), any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.requestUserProfile("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testDeleteUser() {
        //Arrange
        doNothing().when(service).deleteUser("1");
        //Act
        ResponseEntity<?> response = ctrl.deleteUser("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testDeleteUserException() {
        //Arrange
        doThrow(IllegalArgumentException.class).when(service).deleteUser(any());
        //Act
        ResponseEntity<?> response = ctrl.deleteUser("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void getUserStatus() {
        //Arrange
        when(service.showUserStatus()).thenReturn(CollectionModel.of(List.of(new OutputUserDTO[]{})));
        //Act
        ResponseEntity <Object> response = ctrl.getUserStatus();
        //
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getUserStatusFail() {
        //Arrange
        when(service.showUserStatus()).thenThrow(NullPointerException.class);
        //Act
        ResponseEntity <Object> response = ctrl.getUserStatus();
        //
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }
}