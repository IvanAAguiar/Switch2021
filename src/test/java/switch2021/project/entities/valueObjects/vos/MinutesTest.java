package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinutesTest {

    @Test
    @DisplayName("create effort minutes with success - with values")
    public void MinutesWithSuccess() {
        //Arrange
        int effortMinutes = 10;
        //Act
        Minutes expected = new Minutes(effortMinutes);
        //Assert
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort minutes with success, minutes field without value")
    public void MinutesToZero() {
        //Arrange
        int effortMinutes = 0;
        //Act
        Minutes expected = new Minutes(effortMinutes);
        //Assert
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort minutes - field on limit")
    public void MinutesOnLimit() {
        //Arrange
        int effortMinutes = 59;
        //Act
        Minutes expected = new Minutes(effortMinutes);
        //Assert
        assertEquals(expected.getEffortMinutes(), effortMinutes);
    }

    @Test
    @DisplayName("create effort minutes - field with negative value")
    public void minuteNegativeValue() {
        //Arrange
        int effortMinutes = -3;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Minutes(effortMinutes));
        //Assert
        assertEquals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]",
                exception.getMessage());
    }

    @Test
    @DisplayName("create effort minutes - More than 60 minutes")
    public void minuteMoreThan60Minutes() {
        //Arrange
        int effortMinutes = 60;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Minutes(effortMinutes));
        //Assert
        assertEquals("Not valid work time values." + " Minute interval: [" + 0 + " - " + 60 + "]",
                exception.getMessage());
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestEquals() {
        //Arrange
        Minutes minutes = new Minutes(1);
        Minutes minutes1 = new Minutes(1);
        //Assert
        assertEquals(minutes, minutes1);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestNotEquals() {
        //Arrange
        Minutes minutes = new Minutes(1);
        Minutes minutes1 = new Minutes(2);
        //Assert
        assertNotEquals(minutes, minutes1);
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
        Minutes minutes = new Minutes(1);
        Minutes minutes1 = new Minutes(1);
        //Assert
        assertEquals(minutes.hashCode(), minutes1.hashCode());
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestFail() {
        //Arrange
        Minutes minutes = new Minutes(1);
        Minutes minutes1 = new Minutes(11);
        //Assert
        assertNotEquals(minutes.hashCode(), minutes1.hashCode());
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
        Minutes minutes = new Minutes(1);
        Minutes minutes1 = new Minutes(1);
        //Assert
        assertTrue(minutes.sameValueAs(minutes1));
    }

    @DisplayName("Test Same Value As - False")
    @Test
    public void sameValueAs_2() {
        //Arrange
        Minutes minutes = new Minutes(1);
        Minutes minutes1 = new Minutes(11);
        //Assert
        assertFalse(minutes.sameValueAs(minutes1));
    }


    @Test
    @DisplayName("Empty Constructor")
    public void emptyConstructor() {
        //Act
        Minutes minutes = new Minutes();
        //Assert
        assertEquals(0, minutes.getEffortMinutes());
    }

    @Test
    @DisplayName("Test to set Hours")
    public void setMinutes() {
        //Act
        Minutes minutes = new Minutes(2);
        minutes.setEffortMinutes(3);
        //Assert
        assertEquals(3, minutes.getEffortMinutes());
    }
}


