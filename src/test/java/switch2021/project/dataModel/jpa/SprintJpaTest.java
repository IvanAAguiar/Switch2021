package switch2021.project.dataModel.jpa;

import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.JPA.SprintJpa;
import switch2021.project.entities.valueObjects.vos.SprintID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintJpaTest {

    @Test
    void setSprintId() {
        //Arrange
        SprintID id = mock(SprintID.class);
        SprintJpa sprintJpa = new SprintJpa();
        //Act
        sprintJpa.setSprintId(id);
        sprintJpa.setStartDate("2022-12-12");
        sprintJpa.setEndDate("2023-12-12");
        //Assert
        assertEquals(id, sprintJpa.getSprintId());
    }
}