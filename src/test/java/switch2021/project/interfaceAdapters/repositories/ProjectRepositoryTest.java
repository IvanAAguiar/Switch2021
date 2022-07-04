package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.JPA.ProjectJpa;
import switch2021.project.dataModel.JPA.assembler.ProjectJpaAssembler;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.persistence.ProjectJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProjectRepositoryTest {

    @MockBean
    private ProjectJpaRepository projectJpaRepository;

    @MockBean
    private ProjectJpaAssembler projectJpaAssembler;

    @InjectMocks
    private ProjectRepository projRepo;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @DisplayName("Save success")
    @Test
    public void saveSuccess() {
        //Arrange
        ProjectJpa projJpa = mock(ProjectJpa.class);
        ProjectJpa savedProjJpa = mock(ProjectJpa.class);
        Project project = mock(Project.class);
        when(projectJpaAssembler.toJpaData(project)).thenReturn(projJpa);
        when(projectJpaRepository.save(projJpa)).thenReturn(savedProjJpa);
        when(projectJpaAssembler.toDomain(savedProjJpa)).thenReturn(project);
        //Act
        Project result = projRepo.save(project);

        //Assert
        assertEquals(result, project);
    }

    @DisplayName("Find by id success")
    @Test
    public void findByIdSuccess() {
        //Arrange
        ProjectID projId = mock(ProjectID.class);
        ProjectJpa projJpa = mock(ProjectJpa.class);
        Project project = mock(Project.class);
        when(projectJpaRepository.findById(projId)).thenReturn(Optional.of(projJpa));
        when(projectJpaAssembler.toDomain(projJpa)).thenReturn(project);

        //Act
        Optional<Project> result = projRepo.findById(projId);

        //Assert
        assertEquals(result, Optional.of(project));
    }

    @DisplayName("Find All success")
    @Test
    public void findAllSuccess() {
        //Arrange
        ProjectJpa projJpa1 = mock(ProjectJpa.class);
        ProjectJpa projJpa2 = mock(ProjectJpa.class);
        ProjectJpa projJpa3 = mock(ProjectJpa.class);

        List<ProjectJpa> projJpaList = new ArrayList<>();
        projJpaList.add(projJpa1);
        projJpaList.add(projJpa2);
        projJpaList.add(projJpa3);

        Project project1 = mock(Project.class);
        Project project2 = mock(Project.class);
        Project project3 = mock(Project.class);
        List<Project> projList = new ArrayList<>();
        projList.add(project1);
        projList.add(project2);
        projList.add(project3);

        when(projectJpaRepository.findAll()).thenReturn(projJpaList);
        when(projectJpaAssembler.toDomain(projJpa1)).thenReturn(project1);
        when(projectJpaAssembler.toDomain(projJpa2)).thenReturn(project2);
        when(projectJpaAssembler.toDomain(projJpa3)).thenReturn(project3);

        //Act
        List<Project> result = projRepo.findAll();

        //Assert
        assertEquals(result, projList);
    }

    @DisplayName("Delete by Id success")
    @Test
    public void deleteByIdSuccess() {
        //Arrange
        ProjectID projId = mock(ProjectID.class);
        when(projectJpaRepository.existsById(projId)).thenReturn(true);

        //Act
        boolean result = projRepo.delete(projId);

        //Assert
        assertTrue(result);
    }

    @DisplayName("Delete by Id fail")
    @Test
    public void deleteByIdFail() {
        //Arrange
        ProjectID projId = mock(ProjectID.class);
        when(projectJpaRepository.existsById(projId)).thenReturn(false);

        //Act
        boolean result = projRepo.delete(projId);

        //Assert
        assertFalse(result);
    }

    @Test
    public void existByIdTrue() {
        //Arrange
        ProjectID id = mock(ProjectID.class);
        when(projectJpaRepository.existsById(id)).thenReturn(true);
        //Act and Assert
        assertTrue(projRepo.existsById(id));
    }

    @Test
    public void existByIdFalse() {
        //Arrange
        ProjectID id = mock(ProjectID.class);
        when(projectJpaRepository.existsById(id)).thenReturn(false);
        //Act and Assert
        assertFalse(projRepo.existsById(id));
    }
}
