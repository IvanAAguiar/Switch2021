package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhotoTest {

    @Test
    @DisplayName("Validate that photo extension fail")
    public void photoWithExtensionNotAllowed() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String photo = "photo.xxx";
            //Act
            new Photo(photo);
        });
    }

    @Test
    @DisplayName("Validate that photo has a correct extension")
    public void photoWithCorrectExtension_JPG() {
        //Arrange
        String photo = "photo.jpg";
        //Act
        Photo x = new Photo(photo);
        //Assert
        assertEquals(photo, x.getExtension());
    }

    @Test
    @DisplayName("Validate that photo has a correct extension")
    public void photoWithCorrectExtension_PNG() {
        //Arrange
        String photo = "photo.png";
        //Act
        Photo y = new Photo(photo);
        //Assert
        assertEquals(photo, y.getExtension());
    }

    @Test
    @DisplayName("Validate that attachment extension is correct")
    public void photoWithNullExtension() {
        //Arrange
        String photo = "photo.jpg";
        //Act
        Photo x = new Photo(photo);
        //Assert
        assertNotNull(x);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        Photo pwd = new Photo("Photo.png");
        Photo pwd1 = new Photo("Photo.png");
        Photo pwd2 = null;
        Description test = new Description("test");
        // Act
        assertEquals(pwd,pwd1);
        assertNotEquals(pwd, pwd2);
        assertNotEquals(pwd, test);
        assertEquals(pwd,pwd);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Success(){
        //Arrange
        Photo pwd = new Photo("Photo.png");
        Photo pwd1 = new Photo("Photo.png");
        //Act and Assert
        assertEquals(pwd.hashCode(),pwd1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Fail(){
        //Arrange
        Photo pwd = new Photo("Photo.png");
        Name pwd1 = new Name("Test");
        //Act and Assert
        assertNotEquals(pwd.hashCode(),pwd1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void sameValueAs_Success(){
        //Arrange
        Photo pwd = new Photo("Photo.png");
        Photo pwd1 = new Photo("Photo.png");
        //Act and Assert
        assertTrue(pwd.sameValueAs(pwd1));
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void sameValueAs_False(){
        //Arrange
        Photo pwd = new Photo("Photo.png");
        Photo pwd1 = new Photo("Photo1.png");
        //Act and Assert
        assertFalse(pwd.sameValueAs(pwd1));
    }
}
