package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.OutputUserStoryDTO;
import switch2021.project.entities.aggregates.UserStory.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryStatusEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserStoryMapperTest {

    @InjectMocks
    private UserStoryMapper mapper;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Transform newUserStory into DTO")
    public void ToDtoNewUserStory() {
        //Arrange
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        //Act
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);
        when(newUserStory.getUsStatus()).thenReturn(UserStoryStatusEnum.OPEN);

        OutputUserStoryDTO outputUserStoryDTO = mapper.toDto(newUserStory);

        //Assert
        assertEquals(newUserStory.getPriority().getPriorityUs(), outputUserStoryDTO.priority);
        assertEquals(newUserStory.getDescription().getText(), outputUserStoryDTO.description);
        assertEquals(newUserStory.getTimeEstimate().getUsHours(), outputUserStoryDTO.timeEstimate);
    }

    @Test
    @DisplayName("Transform newUserStory into DTO")
    public void ToDtoNewUserStoryProjectID() {
        //Arrange
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);

        //Act
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);
        when(newUserStory.getUsStatus()).thenReturn(UserStoryStatusEnum.OPEN);

        OutputUserStoryDTO outputUserStoryDTO = mapper.toDto(newUserStory);

        //Assert
        assertEquals(projectID.getCode(), outputUserStoryDTO.projectID);

    }

    @Test
    @DisplayName("Transform newUserStory into DTO")
    public void ToDtoNewUserStoryUsTitle() {
        //Arrange
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        //Act
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);
        when(newUserStory.getUsStatus()).thenReturn(UserStoryStatusEnum.OPEN);

        OutputUserStoryDTO outputUserStoryDTO = mapper.toDto(newUserStory);

        //Assert
        assertEquals(usTitle.getTitleUs(), outputUserStoryDTO.usTitle);
    }

    @Test
    @DisplayName("Validate data from outputDTO")
    public void getInfoFromOutPutUsDTO() {
        //Arrange
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        //Act
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);
        when(newUserStory.getUsStatus()).thenReturn(UserStoryStatusEnum.OPEN);

        OutputUserStoryDTO outputUserStoryDTO = mapper.toDto(newUserStory);

        //Assert
        assertEquals(outputUserStoryDTO.projectID,"Project_2022_1" );
        assertEquals(outputUserStoryDTO.usTitle,"As a PO, i want to test this string" );
        assertEquals(outputUserStoryDTO.getPriority(),1 );
        assertEquals(outputUserStoryDTO.getDescription(),"Make Test" );
        assertEquals(outputUserStoryDTO.getTimeEstimate(),5.0 );
    }

    @Test
    public void toCollectioModel() {
        //Arrange
        UserStory newUserStory = mock(UserStory.class);
        UserStory parent = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(parent.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(parent.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(parent.getDescription()).thenReturn(description);
        when(newUserStory.getUsRefined()).thenReturn(LocalDate.parse("2022-12-12"));
        when(parent.getUsRefined()).thenReturn(LocalDate.parse("2022-12-12"));
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(parent.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);
        when(newUserStory.getParentUserStory()).thenReturn(parent);
        when(newUserStory.getUsStatus()).thenReturn(UserStoryStatusEnum.OPEN);
        List<UserStory> userStories = new ArrayList<>();
        userStories.add(newUserStory);
        //Act
        CollectionModel<OutputUserStoryDTO> result = mapper.toCollectionDto(userStories);
        //Assert
        assertEquals(1, result.getContent().size());
    }
}

