package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsPriorityFactory;
import switch2021.project.entities.valueObjects.vos.UsPriority;

@Component
public class UsPriorityFactory implements IUsPriorityFactory {
    @Override
    public UsPriority create(int priority){
        return new UsPriority(priority);
    }

}
