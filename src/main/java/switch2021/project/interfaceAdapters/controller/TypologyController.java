package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.ResponseMessage;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.applicationServices.service.TypologyService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/typologies")
public class TypologyController {

    /**
     * Attributes
     **/
    @Autowired
    private TypologyService service;


    /**
     * Create a new Typology (US012)
     */
    @PostMapping
    public ResponseEntity<Object> createTypology(@RequestBody TypologyDTO inputDto) {
        TypologyDTO outputDto;

        if(inputDto.getDescription() == null || inputDto.getDescription().isEmpty()) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(message ,HttpStatus.NOT_ACCEPTABLE);}
        try {
            outputDto = service.createAndSaveTypology(inputDto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.CREATED);
    }


    /**
     * Find a requested Typology
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findTypologyRequested(@PathVariable("id") String id) {
        TypologyDTO outputDto;

        if(id == null || id.isEmpty()) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(message ,HttpStatus.NOT_ACCEPTABLE);}
        try {
            outputDto = service.findTypologyRequested(id);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.OK);
    }


    /**
     * Find all Typologies
     */
    @GetMapping
    public ResponseEntity<Object> findTypologyList() {
        CollectionModel<TypologyDTO> typologyDTOList;

        try {
            typologyDTOList = service.findAllTypologies();
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(typologyDTOList, HttpStatus.OK);
    }

    /**
     * Delete a requested Typology
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTypology(@PathVariable("id") String id) {

        if(id == null || id.isEmpty()) {
            ErrorMessage error = new ErrorMessage();
            error.errorMessage = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(error ,HttpStatus.NOT_ACCEPTABLE);}
        try {
            service.deleteTypology(id);
            ResponseMessage response = new ResponseMessage();
            response.responseMessage = "Typology successfully deleted.";
            response.add(linkTo(methodOn(TypologyController.class).findTypologyList()).withRel("Collection"));
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            ErrorMessage error = new ErrorMessage();
            error.errorMessage = exception.getMessage();
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
