package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.LoginDto;
import switch2021.project.dtoModel.dto.OutputLoginDTO;
import switch2021.project.dtoModel.mapper.LoginMapper;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.voFactories.voFactories.PasswordFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.Password;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    IUserRepo repo;
    @Autowired
    IUserIDFactory idFactory;
    @Autowired
    LoginMapper map;
    @Autowired
    PasswordFactory passwordFactory;

    public OutputLoginDTO authentication(LoginDto login) throws Exception {
        Optional<User> logged = repo.findByUserId(idFactory.createUserID(login.getEmail()));

        if (logged.isPresent()) {
            User userLogged = logged.get();

            if (userLogged.checkPassword(passwordFactory.createPassword(login.getPassword()))) {
                return map.toDto(userLogged);
            }
        }
        throw new Exception("Username or Password invalid");
    }
}
