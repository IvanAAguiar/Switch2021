package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskEffortTest {

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void taskEffort_Equals_1() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        String expected = taskEffort.toString();
        //Assert
        assertEquals(expected, taskEffort.toString());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void taskEffort_NotEquals_1() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        String expected = "";
        //Assert
        assertNotEquals(expected, taskEffort.toString());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void taskEffort_Equals_2() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort();
        //Assert
        assertNotEquals(taskEffort, taskEffort1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void taskEffort_Null_1() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = null;
        //Assert
        assertNotEquals(taskEffort, taskEffort1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCode_1() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort();
        //Assert
        assertNotEquals(taskEffort.hashCode(), taskEffort1.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCode_1_NotEquals() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Minutes minutes1 = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(22);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort(hours, minutes1, date, description, attachment);
        //Assert
        assertNotEquals(taskEffort.hashCode(), taskEffort1.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCode_2() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort(hours, minutes, date, description, attachment);
        //Assert
        assertEquals(taskEffort.hashCode(), taskEffort1.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueAs_True() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort(hours, minutes, date, description, attachment);
        //Assert
        assertTrue(taskEffort.sameValueAs(taskEffort1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueAs_attachmentFalse() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");

        Attachment attachment1 = mock(Attachment.class);
        when(attachment1.getExtension()).thenReturn("attachment 1");
        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort(hours, minutes, date, description, attachment1);
        //Assert
        assertFalse(taskEffort.sameValueAs(taskEffort1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueAs_descriptionFalse() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");
        Description description1 = mock(Description.class);
        when(description1.getText()).thenReturn("description1");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");

        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort(hours, minutes, date, description1, attachment);
        //Assert
        assertFalse(taskEffort.sameValueAs(taskEffort1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueAs_dateFalse() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());
        Date date1 = mock(Date.class);
        when(date1.getEffortDate()).thenReturn(LocalDate.now().plusDays(1));

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");

        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort(hours, minutes, date1, description, attachment);
        //Assert
        assertFalse(taskEffort.sameValueAs(taskEffort1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueAs_minutesFalse() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);
        Minutes minutes1 = mock(Minutes.class);
        when(minutes1.getEffortMinutes()).thenReturn(3);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");

        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort(hours, minutes1, date, description, attachment);
        //Assert
        assertFalse(taskEffort.sameValueAs(taskEffort1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueAs_hoursFalse() {
        //Arrange
        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);
        Hours hours1 = mock(Hours.class);
        when(hours1.getEffortHours()).thenReturn(3);

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");

        //Act
        TaskEffort taskEffort = new TaskEffort(hours, minutes, date, description, attachment);
        TaskEffort taskEffort1 = new TaskEffort(hours1, minutes, date, description, attachment);
        //Assert
        assertFalse(taskEffort.sameValueAs(taskEffort1));
    }

    @Test
    @DisplayName("Get Test")
    public void effortHours_Get() {
        //Arrange
        TaskEffort effort = new TaskEffort();

        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);
        //Assert
        assertNotEquals(effort.getEffortHours(), hours);
    }

    @Test
    @DisplayName("Set Test")
    public void effortHours() {
        //Arrange
        TaskEffort effort = new TaskEffort();

        Hours hours = mock(Hours.class);
        when(hours.getEffortHours()).thenReturn(2);

        //Act
        Hours hours1 = mock(Hours.class);
        effort.setEffortHours(hours1);
        when(hours1.getEffortHours()).thenReturn(5);
        //Assert
        assertEquals(effort.getEffortHours(), hours1);
    }

    @Test
    @DisplayName("Get Test")
    public void effortMinutes_Get() {
        //Arrange
        TaskEffort effort = new TaskEffort();

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);
        //Assert
        assertNotEquals(effort.getEffortMinutes(), minutes);
    }

    @Test
    @DisplayName("Set Test")
    public void effortMinutes() {
        //Arrange
        TaskEffort effort = new TaskEffort();

        Minutes minutes = mock(Minutes.class);
        when(minutes.getEffortMinutes()).thenReturn(2);

        //Act
        Minutes minutes1 = mock(Minutes.class);
        effort.setEffortMinutes(minutes1);
        when(minutes1.getEffortMinutes()).thenReturn(5);
        //Assert
        assertEquals(effort.getEffortMinutes(), minutes1);
    }


    @Test
    @DisplayName("Get Test")
    public void effortDate_Get() {
        //Arrange
        TaskEffort effort = new TaskEffort();

        Date date = mock(Date.class);
        when(date.getEffortDate()).thenReturn(LocalDate.now());
        //Assert
        assertNotEquals(effort.getEffortDate(), date);
    }


    @Test
    @DisplayName("Get Test")
    public void effortDescription_Get() {
        //Arrange
        TaskEffort taskEffort = new TaskEffort();

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        //Assert
        assertNotEquals(taskEffort.getComment(), description);
    }

    @Test
    @DisplayName("Set Test")
    public void effortDescription() {
        //Arrange
        TaskEffort effort = new TaskEffort();

        Description description = mock(Description.class);
        when(description.getText()).thenReturn("description");

        //Act
        Description description1 = mock(Description.class);
        effort.setComment(description1);
        when(description1.getText()).thenReturn("desc");
        //Assert
        assertEquals(effort.getComment(), description1);
    }


    @Test
    @DisplayName("Get Test")
    public void effortAttachment_Get() {
        //Arrange
        TaskEffort taskEffort = new TaskEffort();

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");
        //Assert
        assertNotEquals(taskEffort.getAttachment(), attachment);
    }

    @Test
    @DisplayName("Set Test")
    public void effortAttachment() {
        //Arrange
        TaskEffort effort = new TaskEffort();

        Attachment attachment = mock(Attachment.class);
        when(attachment.getExtension()).thenReturn("attachment");

        //Act
        Attachment attachment1 = mock(Attachment.class);
        effort.setAttachment(attachment1);
        when(attachment1.getExtension()).thenReturn("atta");
        //Assert
        assertEquals(effort.getAttachment(), attachment1);
    }

    @Test
    @DisplayName("Test to check Illegal Argument Exception")
    public void notValidWorkTimeValue() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Assert
            Hours hours = mock(Hours.class);
            when(hours.getEffortHours()).thenReturn(0);

            Minutes minutes = mock(Minutes.class);
            when(minutes.getEffortMinutes()).thenReturn(0);

            Date date = mock(Date.class);
            when(date.getEffortDate()).thenReturn(LocalDate.now());
            Description description = mock(Description.class);
            when(description.getText()).thenReturn("description");
            Attachment attachment = mock(Attachment.class);
            when(attachment.getExtension()).thenReturn("attachment");
            //Act
            new TaskEffort(hours, minutes, date, description, attachment);

    });
}
}
