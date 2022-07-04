package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputTypologyDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        String typology = "new typology";

        OutputTypologyDTO dtoAll = new OutputTypologyDTO(typology);
        //Act + Assert
        assertEquals(typology, dtoAll.getTypology());
    }
}
