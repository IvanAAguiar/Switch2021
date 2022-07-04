package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        String code = "TeamMember";
        String projectName = "TeamMember";
        String description = "TeamMember";
        String businessSector = "TeamMember";
        String typology = "TeamMember";
        String customer = "TeamMember";
        String startDate = "TeamMember";
        String endDate = "TeamMember";
        String numberOfSprints = "TeamMember";
        String budget = "TeamMember";
        String projectStatus = "TeamMember";
        String sprintDuration = "TeamMember";

        ProjectDTO dtoAll = new ProjectDTO(code, projectName, description, businessSector, typology, customer,
                startDate, endDate, numberOfSprints, budget, projectStatus, sprintDuration);
        //Act + Assert
        assertEquals(code, dtoAll.getCode());
        assertEquals(projectName, dtoAll.getProjectName());
        assertEquals(description, dtoAll.getDescription());
        assertEquals(businessSector, dtoAll.getBusinessSector());
        assertEquals(typology, dtoAll.getTypology());
        assertEquals(customer, dtoAll.getCustomer());
        assertEquals(startDate, dtoAll.getStartDate());
        assertEquals(endDate, dtoAll.getEndDate());
        assertEquals(numberOfSprints, dtoAll.getNumberOfSprints());
        assertEquals(budget, dtoAll.getBudget());
        assertEquals(businessSector, dtoAll.getBusinessSector());
        assertEquals(projectStatus, dtoAll.getProjectStatus());
        assertEquals(sprintDuration, dtoAll.getSprintDuration());
    }
}
