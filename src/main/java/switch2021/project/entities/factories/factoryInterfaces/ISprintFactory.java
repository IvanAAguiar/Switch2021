package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.NewSprintDTO;
import switch2021.project.entities.aggregates.Sprint.Sprint;


public interface ISprintFactory {
    Sprint createSprint(NewSprintDTO dto);
}
