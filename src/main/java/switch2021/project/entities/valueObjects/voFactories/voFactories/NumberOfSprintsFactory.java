package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.INumberOfSprintsFactory;
import switch2021.project.entities.valueObjects.vos.NumberOfSprints;


@Component
public class NumberOfSprintsFactory implements INumberOfSprintsFactory {

        @Override
        public NumberOfSprints create(int numberOfSprints){
            return new NumberOfSprints(numberOfSprints);
        }

    }
