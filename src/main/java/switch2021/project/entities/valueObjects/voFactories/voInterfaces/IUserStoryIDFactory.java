package switch2021.project.entities.valueObjects.voFactories.voInterfaces;


import switch2021.project.entities.valueObjects.vos.UserStoryID;


public interface IUserStoryIDFactory {
    UserStoryID create(String projectID, String usTitle);
}
