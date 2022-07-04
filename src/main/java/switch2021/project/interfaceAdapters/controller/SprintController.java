package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.applicationServices.service.SprintService;
import switch2021.project.dtoModel.dto.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/sprints")
public class SprintController {

    /**
     * Attributes
     **/

    @Autowired
    private SprintService sprintService;

    /**
     * Methods
     **/

    @PostMapping
    public ResponseEntity<Object> createAndSaveSprint(@RequestBody NewSprintDTO dto) {
        OutputSprintDTO outPutSprintDTO;

        try {
            outPutSprintDTO = sprintService.createAndSaveSprint(dto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutSprintDTO, HttpStatus.CREATED);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Object> addUserStoryToSprintBacklog(@PathVariable("id") String id,
                                                              @RequestBody UserStoryIdDTO UsIdDto) {
        ErrorMessage message = new ErrorMessage();

        try {
            sprintService.addUserStoryToSprintBacklog(id, UsIdDto);
            message.errorMessage = "User story added to sprintbacklog";
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Find All Sprints
     */

    @GetMapping
    public ResponseEntity<Object> showSprints(){

        CollectionModel<OutputSprintDTO> result;

        try {
            result = CollectionModel.of(sprintService.showAllSprints());

            if(result.getContent().isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "Was not created any sprint yet!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/sprintList/{id}")
    public ResponseEntity<Object> showSprintsOfAProject(@PathVariable("id") String projId){

        CollectionModel<OutputSprintDTO> result;

        try {
            result = CollectionModel.of(sprintService.showSprintsOfAProject(projId));

            if(result.getContent().isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "Was not created any sprint yet!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sprintsList/{id}")
    public ResponseEntity<Object> showSprintsInProject(@PathVariable("id") String projId){

        CollectionModel<OutSprintDTO> result;

        try {
            result = sprintService.showSprintsInProject(projId);

        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Object> showSprintById(@PathVariable("id") String id) {

        ErrorMessage message = new ErrorMessage();
        OutSprintDTO outputSprintDTO;

        try {
            outputSprintDTO = sprintService.showSprintById(id);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(outputSprintDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSprint(@PathVariable String id) {
        ErrorMessage message = new ErrorMessage();

        try {
            sprintService.deleteSprint(id);
            message.errorMessage = "Sprint was deleted successfully";
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Object> startSprint(@PathVariable String id,
                                              @RequestBody StartSprintDTO startSprintDTO) {

        ErrorMessage message = new ErrorMessage();

        OutputSprintDTO outputSprintDTO;

        try {
            outputSprintDTO = sprintService.startSprint(id, startSprintDTO);
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputSprintDTO, HttpStatus.OK);
    }

    @GetMapping("/scrumBoard/{id}")
    public  ResponseEntity<Object> showScrumBoard(@PathVariable("id") String id) {

        ErrorMessage message = new ErrorMessage();
        CollectionModel<UserStoryOfSprintDTO> uSOfSprintDTOS;

        try {
            uSOfSprintDTOS = sprintService.showScrumBoard(id);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(uSOfSprintDTOS, HttpStatus.OK);
    }

    @GetMapping("/scrumboard/{id}")
    public  ResponseEntity<Object> showScrumBoardOfSprint(@PathVariable("id") String id) {

        ErrorMessage message = new ErrorMessage();
        CollectionModel<UserStoryOfSprintDTO> UsSprintDTO;

        try {
            UsSprintDTO = sprintService.showScrumBoardOfSprint(id);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(UsSprintDTO, HttpStatus.OK);
    }

    @PatchMapping("/scrumBoard/{id}")
    public  ResponseEntity<Object> changeStatusScrumBoard(@PathVariable("id") String id,
                                                          @RequestBody UserStoryOfSprintDTO userStoryDTO) {
        ErrorMessage message = new ErrorMessage();
        UserStoryOfSprintDTO UsSprintDTO;

        try {
            UsSprintDTO = sprintService.changeStatusScrumBoard(id, userStoryDTO);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(UsSprintDTO, HttpStatus.OK);
    }
}


