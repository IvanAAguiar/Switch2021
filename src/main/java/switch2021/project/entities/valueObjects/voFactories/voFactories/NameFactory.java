package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.INameFactory;
import switch2021.project.entities.valueObjects.vos.Name;

@Component
public class NameFactory implements INameFactory {
    @Override
    public Name createName(String name) {
        return new Name(name);
    }
}
