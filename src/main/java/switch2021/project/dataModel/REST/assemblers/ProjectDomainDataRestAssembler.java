package switch2021.project.dataModel.REST.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dataModel.REST.ProjectRestDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.voFactories.voFactories.*;
import switch2021.project.entities.valueObjects.vos.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

@Component
public class ProjectDomainDataRestAssembler {


    @Autowired
    DescriptionFactory descriptionFactory;
    @Autowired
    BusinessSectorFactory businessSectorFactory;
    @Autowired
    NumberOfSprintsFactory numberOfSprintsFactory;
    @Autowired
    SprintDurationFactory sprintDurationFactory;
    @Autowired
    BudgetFactory budgetFactory;


    public List<Project> toDomain(List<ProjectRestDTO> projectRestDTOS) {
        return projectRestDTOS.stream().map(this::toDomain).collect(Collectors.toList());
    }

    public Project toDomain(ProjectRestDTO projectRestDTO) {

        Description name = descriptionFactory.createDescription(projectRestDTO.projectName);
        Description description = descriptionFactory.createDescription(projectRestDTO.projectDescription);
        BusinessSector businessSector =
                businessSectorFactory.createBusinessSector(projectRestDTO.projectBusinessSector);
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        if (projectRestDTO.projectNumberOfPlannedSprints != null) {
            numberOfSprints =
                    numberOfSprintsFactory.create(Integer.parseInt(projectRestDTO.projectNumberOfPlannedSprints));
        }
        SprintDuration sprintDuration = new SprintDuration(projectRestDTO.getProjectSprintDuration());
//        SprintDuration sprintDuration;
//        try {
//            sprintDuration = sprintDurationFactory.create(Integer.parseInt(projectRestDTO.projectSprintDuration));
//        } catch (NullPointerException sprintDurationException) {
//            sprintDuration = sprintDurationFactory.create(15); //default spring number of days
//        }
        Budget budget = new Budget(1);
        if(projectRestDTO.projectBudget != null){
            budget = budgetFactory.create(Double.parseDouble(projectRestDTO.projectBudget));
        }
        LocalDate startDate = LocalDate.parse(projectRestDTO.getStartDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Project project = new Project(name, description, businessSector, startDate, numberOfSprints, sprintDuration,
                budget);
        project.setProjectCode(new ProjectID(projectRestDTO.getProjectCode()));
        return project;

    }

}
