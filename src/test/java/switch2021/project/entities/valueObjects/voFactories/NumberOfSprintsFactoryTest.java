package switch2021.project.entities.valueObjects.voFactories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.entities.valueObjects.voFactories.voFactories.NumberOfSprintsFactory;
import switch2021.project.entities.valueObjects.vos.NumberOfSprints;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NumberOfSprintsFactoryTest {

    @Autowired
    NumberOfSprintsFactory numberOfSprintsFactory;

    @Test
    @DisplayName("Test to create number of sprints - with success")
    public void createNumberOfSprints(){
        //Arrange
        int expected = 1;
        //Act
        NumberOfSprints numberOfSprints = numberOfSprintsFactory.create(expected);
        //Assert
        assertEquals(expected, numberOfSprints.getNumberOfSprintsVO());
    }
}
