package switch2021.project.interfaceAdapters.repositories;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dataModel.JPA.SprintJpa;
import switch2021.project.dataModel.JPA.assembler.SprintJpaAssembler;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.persistence.SprintJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SprintRepositoryTest {

    @InjectMocks
    SprintRepository sprintRepository;

    @Mock
    private SprintJpa sprintJpa;
    @Mock
    private Sprint sprint;
    @Mock
    private SprintJpaRepository sprintJpaRepository;
    @Mock
    private SprintJpaAssembler sprintJpaAssembler;
    @Mock
    private SprintID sprintID;
    @Mock
    private ProjectID projectID;


    @Test
    @SneakyThrows
    @DisplayName("Save Sprint, with success")
    public void saveSprintSuccess() {
        //Arrange
        when(sprintJpaAssembler.toData(any())).thenReturn(sprintJpa);
        when(sprintJpaRepository.existsById(any())).thenReturn(false);
        when(sprintJpa.getSprintId()).thenReturn(sprintID);
        when(sprintJpaRepository.save(any())).thenReturn(sprintJpa);
        when(sprintJpaAssembler.toDomain(any())).thenReturn(sprint);
        //Act
        Sprint result = sprintRepository.save(sprint);
        //Assert
        assertEquals(sprint,result);
    }


    @Test
    @SneakyThrows
    @DisplayName("Save Sprint, with failure")
    public void saveSprintFailure(){
        assertThrows(Exception.class, () -> {
        //Arrange
        when(sprintJpaAssembler.toData(any())).thenReturn(sprintJpa);
        when(sprintJpaRepository.existsById(any())).thenReturn(true);
        when(sprintJpa.getSprintId()).thenReturn(sprintID);
        //Act
        sprintRepository.save(sprint);
        });
    }


    @Test
    @DisplayName("Test to search sprint by sprint id, with success")
    public void findSprintByID_Success() {
        //Arrange
        Optional<SprintJpa> optional = Optional.of(sprintJpa);
        Optional<Sprint> optionalSprint = Optional.of(sprint);
        when(sprintJpaRepository.findById(any())).thenReturn(optional);
        when(sprintJpaAssembler.toDomain(any())).thenReturn(sprint);
        //Act
        Optional<Sprint> result = sprintRepository.findBySprintID(sprintID);
        //Assert
        assertEquals(optionalSprint,result);
    }

    @Test
    @DisplayName("Test to search sprint by sprint id, with failure")
    public void findSprintByID_Failure() {
        //Arrange
        Optional<SprintJpa> optional = Optional.empty();
        Optional<Sprint> optionalSprint = Optional.empty();
        when(sprintJpaRepository.findById(any())).thenReturn(optional);
        //Act
        Optional<Sprint> result = sprintRepository.findBySprintID(sprintID);
        //Assert
        assertEquals(optionalSprint,result);

    }


    @Test
    @DisplayName("Test to search sprint list - find all sprints")
    public void findAllSprints() {
        //Arrange
        List<SprintJpa> sprintJpaList = new ArrayList<>();
        List<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprint);
        sprintJpaList.add(sprintJpa);

        when(sprintJpaRepository.findAll()).thenReturn(sprintJpaList);
        when(sprintJpaAssembler.toDomain(any())).thenReturn(sprint);
        //Act
        List<Sprint> result = sprintRepository.findAllSprints();
        //Assert
        assertEquals(sprintList,result);
    }

    @Test
    @DisplayName("Test to delete a Sprint")
    public void deleteSprint() {
        //Arrange
        when(sprintJpaRepository.existsById(any())).thenReturn(true);
        doNothing().when(sprintJpaRepository).deleteById(any());
        //Act
        boolean result = sprintRepository.deleteSprint(sprintID);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to delete a Sprint fail")
    public void deleteSprint_fail() {
        //Arrange
        when(sprintJpaRepository.existsById(any())).thenReturn(false);
        doNothing().when(sprintJpaRepository).deleteById(any());
        //Act
        boolean result = sprintRepository.deleteSprint(sprintID);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to search sprint list, by Project ID")
    public void findAllSprintsByProjectID() {
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();
        List<SprintJpa> sprintJpaList = new ArrayList<>();
        sprintJpaList.add(sprintJpa);
        sprintList.add(sprint);
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("code");
        when(sprintJpaRepository.findAll()).thenReturn(sprintJpaList);
        when(sprintJpaAssembler.toDomain(any())).thenReturn(sprint);
        //Act
        List<Sprint> result = sprintRepository.findAllByProjectID(projectID);
        //Assert
        assertEquals(sprintList,result);
    }

    @Test
    @DisplayName("Test id sprint exists True")
    public void existsBySprintId() {
        //Arrange
        List<SprintJpa> sprintJpaList = new ArrayList<>();
        sprintJpaList.add(sprintJpa);
        when(sprintJpaRepository.findAll()).thenReturn(sprintJpaList);
        when(sprintJpaAssembler.toDomain(any())).thenReturn(sprint);
        when(sprint.hasSprintID(any())).thenReturn(true);
        //Act
        boolean result = sprintRepository.existsBySprintID("sprintID");
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test id sprint exists False")
    public void existsBySprintId_notFound() {
        //Arrange
        List<SprintJpa> sprintJpaList = new ArrayList<>();
        sprintJpaList.add(sprintJpa);
        when(sprintJpaRepository.findAll()).thenReturn(sprintJpaList);
        when(sprintJpaAssembler.toDomain(any())).thenReturn(sprint);
        when(sprint.hasSprintID(any())).thenReturn(false);
        //Act
        boolean result = sprintRepository.existsBySprintID("sprintID");
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test find current sprint")
    public void findCurrentSprint() {
        //Arrange
        List<SprintJpa> sprintJpaList = new ArrayList<>();
        sprintJpaList.add(sprintJpa);
        when(sprintJpaRepository.findAll()).thenReturn(sprintJpaList);
        when(sprintJpaAssembler.toDomain(any())).thenReturn(sprint);
        when(sprint.isCurrentSprint()).thenReturn(true);
        //Act
        Sprint result = sprintRepository.findCurrentSprint();
        //Assert
        assertEquals(sprint,result);
    }

    @Test
    @DisplayName("Test find current sprint, no current sprint")
    public void findCurrentSprint_fail() {
        assertThrows(Exception.class, () -> {
        //Arrange
        List<SprintJpa> sprintJpaList = new ArrayList<>();
        sprintJpaList.add(sprintJpa);
        when(sprintJpaRepository.findAll()).thenReturn(sprintJpaList);
        when(sprintJpaAssembler.toDomain(any())).thenReturn(sprint);
        when(sprint.isCurrentSprint()).thenReturn(false);
        //Act
        sprintRepository.findCurrentSprint();
        });
    }

}

