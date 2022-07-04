package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Budget {

    /**
     * Attributes
     */
    private final double budgetVO;


    /**
     * Constructor
     */
    public Budget (double budget) {
        if (budget <= 0)
            throw new IllegalArgumentException("Budget field cannot be under or equal to 0.");
        this.budgetVO = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Budget)) return false;
        Budget budget1 = (Budget) o;
        return Double.compare(budget1.budgetVO, budgetVO) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetVO);
    }
}
