package switch2021.project.dataModel.jpa.assembler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import switch2021.project.dataModel.JPA.ProjectJpa;
import switch2021.project.dataModel.JPA.assembler.ProjectJpaAssembler;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProjectJpaAssemblerTest {

    @InjectMocks
    ProjectJpaAssembler projJpaAssembler;

    @Test
    void toJpaDataAllData() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        Customer customer = mock(Customer.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;

        String projName = "x";
        int numberOfSprints = 1;
        long sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(typoId);
        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(customer);
        when(customer.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(endDate);
        when(proj.getProjectStatus()).thenReturn(status);
        ProjectJpa expected = new ProjectJpa();
        expected.setProjectCode(projID);
        expected.setName(projName);
        expected.setDescription("x");
        expected.setBusinessSector("x");
        expected.setEndDate(endDate.toString());
        expected.setNumberOfSprints(numberOfSprints);
        expected.setSprintDuration(sprintDuration);
        expected.setBudget(budget);
        expected.setStartDate(endDate.toString());
        expected.setTypology("x");
        expected.setCustomer("x");
        expected.setStatus(status.toString());
        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);

        //Assert
        assertTrue(new ReflectionEquals(expected).matches(result));
    }

    @Test
    void toJpaDataTypoNull() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        Customer customer = mock(Customer.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;

        String projName = "x";
        int numberOfSprints = 1;
        long sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(null);
        when(proj.getCustomer()).thenReturn(customer);
        when(customer.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(endDate);
        when(proj.getProjectStatus()).thenReturn(status);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                                             numberOfSprints, sprintDuration, budget, endDate.toString(), null, "x",
                                             status.toString());

        //Assert
        assertTrue(new ReflectionEquals(expected).matches(result));
    }

    @Test
    void toJpaDataCustomerNull() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;

        String projName = "x";
        int numberOfSprints = 1;
        long sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(typoId);
        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(null);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(endDate);
        when(proj.getProjectStatus()).thenReturn(status);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                                             numberOfSprints, sprintDuration, budget, endDate.toString(), "x", null,
                                             status.toString());

        //Assert
        assertTrue(new ReflectionEquals(expected).matches(result));
    }

    @Test
    void toJpaDataEndDateNull() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        Customer customer = mock(Customer.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;

        String projName = "x";
        int numberOfSprints = 1;
        long sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(typoId);
        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(customer);
        when(customer.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(null);
        when(proj.getProjectStatus()).thenReturn(status);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                                             numberOfSprints, sprintDuration, budget, null, "x", "x",
                                             status.toString());

        //Assert
        assertTrue(new ReflectionEquals(expected).matches(result));
    }

    @Test
    void toJpaDataStatusNull() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        Customer customer = mock(Customer.class);

        String projName = "x";
        int numberOfSprints = 1;
        long sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(typoId);
        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(customer);
        when(customer.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(endDate);
        when(proj.getProjectStatus()).thenReturn(null);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                                             numberOfSprints, sprintDuration, budget, endDate.toString(), "x", "x",
                                             null);

        //Assert
        assertTrue(new ReflectionEquals(expected).matches(result));
    }


    @Test
    void toDomain() {
        ProjectJpa projJpa = mock(ProjectJpa.class);
        ProjectID projId = mock(ProjectID.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 12);

        String ex = "x";
        int numberOfSprints = 1;
        long sprintDuration = 7;
        double budget = 100;

        when(projJpa.getProjectCode()).thenReturn(projId);
        when(projJpa.getName()).thenReturn(ex);
        when(projJpa.getDescription()).thenReturn(ex);
        when(projJpa.getBusinessSector()).thenReturn(ex);
        when(projJpa.getStartDate()).thenReturn(startDate.toString());
        when(projJpa.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(projJpa.getSprintDuration()).thenReturn(sprintDuration);
        when(projJpa.getBudget()).thenReturn(budget);
        when(projJpa.getEndDate()).thenReturn(endDate.toString());
        when(projJpa.getTypology()).thenReturn(ex);
        when(projJpa.getCustomer()).thenReturn(ex);
        when(projJpa.getStatus()).thenReturn(status.toString());

        //Act
        Project result = projJpaAssembler.toDomain(projJpa);

        //Assert
        assertEquals(projId, result.getProjectCode());
        assertEquals("x", result.getProjectName().getText());

    }
}