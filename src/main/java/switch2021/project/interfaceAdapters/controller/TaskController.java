package switch2021.project.interfaceAdapters.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.applicationServices.service.TaskService;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.dtoModel.dto.TaskEffortDTO;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    /*** Attributes **/
    @Autowired
    TaskService taskService;

    /**
     * Create Task - US031 and US032
     **/
    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody TaskDTO inputDTO) {
        OutputTaskDTO outputDTO;
        try {
            outputDTO = taskService.createAndSaveTask(inputDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        OutputTaskDTO outputDTO;
        try {
            outputDTO = taskService.getTaskById(id);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        ErrorMessage message = new ErrorMessage();
        CollectionModel<OutputTaskDTO> allTasksDto;

        try {
            allTasksDto = CollectionModel.of(taskService.getAllTasks());
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(allTasksDto, HttpStatus.OK);
    }

    @GetMapping("/taskContainer/{id}")
    public ResponseEntity<Object> getTasksByTaskContainerID(@PathVariable("id") String taskContainerID) {
        ErrorMessage message = new ErrorMessage();
        CollectionModel<OutputTaskDTO> taskContainerIDTasks;
        try {
            taskContainerIDTasks = CollectionModel.of(taskService.getAllTasksByTaskContainerID(taskContainerID));
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(taskContainerIDTasks, HttpStatus.OK);
    }

    /**
     * Register Effort - US033
     **/
    @PatchMapping("/{id}/registerEffort")
    public ResponseEntity<Object> registerEffort(@PathVariable("id") String id,
                                                 @RequestBody TaskEffortDTO taskEffortDTO) {
        OutputTaskDTO outputTaskDTO;

        try {
            outputTaskDTO = taskService.createAndAddEffort(id, taskEffortDTO);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputTaskDTO, HttpStatus.OK);
    }

}
