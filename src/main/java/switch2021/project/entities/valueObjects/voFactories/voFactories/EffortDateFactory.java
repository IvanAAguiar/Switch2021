package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IEffortDateFactory;
import switch2021.project.entities.valueObjects.vos.Date;

import java.time.LocalDate;

@Component
public class EffortDateFactory implements IEffortDateFactory {
    @Override
    public Date createEffortDate(String effortDate) {
        return new Date(LocalDate.parse(effortDate));
    }
}
