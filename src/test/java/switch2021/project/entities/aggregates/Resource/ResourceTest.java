package switch2021.project.entities.aggregates.Resource;

import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.CostPerHour;
import switch2021.project.entities.valueObjects.vos.PercentageOfAllocation;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResourceTest {

    @Test
    void noArgsConstructor() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        //Act
        Resource nullRes = new Resource();
        //Assert
        assertNotEquals(res, nullRes);
    }

    @Test
    void withDateBeforeStartDate() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        LocalDate end = LocalDate.of(2022, 12, 31);
        res.setEndDate(end);
        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 5, 30);
        when(id.getStartDate()).thenReturn(start);
        //Assert
        assertFalse(res.isActiveToThisDate(date));
    }

    @Test
    void withDateEqualStartDate() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        LocalDate end = LocalDate.of(2022, 12, 31);
        res.setEndDate(end);
        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 5, 2);
        when(id.getStartDate()).thenReturn(start);
        //Assert
        assertTrue(res.isActiveToThisDate(date));
    }

    @Test
    void withDateAfterStartDate() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        LocalDate end = LocalDate.of(2022, 12, 31);
        res.setEndDate(end);
        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 5, 1);
        when(id.getStartDate()).thenReturn(start);
        //Assert
        assertTrue(res.isActiveToThisDate(date));
    }

    @Test
    void withDateAfterEndDate() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        LocalDate end = LocalDate.of(2022, 4, 30);
        res.setEndDate(end);
        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 4, 1);
        when(id.getStartDate()).thenReturn(start);
        //Assert
        assertFalse(res.isActiveToThisDate(date));
    }

    @Test
    void withDateEqualEndDate() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        LocalDate end = LocalDate.of(2022, 5, 2);
        res.setEndDate(end);
        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 4, 1);
        when(id.getStartDate()).thenReturn(start);
        //Assert
        assertTrue(res.isActiveToThisDate(date));
    }

    @Test
    void hasProjectRoleTrue() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = ProjectRole.TeamMember;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        //Assert
        assertTrue(res.hasProjectRole("TeamMember"));
    }

    @Test
    void hasProjectRoleFalse() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = ProjectRole.TeamMember;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        //Assert
        assertFalse(res.hasProjectRole("ScrumMaster"));
    }

    @Test
    void equalsTrue() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        Resource allRes = new Resource(res.getId(), res.getEndDate(), res.getAllocation(),
                res.getCost(), res.getRole());
        //Act
        when(id.sameValueAs(id)).thenReturn(true);
        //Assert
        assertEquals(res, allRes);
    }

    @Test
    public void getInfo() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = ProjectRole.TeamMember;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        //Act
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        //Assert
        assertEquals(id, res.getId());
        assertEquals(endDate, res.getEndDate());
        assertEquals(costPerHour, res.getCost());
        assertEquals(projRole, res.getRole());
        assertEquals(all, res.getAllocation());
    }

    @Test
    public void sameIdentityAsTrue() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        //Act
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        Resource res2 = new Resource(id, endDate, all, costPerHour, projRole);
        when(id.sameValueAs(id)).thenReturn(true);
        //Assert
        assertTrue(res.sameIdentityAs(res2));
    }

    @Test
    public void sameIdentityAsFalse() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        //Act
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        Resource res2 = new Resource(id, endDate, all, costPerHour, projRole);
        //Assert
        assertFalse(res.sameIdentityAs(res2));
    }

    @Test
    public void hashCodeSuccess() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        //Act
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        Resource res2 = new Resource(id, endDate, all, costPerHour, projRole);
        //Assert
        assertEquals(res.hashCode(), res2.hashCode());
    }

    @Test
    public void hashCodeFail() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        ResourceID id2 = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        //Act
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        Resource res2 = new Resource(id2, endDate, all, costPerHour, projRole);
        //Assert
        assertNotEquals(res.hashCode(), res2.hashCode());
    }

    @Test
    public void overrideTest() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        ResourceID id2 = mock(ResourceID.class);
        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        ProjectRole projRole = null;
        LocalDate endDate = LocalDate.of(2021, 12, 10);
        //Act
        Resource res = new Resource(id, endDate, all, costPerHour, projRole);
        Resource res2 = new Resource(id2, endDate, all, costPerHour, projRole);
        Resource res3 = new Resource(id, endDate, all, costPerHour, projRole);

        boolean expected = res.equals(res2);
        boolean expected2 = res.equals(res);
        //Assert
        assertNotSame(res, res2);
        assertEquals(res.getId(), res3.getId());
        assertNotEquals(res.getId(), res2.getId());
        assertFalse(expected);
        assertTrue(expected2);


    }

}