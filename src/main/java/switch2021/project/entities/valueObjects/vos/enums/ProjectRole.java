package switch2021.project.entities.valueObjects.vos.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProjectRole {

    ProjectManager,
    ProductOwner,
    ScrumMaster,
    TeamMember;

    public static List<String> getProjectRole() {
        ProjectRole[] projectRolesValues = ProjectRole.values();
        List<String> projectRoles = new ArrayList<>();

        for (ProjectRole projectRoleEnum : projectRolesValues) {
            projectRoles.add(projectRoleEnum.toString());
        }
        return projectRoles;
    }

}
