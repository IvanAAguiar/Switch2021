package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.CostPerHour;

import static org.junit.jupiter.api.Assertions.*;

public class CostPerHourTest {

    @Test
    @DisplayName("Validate that cost is correct - limit bound")
    public void costSuccessLimit1() {
        //Arrange
        int cost = 0;
        //Act
        CostPerHour costPerHour = new CostPerHour(cost);
        //Assert
        assertEquals(cost, costPerHour.getCost());
    }

    @Test
    @DisplayName("Validate that cost is correct - other values")
    public void costSuccessInBound() {
        //Arrange
        double cost = 0.3;
        //Act
        CostPerHour costPerHour = new CostPerHour(cost);
        //Assert
        assertEquals(cost, costPerHour.getCost());
    }

    @Test
    @DisplayName("Validate that cost is correct - fail")
    public void costFailOutBoundNegative() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double cost = -0.1;
            //Act
            new CostPerHour(cost);
        });
    }
}
