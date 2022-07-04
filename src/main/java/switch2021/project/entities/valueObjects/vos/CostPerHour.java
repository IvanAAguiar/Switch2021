package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;

@Getter
public class CostPerHour {

    /**
     * Attributes
     **/

    private double cost;
    private static final double MINCOST = 0.0;

    /**
     * Constructor
     **/
    public CostPerHour(double costPerHour) {
        isValidCostPerHour(costPerHour);
        this.cost = costPerHour;
    }

    /**
     * Methods
     **/

    private void isValidCostPerHour(double costPerHour) {
        if (costPerHour < MINCOST) {
            throw new IllegalArgumentException("Check cost per hour, cannot be < 0");
        }
    }
}
