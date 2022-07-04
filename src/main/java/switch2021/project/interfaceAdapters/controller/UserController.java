package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.applicationServices.service.UserService;
import switch2021.project.dtoModel.dto.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Attributes
     */
    @Autowired
    private UserService userService;


    /**
     * Register an User (US001)
     */
    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody NewUserInfoDTO newUserInfoDTO) {
        OutputUserDTO outputDTO;

        try {
            outputDTO = userService.createAndSaveUser(newUserInfoDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.CREATED);
    }


    /**
     * Get User, by ID (US004)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") String id) {
        ErrorMessage message = new ErrorMessage();
        OutputUserDTO outputDTO;

        try {
            outputDTO = userService.findUserById(id);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.OK);
    }


    /**
     * Show All Users in the System (US004 and US024)
     */
    @GetMapping
    public ResponseEntity<Object> showAllUsers() {
        CollectionModel<PartialUserDTO> allUsersDto;

        try {
            allUsersDto = CollectionModel.of(userService.findAllUsers());
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(allUsersDto, HttpStatus.OK);
    }

    /**
     * Show All User, By some Parameters (US004 and US024)
     */
    @PostMapping("/findBy")
    public ResponseEntity<Object> searchUsersByTypedParams(@RequestBody SearchUserDTO inDto) {
        CollectionModel<OutputUserDTO> usersFoundedDto;

        try {
            usersFoundedDto = userService.searchUsersByParams(inDto);
        } catch (Exception exception) {
            ErrorMessage error = new ErrorMessage();
            error.errorMessage = exception.getMessage();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usersFoundedDto, HttpStatus.OK);
    }


    /**
     * Update User Data (UserName, Function, Photo) (US010)
     * Change Password (US011)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updatePersonalData(@PathVariable("id") String id,
                                                     @RequestBody UpdateDataDTO updateDataDTO) {
        OutputUserDTO outputDTO;

        try {
            outputDTO = userService.updatePersonalData(id, updateDataDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.OK);
    }


    /**
     * Assign a User Profiles (US006)
     */
    @PatchMapping("/{id}/assignProfile")
    public ResponseEntity<Object> assignProfile(@PathVariable("id") String id,
                                                @RequestBody UpdateUserProfileDTO profileDTO) {
        OutputUserDTO outputDTO;

        try {
            outputDTO = userService.assignUserProfile(id, profileDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(outputDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}/assignProfiles")
    public ResponseEntity<Object> assignProfiles(@PathVariable("id") String id,
                                                @RequestBody UpdateUserProfilesDTO profileDTO) {
        OutputUserDTO outputDTO;

        try {
            outputDTO = userService.assignUserProfiles(id, profileDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.OK);
    }


    /**
     * Remove a User Profiles (US006)
     */
    @PatchMapping("/{id}/removeProfile")
    public ResponseEntity<Object> removeProfile(@PathVariable("id") String id,
                                                @RequestBody UpdateUserProfileDTO profileDTO) {
        OutputUserDTO outputDTO;

        try {
            outputDTO = userService.removeUserProfile(id, profileDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.OK);
    }


    /**
     * Active User (US002 and US026)
     */
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Object> activateUser(@PathVariable("id") String id) {
        OutputUserDTO outputDTO;

        try {
            outputDTO = userService.activateUser(id);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.OK);
    }


    /**
     * Inactive User (US025)
     */
    @PatchMapping("/{id}/inactivate")
    public ResponseEntity<Object> inactivateUser(@PathVariable("id") String id) {
        OutputUserDTO outputDTO;

        try {
            outputDTO = userService.inactivateUser(id);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.OK);
    }


    /**
     * Create a Request to assign a user profile to a user (US003)
     * >>>>>>>>>> Must be review how to assign this method to the ADM<<<<<<<<<<<<<
     */
    @PatchMapping("/{id}/requests")
    public ResponseEntity<Object> requestUserProfile(@PathVariable String id,
                                                     @RequestBody RequestDTO requestDTO) {
        ResponseMessage response = new ResponseMessage();

        try{
            if(userService.createAndAddRequest(id, requestDTO))
                response.responseMessage = "Creation of request profile was successfully!";
                response.add(linkTo(methodOn(UserController.class).showAllUsers()).withRel("Collection"));
        } catch (Exception exception) {
            ErrorMessage msg = new ErrorMessage();
            msg.errorMessage = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Delete User
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        ResponseMessage response = new ResponseMessage();

        try {
            userService.deleteUser(id);
            response.responseMessage = "User was deleted successfully!";
            response.add(linkTo(methodOn(UserController.class).showAllUsers()).withRel("Collection"));
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Object> getUserStatus() {
        CollectionModel<OutputUserDTO> usersStatusDto;

        try {
            usersStatusDto = userService.showUserStatus();
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usersStatusDto, HttpStatus.OK);
    }
}