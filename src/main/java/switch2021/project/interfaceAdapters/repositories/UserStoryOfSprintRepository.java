package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.IUserStoryOfSprintRepo;
import switch2021.project.dataModel.JPA.UserStoryOfSprintJpa;
import switch2021.project.dataModel.JPA.assembler.UserStoryOfSprintJpaAssembler;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;
import switch2021.project.persistence.UserStoryOfSprintJpaRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserStoryOfSprintRepository implements IUserStoryOfSprintRepo {

    @Autowired
    private UserStoryOfSprintJpaRepository userStoryOfSprintJpaRepository;
    @Autowired
    private UserStoryOfSprintJpaAssembler userStoryOfSprintJpaAssembler;

    @Override
    public List<UserStoryOfSprint> findAllUserStoryOfSprint() {
        List<UserStoryOfSprintJpa> userStoryOfSprintJpaList = userStoryOfSprintJpaRepository.findAll();
        List<UserStoryOfSprint> userStoryOfSprintList = new ArrayList<>();

        for (UserStoryOfSprintJpa userStoryOfSprintJpa : userStoryOfSprintJpaList) {
            userStoryOfSprintList.add(userStoryOfSprintJpaAssembler.toDomain(userStoryOfSprintJpa));
        }
        return userStoryOfSprintList;
    }

    @Override
    public List<UserStoryOfSprint> findAllUserStoriesBySprintID(SprintID sprintID) {
        List<UserStoryOfSprintJpa> userStoryOfSprintJpaList = userStoryOfSprintJpaRepository.findAll();
        List<UserStoryOfSprint> userStoryOfSprintList = new ArrayList<>();

        for (UserStoryOfSprintJpa userStoryOfSprintJpa : userStoryOfSprintJpaList) {
            if (userStoryOfSprintJpa.getSprintName().equals(sprintID.getSprintName().getText())) {
                userStoryOfSprintList.add(userStoryOfSprintJpaAssembler.toDomain(userStoryOfSprintJpa));
            }
        }
        return userStoryOfSprintList;
    }

    @Override
    public UserStoryOfSprint save(UserStoryOfSprint userStoryOfSprint) {
        UserStoryOfSprintJpa userStoryOfSprintJpa = userStoryOfSprintJpaAssembler.toData(userStoryOfSprint);
        UserStoryOfSprint usSprint;

        UserStoryOfSprintJpa sprintJpaSaved = userStoryOfSprintJpaRepository.save(userStoryOfSprintJpa);
        usSprint = userStoryOfSprintJpaAssembler.toDomain(sprintJpaSaved);

        return usSprint;
    }

    @Override
    public boolean deleteUserStoryOfSprint(UserStoryID userStoryID) {

        if(userStoryOfSprintJpaRepository.existsById(userStoryID)) {
        userStoryOfSprintJpaRepository.deleteById(userStoryID);
        return true;
        }

        return false;
    }
}
