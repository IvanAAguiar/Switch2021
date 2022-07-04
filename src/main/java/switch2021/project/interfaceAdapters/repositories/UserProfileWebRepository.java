package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.IUserProfileWebRepository;
import switch2021.project.dataModel.REST.assemblers.UserProfileDomainDataRestAssembler;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.interfaceAdapters.repositories.REST.UserProfileRestRepository;

import javax.net.ssl.SSLException;
import java.util.List;

@Repository
public class UserProfileWebRepository implements IUserProfileWebRepository {

    @Autowired
    UserProfileRestRepository userProfileRestRepository;

    @Autowired
    UserProfileDomainDataRestAssembler userProfileDomainDataRestAssembler;

    /**
     * @return List of User Profiles
     */
    public List<UserProfile> findAll() throws SSLException {

        return userProfileDomainDataRestAssembler.toDomain(userProfileRestRepository.findAll());

    }
}
