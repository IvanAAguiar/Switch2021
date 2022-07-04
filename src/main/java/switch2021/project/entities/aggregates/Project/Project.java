package switch2021.project.entities.aggregates.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Project {

    /**
     * Class Attributes
     **/
    private ProjectID projectCode;
    private Description projectName;
    private Description description;
    private TypologyID typologyId;
    private ProjectStatusEnum projectStatus;
    private Customer customer;
    private BusinessSector businessSector;
    private NumberOfSprints numberOfSprints;
    private Budget budget;
    private SprintDuration sprintDuration;
    private LocalDate startDate;
    private LocalDate endDate;


    public Project(Description name, Description description, BusinessSector sector, LocalDate startDate,
                   NumberOfSprints numberOfSprints, SprintDuration sprintDuration, Budget budget) {

        this.projectName = name;
        this.description = description;
        this.businessSector = sector;
        this.startDate = startDate;
        this.numberOfSprints = numberOfSprints;
        this.sprintDuration = sprintDuration;
        this.budget = budget;
        this.projectStatus = ProjectStatusEnum.PLANNED;

    }

    /**
     * Method that checks if given code is this projects code
     * **/
    public boolean hasCode(String code) {
        String projCode = this.projectCode.getCode().toLowerCase();
        return projCode.equals(code.toLowerCase(Locale.ROOT));
    }

    public boolean isActiveInThisDate(LocalDate date) {
        boolean result = false;

        if(this.startDate.isBefore(date) || this.startDate.isEqual(date)){

            if(this.endDate == null || this.endDate.isEqual(date) || this.endDate.isAfter(date)){
                result = true;
            }
        }
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(projectCode, project.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode);
    }
}
