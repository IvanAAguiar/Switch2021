package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.applicationServices.iRepositories.ITypologyRepo;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.dtoModel.mapper.TypologyMapper;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.factories.factoryInterfaces.ITypologyFactory;
import switch2021.project.entities.valueObjects.voFactories.voFactories.TypologyIDFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TypologyServiceTest {

    @MockBean
    ITypologyRepo iTypologyRepo;
    @MockBean
    ITypologyFactory iTypologyFactory;
    @MockBean
    TypologyIDFactory factoryId;
    @MockBean
    TypologyMapper typologyMapper;
    @InjectMocks
    TypologyService typologyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAndSaveTypologySuccess() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        Typology typology = mock(Typology.class);
        TypologyID id = mock((TypologyID.class));
        TypologyDTO output = mock(TypologyDTO.class);
        Typology saved = mock(Typology.class);

        when(iTypologyFactory.createTypology(dto)).thenReturn(typology);
        when(typology.getDescriptionID()).thenReturn(id);
        when(iTypologyRepo.existsByTypologyId(id)).thenReturn(false);
        when(factoryId.createIdWithString("Fixed Cost")).thenReturn(id);
        when(iTypologyRepo.save(typology)).thenReturn(saved);
        when(typologyMapper.modelToDto(saved)).thenReturn(output);
        //Act
        TypologyDTO res = typologyService.createAndSaveTypology(dto);
        //Assert
        assertEquals(output, res);
    }

    @Test
    public void createAndSaveTypologyFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TypologyDTO dto = mock(TypologyDTO.class);
            Description des = mock(Description.class);
            TypologyID id = mock((TypologyID.class));
            Typology typo = mock(Typology.class);
            //Act
            when(dto.getDescription()).thenReturn("Fixed Cost");
            when(iTypologyFactory.createTypology(dto)).thenReturn(typo);
            when(iTypologyRepo.save(typo)).thenReturn(typo);
            when(iTypologyRepo.existsByTypologyId(typo.getDescriptionID())).thenReturn(true);
            when(des.getText()).thenReturn("Fixed Cost");
            when(id.getDescription()).thenReturn(des);
            when(typo.hasDescriptionID(des.getText())).thenReturn(true);
            typologyService.createAndSaveTypology(dto);
        });
    }

    @Test
    public void findTypologyByDescription_Null() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TypologyDTO dto = mock(TypologyDTO.class);
            Description des = mock(Description.class);
            TypologyID id = mock((TypologyID.class));
            Typology typo = mock(Typology.class);
            when(factoryId.createId(dto)).thenReturn(id);
            when(iTypologyFactory.createTypology(dto)).thenReturn(typo);
            when(iTypologyRepo.save(typo)).thenReturn(typo);
            when(des.getText()).thenReturn("Fixed Cost");
            when(id.getDescription()).thenReturn(des);
            when(typo.getDescriptionID()).thenReturn(id);
            when(typo.hasDescriptionID(des.getText())).thenReturn(true);
            when(iTypologyRepo.findByTypologyId(id)).thenReturn(null);
            //Act
            typologyService.findTypologyRequested("Fixed Cost");
        });
    }

    @DisplayName("Typology Non Exist")
    @Test
    void findTypologyNonExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            Typology typology = mock(Typology.class);
            TypologyID typologyID = mock(TypologyID.class);
            TypologyDTO typologyDTO = mock(TypologyDTO.class);
            when(iTypologyRepo.findByTypologyId(typologyID)).thenReturn(Optional.of(typology));
            when(typologyMapper.modelToDto(typology)).thenReturn(typologyDTO);
            //Act
            typologyService.findTypologyRequested("");
        });
    }

    @Test
    public void findAllTypology() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        TypologyDTO dto1 = mock(TypologyDTO.class);
        TypologyDTO dto2 = mock(TypologyDTO.class);
        Description des = mock(Description.class);
        Description des1 = mock(Description.class);
        Description des2 = mock(Description.class);
        TypologyID id = mock((TypologyID.class));
        TypologyID id1 = mock((TypologyID.class));
        TypologyID id2 = mock((TypologyID.class));
        Typology typo = mock(Typology.class);
        Typology typo1 = mock(Typology.class);
        Typology typo2 = mock(Typology.class);
        //Act
        when(dto.getDescription()).thenReturn("Test");
        when(dto1.getDescription()).thenReturn("Test1");
        when(dto2.getDescription()).thenReturn("Test2");
        when(iTypologyFactory.createTypology(dto)).thenReturn(typo);
        when(iTypologyFactory.createTypology(dto1)).thenReturn(typo1);
        when(iTypologyFactory.createTypology(dto2)).thenReturn(typo2);
        when(iTypologyRepo.save(typo)).thenReturn(typo);
        when(iTypologyRepo.save(typo1)).thenReturn(typo1);
        when(iTypologyRepo.save(typo2)).thenReturn(typo2);
        when(des.getText()).thenReturn("Test");
        when(des.getText()).thenReturn("Test1");
        when(des.getText()).thenReturn("Test2");
        when(id.getDescription()).thenReturn(des);
        when(id1.getDescription()).thenReturn(des1);
        when(id2.getDescription()).thenReturn(des2);
        when(typo.hasDescriptionID(des.getText())).thenReturn(false);
        when(typo1.hasDescriptionID(des1.getText())).thenReturn(false);
        when(typo2.hasDescriptionID(des2.getText())).thenReturn(false);
        when(iTypologyRepo.findAll()).thenReturn(List.of(new Typology[]{typo, typo1, typo2}));
        when(typologyMapper.toCollectionModel(iTypologyRepo.findAll())).thenReturn(CollectionModel.of(List.of(new TypologyDTO[]{dto,
                dto1, dto2})));
        //Assert
        assertEquals(3, typologyService.findAllTypologies().getContent().size());
    }

    @DisplayName("Delete Typology")
    @Test
    public void deleteTypology_Fail() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            String id = "Fixed Cost";
            TypologyID typologyID = mock(TypologyID.class);
            when(factoryId.createIdWithString("Fixed Cost")).thenReturn(typologyID);
            when(iTypologyRepo.findByTypologyId(typologyID)).thenReturn(Optional.empty());
            //Act
            typologyService.deleteTypology(id);
        });
    }

}
