package switch2021.project.dataModel.REST.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dataModel.REST.UserProfileRestDTO;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectDomainDataRestAssemblerTest {

    @MockBean
    private IUserProfileIDFactory idFactory;
    @InjectMocks
    private UserProfileDomainDataRestAssembler assembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toDomain() {
        //Arrange
        UserProfileRestDTO dto = mock(UserProfileRestDTO.class);
        UserProfileID id = mock(UserProfileID.class);
        when(dto.getDesignation()).thenReturn("designation");
        when(idFactory.createUserProfileID(dto.getDesignation())).thenReturn(id);
        List<UserProfileRestDTO> dtos = new ArrayList<>();
        dtos.add(dto);

        //Act
        List<UserProfile> result = assembler.toDomain(dtos);
        //Assert
        assertEquals(1, result.size());
    }
}