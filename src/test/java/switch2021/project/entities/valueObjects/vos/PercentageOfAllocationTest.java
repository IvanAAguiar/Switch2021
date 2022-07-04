package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PercentageOfAllocationTest {

    @Test
    @DisplayName("Validate that percentage is correct - limit bound")
    public void percentageSuccessLimit1() {
        //Arrange
        double percentage = 0.0;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(percentage, percOfAllo.getPercentage());
    }

    @Test
    @DisplayName("Validate that percentage is correct - limit bound")
    public void percentageSuccessLimit2() {
        //Arrange
        double percentage = 1.0;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(percentage, percOfAllo.getPercentage());
    }

    @Test
    @DisplayName("Validate that percentage is correct - other values")
    public void percentageSuccessInBound() {
        //Arrange
        double percentage = 0.3;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(percentage, percOfAllo.getPercentage());
    }

    @Test
    @DisplayName("Validate that percentage is correct - fail")
    public void percentageFailOutBoundNegative() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double percentage = -1;
            //Act
            new PercentageOfAllocation(percentage);
        });
    }

    @Test
    @DisplayName("Validate that percentage is correct - fail")
    public void percentageFailOutBoundMoreThanOne() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double percentage = 1.1;
            //Act
            new PercentageOfAllocation(percentage);
        });
    }

    @Test
    public void equalsTrue_0() {
        //Arrange
        double percentage = 0.3;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        PercentageOfAllocation expected = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(expected, percOfAllo);
    }

    @Test
    public void equalsTrue_1() {
        //Arrange
        double percentage = 0.3;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        PercentageOfAllocation expected = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(expected, percOfAllo);
    }


    @Test
    public void equalsTrue_2() {
        //Arrange
        double percentage = 1;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        PercentageOfAllocation expected = new PercentageOfAllocation(1);
        //Assert
        assertTrue(percOfAllo.equals(expected));
    }


    @Test
    public void hashCodeTrue() {
        //Arrange
        double percentage = 0.3;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        PercentageOfAllocation expected = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(expected.hashCode(), percOfAllo.hashCode());
    }

    @Test
    public void equalsFalse() {
        //Arrange
        double percentage = 0.3;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        PercentageOfAllocation expected = new PercentageOfAllocation(1);
        //Assert
        assertNotEquals(expected, percOfAllo);
    }

    @Test
    public void equalsFalse_1() {
        //Arrange
        double percentage = 0.3;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        PercentageOfAllocation expected = new PercentageOfAllocation(1);
        //Assert
        assertNotEquals(percOfAllo, expected);
    }


    @Test
    public void hashCodeFalse() {
        //Arrange
        double percentage = 0.3;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        PercentageOfAllocation expected = new PercentageOfAllocation(1);
        //Assert
        assertNotEquals(expected.hashCode(), percOfAllo.hashCode());
    }
}
