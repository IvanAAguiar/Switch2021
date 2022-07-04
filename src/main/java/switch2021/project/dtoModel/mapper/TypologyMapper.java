package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.interfaceAdapters.controller.TypologyController;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.aggregates.Typology.Typology;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TypologyMapper {

    /**
     * Attributes
     */
    List<TypologyDTO> typologyDTOList;


    /**
     * Methods to change data in to a Typology DTO.
     */
    public TypologyDTO modelToDto(Typology typology) {
        String description = typology.getDescriptionID().getDescription().getText();

        TypologyDTO result = new TypologyDTO(description);

        //Add HATEOAS to OUTPUT DTOs

        //Add self relation
        result.add(linkTo(methodOn(TypologyController.class).findTypologyRequested(result.description)).withSelfRel());
        //Add collection relation
        result.add(linkTo(methodOn(TypologyController.class).findTypologyList()).withRel("Collection"));
        //Add delete option
        result.add(linkTo(methodOn(TypologyController.class).deleteTypology(result.description)).withRel("Delete"));

        return result;
    }

    public List<TypologyDTO> modelToDto(List<Typology> typologyList) {
        this.typologyDTOList = new ArrayList<>();

        for(Typology typo : typologyList) {
            TypologyDTO typoDTO = modelToDto(typo);
            this.typologyDTOList.add(typoDTO);
        }
        return this.typologyDTOList;
    }

    public CollectionModel<TypologyDTO> toCollectionModel(List<Typology> typologies){

        CollectionModel<TypologyDTO> result = CollectionModel.of(typologies.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList()));

        //Add HATEOAS to OUTPUT DTOs

        //Add self relation
        result.add(linkTo(methodOn(TypologyController.class).findTypologyList()).withSelfRel());

        return result;
    }
}
