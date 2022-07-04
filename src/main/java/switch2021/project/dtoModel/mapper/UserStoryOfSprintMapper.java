package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.UserStoryOfSprintDTO;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;
import switch2021.project.interfaceAdapters.controller.SprintController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserStoryOfSprintMapper {

    public UserStoryOfSprintDTO toDTO(UserStoryOfSprint userStoryOfSprint) {
        UserStoryOfSprintDTO userStoryOfSprintDTO = new UserStoryOfSprintDTO();

        userStoryOfSprintDTO.projectId = userStoryOfSprint.getUserStoryId().getProjectID().getCode();
        userStoryOfSprintDTO.usTitle = userStoryOfSprint.getUserStoryId().getUsTitle().getTitleUs();
        userStoryOfSprintDTO.sprintName =
                userStoryOfSprintDTO.status = userStoryOfSprint.getUserStoryOfSprintStatus().toString();

        String sprintId =
                userStoryOfSprint.getUserStoryId().getProjectID().getCode() + "_" + userStoryOfSprint.getSprintName();

        userStoryOfSprintDTO.add(linkTo(methodOn(SprintController.class).showScrumBoard(sprintId))
                                    .withSelfRel());

        return userStoryOfSprintDTO;
    }

    public UserStoryOfSprintDTO model2DTO(UserStoryOfSprint userStoryOfSprint) {
        UserStoryOfSprintDTO userStoryOfSprintDTO = new UserStoryOfSprintDTO();

        userStoryOfSprintDTO.projectId = userStoryOfSprint.getUserStoryId().getProjectID().getCode();
        userStoryOfSprintDTO.usTitle = userStoryOfSprint.getUserStoryId().getUsTitle().getTitleUs();
        userStoryOfSprintDTO.sprintName = userStoryOfSprint.getSprintName();
        userStoryOfSprintDTO.status = userStoryOfSprint.getUserStoryOfSprintStatus().toString();

        String sprintId =
                userStoryOfSprint.getUserStoryId().getProjectID().getCode() + "_" + userStoryOfSprint.getSprintName();

        userStoryOfSprintDTO.add(linkTo(methodOn(SprintController.class).showScrumBoard(sprintId))
                .withSelfRel());

        return userStoryOfSprintDTO;
    }


    public CollectionModel<UserStoryOfSprintDTO> toCollectionDTO(List<UserStoryOfSprint> userStoryOfSprintList) {

        return CollectionModel.of(userStoryOfSprintList.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    public CollectionModel<UserStoryOfSprintDTO> model2CollectionDTO(List<UserStoryOfSprint> userStoryOfSprintList) {

        return CollectionModel.of(userStoryOfSprintList.stream().map(this::model2DTO).collect(Collectors.toList()));
    }
}
