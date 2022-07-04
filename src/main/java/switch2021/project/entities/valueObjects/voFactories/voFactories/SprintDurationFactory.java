package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ISprintDurationFactory;
import switch2021.project.entities.valueObjects.vos.SprintDuration;

@Component
public class SprintDurationFactory implements ISprintDurationFactory {
    @Override
    public SprintDuration create (int sprintDurationDays){
        return new SprintDuration(sprintDurationDays);
    }
}
