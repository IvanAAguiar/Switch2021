package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.JPA.UserStoryOfSprintJpa;
import switch2021.project.dataModel.JPA.assembler.UserStoryOfSprintJpaAssembler;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;
import switch2021.project.persistence.UserStoryOfSprintJpaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserStoryOfSprintRepositoryTest {

    @MockBean
    private UserStoryOfSprintJpaRepository jpaRepository;
    @MockBean
    private UserStoryOfSprintJpaAssembler assembler;
    @InjectMocks
    private UserStoryOfSprintRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllUserStoryOfSprint() {
        //Arrange
        UserStoryOfSprintJpa jpa = mock(UserStoryOfSprintJpa.class);
        List<UserStoryOfSprintJpa> jpas = new ArrayList<>();
        jpas.add(jpa);
        when(jpaRepository.findAll()).thenReturn(jpas);
        UserStoryOfSprint userStoryOfSprint = mock(UserStoryOfSprint.class);
        when(assembler.toDomain(jpa)).thenReturn(userStoryOfSprint);
        List<UserStoryOfSprint> expected = new ArrayList<>();
        expected.add(userStoryOfSprint);
        //Act
        List<UserStoryOfSprint> result = repository.findAllUserStoryOfSprint();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void findAllUserStoriesBySprintID() {
        //Arrange
        SprintID id = mock(SprintID.class);
        UserStoryOfSprintJpa jpa = mock(UserStoryOfSprintJpa.class);
        when(jpa.getSprintName()).thenReturn("SprintName");
        List<UserStoryOfSprintJpa> jpas = new ArrayList<>();
        jpas.add(jpa);
        when(jpaRepository.findAll()).thenReturn(jpas);
        UserStoryOfSprint userStoryOfSprint = mock(UserStoryOfSprint.class);
        Description name = mock(Description.class);
        when(id.getSprintName()).thenReturn(name);
        when(name.getText()).thenReturn("SprintName");
        when(assembler.toDomain(jpa)).thenReturn(userStoryOfSprint);
        List<UserStoryOfSprint> expected = new ArrayList<>();
        expected.add(userStoryOfSprint);
        //Act
        List<UserStoryOfSprint> result = repository.findAllUserStoriesBySprintID(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void save() {
        //Arrange
        UserStoryOfSprint userStoryOfSprint = mock(UserStoryOfSprint.class);
        when(userStoryOfSprint.getSprintName()).thenReturn("Name");
        UserStoryOfSprintJpa jpa = mock(UserStoryOfSprintJpa.class);
        when(assembler.toData(userStoryOfSprint)).thenReturn(jpa);
        when(jpaRepository.save(jpa)).thenReturn(jpa);
        when(assembler.toDomain(jpa)).thenReturn(userStoryOfSprint);
        //Act
        UserStoryOfSprint result = repository.save(userStoryOfSprint);
        //Assert
        assertEquals(userStoryOfSprint.getSprintName(), result.getSprintName());
    }

    @Test
    void deleteUserStoryOfSprintTrue() {
        //Arrange
        UserStoryID id = mock(UserStoryID.class);
        when(jpaRepository.existsById(id)).thenReturn(true);
        //Act and Assert
        assertTrue(repository.deleteUserStoryOfSprint(id));
    }

    @Test
    void deleteUserStoryOfSprintFalse() {
        //Arrange
        UserStoryID id = mock(UserStoryID.class);
        when(jpaRepository.existsById(id)).thenReturn(false);
        //Act and Assert
        assertFalse(repository.deleteUserStoryOfSprint(id));
    }

    @Test
    void getUserStoryOfSprintJpaRepository() {
    }

    @Test
    void getUserStoryOfSprintJpaAssembler() {
    }
}