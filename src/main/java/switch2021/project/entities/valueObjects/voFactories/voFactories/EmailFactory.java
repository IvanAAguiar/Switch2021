package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IEmailFactory;
import switch2021.project.entities.valueObjects.vos.Email;

@Component
public class EmailFactory implements IEmailFactory {
    @Override
    public Email createEmail(String emailText) {
        return new Email(emailText);
    }
}