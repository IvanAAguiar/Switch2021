package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;
import switch2021.project.interfaceAdapters.controller.ResourceController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest
class ResourceMapperTest {

    @Autowired ResourceMapper mapper;

    @Test
    void toCollectionDto() {
        //Arrange
        Resource resource = mock(Resource.class);
        ResourceID id = mock(ResourceID.class);
        UserID user = mock(UserID.class);
        Email email = mock(Email.class);
        ProjectID project = mock (ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-10");
        LocalDate endDate = LocalDate.parse("2023-10-10");
        PercentageOfAllocation allocation = mock(PercentageOfAllocation.class);
        CostPerHour cost = mock(CostPerHour.class);
        List<Resource> resources = new ArrayList<>();
        List<OutputResourceDTO> collection = new ArrayList<>();
        resources.add(resource);
        resources.add(resource);
        //Act
        when(resource.getId()).thenReturn(id);
        when(id.getUser()).thenReturn(user);
        when(user.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("email");
        when(id.getProject()).thenReturn(project);
        when(project.getCode()).thenReturn("code");
        when(resource.getRole()).thenReturn(ProjectRole.TeamMember);
        when(id.getStartDate()).thenReturn(startDate);
        when(resource.getEndDate()).thenReturn(endDate);
        when(resource.getAllocation()).thenReturn(allocation);
        when(allocation.getPercentage()).thenReturn(0.5);
        when(resource.getCost()).thenReturn(cost);
        when(cost.getCost()).thenReturn(500.00);
        collection.add(mapper.toDto(resource));
        collection.add(mapper.toDto(resource));
        CollectionModel<OutputResourceDTO> result = mapper.toCollectionDto(resources);
        CollectionModel<OutputResourceDTO> expected = CollectionModel.of(collection);
        expected.add(linkTo(methodOn(ResourceController.class).showAllResources()).withSelfRel());
        //Assert
        assertEquals(expected,result);
    }
}