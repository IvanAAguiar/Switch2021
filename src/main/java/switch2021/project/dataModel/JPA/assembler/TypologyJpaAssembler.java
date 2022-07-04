package switch2021.project.dataModel.JPA.assembler;

import org.springframework.stereotype.Service;
import switch2021.project.dataModel.JPA.TypologyJpa;
import switch2021.project.entities.aggregates.Typology.Typology;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypologyJpaAssembler {

    public TypologyJpa toData(Typology typology) {
        return new TypologyJpa(typology.getDescriptionID());
    }

    public List<TypologyJpa> toData(List<Typology> typologies) {
        List<TypologyJpa> typologyJpas = new ArrayList<>();

        for (Typology i : typologies) {
            typologyJpas.add(toData(i));
        }
        return typologyJpas;
    }

    public Typology toDomain(TypologyJpa typologyJpa) {
        return new Typology(typologyJpa.getId());
    }

    public List<Typology> toDomain(List<TypologyJpa> typologyJpas) {
        List<Typology> typologies = new ArrayList<>();

        for (TypologyJpa i : typologyJpas) {
            typologies.add(toDomain(i));
        }
        return typologies;
    }
}
