package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsHourFactory;
import switch2021.project.entities.valueObjects.vos.UsHour;

@Component
public class UsHourFactory implements IUsHourFactory {
    @Override
    public UsHour create(double timeEstimate) {
        return new UsHour(timeEstimate);

    }
}
