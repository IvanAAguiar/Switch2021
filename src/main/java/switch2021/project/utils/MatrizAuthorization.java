package switch2021.project.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
@Getter
public class MatrizAuthorization {

    List<String> seeUsers = List.of("Administrator", "Director");
    List<String> seeProjects = List.of("User", "Director");
    List<String> seeAndEditProfiles = List.of("Administrator");
    List<String> seeAndEditTypologies = List.of("Administrator", "Director");

    List<String> editUsers = List.of("Administrator", "Visitor", "User");
    List<String> editUserProfiles = List.of("Administrator");
    List<String> createRequestUser = List.of("User");
    List<String> editProfiles = List.of("Administrator");
    List<String> editTypologies = List.of("Administrator", "Director");
    List<String> createProjects = List.of("Director");
    List<String> editProjects = List.of("Director", "ProjectManager");
    List<String> createResources = List.of("Director");
    List<String> editResources = List.of("Director", "ProjectManager");
    List<String> seeResources = List.of("ProjectManager");
    List<String> createUserStories = List.of("ProductOwner");
    List<String> editUserStories = List.of("ProductOwner");
    List<String> seeUserStories = List.of("TeamMember", "ScrumMaster", "ProductOwner", "ProjectManager");
    List<String> seeSprints = List.of("TeamMember", "ScrumMaster", "ProductOwner", "ProjectManager");
    List<String> createSprint = List.of("ProjectManager");
    List<String> startSprint = List.of("ProjectManager");
    List<String> addUSToSprint = List.of("TeamMember", "ScrumMaster");
    List<String> seeUSOfSprint = List.of("TeamMember", "ScrumMaster", "ProductOwner");
    List<String> editUSOfSprint = List.of("TeamMember", "ScrumMaster");
    List<String> seeTasks = List.of("TeamMember", "ScrumMaster", "ProductOwner", "ProjectManager");
    List<String> createTasks = List.of("TeamMember", "ScrumMaster");
    List<String> editTasks = List.of("TeamMember", "ScrumMaster");

}
