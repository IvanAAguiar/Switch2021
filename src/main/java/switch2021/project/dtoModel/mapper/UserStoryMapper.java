package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutputUserStoryDTO;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.interfaceAdapters.controller.TaskController;
import switch2021.project.interfaceAdapters.controller.UserStoryController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserStoryMapper {

    public OutputUserStoryDTO toDto(UserStory newUserStory) {
        OutputUserStoryDTO result = new OutputUserStoryDTO();

        result.id = newUserStory.getUserStoryID().toString();
        result.projectID = newUserStory.getUserStoryID().getProjectID().getCode();
        result.usTitle = newUserStory.getUserStoryID().getUsTitle().getTitleUs();
        result.priority = newUserStory.getPriority().getPriorityUs();
        result.description = newUserStory.getDescription().getText();
        result.timeEstimate = newUserStory.getTimeEstimate().getUsHours();
        result.status = newUserStory.getUsStatus().name();

        if (newUserStory.getParentUserStory() != null) {
            result.parentUserStory = newUserStory.getParentUserStory().getUserStoryID().toString();
        }
        if (newUserStory.getUsStartDate() != null) {
            result.usStartDate = newUserStory.getUsStartDate().toString();
        }
        if (newUserStory.getUsEndDate() != null) {
            result.usEndDate = newUserStory.getUsEndDate().toString();
        }
        if (newUserStory.getUsRefined() != null) {
            result.usRefined = newUserStory.getUsRefined().toString();
        }


        /*
         * Add HATEOAS to OUTPUT DTOs
         */

        //Add self relation
        result.add(linkTo(methodOn(UserStoryController.class).getUserStoryRequested(result.id)).withSelfRel());

        //Add collection relation
        result.add(linkTo(methodOn(UserStoryController.class).getAllUserStories()).withRel("Collection"));

        //Add delete option
        result.add(linkTo(methodOn(UserStoryController.class).deleteAUserStory(result.id)).withRel("Delete"));

        //Show all tasks in the User Story
        result.add(linkTo(methodOn(TaskController.class).getTasksByTaskContainerID(result.id)).withRel("Show Tasks"));

        return result;


    }

    public CollectionModel<OutputUserStoryDTO> toCollectionDto(List<UserStory> userStories) {

        CollectionModel<OutputUserStoryDTO> result = CollectionModel.of(userStories.stream()
                .map(this::toDto)
                .collect(Collectors.toList()));

        //Add HATEOAS to OUTPUT DTOs

        //Add self relation
        result.add(linkTo(methodOn(UserStoryController.class).getAllUserStories()).withSelfRel());

        return result;
    }
}
