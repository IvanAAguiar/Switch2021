package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HoursTest {

    @Test
    @DisplayName("create effort hours with success - with values")
    public void hoursWithSuccess() {
        //Arrange
        int effortHours = 10;
        //Act
        Hours expected = new Hours(effortHours);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
    }

    @Test
    @DisplayName("create effort hours with success, hours field without value")
    public void hoursToZero()  {
        //Arrange
        int effortHours = 0;
        //Act
        Hours expected = new Hours(effortHours);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
    }

    @Test
    @DisplayName("create effort hours - field on limit")
    public void hoursOnLimit()  {
        //Arrange
        int effortHours = 23;
        //Act
        Hours expected = new Hours(effortHours);
        //Assert
        assertEquals(expected.getEffortHours(), effortHours);
    }

    @Test
    @DisplayName("create effort hours - field with negative value")
    public void hourNegativeValue()  {
        //Arrange
        int effortHours = -3;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hours(effortHours));
        //Assert
        assertEquals("Not valid work time values." + "Hour interval: [" + 0 + " - " + 23 + "]", exception.getMessage());
    }

    @Test
    @DisplayName("create effort hours - More than 24 hours")
    public void hourMoreThan24Hours()  {
        //Arrange
        int effortHours = 24;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hours(effortHours));
        //Assert
        assertEquals("Not valid work time values." + "Hour interval: [" + 0 + " - " + 23 + "]", exception.getMessage());
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestEquals() {
        //Arrange
        Hours hours = new Hours(1);
        Hours hours1 = new Hours(1);
        //Assert
        assertEquals(hours, hours1);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestNotEquals() {
        //Arrange
        Hours hours = new Hours(1);
        Hours hours1 = new Hours(2);
        //Assert
        assertNotEquals(hours, hours1);
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestClass() {
        //Arrange
        Minutes minutes = new Minutes(1);
        Hours hours = new Hours(1);
        //Assert
        assertNotEquals(hours, minutes);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTest() {
        //Arrange
        Hours hours = new Hours(1);
        Hours hours1 = new Hours(1);
        //Assert
        assertEquals(hours.hashCode(), hours1.hashCode());
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestFail() {
        //Arrange
        Hours hours = new Hours(1);
        Hours hours1 = new Hours(11);
        //Assert
        assertNotEquals(hours.hashCode(), hours1.hashCode());
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestClassFail() {
        //Arrange
        Hours hours = new Hours(1);
        Minutes minutes = new Minutes(11);
        //Assert
        assertNotEquals(hours.hashCode(), minutes.hashCode());
    }


    @DisplayName("Test Same Value As - True")
    @Test
    public void sameValueAs_1() {
        //Arrange
        Hours hours = new Hours(1);
        Hours hours1 = new Hours(1);
        //Assert
        assertTrue(hours.sameValueAs(hours1));
    }

    @DisplayName("Test Same Value As - False")
    @Test
    public void sameValueAs_2() {
        //Arrange
        Hours hours = new Hours(1);
        Hours hours1 = new Hours(11);
        //Assert
        assertFalse(hours.sameValueAs(hours1));
    }

    @Test
    @DisplayName("Empty Constructor")
    public void emptyConstructor() {
        //Act
        Hours hours = new Hours();
        //Assert
        assertEquals(0, hours.getEffortHours());
    }

    @Test
    @DisplayName("Test to set Hours")
    public void setHours() {
        //Act
        Hours hours = new Hours(2);
        hours.setEffortHours(3);
        //Assert
        assertEquals(3, hours.getEffortHours());
    }
}
