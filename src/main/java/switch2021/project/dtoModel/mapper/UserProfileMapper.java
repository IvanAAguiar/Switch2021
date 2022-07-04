package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.interfaceAdapters.controller.UserProfileController;
import switch2021.project.interfaceAdapters.repositories.REST.UserProfileRestRepository;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserProfileMapper {

    public UserProfileDTO toDTO(UserProfile newUserProfile) {

        String userProfileName = newUserProfile.getUserProfileId().getUserProfileName().getText();

        UserProfileDTO result = new UserProfileDTO(userProfileName);

        //Add HATEOAS to OUTPUT DTOs

        //Add self relation
        result.add(linkTo(methodOn(UserProfileController.class).showUserProfileRequested(result.userProfileName)).withSelfRel());

        //Add collection relation
        result.add(linkTo(methodOn(UserProfileController.class).getAllProfiles()).withRel("Collection"));
        
        //Add edit option
        result.add(linkTo(methodOn(UserProfileController.class).editAUserProfile(result.userProfileName, result)).withRel("Edit"));
        
        //Add delete option
        result.add(linkTo(methodOn(UserProfileController.class).deleteAUserProfile(result.userProfileName)).withRel("Delete"));

        return result;
    }

    public CollectionModel<UserProfileDTO> toCollectionDTO (List<UserProfile> userProfiles, boolean isExternal) {

        CollectionModel <UserProfileDTO> result = CollectionModel.of(userProfiles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));


        if(isExternal)
            result.add(Link.of(UserProfileRestRepository.ENDPOINT + UserProfileRestRepository.COLLECTION));
        else
        //HATEOAS - Get all UserProfiles
            result.add(linkTo(methodOn(UserProfileController.class).getAllProfiles()).withSelfRel());

        return result;
    }
}
