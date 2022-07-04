package switch2021.project.entities.valueObjects.voFactories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.valueObjects.voFactories.voFactories.TypologyIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TypologyIDFactoryTest {

    @MockBean
    private IDescriptionFactory descriptionFactory;
    @InjectMocks
    private TypologyIDFactory idFactory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

/*    @Test
    public void createTypologyID_Success() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        Description des = mock(Description.class);
        //Act
        when(dto.getDescription()).thenReturn("Test");
        when(descriptionFactory.createDescription(dto.getDescription())).thenReturn(des);
        //Assert
        assertEquals(des, idFactory.createId(dto).getDescription());
    }

    @Test
    public void createTypologyID_Fail() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        Description des = mock(Description.class);
        TypologyID id = mock(TypologyID.class);
        //Act
        when(dto.getDescription()).thenReturn("Test");
        when(descriptionFactory.createDescription("Test")).thenReturn(null);
        when(id.getDescription()).thenReturn(des);
        //Assert
        assertNotEquals(id.getDescription(), idFactory.createId(dto).getDescription());
    }

 */
}
