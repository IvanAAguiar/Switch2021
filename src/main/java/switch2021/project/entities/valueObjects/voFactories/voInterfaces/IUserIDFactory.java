package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.entities.valueObjects.vos.UserID;

public interface IUserIDFactory {
    UserID createUserID(String emailText);
}
