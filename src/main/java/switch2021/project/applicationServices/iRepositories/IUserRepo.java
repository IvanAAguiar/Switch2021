package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import java.util.List;
import java.util.Optional;

public interface IUserRepo{

    Optional<User> findByUserId(UserID id);

    List<User> findAll();

    List<User> findAllByNameContains(String name);

    List<User> findAllByFunctionContains(String function);

    List<User> findAllByUserProfileContains(UserProfileID profile);

    boolean existsById(UserID id);

    User save(User newUser);

    void delete(UserID userID);
}
