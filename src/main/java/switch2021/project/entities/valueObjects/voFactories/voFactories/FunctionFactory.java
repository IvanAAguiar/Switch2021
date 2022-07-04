package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IFunctionFactory;
import switch2021.project.entities.valueObjects.vos.Function;

@Component
public class FunctionFactory implements IFunctionFactory {
    @Override
    public Function createFunction(String function) {
        return new Function(function);
    }
}
