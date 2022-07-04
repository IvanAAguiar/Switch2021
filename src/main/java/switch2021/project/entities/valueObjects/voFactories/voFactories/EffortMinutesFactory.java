package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IEffortMinutesFactory;
import switch2021.project.entities.valueObjects.vos.Minutes;

@Component
public class EffortMinutesFactory implements IEffortMinutesFactory {
    @Override
    public Minutes createEffortMinutes(int effortMinutes) {
        return new Minutes(effortMinutes);
    }
}
