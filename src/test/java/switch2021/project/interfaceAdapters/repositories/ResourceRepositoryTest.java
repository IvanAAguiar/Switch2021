package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.JPA.ResourceIDJpa;
import switch2021.project.dataModel.JPA.ResourceJpa;
import switch2021.project.dataModel.JPA.assembler.ResourceJpaAssembler;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.persistence.ResourceJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ResourceRepositoryTest {

    @MockBean
    private ResourceJpaRepository jpaRepository;
    @MockBean
    private ResourceJpaAssembler assembler;
    @InjectMocks
    private ResourceRepository repository;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdSuccess() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        Resource resource = mock(Resource.class);
        ResourceJpa jpa = mock(ResourceJpa.class);
        when(assembler.toData(id)).thenReturn(idJpa);
        when(jpaRepository.findById(idJpa)).thenReturn(Optional.of(jpa));
        when(assembler.toDomain(jpa)).thenReturn(resource);
        //Act
        Optional<Resource> result = repository.findById(id);
        //Assert
        assertEquals(Optional.of(resource), result);
    }

    @Test
    void findByIdFail() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        when(assembler.toData(id)).thenReturn(idJpa);
        when(jpaRepository.findById(idJpa)).thenReturn(Optional.empty());
        //Act
        Optional<Resource> result = repository.findById(id);
        //Assert
        assertEquals(Optional.empty(), result);
    }

    @Test
    void findAll() {
        //Arrange
        Resource resource = mock(Resource.class);
        ResourceJpa jpa = mock(ResourceJpa.class);
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        List<ResourceJpa> jpas = new ArrayList<>();
        jpas.add(jpa);
        when(jpaRepository.findAll()).thenReturn(jpas);
        when(assembler.toDomain(jpas)).thenReturn(resources);
        //Act
        List<Resource> result = repository.findAll();
        //Assert
        assertEquals(resources, result);
    }

    @Test
    void findAllByProject() {
        //Arrange
        ProjectID projectID = mock(ProjectID.class);
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        Resource resource = mock(Resource.class);
        ResourceJpa jpa = mock(ResourceJpa.class);
        when(jpa.getId()).thenReturn(idJpa);
        when(idJpa.getProject()).thenReturn(projectID);
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        List<ResourceJpa> jpas = new ArrayList<>();
        jpas.add(jpa);
        when(jpaRepository.findAll()).thenReturn(jpas);
        when(assembler.toDomain(jpas)).thenReturn(resources);
        //Act
        List<Resource> result = repository.findAllByProject(projectID);
        //Assert
        assertEquals(resources, result);
    }

    @Test
    void findAllByUser() {
        //Arrange
        UserID userID = mock(UserID.class);
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        Resource resource = mock(Resource.class);
        ResourceJpa jpa = mock(ResourceJpa.class);
        when(jpa.getId()).thenReturn(idJpa);
        when(idJpa.getUser()).thenReturn(userID);
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        List<ResourceJpa> jpas = new ArrayList<>();
        jpas.add(jpa);
        when(jpaRepository.findAll()).thenReturn(jpas);
        when(assembler.toDomain(jpas)).thenReturn(resources);
        //Act
        List<Resource> result = repository.findAllByUser(userID);
        //Assert
        assertEquals(resources, result);
    }

    @Test
    void existsByIdTrue() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        when(assembler.toData(id)).thenReturn(idJpa);
        when(jpaRepository.existsById(idJpa)).thenReturn(true);
        //Act and Assert
        assertTrue(repository.existsById(id));
    }

    @Test
    void existsByIdFalse() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        when(assembler.toData(id)).thenReturn(idJpa);
        when(jpaRepository.existsById(idJpa)).thenReturn(false);
        //Act and Assert
        assertFalse(repository.existsById(id));
    }

    @Test
    void savesuccess() {
        //Arrange
        Resource resource = mock(Resource.class);
        ResourceJpa jpa = mock(ResourceJpa.class);
        when(assembler.toData(resource)).thenReturn(jpa);
        when(jpaRepository.save(jpa)).thenReturn(jpa);
        when(assembler.toDomain(jpa)).thenReturn(resource);
        //Act
        Resource result = repository.save(resource);
        //Assert
        assertEquals(resource, result);
    }

    @Test
    void saveFail() {
        //Arrange
        Resource resource = mock(Resource.class);
        ResourceJpa jpa = mock(ResourceJpa.class);
        when(assembler.toData(resource)).thenReturn(jpa);
        when(jpaRepository.save(jpa)).thenReturn(null);
        //Act
        Resource result = repository.save(resource);
        //Assert
        assertNull(result);
    }

    @Test
    void deleteByResourceIDSuccess() {
            //Arrange
            ResourceID id = mock(ResourceID.class);
            ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
            when(assembler.toData(id)).thenReturn(idJpa);
            when(jpaRepository.existsById(idJpa)).thenReturn(true);
            //Act and Assert
            assertTrue(repository.deleteByResourceID(id));
    }

    @Test
    void deleteByResourceIDFail() {
        //Arrange
        ResourceID id = mock(ResourceID.class);
        ResourceIDJpa idJpa = mock(ResourceIDJpa.class);
        when(assembler.toData(id)).thenReturn(idJpa);
        when(jpaRepository.existsById(idJpa)).thenReturn(false);
        //Act and Assert
        assertFalse(repository.deleteByResourceID(id));
    }
}