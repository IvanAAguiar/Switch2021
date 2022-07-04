package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IBusinessSectorFactory;
import switch2021.project.entities.valueObjects.vos.BusinessSector;

@Component
public class BusinessSectorFactory implements IBusinessSectorFactory {

    @Override
    public BusinessSector createBusinessSector(String description) {

        return new BusinessSector(description);
    }
}
