package switch2021.project.dataModel.REST;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRestDTOTest {

    @Test
    void setProjectCode() {
        //Arrange
        String projectCode = "Project_2022_1";
        String projectName = "ProjectName";
        String projectDescription = "ProjectDescription";
        String projectBusinessSector = "ProjectBusinessSector";
        String typologyDescription = "typologyDescription";
        String customerName = "customerName";
        String startDate = "2022-12-12";
        String endDate = "2023-12-12";
        String projectNumberOfPlannedSprint = "30";
        String projectBudget = "50000";
        String status = "status";
        String projectSprintDuration = "7";
        ProjectRestDTO expected = new ProjectRestDTO();
        expected.setProjectCode(projectCode);
        expected.setProjectName(projectName);
        expected.setProjectDescription(projectDescription);
        expected.setProjectBusinessSector(projectBusinessSector);
        expected.setTypologyDescription(typologyDescription);
        expected.setCustomerName(customerName);
        expected.setStartDate(startDate);
        expected.setEndDate(endDate);
        expected.setProjectNumberOfPlannedSprints(projectNumberOfPlannedSprint);
        expected.setProjectBudget(projectBudget);
        expected.setStatus(status);
        expected.setProjectSprintDuration(projectSprintDuration);
        //Act
        ProjectRestDTO result = new ProjectRestDTO(projectName, projectDescription, projectBusinessSector, startDate,
                projectNumberOfPlannedSprint, projectBudget, projectSprintDuration,
                typologyDescription,customerName);
        result.setProjectCode(expected.getProjectCode());
        result.setEndDate(expected.getEndDate());
        result.setStatus(expected.getStatus());
        //Assert
        assertEquals(expected.getProjectName(), result.getProjectName());
        assertEquals(expected.getProjectDescription(), result.getProjectDescription());
        assertEquals(expected.getProjectBusinessSector(), result.getProjectBusinessSector());
        assertEquals(expected.getTypologyDescription(), result.getTypologyDescription());
        assertEquals(expected.getCustomerName(), result.getCustomerName());
        assertEquals(expected.getStartDate(), result.getStartDate());
        assertEquals(expected.getProjectNumberOfPlannedSprints(), result.getProjectNumberOfPlannedSprints());
        assertEquals(expected.getProjectBudget(), result.getProjectBudget());
        assertEquals(expected.getStatus(), result.getStatus());
        assertEquals(expected.getProjectSprintDuration(), result.getProjectSprintDuration());
    }
}