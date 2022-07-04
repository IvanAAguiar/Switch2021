package switch2021.project.entities.factories.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITypologyIDFactory;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TypologyFactoryTest {

    @InjectMocks
    private TypologyFactory factory;
    @MockBean
    private ITypologyIDFactory idFactory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTypology_Success() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        TypologyID id = mock(TypologyID.class);
        //Act
        when(dto.getDescription()).thenReturn("Test");
        when(idFactory.createId(dto)).thenReturn(id);
        //Assert
        assertEquals(id, factory.createTypology(dto).getDescriptionID());
    }

    @Test
    public void createTypology_Fail() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        TypologyID id = mock(TypologyID.class);
        //Act
        when(dto.getDescription()).thenReturn("Test");
        when(idFactory.createId(dto)).thenReturn(null);
        //Assert
        assertNotEquals(id, factory.createTypology(dto).getDescriptionID());
    }
}
