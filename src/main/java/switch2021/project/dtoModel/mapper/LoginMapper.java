package switch2021.project.dtoModel.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.OutputLoginDTO;
import switch2021.project.dtoModel.dto.UpdateDataDTO;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import switch2021.project.interfaceAdapters.controller.ProjectController;
import switch2021.project.interfaceAdapters.controller.TypologyController;
import switch2021.project.interfaceAdapters.controller.UserController;
import switch2021.project.interfaceAdapters.controller.UserProfileController;
import switch2021.project.utils.MatrizAuthorization;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LoginMapper {

    @Autowired
    MatrizAuthorization auth;

    public OutputLoginDTO toDto(User logged) {

        OutputLoginDTO dto = new OutputLoginDTO();

        dto.email = logged.getUserId().getEmail().getEmailText();
        dto.username = logged.getUserName().getText();
        dto.token = "authenticated";

        //Add HATEOAS to user edit your own information
        dto.add(linkTo(methodOn(UserController.class).updatePersonalData(dto.username, new UpdateDataDTO())).withRel("editUser").withType("PATCH"));

        //Add HATEOAS to user request a new profile
        //To be implemented

        for (UserProfileID profile : logged.getAssignedIdProfiles()){
            //Check if this User can access resource Projects
            if(auth.getSeeProjects().contains(profile.getUserProfileName().getText())){
                dto.add(linkTo(methodOn(ProjectController.class).getAllProjects()).withRel("projects").withType("GET"));
            }

            //Check if this User can access resource Users
            if(auth.getSeeUsers().contains(profile.getUserProfileName().getText())){
                dto.add(linkTo(methodOn(UserController.class).showAllUsers()).withRel("users").withType("GET"));
            }

            //Check if this User can access resource Typologies
            if(auth.getSeeAndEditTypologies().contains(profile.getUserProfileName().getText())){
                dto.add(linkTo(methodOn(TypologyController.class).findTypologyList()).withRel("typologies").withType("GET"));
            }

            //Check if this User can access resource Projects
            if(auth.getSeeAndEditProfiles().contains(profile.getUserProfileName().getText())){
                dto.add(linkTo(methodOn(UserProfileController.class).getAllProfiles()).withRel("profiles").withType("GET"));
            }

        }

        return dto;

    }

}
