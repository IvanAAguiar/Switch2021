package switch2021.project.entities.valueObjects.voFactories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.entities.valueObjects.voFactories.voFactories.UsPriorityFactory;
import switch2021.project.entities.valueObjects.vos.UsPriority;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UsPriorityFactoryTest {

    @Autowired
    UsPriorityFactory usPriorityFactory;

    @Test
    @DisplayName("Test to create user story priority - with success")
    public void createUsPrioritySuccess(){
        //Arrange
        int expected = 5;
        // Act
        UsPriority usPriority = usPriorityFactory.create(expected);
        //Assert
        assertEquals(expected, usPriority.getPriorityUs());

    }
}
