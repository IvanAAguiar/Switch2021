package switch2021.project.entities.valueObjects.voFactories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.entities.valueObjects.voFactories.voFactories.BudgetFactory;
import switch2021.project.entities.valueObjects.vos.Budget;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BudgetFactoryTest {

    @Autowired
    BudgetFactory budgetFactory;

    @Test
    @DisplayName("Test to create budget - with success")
    public void createBudgetSuccess(){
        //Arrange
        Double expected = 100.10;
        //Act
        Budget budget = budgetFactory.create(expected);
        //Assert
        assertEquals(expected, budget.getBudgetVO());
    }
}
