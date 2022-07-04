package switch2021.project.dataModel.JPA.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dataModel.JPA.ProjectJpa;
import switch2021.project.entities.factories.factoryInterfaces.IProjectFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;

@Service
public class ProjectJpaAssembler {

    @Autowired
    IProjectFactory factory;

    public ProjectJpa toJpaData(Project project) {

        String projectName = project.getProjectName().getText();
        String description = project.getDescription().getText();
        String businessSector = project.getBusinessSector().getDescription().getText();
        String startDate = project.getStartDate().toString();
        int numberOfSprints = project.getNumberOfSprints().getNumberOfSprintsVO();
        long sprintDuration = project.getSprintDuration().getSprintDurationDays();
        double budget = project.getBudget().getBudgetVO();

        String endDate = null;
        String typology = null;
        String customer = null;
        String status = null;

        if (!(project.getEndDate() == null)) {
            endDate = project.getEndDate().toString();
        }
        if (!(project.getTypologyId() == null)) {
            typology = project.getTypologyId().getDescription().getText();
        }
        if (!(project.getCustomer() == null)) {
            customer = project.getCustomer().getCustomerName().getText();
        }
        if (!(project.getProjectStatus() == null)) {
            status = project.getProjectStatus().toString();
        }

        return new ProjectJpa(project.getProjectCode(), projectName, description, businessSector, startDate,
                              numberOfSprints, sprintDuration, budget, endDate, typology, customer, status);

    }

    public Project toDomain(ProjectJpa projectJpa) {

        ProjectID projectCode = projectJpa.getProjectCode();
        Description projectName = new Description(projectJpa.getName());
        Description description = new Description(projectJpa.getDescription());
        BusinessSector businessSector = new BusinessSector(projectJpa.getBusinessSector());
        LocalDate startDate = LocalDate.parse(projectJpa.getStartDate());
        NumberOfSprints numberOfSprints = new NumberOfSprints(projectJpa.getNumberOfSprints());
        SprintDuration sprintDuration = new SprintDuration(projectJpa.getSprintDuration());
        Budget budget = new Budget(projectJpa.getBudget());

        LocalDate endDate = null;
        TypologyID typology = null;
        Customer customer = null;
        ProjectStatusEnum status = null;

        if (!(projectJpa.getEndDate() == null)) {
            endDate = LocalDate.parse(projectJpa.getEndDate());
        }
        if (!(projectJpa.getTypology() == null)) {
            typology = new TypologyID(new Description(projectJpa.getTypology()));
        }
        if (!(projectJpa.getCustomer() == null)) {
            customer = Customer.create(projectJpa.getCustomer());
        }
        if (!(projectJpa.getStatus() == null)) {
            status = ProjectStatusEnum.valueOf(projectJpa.getStatus());
        }


        return new Project(projectCode, projectName, description, typology, status,
                           customer, businessSector, numberOfSprints, budget, sprintDuration, startDate, endDate);
    }



}
