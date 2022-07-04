package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberOfSprintsTest {

    @Test
    @DisplayName("Validate that the number of sprints are less than 0")
    public void checkNumberOfSprintsNegative() {

        //Act
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            int nrSprints = -1;

            //Assert
            new NumberOfSprints(nrSprints);
        });
    }

    @Test
    @DisplayName("Validate that the number of sprints are equal to 0")
    public void checkNumberOfSprintsZero() {

        //Act
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            int nrSprints = 0;

            //Assert
            new NumberOfSprints(nrSprints);
        });

    }

    @Test
    @DisplayName("Validate that the number of sprints")
    public void numberSprintsSuccess() {
        //Arrange
        int nrSprints = 5;
        //Act
        NumberOfSprints sprintsSuccess = new NumberOfSprints(nrSprints);
        //Arrange
        assertEquals(nrSprints, sprintsSuccess.getNumberOfSprintsVO());
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestEquals() {
        //Arrange
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        NumberOfSprints numberOfSprints1 = new NumberOfSprints(1);
        //Assert
        assertEquals(numberOfSprints, numberOfSprints1);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestEquals_true() {
        //Arrange
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        NumberOfSprints numberOfSprints1 = new NumberOfSprints(1);
        //Assert
        assertTrue(numberOfSprints.equals(numberOfSprints1));
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestEquals_null() {
        //Arrange
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        NumberOfSprints numberOfSprints1 = null;
        //Assert
        assertNotEquals(numberOfSprints, numberOfSprints1);
        assertNull(numberOfSprints1);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestNotEquals() {
        //Arrange
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        NumberOfSprints numberOfSprints1 = new NumberOfSprints(2);
        //Assert
        assertNotEquals(numberOfSprints, numberOfSprints1);
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestClass() {
        //Arrange
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        Hours hours = new Hours(1);
        //Assert
        assertNotEquals(hours, numberOfSprints);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTest() {
        //Arrange
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        NumberOfSprints numberOfSprints1 = new NumberOfSprints(1);
        //Assert
        assertEquals(numberOfSprints.hashCode(), numberOfSprints1.hashCode());
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestFail() {
        //Arrange
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        NumberOfSprints numberOfSprints1 = new NumberOfSprints(11);
        //Assert
        assertNotEquals(numberOfSprints.hashCode(), numberOfSprints1.hashCode());
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestClassFail() {
        //Arrange
        NumberOfSprints numberOfSprints = new NumberOfSprints(1);
        Minutes minutes = new Minutes(11);
        //Assert
        assertNotEquals(numberOfSprints.hashCode(), minutes.hashCode());
    }


}

