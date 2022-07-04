package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttachmentTest {


    @Test
    @DisplayName("Validate that attachment extension fail - not allowed")
    public void attachmentWithExtensionNotAllowed() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String filename = "test.xxx";
            //Act
            new Attachment(filename);
        });
    }

    @Test
    @DisplayName("Validate that attachment extension is correct")
    public void attachmentWithCorrectExtension() {
        //Arrange
        String filename = "test.pdf";
        //Act
        Attachment attachment = new Attachment(filename);
        //Assert
        assertEquals(filename, attachment.getExtension());
    }

    @Test
    @DisplayName("Validate that attachment extension is correct")
    public void attachmentWithCorrectExtensionAnotherExtension() {
        //Arrange
        String filename = "test.txt";
        //Act
        Attachment attachment = new Attachment(filename);
        //Assert
        assertEquals(filename, attachment.getExtension());
        assertNotNull(attachment);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest_1() {
        //Arrange
        String filename = "test.txt";
        //Act
        Attachment attachment = new Attachment(filename);
        //Assert
        assertEquals(attachment, attachment);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest_2() {
        //Arrange
        String filename = "test.txt";
        //Act
        Attachment attachment = new Attachment(filename);
        Attachment attachment1 = new Attachment(filename);
        //Assert
        assertEquals(attachment, attachment1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest_3() {
        //Arrange
        String filename1 = "test1.txt";
        String filename2 = "test2.txt";
        //Act
        Attachment attachment = new Attachment();
        attachment.setExtension(filename1);
        Attachment attachment1 = new Attachment();
        attachment1.setExtension(filename2);
         //Assert
        assertNotEquals(attachment, attachment1);
    }


    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest_4() {
        //Arrange
        String filename1 = "test1.txt";
        //Act
        Attachment attachment = new Attachment(filename1);
        Attachment attachment1 = new Attachment(filename1);
        //Assert
        assertEquals(attachment, attachment1);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess_1() {
        //Arrange
        String filename = "Test.txt";
        //Act
        Attachment attachment = new Attachment(filename);
        Attachment attachment1 = new Attachment(filename);
        //Assert
        assertEquals(attachment.hashCode(), attachment1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess_2() {
        //Arrange
        String filename1 = "Test.txt";
        String filename2 = "Test.txt";
        //Act
        Attachment attachment = new Attachment(filename1);
        Attachment attachment1 = new Attachment(filename2);
        //Assert
        assertEquals(attachment.hashCode(), attachment1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeFail() {
        //Arrange
        String filename = "Test1.txt";
        String filename1 = "Test2.txt";
        //Act
        Attachment attachment = new Attachment(filename);
        Attachment attachment1 = new Attachment(filename1);
        //Assert
        assertNotEquals(attachment, attachment1);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeNull() {
        //Arrange
        String filename = "Test1.txt";
        //Act
        Attachment attachment = new Attachment(filename);
        Attachment attachment1 = null;
        //Assert
        assertNotEquals(attachment, attachment1);
    }

}

