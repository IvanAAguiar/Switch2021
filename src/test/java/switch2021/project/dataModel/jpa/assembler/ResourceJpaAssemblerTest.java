package switch2021.project.dataModel.jpa.assembler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dataModel.JPA.ResourceIDJpa;
import switch2021.project.dataModel.JPA.ResourceJpa;
import switch2021.project.dataModel.JPA.assembler.ResourceJpaAssembler;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//
@SpringBootTest
class ResourceJpaAssemblerTest {

    @Autowired
    ResourceJpaAssembler assembler;

    @Test
    void resourceIDToDataSuccess() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse("2022-10-12");
        ResourceID id = mock(ResourceID.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        ResourceIDJpa result = assembler.toData(id);
        ResourceIDJpa expected = new ResourceIDJpa(userID, projectID, startDate);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void resourceIDToDataFail() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-15-12";
        LocalDate sD = LocalDate.parse("2022-10-12");
        ResourceID id = mock(ResourceID.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        ResourceIDJpa result = assembler.toData(id);
        ResourceIDJpa expected = new ResourceIDJpa(userID, projectID, startDate);
        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void resourceToDataSuccess() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse(startDate);
        ResourceID id = mock(ResourceID.class);
        String endDate = "2023-10-12";
        LocalDate eD = LocalDate.parse(endDate);
        double alloc = 0.4;
        PercentageOfAllocation allocation = mock(PercentageOfAllocation.class);
        double cost = 500.00;
        CostPerHour costPerHour = mock(CostPerHour.class);
        String role = "TeamMember";
        ProjectRole projectRole = ProjectRole.TeamMember;
        Resource resource = mock(Resource.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        when(resource.getId()).thenReturn(id);
        ResourceIDJpa idJpa = assembler.toData(id);
        when(resource.getAllocation()).thenReturn(allocation);
        when(allocation.getPercentage()).thenReturn(alloc);
        when(resource.getCost()).thenReturn(costPerHour);
        when(costPerHour.getCost()).thenReturn(cost);
        when(resource.getRole()).thenReturn(projectRole);
        when(resource.getEndDate()).thenReturn(eD);
        ResourceJpa result = assembler.toData(resource);
        ResourceJpa expected = new ResourceJpa(idJpa, endDate, alloc, cost, role);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void resourceToDataFail() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        String startDate = "2022-10-12";
        LocalDate sD = LocalDate.parse(startDate);
        ResourceID id = mock(ResourceID.class);
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        String endDate = "2023-10-12";
        LocalDate eD = LocalDate.parse(endDate);
        double alloc = 0.4;
        PercentageOfAllocation allocation = mock(PercentageOfAllocation.class);
        double cost = 500.00;
        CostPerHour costPerHour = mock(CostPerHour.class);
        String role = "TeamMember";
        ProjectRole projectRole = ProjectRole.TeamMember;
        Resource resource = mock(Resource.class);
        //Act
        when(id.getUser()).thenReturn(userID);
        when(id.getProject()).thenReturn(projectID);
        when(id.getStartDate()).thenReturn(sD);
        when(resource.getId()).thenReturn(id);
        when(resource.getAllocation()).thenReturn(allocation);
        when(allocation.getPercentage()).thenReturn(alloc);
        when(resource.getCost()).thenReturn(costPerHour);
        when(costPerHour.getCost()).thenReturn(cost);
        when(resource.getRole()).thenReturn(projectRole);
        when(resource.getEndDate()).thenReturn(eD);
        ResourceJpa result = assembler.toData(resource);
        ResourceJpa expected = new ResourceJpa(idJpa, endDate, alloc, cost, role);
        //Assert
        assertNotEquals(expected, result);
    }
}