package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;
import static org.junit.jupiter.api.Assertions.*;

class SprintDurationTest {

    @Test
    void getSprintDurationDays() {
        SprintDuration duration = new SprintDuration(7);

        assertEquals(7, duration.getSprintDurationDays());
    }

    @Test
    void getSprintDurationDays_2() {
        SprintDuration duration = new SprintDuration(31);

        assertEquals(31, duration.getSprintDurationDays());
    }

    @Test
    void getSprintDurationDays_3() {
        SprintDuration duration = new SprintDuration(14);

        assertEquals(14, duration.getSprintDurationDays());
    }

    @Test
    void getMIN() {
        SprintDuration duration = new SprintDuration(14);

        assertEquals(7, duration.getMIN());
    }

    @Test
    void getMAX() {
        SprintDuration duration = new SprintDuration(14);

        assertEquals(31, duration.getMAX());
    }

    @Test
    void shouldTrowNullExceptionValueToHigh() {
        assertThrows(NullPointerException.class, () -> new SprintDuration(35));
    }

    @Test
    void shouldTrowNullExceptionNegative() {
        assertThrows(NullPointerException.class, () -> new SprintDuration(3));
    }

    @Test
    void overrideTestDifferentClass() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        Typology test = new Typology(new TypologyID(new Description("Test")));
        //Assert
        assertNotEquals(duration, test);

    }

    @Test
    void overrideTestEqualsObject() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = new SprintDuration(15);
        //Assert
        assertEquals(duration, duration2);

    }

    @Test
    void overrideTestSameObject() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        //Assert
        assertEquals(duration, duration);

    }

    @Test
    void overrideTestNotEqualsObject() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = new SprintDuration(16);
        //Assert
        assertNotEquals(duration, duration2);

    }

    @Test
    void overrideTestNullObject() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = null;
        //Assert
        assertNotEquals(duration, duration2);

    }

    @Test
    public void hashCodeTestSuccess() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = new SprintDuration(15);
        //Assert
        assertEquals(duration.hashCode(), duration2.hashCode());
    }

    @Test
    public void hashCodeTestFail() {
        // Arrange + Act
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = new SprintDuration(16);
        //Assert
        assertNotEquals(duration.hashCode(), duration2.hashCode());
    }

    @Test
    public void sameValueAs_False() {
        // Arrange + Act
        SprintDuration duration = new SprintDuration(16);
        SprintDuration duration2 = new SprintDuration(15);
        //Assert
        assertFalse(duration.sameValueAs(duration2));
    }
}