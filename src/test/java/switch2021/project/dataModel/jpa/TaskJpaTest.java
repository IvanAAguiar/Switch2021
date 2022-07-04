package switch2021.project.dataModel.jpa;

import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.JPA.TaskJpa;
import switch2021.project.entities.valueObjects.vos.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskJpaTest {

    @Test
    void setTaskID() {
        //Arrange
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        Description sprintName = mock(Description.class);
        Description taskTitle = mock(Description.class);
        UserID id = mock(UserID.class);
        List<TaskID> taskIDList = new ArrayList<>();
        TaskJpa taskJpa = new TaskJpa();
        //Act
        taskJpa.setTaskID("TaskID");
        taskJpa.setProjectID(projectID);
        taskJpa.setUsTitle(usTitle);
        taskJpa.setSprintName(sprintName);
        taskJpa.setTaskTitle(taskTitle);
        taskJpa.setTaskDescription("description");
        taskJpa.setTaskType("type");
        taskJpa.setTaskStatus("status");
        taskJpa.setTaskEffortEstimate(20.2);
        taskJpa.setTaskStartDate("2022-12-12");
        taskJpa.setTaskEndDate("2023-12-12");
        taskJpa.setResourceUserID(id);
        taskJpa.setResourceStartDate("2022-12-10");
        taskJpa.setTaskEffortList(new ArrayList<>());
        taskJpa.setTaskPrecedenceList(taskIDList);
        //Assert
        assertEquals("TaskID", taskJpa.getTaskID());
    }
}