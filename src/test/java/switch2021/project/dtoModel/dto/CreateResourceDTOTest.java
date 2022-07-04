package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateResourceDTOTest {

    @Test
    void getSystemUserID() {
        //Arrange
        String systemUserID = "zbz@mymail.com";
        String projectId = "Project_2022_3";
        String projectRole = "TeamMember";
        String startDate = "2022-03-08";
        String endDate = "2022-11-18";
        double costPerHour = 20.0;
        double percentageOfAllocation = 1;

        CreateResourceDTO dtoAll = new CreateResourceDTO(systemUserID, projectId, projectRole, startDate, endDate,
                costPerHour, percentageOfAllocation);
        //Act + Assert
        assertEquals(systemUserID, dtoAll.getSystemUserID());
        assertEquals(projectId, dtoAll.getProjectId());
        assertEquals(projectRole, dtoAll.getProjectRole());
        assertEquals(startDate, dtoAll.getStartDate());
        assertEquals(endDate, dtoAll.getEndDate());
        assertEquals(costPerHour, dtoAll.getCostPerHour());
        assertEquals(percentageOfAllocation, dtoAll.getPercentageOfAllocation());
    }
}