package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;
import static org.junit.jupiter.api.Assertions.*;

public class UsPriorityTest {

    @Test
    @DisplayName("Validate that priority is correct - limit bound")
    public void usPrioritySuccessLimit1() {
        //Arrange
        int priority = 0;
        //Act
        UsPriority usPriority = new UsPriority(priority);
        //Assert
        assertEquals(priority, usPriority.getPriorityUs());
    }

    @Test
    @DisplayName("Validate that priority is correct - limit bound")
    public void usPrioritySuccessLimit2() {
        //Arrange
        int priority2 = 5;
        //Act
        UsPriority usPriority2 = new UsPriority(priority2);
        //Assert
        assertEquals(priority2, usPriority2.getPriorityUs());
    }

    @Test
    @DisplayName("Validate that priority is correct - other values")
    public void usPrioritySuccessInBound() {
        //Arrange
        int priority = 3;
        //Act
        UsPriority usPriority = new UsPriority(priority);
        //Assert
        assertEquals(priority, usPriority.getPriorityUs());
    }

    @Test
    @DisplayName("Validate that priority is correct - fail")
    public void usPriorityFailOutBoundNegative() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            int priority = -1;
            //Act
            new UsPriority(priority);
        });
    }

    @Test
    @DisplayName("Validate that priority is correct - fail")
    public void usPriorityFailOutBoundMoreThanFive() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            int priority2 = 6;
            //Act
            new UsPriority(priority2);
        });
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange
        UsPriority usPriority = new UsPriority(1);
        //Act and Assert
        assertEquals(usPriority, usPriority);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestSuccess() {
        //Arrange
        UsPriority usPriority = new UsPriority(1);
        UsPriority usPriority1 = new UsPriority(1);
        //Act and Assert
        assertEquals(usPriority, usPriority1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void compareToTestZero() {
        //Arrange
        UsPriority usPriority = new UsPriority(1);
        UsPriority usPriority1 = new UsPriority(1);
        //Act and Assert
        assertEquals(0, usPriority.compareTo(usPriority1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void compareToTestOne() {
        //Arrange
        UsPriority usPriority = new UsPriority(2);
        UsPriority usPriority1 = new UsPriority(1);
        //Act and Assert
        assertEquals(1, usPriority.compareTo(usPriority1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void compareToTestMinusOne() {
        //Arrange
        UsPriority usPriority = new UsPriority(1);
        UsPriority usPriority1 = new UsPriority(2);
        //Act and Assert
        assertEquals(-1, usPriority.compareTo(usPriority1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail_1() {
        //Arrange
        UsPriority usPriority = new UsPriority(1);
        UsPriority usPriority1 = new UsPriority(2);
        //Act and Assert
        assertNotEquals(usPriority, usPriority1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail_2() {
        //Arrange
        UsPriority usPriority = new UsPriority(2);
        Typology budget1 =  new Typology(new TypologyID(new Description("Test")));
        //Act and Assert
        assertNotEquals(usPriority, budget1);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess() {
        //Arrange
        UsPriority usPriority = new UsPriority(1);
        UsPriority usPriority1 = new UsPriority(1);
        //Act and Arrange
        assertEquals(usPriority.hashCode(), usPriority1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeFail() {
        //Arrange
        UsPriority usPriority1 = new UsPriority(2);
        UsPriority usPriority = new UsPriority(4);
        //Act and Arrange
        assertNotEquals(usPriority.hashCode(), usPriority1.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueOf() {
        //Arrange
        UsPriority usPriority = new UsPriority(1);
        UsPriority usPriority1 = new UsPriority(2);
        //Act and Assert
        assertNotEquals(usPriority, usPriority1);
    }
}
