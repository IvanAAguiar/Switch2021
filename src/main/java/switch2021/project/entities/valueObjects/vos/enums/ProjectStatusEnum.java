package switch2021.project.entities.valueObjects.vos.enums;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ProjectStatusEnum {

    PLANNED,
    INCEPTION,
    ELABORATION,
    CONSTRUCTION,
    TRANSITION,
    WARRANTY,
    CLOSED;

    public static List<String> getProjectStatus() {

        ProjectStatusEnum[] statusValues = ProjectStatusEnum.values();
        List<String> projectStatusEnum = new ArrayList<>();

        for (ProjectStatusEnum status : statusValues) {
            projectStatusEnum.add(status.toString());
        }
        return projectStatusEnum;
    }
}
