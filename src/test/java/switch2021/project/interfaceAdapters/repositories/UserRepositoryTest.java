package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.JPA.UserJpa;
import switch2021.project.dataModel.JPA.assembler.UserJpaAssembler;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.Email;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import switch2021.project.persistence.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserRepositoryTest {

    @MockBean
    private UserJpaRepository jpaRepository;
    @MockBean
    private UserJpaAssembler assembler;
    @InjectMocks
    UserRepository repository;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(".findSystemUserByEmail(String email)")
    void itShouldFindASystemUser() {
        //Arrange
        String email = "oliveira@beaver.com";
        User userDouble = mock(User.class);
        UserID idDouble = mock(UserID.class);
        Email emailDouble = mock(Email.class);
        //Stubbing behaviour of userDouble
        when(userDouble.getUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        repository.save(userDouble);

        when(userDouble.isYourEmail(email)).thenReturn(true);
        //Act
        Optional<User> actual = repository.findByUserId(idDouble);
        //Assert
        assertNotNull(actual);
    }

    @Test
    @DisplayName(".findSystemUserByEmail(String email)")
    void itShouldNotFindTheSystemUser() {
        //Arrange
        //User1
        String email = "oliveira@beaver.com";
        User userDouble = mock(User.class);
        UserID idDouble = mock(UserID.class);
        Email emailDouble = mock(Email.class);
        //Stubbing behaviour of User1
        when(userDouble.getUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        repository.save(userDouble);
        when(userDouble.isYourEmail(email)).thenReturn(true);
        //User2
        String email2 = "pereira@beaver.com";
        User user2Double = mock(User.class);
        UserID id2Double = mock(UserID.class);
        Email email2Double = mock(Email.class);
        when(user2Double.getUserId()).thenReturn(id2Double);
        when(id2Double.getEmail()).thenReturn(email2Double);
        when(email2Double.getEmailText()).thenReturn(email2);
        //Stubbing behaviour of User2
        when(user2Double.isYourEmail(email2)).thenReturn(false);
        //Act
        //User actual = underTest.findByUserID(email2);
        Optional<User> actual = repository.findByUserId(id2Double);
        //Assert
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName(".findAllSystemUsers()")
    void itShouldFindAnEmptyList() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            when(jpaRepository.findAll()).thenReturn(new ArrayList<>());
            //Act
            repository.findAll();
        });
    }

    @Test
    @DisplayName(".findAllSystemUsers()")
    void itShouldFindAList() {
        //Arrange
        User user = mock(User.class);
        UserJpa userDouble = mock(UserJpa.class);
        List<UserJpa> foundUsers = new ArrayList<>();
        foundUsers.add(userDouble);
        List<User> found = new ArrayList<>();
        found.add(user);
        when(jpaRepository.findAll()).thenReturn(foundUsers);
        when(assembler.toDomain(foundUsers)).thenReturn(found);
        //Act
        List<User> actualList = repository.findAll();
        //Assert
        assertEquals(1, actualList.size());
    }

    @Test
    @DisplayName(".findAllByNameContains()")
    void findAllByNameContainsTrue() {
        //Arrange
        String name = "Zé";
        User user = mock(User.class);
        UserJpa userDouble = mock(UserJpa.class);
        List<UserJpa> foundUsers = new ArrayList<>();
        List<User> found = new ArrayList<>();
        when(jpaRepository.findAll()).thenReturn(foundUsers);
        when(assembler.toDomain(userDouble)).thenReturn(user);
        foundUsers.add(userDouble);
        found.add(user);
        when(user.hasName(name)).thenReturn(true);
        //Act
        List<User> actualList = repository.findAllByNameContains(name);
        //Assert
        assertEquals(found, actualList);
    }

    @Test
    @DisplayName(".findAllByNameContains()")
    void findAllByNameContainsFalse() {
        //Arrange
        String name = "Zé";
        User user = mock(User.class);
        UserJpa userDouble = mock(UserJpa.class);
        List<UserJpa> foundUsers = new ArrayList<>();
        foundUsers.add(userDouble);
        when(jpaRepository.findAll()).thenReturn(foundUsers);
        when(assembler.toDomain(userDouble)).thenReturn(user);
        when(user.hasName(name)).thenReturn(false);
        List<User> expected = new ArrayList<>();
        //Act
        List<User> actualList = repository.findAllByNameContains(name);
        //Assert
        assertEquals(expected, actualList);
    }

    @Test
    @DisplayName(".findAllByFunctionContains()")
    void findAllByFunctionContainsTrue() {
        //Arrange
        String function = "Tester";
        User user = mock(User.class);
        UserJpa userDouble = mock(UserJpa.class);
        List<UserJpa> foundUsers = new ArrayList<>();
        foundUsers.add(userDouble);
        List<User> found = new ArrayList<>();
        found.add(user);
        when(jpaRepository.findAll()).thenReturn(foundUsers);
        when(assembler.toDomain(userDouble)).thenReturn(user);
        when(user.hasFunction(function)).thenReturn(true);
        //Act
        List<User> actualList = repository.findAllByFunctionContains(function);
        //Assert
        assertEquals(found, actualList);
    }

    @Test
    @DisplayName(".findAllByFunctionContains()")
    void findAllByFunctionContainsFalse() {
        //Arrange
        String function = "tester";
        User user = mock(User.class);
        UserJpa userDouble = mock(UserJpa.class);
        List<UserJpa> foundUsers = new ArrayList<>();
        foundUsers.add(userDouble);
        when(jpaRepository.findAll()).thenReturn(foundUsers);
        when(assembler.toDomain(userDouble)).thenReturn(user);
        when(user.hasFunction(function)).thenReturn(false);
        List<User> expected = new ArrayList<>();
        //Act
        List<User> actualList = repository.findAllByFunctionContains(function);
        //Assert
        assertEquals(expected, actualList);
    }

    @Test
    @DisplayName(".findAllByUserProfileContains()")
    void findAllByUserProfileContainsTrue() {
        //Arrange
        UserProfileID profile = mock(UserProfileID.class);
        User user = mock(User.class);
        UserJpa userDouble = mock(UserJpa.class);
        List<UserJpa> foundUsers = new ArrayList<>();
        foundUsers.add(userDouble);
        List<User> found = new ArrayList<>();
        found.add(user);
        when(jpaRepository.findAll()).thenReturn(foundUsers);
        when(assembler.toDomain(userDouble)).thenReturn(user);
        when(user.hasProfile(profile)).thenReturn(true);
        //Act
        List<User> actualList = repository.findAllByUserProfileContains(profile);
        //Assert
        assertEquals(found, actualList);
    }

    @Test
    @DisplayName(".findAllByUserProfileContains()")
    void findAllByUserProfileContainsFalse() {
        //Arrange
        UserProfileID profile = mock(UserProfileID.class);
        User user = mock(User.class);
        UserJpa userDouble = mock(UserJpa.class);
        List<UserJpa> foundUsers = new ArrayList<>();
        foundUsers.add(userDouble);
        when(jpaRepository.findAll()).thenReturn(foundUsers);
        when(assembler.toDomain(userDouble)).thenReturn(user);
        when(user.hasProfile(profile)).thenReturn(false);
        List<User> expected = new ArrayList<>();
        //Act
        List<User> actualList = repository.findAllByUserProfileContains(profile);
        //Assert
        assertEquals(expected, actualList);
    }

    @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldSaveSystemUser() {
        //Arrange
        User userDouble = mock(User.class);
        UserJpa userJpa = mock(UserJpa.class);
        when(assembler.toData(userDouble)).thenReturn(userJpa);
        when(jpaRepository.saveAndFlush(userJpa)).thenReturn(userJpa);
        when(assembler.toDomain(userJpa)).thenReturn(userDouble);
        User expected = assembler.toDomain(userJpa);
        //Act
        User result = repository.save(userDouble);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldFailSaveSystemUserPresent() {
        //Arrange
        User userDouble = mock(User.class);
        UserJpa userJpa = mock(UserJpa.class);
        when(assembler.toData(userDouble)).thenReturn(userJpa);
        when(jpaRepository.saveAndFlush(userJpa)).thenReturn(null);
        //Act
        User result = repository.save(userDouble);
        //Assert
        assertNull(result);
    }

    @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldFailSaveNull() {
        //Act
        User result = repository.save(null);
        //Assert
        assertNull(result);
    }

    @Test
    void itShouldCheckIfSystemUserExistsByEmail() {
        //Arrange
        User userDouble = mock(User.class);
        UserID idDouble = mock(UserID.class);
        when(userDouble.getUserId()).thenReturn(idDouble);
        when(jpaRepository.existsById(idDouble)).thenReturn(true);
        //Act
        boolean exists = repository.existsById(idDouble);
        //Assert
        assertTrue(exists);
    }

    @Test
    void itShouldCheckIfSystemUserExistsByEmailFail() {
        //Arrange
        User userDouble = mock(User.class);
        UserID idDouble = mock(UserID.class);
        when(userDouble.getUserId()).thenReturn(idDouble);
        when(jpaRepository.existsById(idDouble)).thenReturn(false);
        //Act
        boolean exists = repository.existsById(idDouble);
        //Assert
        assertFalse(exists);
    }

    @Test
    public void deleteUserFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            UserID id = mock(UserID.class);
            when(jpaRepository.existsById(id)).thenReturn(false);
            //Act
            repository.delete(id);
        });
    }
}
