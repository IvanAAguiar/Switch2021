package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.NewSprintDTO;
import switch2021.project.entities.factories.factoryInterfaces.ISprintFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ISprintIDFactory;
import switch2021.project.entities.aggregates.Sprint.Sprint;

@Component
public class SprintFactory implements ISprintFactory {

    /**
     * Attributes
     */
    @Autowired
    private ISprintIDFactory sprintIDFactory;

    /**
     * Method
     */
    @Override
    public Sprint createSprint(NewSprintDTO dto) {
        return new Sprint(sprintIDFactory.create(dto.projectID, dto.name));
    }
}
