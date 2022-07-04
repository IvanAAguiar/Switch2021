package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IEmailFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.Email;
import switch2021.project.entities.valueObjects.vos.UserID;

@Component
public class UserIDFactory implements IUserIDFactory {

    @Autowired
    private IEmailFactory emailFactory;

    @Override
    public UserID createUserID(String emailText) {
        Email email = emailFactory.createEmail(emailText);
        return new UserID(email);
    }
}
