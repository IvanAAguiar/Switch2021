package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.OutputStatusDTO;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatusMapperTest {
    @Test
    void toDto() {
        //Arrange
        String status = "status";
        StatusMapper mapper = new StatusMapper();
        //Act
        OutputStatusDTO result = mapper.toDTO(status);
        // Assert
        assertEquals(status, result.getStatus());
    }

    @Test
    void toCollectionDto() {
        //Arrange
        List<String> status = ProjectStatusEnum.getProjectStatus();
        StatusMapper mapper = new StatusMapper();
        //Act
        CollectionModel<OutputStatusDTO> result = mapper.toCollectionDto(status);
        //Assert
        assertEquals(7,result.getContent().size());
    }
}