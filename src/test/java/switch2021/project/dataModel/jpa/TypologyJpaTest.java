package switch2021.project.dataModel.jpa;

import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.JPA.TypologyJpa;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TypologyJpaTest {

    @Test
    void getId() {
        //Arrange
        TypologyJpa result = new TypologyJpa();
        TypologyID id = mock(TypologyID.class);
        // Act
        result.setId(id);
        //Assert
        assertEquals(id, result.getId());
    }
}