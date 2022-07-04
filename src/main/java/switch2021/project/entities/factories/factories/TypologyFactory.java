package switch2021.project.entities.factories.factories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.factories.factoryInterfaces.ITypologyFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITypologyIDFactory;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.TypologyID;

@Component
public class TypologyFactory implements ITypologyFactory {

    @Autowired
    private ITypologyIDFactory typoIDFact;

    public TypologyFactory(ITypologyIDFactory typoIDFact) {
        this.typoIDFact = typoIDFact;
    }

    @Override
    public Typology createTypology(TypologyDTO inputDto) {
        TypologyID id = typoIDFact.createId(inputDto);
        return new Typology(id);
    }
}
