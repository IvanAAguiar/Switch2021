package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITypologyIDFactory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;

@Component
public class TypologyIDFactory implements ITypologyIDFactory {

    @Autowired
    private IDescriptionFactory descFact;

    @Override
    public TypologyID createId(TypologyDTO inputDto) {
        Description des = descFact.createDescription(inputDto.getDescription());
        return new TypologyID(des);
    }

    @Override
    public TypologyID createIdWithString(String typoId) {
        Description des = descFact.createDescription(typoId);
        return new TypologyID(des);
    }
}
