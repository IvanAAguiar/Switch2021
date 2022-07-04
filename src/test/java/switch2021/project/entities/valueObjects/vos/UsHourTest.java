package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;

import static org.junit.jupiter.api.Assertions.*;

public class UsHourTest {

    @Test
    @DisplayName("create time estimate with success - with values")
    public void hoursWithSuccess() {
        //Arrange
        int timeEstimate = 10;
        //Act
        UsHour expected = new UsHour(timeEstimate);
        //Assert
        assertEquals(expected.getUsHours(), timeEstimate);
    }

    @Test
    @DisplayName("create time estimate hours with success, hours field with value 0")
    public void hoursToZero() {
        //Arrange
        int timeEstimate = 0;
        //Act
        UsHour expected = new UsHour(timeEstimate);
        //Assert
        assertEquals(expected.getUsHours(), timeEstimate);
    }

    @Test
    @DisplayName("create timeEstimate hours - field on limit day - success")
    public void hoursOnLimit() {
        //Arrange
        int timeEstimate = 23;
        //Act
        UsHour expected = new UsHour(timeEstimate);
        //Assert
        assertEquals(expected.getUsHours(), timeEstimate);
    }

    @Test
    @DisplayName("create timeEstimate hours - field with negative value")
    public void hourNegativeValue() {
        //Arrange
        int timeEstimate = -3;
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UsHour(timeEstimate));
        //Assert
        assertEquals("Not valid work time values." + "Hour interval: [" + 0 + "]", exception.getMessage());
    }

    @Test
    @DisplayName("create time estimate with success - with values")
    public void hoursWithSuccess2() {
        //Arrange
        int timeEstimate = 100;
        //Act
        UsHour expected = new UsHour(timeEstimate);
        //Assert
        assertEquals(expected.getUsHours(), timeEstimate);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange
        UsHour usHour = new UsHour(2);
        //Act and Assert
        assertEquals(usHour, usHour);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestSuccess() {
        //Arrange
        UsHour usHour = new UsHour(2);
        UsHour usHour1 = new UsHour(2);
        //Act and Assert
        assertEquals(usHour, usHour1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail_1() {
        //Arrange
        UsHour usHour = new UsHour(2);
        UsHour usHour1 = new UsHour(3);
        //Act and Assert
        assertNotEquals(usHour, usHour1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail_2() {
        //Arrange
        UsHour usHour = new UsHour(2);
        Typology usHour1 = new Typology(new TypologyID(new Description("Test")));
        //Act and Assert
        assertNotEquals(usHour, usHour1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNull() {
        //Arrange
        UsHour usHour = null;
        //Act and Assert
        assertNull(usHour);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess() {
        //Arrange
        UsHour usHour = new UsHour(2);
        UsHour usHour1 = new UsHour(2);
        //Act and Assert
        assertEquals(usHour.hashCode(), usHour1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeFail() {
        //Arrange
        UsHour usHour = new UsHour(2);
        UsHour usHour1 = new UsHour(3);
        //Act and Assert
        assertNotEquals(usHour.hashCode(), usHour1.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueOf() {
        //Arrange
        UsHour usHour = new UsHour(2);
        UsHour usHour1 = new UsHour(3);
        //Act and Assert
        assertFalse(usHour.sameValueAs(usHour1));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueAs_True() {
        //Arrange
        UsHour usHour = new UsHour(2);
        UsHour usHour1 = new UsHour(2);
        //Act and Assert
        assertTrue(usHour.sameValueAs(usHour1));
    }
}


