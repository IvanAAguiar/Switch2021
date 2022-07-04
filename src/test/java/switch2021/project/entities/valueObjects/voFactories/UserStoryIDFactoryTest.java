package switch2021.project.entities.valueObjects.voFactories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.entities.valueObjects.voFactories.voFactories.ProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voFactories.UsTitleFactory;
import switch2021.project.entities.valueObjects.voFactories.voFactories.UserStoryIDFactory;
import switch2021.project.entities.valueObjects.vos.UsTitle;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import switch2021.project.entities.valueObjects.vos.ProjectID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserStoryIDFactoryTest {

    @InjectMocks
    UserStoryIDFactory userStoryIDFactory;

    @Mock
    UserStoryID userStoryID;

    @Mock
    ProjectID projectID;

    @Mock
    UsTitle usTitle;

    @Mock
    ProjectIDFactory projectIDFactory;

    @Mock
    UsTitleFactory usTitleFactory;


//    @Test
//    @DisplayName("Test to create user story - UserStoryID - with success")
//    public void createUserStoryIDSuccess() {
//        //Arrange
//        String projID = "Project_2022_1";
//        projectID = new ProjectID(projID);
//        when(projectIDFactory.create(projID)).thenReturn(projectID);
//        String titleUS = "As a PO, i want to test this string";
//        usTitle = new UsTitle(titleUS);
//        when(usTitleFactory.create(titleUS)).thenReturn(usTitle);
//        // Act
//        userStoryID = userStoryIDFactory.create(projID, titleUS);
//        String expected = "Project_2022_Project_2022_1_As a PO, i want to test this string";
//        //Assert
//        assertEquals(expected, userStoryID.toString());
//    }

//    @Test
//    @DisplayName("Test to create user story - projectID - with success")
//    public void createUserStoryIDSuccessProjID() {
//        //Arrange
//        String projID = "Project_2022_Project_2022_Project_2022_Project_2022_1";
//        projectID = new ProjectID(projID);
//        when(projectIDFactory.create(projID)).thenReturn(projectID);
//        String titleUS = "As a PO, i want to test this string";
//        usTitle = new UsTitle(titleUS);
//        when(usTitleFactory.create(titleUS)).thenReturn(usTitle);
//        // Act
//        userStoryID = userStoryIDFactory.create(projID, titleUS);
//        //Assert
//        assertEquals("Project_2022_Project_2022_Project_2022_Project_2022_Project_2022_1", userStoryID.getProjectID().getCode());
//
//    }

    @Test
    @DisplayName("Test to create user story - usTitle - with success")
    public void createUserStoryIDSuccessUsTitle() {
        //Arrange
        String projID = "Project_2022_1";
        projectID = new ProjectID(projID);
        when(projectIDFactory.create(projID)).thenReturn(projectID);
        String titleUS = "As a PO, i want to test this string";
        usTitle = new UsTitle(titleUS);
        when(usTitleFactory.create(titleUS)).thenReturn(usTitle);
        // Act
        userStoryID = userStoryIDFactory.create(projID, titleUS);
        //Assert
        assertEquals(titleUS, userStoryID.getUsTitle().getTitleUs());
    }
}
