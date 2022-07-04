package switch2021.project.entities.valueObjects.vos.enums;

import java.util.ArrayList;
import java.util.List;

public enum UserStoryResult {

    ACCEPTED,
    REJECTED,
    UNFINISHED;

    public static List<String> getUSResult() {
        UserStoryResult[] userStoryResultValues = UserStoryResult.values();
        List<String> userStoryResults = new ArrayList<>();

        for (UserStoryResult userStoryResultEnum : userStoryResultValues) {
            userStoryResults.add(userStoryResultEnum.toString());
        }
        return userStoryResults;
    }
}
