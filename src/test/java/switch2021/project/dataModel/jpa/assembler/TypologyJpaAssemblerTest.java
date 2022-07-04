package switch2021.project.dataModel.jpa.assembler;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dataModel.JPA.TypologyJpa;
import switch2021.project.dataModel.JPA.assembler.TypologyJpaAssembler;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TypologyJpaAssemblerTest {

    @InjectMocks
    private TypologyJpaAssembler assembler;

    @Test
    void testToData() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = mock(TypologyID.class);
        Typology typology = mock(Typology.class);
        List<Typology> typologies = new ArrayList<>();
        typologies.add(typology);
        when(typology.getDescriptionID()).thenReturn(id);
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Fixed Cost");
        //Act
        List<TypologyJpa> typologyJpas = assembler.toData(typologies);
        //Assert
        assertEquals(1, typologyJpas.size());
    }

    @Test
    void testToDataEmpty() {
        //Arrange
        List<Typology> typologies = new ArrayList<>();
        //Act
        List<TypologyJpa> typologyJpas = assembler.toData(typologies);
        //Assert
        assertEquals(0, typologyJpas.size());
    }

    @Test
    void testToDomain() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = mock(TypologyID.class);
        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        List<TypologyJpa> typologyJpas = new ArrayList<>();
        typologyJpas.add(typologyJpa);
        when(typologyJpa.getId()).thenReturn(id);
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Fixed Cost");
        //Act
        List<Typology> typologies = assembler.toDomain(typologyJpas);
        //Assert
        assertEquals(1, typologies.size());
    }

    @Test
    void testToDomainEmpty() {
        //Arrange
        List<TypologyJpa> typologyJpas = new ArrayList<>();
        //Act
        List<Typology> typologies = assembler.toDomain(typologyJpas);
        //Assert
        assertEquals(0, typologies.size());
    }
}