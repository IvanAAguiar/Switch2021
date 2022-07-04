package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IEffortHoursFactory;
import switch2021.project.entities.valueObjects.vos.Hours;

@Component
public class EffortHoursFactory implements IEffortHoursFactory {

    @Override
    public Hours createEffortHours(int effortHours) {
        return new Hours(effortHours);
    }
}
