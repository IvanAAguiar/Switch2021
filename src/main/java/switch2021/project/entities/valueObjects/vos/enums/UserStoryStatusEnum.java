package switch2021.project.entities.valueObjects.vos.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum UserStoryStatusEnum {

    OPEN,
    IN_PROGRESS,
    FINISHED,
    CANCELED,
    REFINED;

    public static List<String> getUserStoryStatusEnum() {
        UserStoryStatusEnum[] statusEnums = UserStoryStatusEnum.values();
        List<String> userStoryStatusEnum = new ArrayList<>();

        for (UserStoryStatusEnum x : statusEnums) {
            userStoryStatusEnum.add(x.toString());
        }
        return userStoryStatusEnum;
    }
}
