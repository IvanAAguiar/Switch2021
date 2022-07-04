package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.applicationServices.service.AuthService;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.LoginDto;
import switch2021.project.dtoModel.dto.OutputLoginDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class RootController {

    @Autowired
    AuthService srv;

    @PostMapping
    public ResponseEntity<Object> authentication(@RequestBody LoginDto login){

        try {

            OutputLoginDTO result = srv.authentication(login);

            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (Exception e){

            ErrorMessage error = new ErrorMessage();
            error.errorMessage = e.getMessage();

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }


    }
}
