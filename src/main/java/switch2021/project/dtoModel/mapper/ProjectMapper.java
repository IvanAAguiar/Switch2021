package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.PartialProjectDTO;
import switch2021.project.interfaceAdapters.controller.ProjectController;
import switch2021.project.dtoModel.dto.EditProjectInfoDTO;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.interfaceAdapters.controller.ResourceController;
import switch2021.project.interfaceAdapters.controller.SprintController;
import switch2021.project.interfaceAdapters.controller.UserStoryController;
import switch2021.project.interfaceAdapters.repositories.REST.ProjectRestRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectMapper {

    public OutputProjectDTO model2Dto(Project newProject) {

        String code = newProject.getProjectCode().getCode();
        String projectName = newProject.getProjectName().getText();
        String description = newProject.getDescription().getText();
        String businessSector = newProject.getBusinessSector().getDescription().getText();
        String numberOfSprints = Integer.toString(newProject.getNumberOfSprints().getNumberOfSprintsVO());
        String budget = Double.toString(newProject.getBudget().getBudgetVO());
        String status = newProject.getProjectStatus().name();
        String startDate = newProject.getStartDate().toString();
        String sprintDuration = Long.toString(newProject.getSprintDuration().getSprintDurationDays());

        OutputProjectDTO projDto = new OutputProjectDTO(code, projectName, description, businessSector, startDate,
                numberOfSprints, budget, status, sprintDuration);

        if (newProject.getEndDate() != null) {
            projDto.endDate = newProject.getEndDate().toString();
        }

        if (newProject.getTypologyId() != null) {
            projDto.typo = newProject.getTypologyId().getDescription().getText();
        }

        if (newProject.getCustomer() != null) {
            projDto.customer = newProject.getCustomer().getCustomerName().getText();
        }

        //Show a project
        projDto.add(linkTo(methodOn(ProjectController.class).showProjectRequested(projDto.code))
                .withSelfRel());

        //Show all projects
        projDto.add(linkTo(methodOn(ProjectController.class).getAllProjects()).withRel("Collection"));

        //Delete
        projDto.add(linkTo(methodOn(ProjectController.class).deleteProjectRequest(projDto.code)).withRel("Delete"));

        //Edit a project
        projDto.add(linkTo(methodOn(ProjectController.class).updateProjectPartially(projDto.code,
                new EditProjectInfoDTO())).withRel("Edit"));

        //View ProjectTeam
        projDto.add(linkTo(methodOn(ResourceController.class).showRegisterOfResourcesInAProject(projDto.code))
                .withRel("ProjectTeam"));

        //View Sprints
        projDto.add(linkTo(methodOn(SprintController.class).showSprintsOfAProject(projDto.code))
                .withRel("Sprints"));

        //View Backlog
        projDto.add(linkTo(methodOn(UserStoryController.class).consultProductBacklog(projDto.code))
                .withRel("Backlog"));


        return projDto;
    }

    public CollectionModel<OutputProjectDTO> toCollectionDto(List<Project> projects, boolean isExternal) {

        CollectionModel<OutputProjectDTO> result = CollectionModel.of(projects.stream()
                .map(this::model2Dto)
                .collect(Collectors.toList()));

        if(isExternal)
            result.add(Link.of(ProjectRestRepository.ENDPOINT + ProjectRestRepository.COLLECTION));
        else
            result.add(linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel());

        return result;
    }

    /**
     * Next two methods are for partial projects only, used to showAllProjects
     */
    public PartialProjectDTO model2Dto2(Project newProject) {

        String code = newProject.getProjectCode().getCode();
        String projectName = newProject.getProjectName().getText();
        String description = newProject.getDescription().getText();
        String status = newProject.getProjectStatus().name();
        String startDate = newProject.getStartDate().toString();

        PartialProjectDTO projDto = new PartialProjectDTO(code, projectName, description, startDate, status);

        //Show a project
        projDto.add(linkTo(methodOn(ProjectController.class).showProjectRequested(projDto.code))
                .withSelfRel());

        return projDto;
    }

    public CollectionModel<PartialProjectDTO> toCollectionDto2(List<Project> projects, boolean isExternal) {

        CollectionModel<PartialProjectDTO> result = CollectionModel.of(projects.stream()
                .map(this::model2Dto2)
                .collect(Collectors.toList()));

        if(isExternal)
            result.add(Link.of(ProjectRestRepository.ENDPOINT + ProjectRestRepository.COLLECTION));
        else
            result.add(linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel());

        return result;
    }
}
