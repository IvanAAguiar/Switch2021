package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.entities.factories.factoryInterfaces.IProjectFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.aggregates.Project.Project;

import java.time.LocalDate;

@Component
public class ProjectFactory implements IProjectFactory {

    @Autowired
    private IDescriptionFactory nameF;

    @Autowired
    private IDescriptionFactory descF;

    @Autowired
    private IBusinessSectorFactory busSecF;

    @Autowired
    private INumberOfSprintsFactory numberSprintsF;

    @Autowired
    private ISprintDurationFactory sprintDurationF;

    @Autowired
    private IBudgetFactory budgetF;

    @Autowired
    private ITypologyIDFactory typologyIDFactory;


    @Override
    public Project createProject(ProjectDTO projectDTO) {

        Description name = nameF.createDescription(projectDTO.getProjectName());
        Description description = descF.createDescription(projectDTO.getDescription());
        BusinessSector businessSector = busSecF.createBusinessSector(projectDTO.getBusinessSector());
        LocalDate date = LocalDate.parse(projectDTO.getStartDate());
        NumberOfSprints numberOfSprints = numberSprintsF.create(Integer.parseInt(projectDTO.getNumberOfSprints()));
        SprintDuration sprintDuration = sprintDurationF.create(Integer.parseInt(projectDTO.getSprintDuration()));
        Budget budget = budgetF.create(Integer.parseInt(projectDTO.getBudget()));

        Project project = new Project(name, description, businessSector, date, numberOfSprints,
                                      sprintDuration, budget);

        project.setTypologyId((typologyIDFactory.createIdWithString(projectDTO.getTypology())));

        project.setCustomer((Customer.create(projectDTO.getCustomer())));

        if (projectDTO.getEndDate() != null)
            project.setEndDate(LocalDate.parse(projectDTO.getEndDate()));

        return project;
    }

}
