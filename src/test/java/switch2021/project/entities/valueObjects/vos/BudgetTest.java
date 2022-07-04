package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Budget;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTest {


    @Test
    @DisplayName("Test to create valid budget")
    public void createNewBudget() {
        //Arrange
        Budget budget = new Budget(2500);
        //Assert
        assertEquals(2500, budget.getBudgetVO());
    }

    @Test
    @DisplayName("Test to create valid budget")
    public void createNewBudget_LimitConditions() {
        //Arrange
        Budget budget = new Budget(0.1);
        //Assert
        assertEquals(0.1, budget.getBudgetVO());
    }

    @Test
    @DisplayName("Test throw illegal argument exceptions (Budget field cannot be under 0)")
    public void checkBudgetUnder0() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double budget = -1;
            //Act
            new Budget(budget);
        });
    }

    @Test
    @DisplayName("Test throw illegal argument exceptions (Budget field cannot be under 0)")
    public void checkBudgetUnder0_LimitConditions() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double budget = -0.1;
            //Act
            new Budget(budget);
        });
    }

    @Test
    @DisplayName("Test throw illegal argument exceptions (Budget field cannot be under 0)")
    public void checkBudgetUnder0_LimitConditions_1() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double budget = -6789;
            //Act
            new Budget(budget);
        });
    }

    @Test
    @DisplayName("Test throw illegal argument exceptions (Budget field cannot be 0)")
    public void checkBudgetEqualTo0() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double budget = 0.0;
            //Act
            new Budget(budget);
        });
    }


    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange
        Budget budget = new Budget(2000);
        //Act and Assert
        assertEquals(budget, budget);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestSuccess() {
        //Arrange
        Budget budget = new Budget(2000);
        Budget budget1 = new Budget(2000);
        //Act and Assert
        assertEquals(budget, budget1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail_1() {
        //Arrange
        Budget budget = new Budget(3000);
        Budget budget1 = new Budget(2000);
        //Act and Assert
        assertNotEquals(budget, budget1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail_2() {
        //Arrange
        Budget budget = new Budget(3000);
        Typology budget1 =  new Typology(new TypologyID(new Description("Test")));
        //Act and Assert
        assertNotEquals(budget, budget1);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess() {
        //Arrange
        Budget budget = new Budget(200);
        Budget budget1 = new Budget(200);
        //Act and Arrange
        assertEquals(budget.hashCode(), budget1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeFail() {
        //Arrange
        Budget budget = new Budget(150);
        Budget budget1 = new Budget(200);
        //Act and Arrange
        assertNotEquals(budget.hashCode(), budget1.hashCode());
    }
}
