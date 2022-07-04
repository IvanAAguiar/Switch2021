package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    public void shouldCreateAValidDescription() {
        //Arrange
        Description description = new Description("Test");
        //Assert
        assertEquals("Test", description.getText());
    }

    @Test
    public void shouldCreateAValidDescriptionLengthLimit() {
        //Arrange
        Description description = new Description("T");
        //Assert
        assertEquals("T", description.getText());
    }

    @Test
    public void shouldThrowException_becauseDescriptionIsNull() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void shouldThrowException_becauseDescriptionIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Description(""));
    }

    @Test
    public void shouldThrowException_becauseDescriptionHigherThan() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[1000000];
            Arrays.fill(data, 'a');
            String str = new String(data);
            //Assert
            new Description(str);
        });
    }

    @Test
    public void shouldThrowException_becauseDescriptionLongerThan() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[1001];
            Arrays.fill(data, 'a');
            String str = new String(data);
            //Assert
            new Description(str);
        });
    }

    @Test
    public void shouldThrowException_becauseDescriptionLongerThan_1() {
        //Arrange
        char[] data = new char[1000];
        Arrays.fill(data, 'a');
        String str = new String(data);
        Description description = new Description(str);
        //Assert
        assertEquals(1000, description.getText().length());
    }


    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestEqual() {
        //Arrange
        Description description = new Description("Test");
        Description description2 = new Description();
        description2.setText("Test");
        //Assert
        assertEquals(description, description2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestEqual_Boolean() {
        //Arrange
        Description description = new Description("Test");
        Description description2 = new Description("Test");
        //Assert
        assertEquals(description, description2);
    }


    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNotEqual() {
        //Arrange
        Description description = new Description("Test1");
        Description description3 = new Description();
        //Assert
        assertNotEquals(description, description3);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestClass() {
        // Arrange
        Description description = new Description("Test");
        Typology test = new Typology(new TypologyID(new Description("Test")));
        //Assert
        assertNotEquals(description, test);
    }

    @Test
    public void hashCodeTestSuccess() {
        // Arrange
        Description description = new Description("Test");
        Description description2 = new Description("Test");
        //Assert
        assertEquals(description.hashCode(), description2.hashCode());
    }

    @Test
    public void hashCodeTestFail() {
        // Arrange
        Description description = new Description("Test");
        Description description2 = new Description("T");
        //Assert
        assertNotEquals(description.hashCode(), description2.hashCode());
    }

    @Test
    public void hashCodeTestFailClass() {
        // Arrange
        Description description = new Description("Test");
        Description description2 = new Description("Test123");
        //Assert
        assertNotEquals(description.hashCode(), description2.hashCode());
    }
}