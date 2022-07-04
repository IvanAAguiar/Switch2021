package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IPasswordFactory;
import switch2021.project.entities.valueObjects.vos.Password;

@Component
public class PasswordFactory implements IPasswordFactory {
    @Override
    public Password createPassword(String password) {
        return new Password(password);
    }
}
