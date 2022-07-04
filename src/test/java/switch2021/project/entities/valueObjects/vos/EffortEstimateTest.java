package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.EffortEstimate;
import switch2021.project.entities.valueObjects.vos.UsHour;
import static org.junit.jupiter.api.Assertions.*;

public class EffortEstimateTest {

    @DisplayName("Test to create an effortEstimate")
    @Test
    public void createEffortEstimate() {
        //Arrange
        EffortEstimate effortEstimate = new EffortEstimate(2.5);
        //Assert
        assertEquals(2.5, effortEstimate.getEffortHours());
    }

    @DisplayName("Test to create and effortEstimate - Limit Conditions")
    @Test
    public void createEffortEstimateLimitLength() {
        //Arrange
        EffortEstimate effortEstimate = new EffortEstimate(0.1);
        //Assert
        assertEquals(0.1, effortEstimate.getEffortHours());
    }


    @DisplayName("Test to throw a exception - Negative EffortEstimate")
    @Test
    public void throwException_NegativeEffortEstimate() {
        assertThrows(IllegalArgumentException.class, () -> new EffortEstimate(-1));
    }


    @DisplayName("Test to throw a exception - Max EffortEstimate")
    @Test
    public void throwException_MaxEffortEstimate() {
        assertThrows(IllegalArgumentException.class, () -> new EffortEstimate(1000.1));
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestNotEquals() {
        //Arrange
        EffortEstimate effortEstimate = new EffortEstimate(1.5);
        EffortEstimate effortEstimate1 = new EffortEstimate(2.5);
        //Assert
        assertNotEquals(effortEstimate, effortEstimate1);
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestClass() {
        //Arrange
        EffortEstimate effortEstimate = new EffortEstimate(1.5);
        UsHour test = new UsHour(1.5);
        //Assert
        assertNotEquals(effortEstimate, test);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestFail() {
        //Arrange
        EffortEstimate effortEstimate = new EffortEstimate(1.5);
        EffortEstimate effortEstimate1 = new EffortEstimate(11.5);
        //Assert
        assertNotEquals(effortEstimate.hashCode(), effortEstimate1.hashCode());
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestClassFail() {
        //Arrange
        EffortEstimate effortEstimate = new EffortEstimate(1.5);
        UsHour test = new UsHour(11.5);
        //Assert
        assertNotEquals(effortEstimate.hashCode(), test.hashCode());
    }

    @DisplayName("Test Same Value As")
    @Test
    public void sameValueAs() {
        //Arrange
        EffortEstimate effortEstimate = new EffortEstimate(1.5);
        EffortEstimate effortEstimate1 = new EffortEstimate(1.5);
        //Assert
        assertFalse(effortEstimate.sameValueAs(effortEstimate1));
    }

}
