package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutputProjectRoleDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectRoleMapper {

    public OutputProjectRoleDTO toDTO(String role) {
        return new OutputProjectRoleDTO(role);
    }

    public CollectionModel<OutputProjectRoleDTO> toCollectionDto(List<String> roles) {
        List<OutputProjectRoleDTO> projectRoles = new ArrayList<>();

        for (String role : roles) {
            projectRoles.add(new OutputProjectRoleDTO(role));
        }
        return CollectionModel.of(projectRoles);
    }
}
