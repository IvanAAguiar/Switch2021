package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.JPA.UserProfileJpa;
import switch2021.project.dataModel.JPA.assembler.UserProfileJpaAssembler;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import switch2021.project.persistence.UserProfileJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserProfileRepositoryTest {

    @MockBean
    UserProfileJpaRepository jpaRepository;
    @MockBean
    UserProfileJpaAssembler assembler;
    @InjectMocks
    UserProfileRepository repository;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByUserProfileIDSuccess() {
        //Arrange
        UserProfileID id = mock(UserProfileID.class);
        UserProfileJpa jpa = mock(UserProfileJpa.class);
        UserProfile userProfile = mock(UserProfile.class);
        when(jpaRepository.findById(id)).thenReturn(Optional.of(jpa));
        when(assembler.toDomain(jpa)).thenReturn(userProfile);
        //Act
        Optional<UserProfile> result = repository.findByUserProfileID(id);
        //Assert
        assertEquals(Optional.of(userProfile), result);
    }

    @Test
    public void findByUserProfileIDFail() {
        //Arrange
        UserProfileID id = mock(UserProfileID.class);
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());
        //Act
        Optional<UserProfile> result = repository.findByUserProfileID(id);
        //Assert
        assertEquals(Optional.empty(),result);
    }

    @Test
    public void findAllSuccess() {
        //Arrange
        UserProfileJpa jpa = mock(UserProfileJpa.class);
        List<UserProfileJpa> jpas = new ArrayList<>();
        jpas.add(jpa);
        UserProfile userProfile = mock(UserProfile.class);
        when(jpaRepository.findAll()).thenReturn(jpas);
        when(assembler.toDomain(jpa)).thenReturn(userProfile);
        List<UserProfile> expected = new ArrayList<>();
        expected.add(userProfile);
        //Act
        List<UserProfile> result = repository.findAll();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void saveUserProfileSuccess() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileJpa jpa = mock(UserProfileJpa.class);
        when(assembler.toData(userProfile)).thenReturn(jpa);
        when(jpaRepository.save(jpa)).thenReturn(jpa);
        when(assembler.toDomain(jpa)).thenReturn(userProfile);
        //Act
        UserProfile result = repository.save(userProfile);
        //Assert
        assertEquals(userProfile, result);
    }

    @Test
    public void saveUserProfileFail() {
        //Arrange
        UserProfile userProfile = mock(UserProfile.class);
        UserProfileJpa jpa = mock(UserProfileJpa.class);
        when(assembler.toData(userProfile)).thenReturn(jpa);
        when(jpaRepository.save(jpa)).thenReturn(null);
        //Act and Assert
        assertNull(repository.save(userProfile));
    }

    @Test
    public void existByUserProfileId_Success() {
        //Arrange
        UserProfileID id = mock(UserProfileID.class);
        when(jpaRepository.existsById(id)).thenReturn(true);
        //Act and Assert
        assertTrue(repository.existsByUserProfileId(id));
    }

    @Test
    public void existByUserProfileId_Failure() {
        //Arrange
        UserProfileID id = mock(UserProfileID.class);
        when(jpaRepository.existsById(id)).thenReturn(false);
        //Act and Assert
        assertFalse(repository.existsByUserProfileId(id));
    }

    @Test
    public void deleteByUserProfileId_Success() {
        //Arrange
        UserProfileID id = mock(UserProfileID.class);
        when(repository.existsByUserProfileId(id)).thenReturn(true);
        //Act and Assert
        assertTrue(repository.deleteById(id));
    }

    @Test
    public void deleteByUserProfileId_Failure() {
        //Arrange
        UserProfileID id = mock(UserProfileID.class);
        when(repository.existsByUserProfileId(id)).thenReturn(false);
        //Act and Assert
        assertFalse(repository.deleteById(id));
    }

//    @Test
//    @DisplayName("Override Test")
//    public void overrideTest_1() {
//        //Arrange
//        UserProfileRepository userProfileRepository = new UserProfileRepository();
//        UserProfile user1 = mock(UserProfile.class);
//        UserProfile user2 = mock(UserProfile.class);
//        UserProfileID id1 = mock(UserProfileID.class);
//        UserProfileID id2 = mock(UserProfileID.class);
//        Description description1 = mock(Description.class);
//        Description description2 = mock(Description.class);
//        //Act
//        when(user1.getUserProfileId()).thenReturn(id1);
//        when(user2.getUserProfileId()).thenReturn(id2);
//        when(id1.getUserProfileName()).thenReturn(description1);
//        when(id2.getUserProfileName()).thenReturn(description2);
//        when(description1.getText()).thenReturn("User Profile 1");
//        when(description2.getText()).thenReturn("User Profile 2");
//        userProfileRepository.save(user1);
//        userProfileRepository.save(user2);
//        List<UserProfile> list = userProfileRepository.findAllUserProfiles();
//        //Assert
//        assertEquals(user1, user1);
//        assertNotEquals(user1, user2);
//        assertEquals(list, list);
//    }
//
//    @Test
//    @DisplayName("HashCode Test")
//    public void hasCodeTest_1() {
//        //Arrange
//        UserProfileRepository userProfileRepository = new UserProfileRepository();
//        UserProfile user1 = mock(UserProfile.class);
//        UserProfile user2 = mock(UserProfile.class);
//        UserProfileID id1 = mock(UserProfileID.class);
//        UserProfileID id2 = mock(UserProfileID.class);
//        Description description1 = mock(Description.class);
//        Description description2 = mock(Description.class);
//        //Act
//        when(user1.getUserProfileId()).thenReturn(id1);
//        when(user2.getUserProfileId()).thenReturn(id2);
//        when(id1.getUserProfileName()).thenReturn(description1);
//        when(id2.getUserProfileName()).thenReturn(description2);
//        when(description1.getText()).thenReturn("User Profile 1");
//        when(description2.getText()).thenReturn("User Profile 2");
//        userProfileRepository.save(user1);
//        userProfileRepository.save(user2);
//        List<UserProfile> list = userProfileRepository.findAllUserProfiles();
//        //Assert
//        assertEquals(user1.hashCode(), user1.hashCode());
//        assertNotEquals(user1.hashCode(), user2.hashCode());
//        assertEquals(list.hashCode(), list.hashCode());
//    }
}
