package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UsPriority implements Comparable<UsPriority> {

    /**
     * Attributes
     **/
    private final int priorityUs;
    private static final int MINPRIORITY = 0;
    private static final int MAXPRIORITY = 5;


    /**
     * Constructor
     **/
    public UsPriority(int priorityUs) {
        isValidPriority(priorityUs);
        this.priorityUs = priorityUs;
    }

    /**
     * Methods
     **/
    private void isValidPriority(int usPriority) {
        if (usPriority < MINPRIORITY || usPriority > MAXPRIORITY) {
            throw new IllegalArgumentException("Check priority, cannot be < 0 or superior to 5");
        }
    }


    /**
     * Override
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsPriority that = (UsPriority) o;
        return that.priorityUs == this.priorityUs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(priorityUs);
    }

    @Override
    public int compareTo(UsPriority o) {
        if (this.priorityUs < o.priorityUs) {
            return -1;
        } else if (this.priorityUs > o.priorityUs) {
            return 1;
        }
        return 0;
    }
}
