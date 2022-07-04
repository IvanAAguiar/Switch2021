package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.vos.Description;

@Component
public class DescriptionFactory implements IDescriptionFactory {

        @Override
        public Description createDescription(String description) {
            return new Description(description);
        }
}
