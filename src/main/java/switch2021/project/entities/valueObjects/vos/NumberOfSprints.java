package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;

import java.util.Objects;

@Getter
public class NumberOfSprints {

    /**
     * Attributes
     **/
    private final int numberOfSprintsVO;


    /**
     * Class constructor
     **/
    public NumberOfSprints(int nrSprints) {
        checkNumberOfSprints(nrSprints);
        this.numberOfSprintsVO = nrSprints;
    }

    /**
     * Methods
     **/

    public void checkNumberOfSprints(int nrSprints) {
        if (nrSprints <= 0) {
            throw new IllegalArgumentException("The number of sprints should be greater than 0");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfSprints that = (NumberOfSprints) o;
        return numberOfSprintsVO == that.numberOfSprintsVO;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSprintsVO);
    }
}
