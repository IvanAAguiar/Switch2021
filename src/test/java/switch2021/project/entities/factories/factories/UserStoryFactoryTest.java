package switch2021.project.entities.factories.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dtoModel.dto.UserStoryDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.aggregates.UserStory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class UserStoryFactoryTest {


    @Mock
    private IUserStoryIDFactory usIDF;
    @Mock
    private IUsPriorityFactory priorityF;
    @Mock
    private IDescriptionFactory descriptionF;
    @Mock
    private IUsHourFactory timeEstimateF;
    @Mock
    private UserStoryDTO userStoryDTO;
    @Mock
    private IProjectIDFactory projectIDF;
    @Mock
    private IUsTitleFactory usTitleF;
    @Mock
    private UsPriority priority;
    @Mock
    private UsTitle title;
    @Mock
    private Description description;
    @Mock
    private UsHour timeEstimate;
    @Mock
    private ProjectID projectID;
    @Mock
    private UserStoryID usID;

    @InjectMocks
    private UserStoryFactory userStoryFactory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createUserStory - with success")
    void createUserStoryWithSuccessGetTitle() {
        //Arrange
        when(projectIDF.create(userStoryDTO.projectID)).thenReturn(projectID);
        when(usTitleF.create(userStoryDTO.title)).thenReturn(title);
        when(descriptionF.createDescription(userStoryDTO.description)).thenReturn(description);
        when(timeEstimateF.create(userStoryDTO.timeEstimate)).thenReturn(timeEstimate);
        when(priorityF.create(userStoryDTO.priority)).thenReturn(priority);
        when(usIDF.create(userStoryDTO.projectID, userStoryDTO.title)).thenReturn(usID);
        //Act
        UserStory newUserStory = userStoryFactory.createUserStory(userStoryDTO);
        //Assert
        assertNotNull(newUserStory);
    }

}
