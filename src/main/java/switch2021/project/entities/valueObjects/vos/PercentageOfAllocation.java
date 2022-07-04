package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import switch2021.project.entities.valueObjects.voFactories.voFactories.PercOfAllocationFactory;

import java.util.Objects;

@Getter
public class PercentageOfAllocation {

    /**
     * Attributes
     **/
    private final double percentage;
    private static final double MINPERCENTAGE = 0.0;
    private static final double MAXPERCENTAGE = 1.0;

    /**
     * Constructor
     **/
    public PercentageOfAllocation(double percOfAllo) {
        isValidPercentage(percOfAllo);
        this.percentage = percOfAllo;
    }

    /**
     * Methods
     **/
    private void isValidPercentage(double percOfAllo) {
        if (percOfAllo < MINPERCENTAGE || percOfAllo > MAXPERCENTAGE) {
            throw new IllegalArgumentException("Check percentage, cannot be < 0 or superior to 1");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PercentageOfAllocation)) return false;
        PercentageOfAllocation allocation = (PercentageOfAllocation) o;
        return Double.compare(allocation.percentage, percentage) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentage);
    }
}

