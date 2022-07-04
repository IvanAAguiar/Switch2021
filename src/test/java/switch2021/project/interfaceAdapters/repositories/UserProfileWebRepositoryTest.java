package switch2021.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.JPA.UserProfileJpa;
import switch2021.project.dataModel.REST.UserProfileRestDTO;
import switch2021.project.dataModel.REST.assemblers.UserProfileDomainDataRestAssembler;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.interfaceAdapters.repositories.REST.UserProfileRestRepository;

import javax.net.ssl.SSLException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserProfileWebRepositoryTest {

    @MockBean
    private UserProfileDomainDataRestAssembler assembler;
    @MockBean
    private UserProfileRestRepository repository;
    @InjectMocks
    private UserProfileWebRepository webRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() throws SSLException {
        //Arrange
        List<UserProfileRestDTO> userProfileList = new ArrayList<>();
        UserProfile userProfile = mock(UserProfile.class);
        List<UserProfile> userProfiles = new ArrayList<>();
        userProfiles.add(userProfile);
        when(repository.findAll()).thenReturn(userProfileList);
        when(assembler.toDomain(userProfileList)).thenReturn(userProfiles);
        //Act
        List<UserProfile> result = webRepository.findAll();
        //Assert
        assertEquals(userProfiles, result);
    }
}