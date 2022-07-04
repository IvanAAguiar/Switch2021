package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutputStatusDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusMapper {

    public OutputStatusDTO toDTO(String status) {
        return new OutputStatusDTO(status);
    }

    public CollectionModel<OutputStatusDTO> toCollectionDto(List<String> status) {
        List<OutputStatusDTO> projectStatusEnum = new ArrayList<>();

        for (String status_i : status) {
            projectStatusEnum.add(new OutputStatusDTO(status_i));
        }
        return CollectionModel.of(projectStatusEnum);
    }
}
