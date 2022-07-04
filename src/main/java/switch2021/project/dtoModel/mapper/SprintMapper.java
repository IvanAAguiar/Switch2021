package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutSprintDTO;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.interfaceAdapters.controller.SprintController;
import switch2021.project.interfaceAdapters.controller.TaskController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SprintMapper {

    public OutputSprintDTO toDTO(Sprint newSprint) {
        String sprintID = newSprint.getSprintID().toString();
        String projectID = newSprint.getSprintID().getProjectID().getCode();
        String name = newSprint.getSprintID().getSprintName().getText();

        OutputSprintDTO outputSprintDTO = new OutputSprintDTO(projectID, sprintID, name);

        outputSprintDTO.add(linkTo(methodOn(SprintController.class).showSprintsOfAProject(outputSprintDTO.projectID))
                .withRel("ShowProjectSprints"));
        //Show all tasks in the Sprint
        outputSprintDTO.add(linkTo(methodOn(TaskController.class).getTasksByTaskContainerID(sprintID)).withRel("Show Tasks"));

        return outputSprintDTO;
    }

    public OutSprintDTO model2DTO(Sprint newSprint) {
        String sprintID = newSprint.getSprintID().toString();
        String projectID = newSprint.getSprintID().getProjectID().getCode();
        String name = newSprint.getSprintID().getSprintName().getText();
        String start = "";
        String end = "";
        if(newSprint.getStartDate() != null) {
            start = newSprint.getStartDate().toString();
        }

        if(newSprint.getEndDate() != null) {
            end = newSprint.getEndDate().toString();
        }

        OutSprintDTO outputSprintDTO = new OutSprintDTO(projectID, sprintID, name, start, end);

        outputSprintDTO.add(linkTo(methodOn(SprintController.class).showSprintsOfAProject(outputSprintDTO.projectID))
                .withRel("ShowProjectSprints"));
        //Show all tasks in the Sprint
        outputSprintDTO.add(linkTo(methodOn(TaskController.class).getTasksByTaskContainerID(sprintID)).withRel("Show Tasks"));

        return outputSprintDTO;
    }

    public CollectionModel<OutputSprintDTO> toCollectionDto(List<Sprint> sprints) {

        CollectionModel<OutputSprintDTO> result = CollectionModel.of(sprints.stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));

        //HATEOAS - Get All Sprints
        result.add(linkTo(methodOn(SprintController.class).showSprints()).withSelfRel());

        return result;
    }

    public CollectionModel<OutSprintDTO> toCollectionsDto(List<Sprint> sprints) {

        CollectionModel<OutSprintDTO> result = CollectionModel.of(sprints.stream()
                .map(this::model2DTO)
                .collect(Collectors.toList()));

        //HATEOAS - Get All Sprints
        result.add(linkTo(methodOn(SprintController.class).showSprints()).withSelfRel());

        return result;
    }
}
