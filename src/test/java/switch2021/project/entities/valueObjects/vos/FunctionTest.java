package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    @Test
    public void functionConstructorSuccess() {
        //Arrange
        String actual = "Developer";
        //Act
        Function function = new Function(actual);
        //Assert
        assertEquals(function.getText(), actual);
    }

    @Test
    public void functionConstructorSuccessUpLimit() {
        //Arrange
        String actual = "DeveloperDeveloperOO";
        //Act
        Function function = new Function(actual);
        //Assert
        assertEquals(function.getText(), actual);
    }

    @Test
    public void checkFunctionEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String actual = "";
            //Act
            new Function(actual);
        });
    }

    @Test
    public void checkFunctionEmpty1() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String actual = null;
            //Act
            new Function(actual);
        });
    }

    @Test
    public void checkNameEmpty2() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String actual = "   ";
            //Act
            new Function(actual);
        });
    }

    @Test
    public void checkFunctionMaxChars() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[21];
            Arrays.fill(data, 'a');
            String str = new String(data);
            //Assert
            new Function(str);
        });
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        Function function = new Function("Test");
        Function function1 = new Function("Test");
        Function function2 = null;
        Description test = new Description("test");
        // Act
        assertEquals(function, function1);
        assertNotEquals(function, function2);
        assertNotEquals(function, test);
        assertEquals(function, function);
    }

    @DisplayName("Test Same Value As")
    @Test
    public void sameValueAs_1() {
        //Arrange
        Function function = new Function("Function");
        Function function1 = new Function("Function");
        //Assert
        assertTrue(function.sameValueAs(function1));
    }

    @DisplayName("Test Same Value As")
    @Test
    public void sameValueAs_2() {
        //Arrange
        Function function = new Function("Function");
        Function function1 = new Function("Function1");
        //Assert
        assertFalse(function.sameValueAs(function1));
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void equalsTest_Boolean() {
        //Arrange
        Function function = new Function("Function");
        Function function1 = new Function("Function");
        //Assert
        assertEquals(function, function1);
    }



    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Success() {
        //Arrange
        Function function = new Function("Test");
        Function function1 = new Function("Test");
        //Act and Assert
        assertEquals(function.hashCode(), function1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Fail() {
        //Arrange
        Function function = new Function("Test");
        Typology function1 = new Typology(new TypologyID(new Description("Test")));
        //Act and Assert
        assertNotEquals(function.hashCode(), function1.hashCode());
    }

    @Test
    @DisplayName("Test check minimum length conditions for coverage purposes")
    void MinimumLengthTest() {
        //Arrange
        Function function = new Function("Te");
        Function function1 = new Function("Te");
        //Act and Assert
        assertEquals(function.getText(), function1.getText());
    }

    @Test
    @DisplayName("Test check maximum length conditions for coverage purposes")
    void MaximumLengthTest() {
        //Arrange
        Function function = new Function("12345678998765432123");
        Function function1 = new Function("12345678998765432123");
        //Act and Assert
        assertEquals(function.getText(), function1.getText());
    }
}