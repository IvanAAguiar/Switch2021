package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.valueObjects.vos.TypologyID;

public interface ITypologyIDFactory {
    TypologyID createId(TypologyDTO inputDto);

    TypologyID createIdWithString(String typoId);
}
