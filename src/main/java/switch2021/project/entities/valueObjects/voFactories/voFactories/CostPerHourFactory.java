package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ICostPerHourFactory;
import switch2021.project.entities.valueObjects.vos.CostPerHour;

@Component
public class CostPerHourFactory implements ICostPerHourFactory {

    @Override
    public CostPerHour create (double costPerHour){
        return new CostPerHour(costPerHour);
    }
}
