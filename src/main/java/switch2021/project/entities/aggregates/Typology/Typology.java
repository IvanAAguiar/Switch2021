package switch2021.project.entities.aggregates.Typology;

import lombok.Getter;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import switch2021.project.utils.Entity;

import java.util.Objects;

/**
 * Typology must be a part of information which compose the project.
 * In this way, typology is considerate an entity even though it is really close of a
 * value object. It was defined to implement the feature to create a typology and make
 * possible the user maintenance.
 * Typology has as attribute a description which will provide the typology´s value
 * and that will be considerate as ID for itself.
 */

@Getter
public class Typology implements Entity<Typology> {

    /**
     * Typology attributes are composed of a description of the Typology.
     **/
    private final TypologyID descriptionID;


    /**
     * Constructors of typology´s class. Creates a new typology instance.
     **/
    public Typology(TypologyID id) {
        this.descriptionID = id;
    }


    /**
     * Methods
     */
    public TypologyID getDescriptionID() {return this.descriptionID;}

    public boolean hasDescriptionID(String description) {
        return this.descriptionID.hasDescription(description);
    }


    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typology that = (Typology) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionID);
    }

    @Override
    public boolean sameIdentityAs(Typology other) {
        return other != null && this.descriptionID.sameValueAs(other.getDescriptionID());
    }
}