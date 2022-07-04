package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import java.util.List;
import java.util.Optional;

public interface IUserStoryRepo {

    Optional<UserStory> findByUserStoryId(UserStoryID userStoryID);

    List<UserStory> findAll();

    List<UserStory>  findProductBacklog(String projectId);

    boolean existsUserStoryByID(UserStoryID id);

    UserStory save(UserStory newUserStory);

    void deleteByUserStoryId(UserStoryID usId);
}
