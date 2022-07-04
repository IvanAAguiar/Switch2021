package switch2021.project.entities.valueObjects.voFactories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.entities.valueObjects.voFactories.voFactories.DescriptionFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DescriptionFactoryTest {

    @Autowired
    DescriptionFactory descriptionFactory;

    @Test
    @DisplayName("Test to create description - with success")
    public void createTimeEstimateUsSuccess() {
        //Arrange
        String expected = "Making some tests";
        // Act
        Description description = descriptionFactory.createDescription(expected);
        //Assert
        assertEquals(expected, description.getText());
    }
}
