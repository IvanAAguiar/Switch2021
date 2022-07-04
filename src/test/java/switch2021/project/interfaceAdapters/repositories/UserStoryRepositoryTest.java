package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dataModel.JPA.UserStoryJpa;
import switch2021.project.dataModel.JPA.assembler.UserStoryJpaAssembler;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UsHour;
import switch2021.project.entities.valueObjects.vos.UsPriority;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import switch2021.project.interfaceAdapters.repositories.UserStoryRepository;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.persistence.UserStoryJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserStoryRepositoryTest {

    @MockBean
    private UserStoryJpaRepository jpaRepository;
    @MockBean
    private UserStoryJpaAssembler assembler;
    @InjectMocks
    UserStoryRepository userStoryRepo;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByUserStoryByIdSuccess() {
        //Arrange
        UserStoryID id = mock(UserStoryID.class);
        UserStoryJpa userStoryJpa = mock(UserStoryJpa.class);
        UserStory userStory = mock(UserStory.class);
        when(jpaRepository.findById(id)).thenReturn(Optional.of(userStoryJpa));
        when(assembler.toDomain(userStoryJpa)).thenReturn(userStory);
        Optional<UserStory> expected = Optional.of(userStory);
        //Act
        Optional<UserStory> result = userStoryRepo.findByUserStoryId(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void findByUserStoryByIdNull() {
        //Arrange
        UserStoryID id = mock(UserStoryID.class);
        UserStoryJpa userStoryJpa = mock(UserStoryJpa.class);
        UserStory userStory = mock(UserStory.class);
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());
        when(assembler.toDomain(userStoryJpa)).thenReturn(userStory);
        Optional<UserStory> expected = Optional.empty();
        //Act
        Optional<UserStory> result = userStoryRepo.findByUserStoryId(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void findAll() {
        //Arrange
        UserStoryJpa jpa1 = mock(UserStoryJpa.class);
        UserStoryJpa jpa2 = mock(UserStoryJpa.class);
        List<UserStoryJpa> jpas = new ArrayList<>();
        jpas.add(jpa1);
        jpas.add(jpa2);
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        List<UserStory> uss = new ArrayList<>();
        uss.add(us1);
        uss.add(us2);
        when(jpaRepository.findAll()).thenReturn(jpas);
        when(assembler.toDomain(jpas)).thenReturn(uss);
        //Act
        List<UserStory> result = userStoryRepo.findAll();
        //Assert
        assertEquals(uss, result);
    }

    @Test
    public void findAllEmpty() {
        //Arrange
        List<UserStoryJpa> jpas = new ArrayList<>();
        List<UserStory> uss = new ArrayList<>();
        when(jpaRepository.findAll()).thenReturn(null);
        when(assembler.toDomain(jpas)).thenReturn(uss);
        //Act
        List<UserStory> result = userStoryRepo.findAll();
        //Assert
        assertEquals(uss, result);
    }

    @Test
    public void findProductBacklogSuccess() {
        //Arrange
        UserStoryJpa jpa1 = mock(UserStoryJpa.class);
        UserStoryJpa jpa2 = mock(UserStoryJpa.class);
        List<UserStoryJpa> jpas = new ArrayList<>();
        jpas.add(jpa1);
        jpas.add(jpa2);
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        List<UserStory> uss = new ArrayList<>();
        uss.add(us1);
        uss.add(us2);
        List<UserStory> productBacklog = new ArrayList<>();
        productBacklog.add(us1);
        when(jpaRepository.findAll()).thenReturn(jpas);
        when(assembler.toDomain(jpas)).thenReturn(uss);
        when(us1.hasProjectId("Project_2022_1")).thenReturn(true);
        when(us2.hasProjectId("Project_2022_1")).thenReturn(false);
        //Act
        List<UserStory> result = userStoryRepo.findProductBacklog("Project_2022_1");
        //Assert
        assertEquals(productBacklog, result);
    }

    @Test
    public void findProductBacklogFail() {
        //Arrange
        List<UserStoryJpa> jpas = new ArrayList<>();
        List<UserStory> uss = new ArrayList<>();
        List<UserStory> productBacklog = new ArrayList<>();
        when(jpaRepository.findAll()).thenReturn(null);
        when(assembler.toDomain(jpas)).thenReturn(uss);
        //Act
        List<UserStory> result = userStoryRepo.findProductBacklog("Project_2022_1");
        //Assert
        assertEquals(productBacklog, result);
    }

    @Test
    public void existUserStoryByIdTrue() {
        //Arrange
        UserStoryID id = mock(UserStoryID.class);
        when(jpaRepository.existsById(id)).thenReturn(true);
        //Act and Assert
        assertTrue(userStoryRepo.existsUserStoryByID(id));
    }

    @Test
    public void existUserStoryByIdFalse() {
        //Arrange
        UserStoryID id = mock(UserStoryID.class);
        when(jpaRepository.existsById(id)).thenReturn(false);
        //Act and Assert
        assertFalse(userStoryRepo.existsUserStoryByID(id));
    }

    @Test
    public void deleteUSById() {
        //Arrange
        UserStoryJpa jpa1 = mock(UserStoryJpa.class);
        UserStoryJpa jpa2 = mock(UserStoryJpa.class);
        List<UserStoryJpa> jpas = new ArrayList<>();
        jpas.add(jpa1);
        jpas.add(jpa2);
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        List<UserStory> uss = new ArrayList<>();
        uss.add(us1);
        uss.add(us2);
        when(jpaRepository.findAll()).thenReturn(jpas);
        when(assembler.toDomain(jpas)).thenReturn(uss);
        List<UserStory> result = userStoryRepo.findAll();
        UserStoryID id = mock(UserStoryID.class);
        //Act
        userStoryRepo.deleteByUserStoryId(id);
        //Assert
        assertEquals(uss, result);
    }
}
