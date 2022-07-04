package switch2021.project.entities.aggregates.Resource;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.PercentageOfAllocation;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ManagementResourcesServiceTest {

    @Test
    void returnAllCurrentResourcesTrue(){
        //Arrange
        ManagementResourcesService manageSrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        //Act
        LocalDate date = LocalDate.now();
        when(res1.isActiveToThisDate(date)).thenReturn(true);
        when(res2.isActiveToThisDate(date)).thenReturn(true);
        when(res3.isActiveToThisDate(date)).thenReturn(true);
        List<Resource> result = manageSrv.currentResourcesByDate(initial);
        //Assert
        List<Resource> expected = new ArrayList<>(List.of(res1, res2, res3));
        assertEquals(expected, result);
    }

    @Test
    void returnPartialCurrentResourcesTrue(){
        //Arrange
        ManagementResourcesService manageSrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        //Act
        LocalDate date = LocalDate.now();
        when(res1.isActiveToThisDate(date)).thenReturn(true);
        when(res2.isActiveToThisDate(date)).thenReturn(false);
        when(res3.isActiveToThisDate(date)).thenReturn(true);
        List<Resource> result = manageSrv.currentResourcesByDate(initial);
        //Assert
        List<Resource> expected = new ArrayList<>(List.of(res1, res3));
        assertEquals(expected, result);
    }

    @Test
    void returnAllCurrentResourcesFalse(){
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        //Act
        LocalDate date = LocalDate.now();
        when(res1.isActiveToThisDate(date)).thenReturn(false);
        when(res2.isActiveToThisDate(date)).thenReturn(false);
        when(res3.isActiveToThisDate(date)).thenReturn(false);
        List<Resource> result = dsrv.currentResourcesByDate(initial);
        //Assert
        List<Resource> expected = new ArrayList<>();
        assertEquals(expected, result);
    }

    @Test
    void receivingEmptyList(){
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        List<Resource> result = dsrv.currentResourcesByDate(initial);
        //Assert
        List<Resource> expected = new ArrayList<>();
        assertEquals(expected, result);
    }

    @Test
    void validateAllocationIsActiveTothisDateTrueAndAllocationTrue() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        PercentageOfAllocation allocation1 = new PercentageOfAllocation(0.4);
        PercentageOfAllocation allocation2 = new PercentageOfAllocation(0.2);
        PercentageOfAllocation allocation3 = new PercentageOfAllocation(0.2);
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res1.getAllocation()).thenReturn(allocation1);
        when(res2.getAllocation()).thenReturn(allocation2);
        when(res3.getAllocation()).thenReturn(allocation3);
        //Assert
        assertTrue(dsrv.validateAllocation(initial,startDate,endDate,0.1));
    }

    @Test
    void validateAllocationIsActiveTothisDateFalseAndAllocationTrue() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        PercentageOfAllocation allocation1 = new PercentageOfAllocation(0.4);
        PercentageOfAllocation allocation2 = new PercentageOfAllocation(0.2);
        PercentageOfAllocation allocation3 = new PercentageOfAllocation(0.2);
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(false);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(false);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(false);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(false);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(false);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(false);
        when(res1.getAllocation()).thenReturn(allocation1);
        when(res2.getAllocation()).thenReturn(allocation2);
        when(res3.getAllocation()).thenReturn(allocation3);
        //Assert
        assertTrue(dsrv.validateAllocation(initial,startDate,endDate,0.1));
    }

    @Test
    void validateAllocationIsActiveTothisDateTrueAndAllocationFalse() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        PercentageOfAllocation allocation1 = new PercentageOfAllocation(0.4);
        PercentageOfAllocation allocation2 = new PercentageOfAllocation(0.2);
        PercentageOfAllocation allocation3 = new PercentageOfAllocation(0.2);
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res1.getAllocation()).thenReturn(allocation1);
        when(res2.getAllocation()).thenReturn(allocation2);
        when(res3.getAllocation()).thenReturn(allocation3);
        //Assert
        assertFalse(dsrv.validateAllocation(initial,startDate,endDate,0.4));
    }

    @Test
    void validateAllocationIsActiveTothisDateFalseAndAllocationFalse() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        PercentageOfAllocation allocation1 = new PercentageOfAllocation(0.4);
        PercentageOfAllocation allocation2 = new PercentageOfAllocation(0.2);
        PercentageOfAllocation allocation3 = new PercentageOfAllocation(0.2);
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(false);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(false);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(false);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(false);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(false);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(false);
        when(res1.getAllocation()).thenReturn(allocation1);
        when(res2.getAllocation()).thenReturn(allocation2);
        when(res3.getAllocation()).thenReturn(allocation3);
        //Assert
        assertFalse(dsrv.validateAllocation(initial,startDate,endDate,1.3));
    }


    @Test
    void validateProjectRoleNotTeamMemberTrue() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        String projectRole = "ScrumMaster";
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res1.hasProjectRole(projectRole)).thenReturn(false);
        when(res2.hasProjectRole(projectRole)).thenReturn(false);
        when(res3.hasProjectRole(projectRole)).thenReturn(false);
        //Assert
        assertTrue(dsrv.validateProjectRole(initial, startDate, endDate, projectRole));
    }

    @Test
    void validateProjectRoleTeamMemberTrue() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        String projectRole = "TeamMember";
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res1.getRole()).thenReturn(ProjectRole.valueOf(projectRole));
        when(res2.getRole()).thenReturn(ProjectRole.valueOf(projectRole));
        when(res3.getRole()).thenReturn(ProjectRole.valueOf(projectRole));
        when(res1.hasProjectRole(projectRole)).thenReturn(true);
        when(res2.hasProjectRole(projectRole)).thenReturn(true);
        when(res3.hasProjectRole(projectRole)).thenReturn(true);
        //Assert
        assertTrue(dsrv.validateProjectRole(initial, startDate, endDate, projectRole));
    }

    @Test
    void validateProjectRoleNotTeamMemberFalse() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        String projectRole = "ScrumMaster";
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res1.getRole()).thenReturn(ProjectRole.valueOf("TeamMember"));
        when(res2.getRole()).thenReturn(ProjectRole.valueOf("TeamMember"));
        when(res3.getRole()).thenReturn(ProjectRole.valueOf(projectRole));
        when(res1.hasProjectRole(projectRole)).thenReturn(false);
        when(res2.hasProjectRole(projectRole)).thenReturn(false);
        when(res3.hasProjectRole(projectRole)).thenReturn(true);
        //Assert
        assertFalse(dsrv.validateProjectRole(initial, startDate, endDate, projectRole));
    }

    @Test
    void validateProjectRoleNotTeamMemberTrueWithOtherDate() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        String projectRole = "ScrumMaster";
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(false);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(false);
        when(res1.getRole()).thenReturn(ProjectRole.valueOf("TeamMember"));
        when(res2.getRole()).thenReturn(ProjectRole.valueOf("TeamMember"));
        when(res3.getRole()).thenReturn(ProjectRole.valueOf(projectRole));
        when(res1.hasProjectRole(projectRole)).thenReturn(false);
        when(res2.hasProjectRole(projectRole)).thenReturn(false);
        when(res3.hasProjectRole(projectRole)).thenReturn(true);
        //Assert
        assertTrue(dsrv.validateProjectRole(initial, startDate, endDate, projectRole));
    }

    @Test
    void validateProjectRoleHasNotProjectRole() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        String projectRole = "ScrumMaster";
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(false);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(false);
        when(res1.getRole()).thenReturn(ProjectRole.valueOf(projectRole));
        when(res2.getRole()).thenReturn(ProjectRole.valueOf(projectRole));
        when(res3.getRole()).thenReturn(ProjectRole.valueOf(projectRole));
        when(res1.hasProjectRole(projectRole)).thenReturn(true);
        when(res2.hasProjectRole(projectRole)).thenReturn(true);
        when(res3.hasProjectRole(projectRole)).thenReturn(true);
        //Assert
        assertFalse(dsrv.validateProjectRole(initial, startDate, endDate, projectRole));
    }

    @Test
    void validateProjectRoleEmptyList() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();

        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        String projectRole = "ScrumMaster";
        //Act
        //Assert
        assertTrue(dsrv.validateProjectRole(initial, startDate, endDate, projectRole));
    }

    @Test
    void returnProjectsNotEmpty(){
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        //Act
        ResourceID id = mock(ResourceID.class);
        ProjectID projId = mock(ProjectID.class);
        when(res1.getId()).thenReturn(id);
        when(res2.getId()).thenReturn(id);
        when(res3.getId()).thenReturn(id);
        when(id.getProject()).thenReturn(projId);
        List<ProjectID> result = dsrv.listProjectsOfResources(initial);
        //Assert
        List<ProjectID> expected = new ArrayList<>(List.of(projId, projId, projId));
        assertEquals(expected, result);
    }

    @Test
    void returnProjectsEmpty(){
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        List<Resource> initial = new ArrayList<>();
        //Act
        List<ProjectID> result = dsrv.listProjectsOfResources(initial);
        //Assert
        List<ProjectID> expected = new ArrayList<>();
        assertEquals(expected, result);
    }

    @Test
    void validateAllocationIsActiveTothisDateFalseAndAllocationFalse2() {
        //Arrange
        ManagementResourcesService dsrv = new ManagementResourcesService();
        Project proj = mock(Project.class);
        List<Resource> initial = new ArrayList<>();
        Resource res1 = mock(Resource.class);
        Resource res2 = mock(Resource.class);
        Resource res3 = mock(Resource.class);
        initial.add(res1);
        initial.add(res2);
        initial.add(res3);
        String startDate = "2022-12-22";
        String endDate = "2023-12-22";
        PercentageOfAllocation allocation1 = mock(PercentageOfAllocation.class);
        PercentageOfAllocation allocation2 = mock(PercentageOfAllocation.class);
        PercentageOfAllocation allocation3 = mock(PercentageOfAllocation.class);
        //Act
        when(res1.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res1.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res2.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(startDate))).thenReturn(true);
        when(res3.isActiveToThisDate(LocalDate.parse(endDate))).thenReturn(true);
        when(res1.getAllocation()).thenReturn(allocation1);
        when(res2.getAllocation()).thenReturn(allocation2);
        when(res3.getAllocation()).thenReturn(allocation3);
        when(allocation1.getPercentage()).thenReturn(0.4);
        when(allocation2.getPercentage()).thenReturn(0.2);
        when(allocation3.getPercentage()).thenReturn(0.2);
        //Assert
        assertFalse(dsrv.validateAllocation(initial,startDate,endDate,0.3));
    }


}