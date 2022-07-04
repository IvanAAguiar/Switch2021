package switch2021.project.dataModel.jpa;

import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.JPA.ResourceIDJpa;
import switch2021.project.dataModel.JPA.ResourceJpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class ResourceJpaTest {

    @Test
    void resourceToDataSuccess() {
        //Arrange
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        String endDate = "2023-10-12";
        double alloc = 0.4;
        double cost = 500.00;
        String role = "TeamMember";
        //Act
        ResourceJpa result = new ResourceJpa(idJpa, endDate, alloc, cost, role);
        ResourceJpa expected = new ResourceJpa(idJpa, endDate, alloc, cost, role);
        //Assert
        assertEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    void resourceToDataFail() {
        //Arrange
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        ResourceIDJpa idExp = mock(ResourceIDJpa.class);
        String endDate = "2023-10-12";
        double alloc = 0.4;
        double cost = 500.00;
        String role = "TeamMember";
        //Act
        ResourceJpa result = new ResourceJpa(idJpa, endDate, alloc, cost, role);
        ResourceJpa expected = new ResourceJpa(idExp, endDate, alloc, cost, role);
        //Assert
        assertNotEquals(expected.hashCode(), result.hashCode());
    }
}