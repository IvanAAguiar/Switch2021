package switch2021.project.entities.aggregates.Resource;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManagementResourcesService {

    public List<Resource> currentResourcesByDate(List<Resource> resourcesInProject) {
        List<Resource> currentResources = new ArrayList<>();

        for (Resource res : resourcesInProject) {
            if (res.isActiveToThisDate(LocalDate.now())) {
                currentResources.add(res);
            }
        }
        return currentResources;
    }


    public boolean validateAllocation(List<Resource> resourceProjectsList, String startDate,
                                      String endDate, double percentageOfAllocation) {
        double sum = 0;
        boolean msg = false;

        for (Resource res : resourceProjectsList) {
            if (res.isActiveToThisDate(LocalDate.parse(startDate)) || res.isActiveToThisDate(LocalDate.parse(endDate))) {
                sum = sum + res.getAllocation().getPercentage();
            }
        }
        if ((sum + percentageOfAllocation) <= 1) {
            msg = true;
        }
        return msg;
    }


    public boolean validateProjectRole(List<Resource> projectTeamList, String startDate,
                                       String endDate, String projectRole) {
        boolean msg = true;

        for (Resource res : projectTeamList) {
            if (res.isActiveToThisDate(LocalDate.parse(startDate)) || res.isActiveToThisDate(LocalDate.parse(endDate))) {
                if (res.hasProjectRole(projectRole)) {
                    msg = res.getRole().equals(ProjectRole.TeamMember);
                }
            }
        }
        return msg;
    }


    public List<ProjectID> listProjectsOfResources(List<Resource> currentUserResources) {
        List<ProjectID> resourceProjects = new ArrayList<>();

        for (Resource res : currentUserResources) {
            ProjectID projId = res.getId().getProject();

            resourceProjects.add(projId);
        }
        return resourceProjects;
    }
}