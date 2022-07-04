package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.NewUserInfoDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.factories.factoryInterfaces.*;
import switch2021.project.entities.aggregates.User.User;

@Component
public class UserFactory implements IUserFactory {

    @Autowired
    private IUserIDFactory idFactory;
    @Autowired
    private INameFactory nameFactory;
    @Autowired
    private IFunctionFactory functionFactory;
    @Autowired
    private IPasswordFactory passwordFactory;
    @Autowired
    private IPhotoFactory photoFactory;
    @Autowired
    private IUserProfileIDFactory userProfileIDFactory;

    @Override
    public User createUser(NewUserInfoDTO infoDTO) {
        UserID userID = idFactory.createUserID(infoDTO.email);
        Name userName = nameFactory.createName(infoDTO.userName);
        Photo photo = photoFactory.createPhoto(infoDTO.photo);
        Password password = passwordFactory.createPassword(infoDTO.password);
        Password passwordConfirmation = passwordFactory.createPassword(infoDTO.passwordConfirmation);
        Function function = functionFactory.createFunction(infoDTO.function);
        UserProfileID visitorID = userProfileIDFactory.createUserProfileID("Visitor");

        return new User(userID, userName, photo, password, passwordConfirmation, function, visitorID);
    }
}