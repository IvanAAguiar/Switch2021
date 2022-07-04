package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserIDTest {

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        UserID pwd = new UserID(new Email("oliveira@beaver.com"));
        UserID pwd1 = new UserID(new Email("oliveira@beaver.com"));
        UserID pwd2 = null;
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
        UserID pwd = new UserID(new Email("oliveira@beaver.com"));
        UserID pwd1 = new UserID(new Email("oliveira@beaver.com"));
        //Act and Assert
        assertEquals(pwd.hashCode(),pwd1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Fail(){
        //Arrange
        UserID pwd = new UserID(new Email("oliveira@beaver.com"));
        Name pwd1 = new Name("Test");
        //Act and Assert
        assertNotEquals(pwd.hashCode(),pwd1.hashCode());
    }

    @Test
    @DisplayName("Same Value As - True")
    void sameValueAs_False(){
        //Arrange
        UserID user1 = new UserID(new Email("oliveira@beaver.com"));
        UserID user2 = new UserID(new Email("maria@beaver.com"));
        //Act and Assert
        assertFalse(user1.sameValueAs(user2));
    }

    @Test
    @DisplayName("Same Value As - True")
    void sameValueAs_True(){
        //Arrange
        UserID user1 = new UserID(new Email("oliveira@beaver.com"));
        UserID user2 = new UserID(new Email("oliveira@beaver.com"));
        //Act and Assert
        assertTrue(user1.sameValueAs(user2));
    }
}
