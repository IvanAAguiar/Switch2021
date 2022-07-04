package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefineRoleOfResourceDTOTest {

    @Test
    void getSystemUserID() {
        //Arrange
        String projectRole = "TeamMember";
        String startDate = "2022-03-08";
        String endDate = "2022-11-18";
        double costPerHour = 20.0;
        double percentageOfAllocation = 1;

        DefineRoleOfResourceDTO dtoAll = new DefineRoleOfResourceDTO(projectRole, startDate, endDate,
                costPerHour, percentageOfAllocation);
        //Act + Assert
        assertEquals(projectRole, dtoAll.getRole());
        assertEquals(startDate, dtoAll.getStartDate());
        assertEquals(endDate, dtoAll.getEndDate());
        assertEquals(costPerHour, dtoAll.getCostPerHour());
        assertEquals(percentageOfAllocation, dtoAll.getPercentageOfAllocation());
    }
}