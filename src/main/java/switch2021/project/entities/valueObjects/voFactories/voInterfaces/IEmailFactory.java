package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.entities.valueObjects.vos.Email;

public interface IEmailFactory {
    Email createEmail(String emailText);
}
