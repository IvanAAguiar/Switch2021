package switch2021.project.dataModel.JPA.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.dataModel.JPA.SprintJpa;
import switch2021.project.entities.aggregates.Sprint.Sprint;

import java.time.LocalDate;

@Component
public class SprintJpaAssembler {

    public SprintJpa toData(Sprint sprint) {

        String startDate = null;
        String endDate = null;

        if (!(sprint.getStartDate() == null)) {
            startDate = sprint.getStartDate().toString();
        }

        if (!(sprint.getEndDate() == null)) {
            endDate = sprint.getEndDate().toString();
        }


        return new SprintJpa(sprint.getSprintID(), startDate, endDate);
    }

    public Sprint toDomain(SprintJpa sprintJpaSaved) {
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (!(sprintJpaSaved.getStartDate() == null)) {
            startDate = LocalDate.parse(sprintJpaSaved.getStartDate());
        }
        if (!(sprintJpaSaved.getEndDate() == null)) {
            endDate = LocalDate.parse(sprintJpaSaved.getEndDate());
        }


        return new Sprint(sprintJpaSaved.getSprintId(), startDate, endDate);
    }
}
