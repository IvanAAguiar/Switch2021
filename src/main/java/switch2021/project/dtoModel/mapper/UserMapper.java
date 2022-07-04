package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import switch2021.project.interfaceAdapters.controller.UserController;
import switch2021.project.entities.aggregates.User.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserMapper {

    public OutputUserDTO toDto(User user) {

        String username = user.getUserName().getText();
        String email = user.getUserId().getEmail().getEmailText();
        String function = user.getFunction().getText();
        String photo = user.getPhoto().getExtension();
        String active = user.isActive() ? "True" : "False";
        List<String> assignedIdProfiles = profilesToDTO(user.getAssignedIdProfiles());

        OutputUserDTO outputUserDTO = new OutputUserDTO(username, email, function, photo, active, assignedIdProfiles);

        //Add HATEOAS to OutPut DTO
        outputUserDTO
                //Self Relation
                .add(linkTo(methodOn(UserController.class).getUser(outputUserDTO.email)).withRel("Find by ID"))
                //Collection Relation
                .add(linkTo(methodOn(UserController.class).showAllUsers()).withRel("Collection"))
                //Activation
                .add(linkTo(methodOn(UserController.class).activateUser(outputUserDTO.getEmail())).withRel("Activate " +
                        "User"))
                //Inactivation
                .add(linkTo(methodOn(UserController.class).inactivateUser(outputUserDTO.getEmail())).withRel(
                        "Inactivate " + "User"))
                //Assign Profile
                .add(linkTo(methodOn(UserController.class).assignProfile(outputUserDTO.getEmail(),
                        new UpdateUserProfileDTO())).withRel("Assign Profile"))
                //Remove Profile
                .add(linkTo(methodOn(UserController.class).removeProfile(outputUserDTO.getEmail(),
                        new UpdateUserProfileDTO())).withRel("Remove Profile"))
                //Update
                .add(linkTo(methodOn(UserController.class).updatePersonalData(outputUserDTO.getEmail(),
                        new UpdateDataDTO())).withRel("Edit"))
                //Delete option
                .add(linkTo(methodOn(UserController.class).deleteUser(outputUserDTO.email)).withRel("Delete"));
        return outputUserDTO;
    }


    public CollectionModel<OutputUserDTO> toCollectionDTO(List<User> userList) {

        CollectionModel<OutputUserDTO> users = CollectionModel.of(userList.stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
        //HATEOAS
        // Add Self Relation
        users.add(linkTo(methodOn(UserController.class).showAllUsers()).withSelfRel());
        return users;
    }

    //Method to convert User Profile list in a String list of User Profiles
    private List<String> profilesToDTO(List<UserProfileID> profiles) {
        return profiles.stream()
                .map(profileID -> profileID.getUserProfileName().getText())
                .collect(Collectors.toList());
    }

    /**
     * Next two methods are for partial users only, used to showAllUsers
     */

    public PartialUserDTO toDtoPartial(User user) {

        String username = user.getUserName().getText();
        String email = user.getUserId().getEmail().getEmailText();
        String function = user.getFunction().getText();

        PartialUserDTO partialUserDTO = new PartialUserDTO(username, email, function);

        //Add HATEOAS to OutPut DTO
        //Self Relation
        partialUserDTO
                .add(linkTo(methodOn(UserController.class).getUser(partialUserDTO.email))
                .withRel("Find by ID"));
        //Search by Parameter
        partialUserDTO
                .add(linkTo(methodOn(UserController.class).searchUsersByTypedParams(new SearchUserDTO()))
                .withRel("Search by Paramenter"));
        return partialUserDTO;
    }

    public CollectionModel<PartialUserDTO> toCollectionDTOPartial(List<User> userList) {

        CollectionModel<PartialUserDTO> users = CollectionModel.of(userList.stream()
                .map(this::toDtoPartial)
                .collect(Collectors.toList()));
        //HATEOAS
        // Add Self Relation
        users.add(linkTo(methodOn(UserController.class).showAllUsers()).withSelfRel());
        return users;
    }
}
