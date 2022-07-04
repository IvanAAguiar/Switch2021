package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.UserStoryOfSprintDTO;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UsTitle;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserStoryOfSprintMapperTest {

    @Test
    void toCollectionDTO() {
        //Arrange
        UserStoryOfSprint storyOfSprint = mock(UserStoryOfSprint.class);
        List<UserStoryOfSprint> userStoryOfSprintList = new ArrayList<>();
        userStoryOfSprintList.add(storyOfSprint);
        UserStoryID id = mock(UserStoryID.class);
        when(storyOfSprint.getUserStoryId()).thenReturn(id);
        ProjectID projectID = mock(ProjectID.class);
        when(id.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(id.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("title");
        UserStoryOfSprintStatus status = UserStoryOfSprintStatus.Todo;
        when(storyOfSprint.getUserStoryOfSprintStatus()).thenReturn(status);
        when(storyOfSprint.getSprintName()).thenReturn("name");
        UserStoryOfSprintMapper mapper = new UserStoryOfSprintMapper();
        //Act
        CollectionModel<UserStoryOfSprintDTO> result = mapper.toCollectionDTO(userStoryOfSprintList);
        //Assert
        assertEquals(1, result.getContent().size());
    }
}