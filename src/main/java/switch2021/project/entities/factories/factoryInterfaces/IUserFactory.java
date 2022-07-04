package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.NewUserInfoDTO;
import switch2021.project.entities.aggregates.User.User;

public interface IUserFactory {
    User createUser(NewUserInfoDTO infoDTO);
}
