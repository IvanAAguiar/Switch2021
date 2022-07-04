package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IBudgetFactory;
import switch2021.project.entities.valueObjects.vos.Budget;

@Component
public class BudgetFactory implements IBudgetFactory {
    @Override
    public Budget create (double budget){
        return new Budget(budget);
    }
}
