package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.interfaceAdapters.controller.ResourceController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ResourceMapper {

    public OutputResourceDTO toDto(Resource res) {

        OutputResourceDTO resDto = new OutputResourceDTO();

        resDto.resourceID = res.getId().toString();
        resDto.user = res.getId().getUser().getEmail().getEmailText();
        resDto.project = res.getId().getProject().getCode();
        resDto.role = res.getRole().toString();
        resDto.startDate = res.getId().getStartDate().toString();
        resDto.endDate = res.getEndDate().toString();
        resDto.allocation = res.getAllocation().getPercentage();
        resDto.cost = res.getCost().getCost();

        //Show a resource
        resDto.add(linkTo(methodOn(ResourceController.class).showResourceRequested(resDto.user))
                .withSelfRel());

        //Show all Resources in the project
        resDto.add(linkTo(methodOn(ResourceController.class).showCurrentProjectTeam(resDto.project)).withRel("Collection"));
        return resDto;
    }

    public CollectionModel<OutputResourceDTO> toCollectionDto(List<Resource> resourceList) {

        CollectionModel<OutputResourceDTO> resources = CollectionModel.of(resourceList.stream()
                .map(this::toDto)
                .collect(Collectors.toList()));

        //Add HATEOAS to OUTPUT DTOs
        //Add self relation
        resources.add(linkTo(methodOn(ResourceController.class).showAllResources()).withSelfRel());

        return resources;
    }
}
