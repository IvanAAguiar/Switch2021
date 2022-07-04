package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    @DisplayName("create effort is in the future")
    public void createWorkDateAfterToday() {
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Date (LocalDate.now().plusDays(1)));
        //Assert
        assertEquals("Invalid workHours value.", exception.getMessage());
    }

    @Test
    @DisplayName("create effort with success")
    public void createDateWithSuccess() {
        //Act
        Date effortDate = new Date (LocalDate.now());
        //Assert
        assertEquals(LocalDate.now(),effortDate.getEffortDate());
    }

    @Test
    @DisplayName("create effort with success")
    public void effortDateNull() {
        //Act
        Date effortDate = new Date (null);
        //Assert
        assertNotNull(effortDate);
    }

    @Test
    @DisplayName("Set Date")
    public void setDate() {
        //Act
        Date effortDate = new Date (LocalDate.now());
        effortDate.setEffortDate(LocalDate.of(2025, 6, 13));
        //Assert
        assertEquals(effortDate.getEffortDate(), LocalDate.of(2025, 6, 13));
    }


    @Test
    @DisplayName("Empty Constructor")
    public void emptyConstructor() {
        //Act
        Date effortDate = new Date();
        //Assert
        assertNull(effortDate.getEffortDate());
    }

}
