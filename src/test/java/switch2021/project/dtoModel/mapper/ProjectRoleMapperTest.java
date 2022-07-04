package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.OutputProjectRoleDTO;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRoleMapperTest {
    @Test
    void toDto() {
        //Arrange
        String role = "role";
        ProjectRoleMapper mapper = new ProjectRoleMapper();
        //Act
        OutputProjectRoleDTO result = mapper.toDTO(role);
        // Assert
        assertEquals(role, result.getRole());
    }

    @Test
    void toCollectionDto() {
        //Arrange
        List<String> roles = ProjectRole.getProjectRole();
        ProjectRoleMapper mapper = new ProjectRoleMapper();
        //Act
        CollectionModel<OutputProjectRoleDTO> result = mapper.toCollectionDto(roles);
        //Assert
        assertEquals(4,result.getContent().size());
    }
}