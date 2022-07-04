package switch2021.project.entities.valueObjects.vos.enums;

import java.util.ArrayList;
import java.util.List;

public enum UserStoryOfSprintStatus {

    Todo,
    InProgress,
    Done,
    Cancelled;

    public static List<String> getUserStoryOfSprintStatus() {
        UserStoryOfSprintStatus[] statusValues = UserStoryOfSprintStatus.values();
        List<String> userStoryOfSprintStatus = new ArrayList<>();

        for (UserStoryOfSprintStatus statusEnum : statusValues) {
            userStoryOfSprintStatus.add(statusEnum.toString());
        }
        return userStoryOfSprintStatus;
    }

}
