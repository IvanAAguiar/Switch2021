package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.BusinessSector;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessSectorTest {

    @DisplayName("Test to create Business Sector")
    @Test
    public void createBusinessSector() {
        //Arrange
        String sector = "Business Sector";
        //Act
        BusinessSector businessSector = new BusinessSector(sector);
        //Assert
        assertEquals("Business Sector", businessSector.getDescription().getText());
    }

    @DisplayName("Test to create Business Sector - Fail")
    @Test
    public void createBusinessSector_Fail() {
        //Arrange
        String sector = "Business Sector";
        //Act
        BusinessSector businessSector = new BusinessSector(sector);
        //Assert
        assertNotEquals("Business Sector XPTO", businessSector.getDescription().getText());
    }
}
