package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.dataModel.JPA.UserProfileJpa;
import switch2021.project.dataModel.JPA.assembler.UserProfileJpaAssembler;
import switch2021.project.applicationServices.iRepositories.IUserProfileRepo;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import switch2021.project.persistence.UserProfileJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserProfileRepository implements IUserProfileRepo {

    @Autowired
    UserProfileJpaRepository userProfileJpaRepository;
    @Autowired
    UserProfileJpaAssembler assembler;


    public Optional<UserProfile> findByUserProfileID(UserProfileID userProfileID) {

        Optional<UserProfileJpa> userProfileJpa = userProfileJpaRepository.findById(userProfileID);
        Optional<UserProfile> userProfile = Optional.empty();

        if (userProfileJpa.isPresent()) {
            userProfile = Optional.of(assembler.toDomain(userProfileJpa.get()));
        }
        return userProfile;
    }


    public List<UserProfile> findAll() {
        List<UserProfileJpa> userProfileJpaList = userProfileJpaRepository.findAll();
        List<UserProfile> userProfileList = new ArrayList<>();

        for (UserProfileJpa userProfileJpa : userProfileJpaList) {
            userProfileList.add(assembler.toDomain(userProfileJpa));
        }
        return userProfileList;
    }


    public UserProfile save(UserProfile profile) {
        UserProfileJpa userProfileJpa = assembler.toData(profile);

        UserProfileJpa saved = userProfileJpaRepository.save(userProfileJpa);

        return assembler.toDomain(saved);
    }


    public boolean existsByUserProfileId(UserProfileID userProfileID) {
        return userProfileJpaRepository.existsById(userProfileID);
    }

    public boolean deleteById(UserProfileID userProfileID) {

        if (userProfileJpaRepository.existsById(userProfileID)) {
            userProfileJpaRepository.deleteById(userProfileID);
            return true;
        }
        return false;
    }
}


