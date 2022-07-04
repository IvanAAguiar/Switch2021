package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.UserStoryDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsHourFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsPriorityFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserStoryIDFactory;
import switch2021.project.entities.factories.factoryInterfaces.*;
import switch2021.project.entities.aggregates.UserStory.*;

@Component
public class UserStoryFactory implements IUserStoryFactory {


    @Autowired
    private IUserStoryIDFactory userStoryID;
    @Autowired
    private IUsPriorityFactory priority;
    @Autowired
    private IDescriptionFactory description;
    @Autowired
    private IUsHourFactory timeEstimate;



    @Override
    public UserStory createUserStory(UserStoryDTO userStoryDTO) {
        return new UserStory(userStoryID.create(userStoryDTO.projectID, userStoryDTO.title),
                priority.create(userStoryDTO.priority),
                description.createDescription(userStoryDTO.description),
                timeEstimate.create(userStoryDTO.timeEstimate));
    }

}