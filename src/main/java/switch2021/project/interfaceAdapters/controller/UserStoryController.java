package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.applicationServices.service.UserStoryService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/userstories")
public class UserStoryController {

    /**
     * Attributes
     **/
    @Autowired
    private UserStoryService service;


    /**
     * Create a User Story (US009)
     */
    @PostMapping
    public ResponseEntity<Object> createAndSaveUserStory(@RequestBody UserStoryDTO userStoryDTO) {
        OutputUserStoryDTO newUserStory;

        try {
            newUserStory = service.createAndSaveUserStory(userStoryDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);
    }


    /**
     * Find all user stories
     */
    @GetMapping
    public ResponseEntity<Object> getAllUserStories() {
        CollectionModel<OutputUserStoryDTO> result;

        try {
            result = service.getAllUserStories();

            if (result.getContent().isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "Was not created any user story yet!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * Consult a Product Backlog of a Project (US018)
     */
    @GetMapping("/productBacklog/{id}")
    public ResponseEntity<Object> consultProductBacklog(@PathVariable("id") String idProject) {
        CollectionModel<OutputUserStoryDTO> result;

        try {
            result = service.consultProductBacklog(idProject);

            if (result.getContent().isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "Was not created any user story yet!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * Find a requested user story
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserStoryRequested(@PathVariable("id") String id) {
        OutputUserStoryDTO userStory;

        try {
            userStory = service.showAUserStory(id);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userStory, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateUserStoryPartially(@PathVariable("id") String id,
                                                           @RequestBody UserStoryUpdateDTO userStoryUpdateDTO) {
        OutputUserStoryDTO userStory;
        try {
            userStory = service.updateUserStoryPartially(id, userStoryUpdateDTO);
            return new ResponseEntity<>(userStory, HttpStatus.OK);
        } catch (Exception error) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = error.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }


    }


    /**
     * Update data of a User Story (US019 and US021)
     */
    @PatchMapping("/{id}/effort")
    public ResponseEntity<Object> estimateEffort(@PathVariable("id") String id,
                                                 @RequestBody UpdateUserStoryDTO effortDTO) {
        OutputUserStoryDTO userStory;

        try {
            userStory = service.updateUSData(id, effortDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userStory, HttpStatus.OK);
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<Object> startUserStory(@PathVariable String id) {
        OutputUserStoryDTO userStory;

        try {
            userStory = service.startUserStory(id);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userStory, HttpStatus.OK);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Object> cancelUserStory(@PathVariable String id) {
        OutputUserStoryDTO userStory;

        try {
            userStory = service.cancelUserStory(id);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userStory, HttpStatus.OK);
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<Object> finishUserStory(@PathVariable String id) {
        OutputUserStoryDTO userStory;

        try {
            userStory = service.finishUserStory(id);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userStory, HttpStatus.OK);
    }


    /**
     * Refine a board user story of the Product Backlog (US020)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> refineUserStory(@PathVariable String id, @RequestBody UserStoryDTO inDto) {
        CollectionModel<OutputUserStoryDTO> refinedStory;

        try {
            refinedStory = service.refineUserStory(id, inDto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(refinedStory, HttpStatus.OK);
    }


    /**
     * Delete a requested User Story
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAUserStory(@PathVariable String id) {
        ResponseMessage response = new ResponseMessage();

        try {
            service.deleteAUserStory(id);
            response.responseMessage = "User Story was deleted successfully";
            response.add(linkTo(methodOn(UserStoryController.class).getAllUserStories()).withRel("Collection"));
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
