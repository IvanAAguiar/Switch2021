package switch2021.project.entities.valueObjects.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectRoleTest {

    @DisplayName("Test to compare two lists of project roles values")
    @Test
    public void getProjectRoleReengDescription() {
        //Act
        List<String> statusList1 = ProjectRole.getProjectRole();
        List<String> statusList2 = List.of(new String[]{"ProjectManager", "ProductOwner", "ScrumMaster", "TeamMember"});
        //Assert
        assertEquals(statusList1, statusList2);
    }

    @DisplayName("Test to get a specific value - Project Manager")
    @Test
    public void value_ProjectManager() {
        //Act
        ProjectRole[] status = ProjectRole.values();
        ProjectRole projectManager = status[0];
        //Assert
        assertEquals(ProjectRole.ProjectManager, projectManager);

    }


    @DisplayName("Test to get a specific value - Product Owner")
    @Test
    public void value_ProductOwner() {
        //Act
        ProjectRole[] status = ProjectRole.values();
        ProjectRole productOwner = status[1];
        //Assert
        assertEquals(ProjectRole.ProductOwner, productOwner);

    }


    @DisplayName("Test to get a specific value - Scrum Master")
    @Test
    public void value_ScrumMaster() {
        //Act
        ProjectRole[] status = ProjectRole.values();
        ProjectRole scrumMaster = status[2];
        //Assert
        assertEquals(ProjectRole.ScrumMaster, scrumMaster);

    }


    @DisplayName("Test to get a specific value - Team Member")
    @Test
    public void value_TeamMember() {
        //Act
        ProjectRole[] status = ProjectRole.values();
        ProjectRole teamMember = status[3];
        //Assert
        assertEquals(ProjectRole.TeamMember, teamMember);

    }

    @DisplayName("Test to get a specific value - Fail")
    @Test
    public void value_Fail() {
        //Act
        ProjectRole[] status = ProjectRole.values();
        ProjectRole teamMember = status[3];
        //Assert
        assertNotEquals(ProjectRole.ScrumMaster, teamMember);

    }


    @DisplayName("Test to valueOf - Project Manager")
    @Test
    public void valueOf_ProjectManager() {
        //Act
        ProjectRole projectRole = ProjectRole.valueOf("ProjectManager");
        //Assert
        assertEquals(ProjectRole.ProjectManager, projectRole);
    }


    @DisplayName("Test to valueOf - Product Owner")
    @Test
    public void valueOf_ProductOwner() {
        //Act
        ProjectRole projectRole = ProjectRole.valueOf("ProductOwner");
        //Assert
        assertEquals(ProjectRole.ProductOwner, projectRole);
    }


    @DisplayName("Test to valueOf - Scrum Master")
    @Test
    public void valueOf_ScrumMaster() {
        //Act
        ProjectRole projectRole = ProjectRole.valueOf("ScrumMaster");
        //Assert
        assertEquals(ProjectRole.ScrumMaster, projectRole);
    }



    @DisplayName("Test to valueOf - Team Member")
    @Test
    public void valueOf_TeamMember() {
        //Act
        ProjectRole projectRole = ProjectRole.valueOf("TeamMember");
        //Assert
        assertEquals(ProjectRole.TeamMember, projectRole);
    }


}
