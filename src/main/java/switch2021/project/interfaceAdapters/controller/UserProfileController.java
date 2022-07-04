package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.applicationServices.service.UserProfileService;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import java.util.Map;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/profiles")
public class UserProfileController {

    /**
     * Attributes
     **/
    @Autowired
    private UserProfileService createUserProfileService;


    /**
     * Find all profiles
     */
    @GetMapping
    public ResponseEntity<Object> getAllProfiles(){
        ErrorMessage message = new ErrorMessage();
        Map<String, CollectionModel<UserProfileDTO>> outPutDTO;

        try {
            outPutDTO = createUserProfileService.getAllProfiles();
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }

    /**
     * Find a requested user profile
     */
     @GetMapping("/{id}")
     public ResponseEntity<Object> showUserProfileRequested(@PathVariable("id") String id){
         ErrorMessage message = new ErrorMessage();
         UserProfileDTO outPutDTO;

         try {
             outPutDTO = createUserProfileService.findUserProfileRequested(id);

         } catch (Exception exception) {
             message.errorMessage = exception.getMessage();
             return  new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
     }

    /**
     * Create a User Profile
     */
    @PostMapping
    public ResponseEntity<Object> createUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
        ErrorMessage message = new ErrorMessage();
        UserProfileDTO outPutDTO;
        try {
            outPutDTO = createUserProfileService.createAndSaveUserProfile(userProfileDTO);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutDTO, HttpStatus.CREATED);
    }

    /**
     * Edit a requested profile
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> editAUserProfile(@PathVariable("id") String id, @RequestBody UserProfileDTO dto){
        ErrorMessage message = new ErrorMessage();
        UserProfileDTO outPutDTO;

        try {

            outPutDTO = createUserProfileService.editARequestedUserProfile(id, dto);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutDTO, HttpStatus.ACCEPTED);
    }

    /**
     * Delete a requested profile
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAUserProfile(@PathVariable("id") String id){
        ErrorMessage message = new ErrorMessage();

        try {

            createUserProfileService.deleteARequestedUserProfile(id);
            message.errorMessage = "User profile successfully deleted";

            message.add(linkTo(methodOn(UserProfileController.class).getAllProfiles()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

}


