package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.aggregates.Typology.Typology;

public interface ITypologyFactory {
    Typology createTypology(TypologyDTO inputDto);
}
