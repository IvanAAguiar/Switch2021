package switch2021.project.entities.valueObjects.vos;

import switch2021.project.utils.ValueObject;

public class EffortEstimate implements ValueObject<EffortEstimate> {

    /**
     * Attributes
     **/

    private final double effortHours;
    private static final int MINHOUR = 0;
    private static final int MAXHOUR = 1000;

    /**
     * Constructor (without SINGLETON)
     **/

    public EffortEstimate(double hours) {
        checkEffortEstimateRules(hours);
        this.effortHours = hours;
    }

    public double getEffortHours() {
        return effortHours;
    }

    /**
     * Methods
     **/

    private void checkEffortEstimateRules(double hours) {
        if (hours < MINHOUR || hours > MAXHOUR)
            throw new IllegalArgumentException("Not valid effort estimate values." + "Hour interval: [" + MINHOUR + " - " + MAXHOUR + "]");
    }

    @Override
    public boolean sameValueAs(EffortEstimate other) {
        return false;
    }
}
