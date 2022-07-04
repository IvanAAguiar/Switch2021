package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IPercOfAllocationFactory;
import switch2021.project.entities.valueObjects.vos.PercentageOfAllocation;

@Component
public class PercOfAllocationFactory implements IPercOfAllocationFactory {
    @Override
    public PercentageOfAllocation create (double percOfAllo){
        return new PercentageOfAllocation(percOfAllo);
    }
}
