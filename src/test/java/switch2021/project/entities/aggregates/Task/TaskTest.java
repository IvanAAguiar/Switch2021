package switch2021.project.entities.aggregates.Task;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.TaskStatus;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskTest {

    @Test
    @DisplayName("Create Task with success")
    void CreateTaskWithSuccess() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        //Act
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        //Assert
        assertEquals(taskID, task.getTaskID());
    }


    @Test
    @DisplayName("Create Task with success and get info")
    void getInformationFromTask() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        TaskStatus taskStatus = mock(TaskStatus.class);
        List<TaskEffort> taskEffortList = new ArrayList<>();
        List<TaskID> precedenceList = new ArrayList<>();
        TaskEffort taskEffort = mock(TaskEffort.class);

        //Act
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        task.setStatus(taskStatus);
        task.setStartDate(LocalDate.now());
        task.setEndDate(LocalDate.now());
        task.setRegisteredEfforts(taskEffortList);
        taskEffortList.add(taskEffort);
        task.setPrecedenceList(precedenceList);
        precedenceList.add(taskID);
        //Assert
        assertEquals(description, task.getDescription());
        assertEquals(effortEstimate, task.getEffortEstimate());
        assertEquals(taskTypeEnum, task.getType());
        assertEquals(resourceID, task.getResponsible());
        assertEquals(taskStatus, task.getStatus());
        assertEquals(LocalDate.now(), task.getEndDate());
        assertEquals(LocalDate.now(), task.getStartDate());
        assertEquals(taskEffortList.size(), task.getRegisteredEfforts().size());
        assertEquals(precedenceList.size(), task.getPrecedenceList().size());
    }

    @Test
    @DisplayName("Validate if task has responsible - False result")
    void hasResponsibleFalse() {
        //Arrange
        ResourceID resp = mock(ResourceID.class);
        TaskID taskID = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        //Act
        boolean expected = task.hasResponsible(resp);
        //Assert
        assertFalse(expected);
    }

    @Test
    @DisplayName("Validate if task has responsible - True result")
    void hasResponsibleTrue() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        //Act
        boolean expected = task.hasResponsible(resourceID);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Validate add Effort - with finish status")
    void toAddEffortFailFinish() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TaskID taskID = mock(TaskID.class);
            Description description = mock(Description.class);
            EffortEstimate effortEstimate = mock(EffortEstimate.class);
            TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
            ResourceID resourceID = mock(ResourceID.class);
            Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);

            TaskEffort taskEffort = mock(TaskEffort.class);
            task.setStatus(TaskStatus.FINISHED);

            List<TaskEffort> taskEffortList = new ArrayList<>();
            taskEffortList.add(taskEffort);
            task.setRegisteredEfforts(taskEffortList);
            //Act
            task.toAddEffort(taskEffort);
        });
    }

    @Test
    @DisplayName("Validate add Effort - with blocked status")
    void toAddEffortFailBlocked() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TaskID taskID = mock(TaskID.class);
            Description description = mock(Description.class);
            EffortEstimate effortEstimate = mock(EffortEstimate.class);
            TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
            ResourceID resourceID = mock(ResourceID.class);
            Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);

            TaskEffort taskEffort = mock(TaskEffort.class);
            task.setStatus(TaskStatus.BLOCKED);

            List<TaskEffort> taskEffortList = new ArrayList<>();
            taskEffortList.add(taskEffort);
            task.setRegisteredEfforts(taskEffortList);
            //Act
            task.toAddEffort(taskEffort);
        });
    }

    @Test
    @DisplayName("Validate add Effort - with cancelled status")
    void toAddEffortFailCancelled() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TaskID taskID = mock(TaskID.class);
            Description description = mock(Description.class);
            EffortEstimate effortEstimate = mock(EffortEstimate.class);
            TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
            ResourceID resourceID = mock(ResourceID.class);
            Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);

            TaskEffort taskEffort = mock(TaskEffort.class);
            task.setStatus(TaskStatus.CANCELLED);

            List<TaskEffort> taskEffortList = new ArrayList<>();
            taskEffortList.add(taskEffort);
            task.setRegisteredEfforts(taskEffortList);
            //Act
            task.toAddEffort(taskEffort);
        });
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        Task task2 = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        //Act
        int taskID1 = task.hashCode();
        int taskID2 = task2.hashCode();
        //Assert
        assertEquals(taskID1, taskID2);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeFail() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        TaskID taskID0 = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        Task task2 = new Task(taskID0, description, effortEstimate, taskTypeEnum, resourceID);
        //Act
        int taskID1 = task.hashCode();
        int taskID2 = task2.hashCode();
        //Assert
        assertNotEquals(taskID1, taskID2);
    }


    @Test
    @DisplayName("Test same identity conditions for coverage purposes")
    public void sameIdentityAsTrue() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        Task task2 = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        //Act
        when(taskID.sameValueAs(taskID)).thenReturn(true);
        boolean expected = task.sameIdentityAs(task2);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test same identity conditions for coverage purposes")
    public void sameIdentityAsFalse() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        Task task2 = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        //Act
        when(taskID.sameValueAs(taskID)).thenReturn(false);
        boolean expected = task.sameIdentityAs(task2);
        //Assert
        assertFalse(expected);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestTrue() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        TaskID taskID2 = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        Task task2 = new Task(taskID2, description, effortEstimate, taskTypeEnum, resourceID);
        //Act
        when(taskID.sameValueAs(taskID2)).thenReturn(true);
        boolean expected = task.equals(task2);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFalse() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        TaskID taskID2 = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        Task task2 = new Task(taskID2, description, effortEstimate, taskTypeEnum, resourceID);
        //Act
        boolean expected = task.equals(task2);
        //Assert
        assertFalse(expected);
    }
    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNull() {
        //Arrange
        TaskID taskID = mock(TaskID.class);
        TaskID taskID2 = mock(TaskID.class);
        Description description = mock(Description.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        TaskTypeEnum taskTypeEnum = mock(TaskTypeEnum.class);
        ResourceID resourceID = mock(ResourceID.class);
        Task task = new Task(taskID, description, effortEstimate, taskTypeEnum, resourceID);
        Task task2 = null;
        //Act
        boolean expected = task.equals(task2);
        //Assert
        assertFalse(expected);
    }

}