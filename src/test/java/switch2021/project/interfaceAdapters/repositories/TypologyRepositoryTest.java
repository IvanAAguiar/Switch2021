package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.JPA.TypologyJpa;
import switch2021.project.dataModel.JPA.assembler.TypologyJpaAssembler;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import switch2021.project.persistence.TypologyJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class TypologyRepositoryTest {

    @MockBean
    private TypologyJpaRepository jpaRepository;
    @MockBean
    private TypologyJpaAssembler assembler;
    @InjectMocks
    private TypologyRepository repository;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByTypologyIdSuccess() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        TypologyJpa typoJpa = mock(TypologyJpa.class);
        when(jpaRepository.findById(id)).thenReturn(Optional.of(typoJpa));
        Typology typo = mock(Typology.class);
        when(assembler.toDomain(typoJpa)).thenReturn(typo);
        Optional<Typology> expected = Optional.of(typo);
        //Act
        Optional<Typology> result = repository.findByTypologyId(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void findByTypologyIdNull() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Typology> expected = Optional.empty();
        //Act
        Optional<Typology> result = repository.findByTypologyId(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void findAll() {
        TypologyJpa typoJpa1 = mock(TypologyJpa.class);
        TypologyJpa typoJpa2 = mock(TypologyJpa.class);
        List<TypologyJpa> jpas = new ArrayList<>();
        jpas.add(typoJpa1);
        jpas.add(typoJpa2);
        when(jpaRepository.findAll()).thenReturn(jpas);
        Typology typo1 = mock(Typology.class);
        Typology typo2 = mock(Typology.class);
        List<Typology> typos = new ArrayList<>();
        typos.add(typo1);
        typos.add(typo2);
        when(assembler.toDomain(jpas)).thenReturn(typos);
        //Act
        List<Typology> result = repository.findAll();
        //Assert
        assertEquals(typos, result);
    }


    @DisplayName("Test typology Store populated and empty store - ")
    @Test
    public void typologySaveSuccess() {
        //Arrange
        Typology typo = mock(Typology.class);
        TypologyJpa typoJpa = mock(TypologyJpa.class);
        when(assembler.toData(typo)).thenReturn(typoJpa);
        TypologyJpa savedTypoJpa = mock(TypologyJpa.class);
        when(jpaRepository.saveAndFlush(typoJpa)).thenReturn(savedTypoJpa);
        Typology savedTypo = mock(Typology.class);
        when(assembler.toDomain(savedTypoJpa)).thenReturn(savedTypo);
        //Act
        Typology result = repository.save(typo);
        //Assert
        assertEquals(result, savedTypo);
    }

    @DisplayName("Add and save several typologies at same time")
    @Test
    public void typologySaveFail() {
        //Arrange
        Typology typo = mock(Typology.class);
        TypologyJpa typoJpa = mock(TypologyJpa.class);
        when(assembler.toData(typo)).thenReturn(typoJpa);
        when(jpaRepository.saveAndFlush(typoJpa)).thenReturn(null);
        //Act
        Typology result = repository.save(typo);
        //Assert
        assertNull(result);
    }

    @Test
    public void ExistByTypologyIdTrue() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        when(jpaRepository.existsById(id)).thenReturn(true);
        //Act and Assert
        assertTrue(repository.existsByTypologyId(id));
    }

    @Test
    public void ExistByTypologyIdFail() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        when(jpaRepository.existsById(id)).thenReturn(false);
        //Act and Assert
        assertFalse(repository.existsByTypologyId(id));
    }

    @Test
    public void deleteTypologyFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            TypologyID id = mock(TypologyID.class);
            when(jpaRepository.existsById(id)).thenReturn(false);
            //Act
            repository.deleteByTypologyId(id);
        });
    }
}
