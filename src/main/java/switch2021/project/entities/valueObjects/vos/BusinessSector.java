package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;

@Getter
public class BusinessSector {

    /**
     * Business Sector class
     * Business Sector atributes are composed of a description of the business sector
     **/
    private final Description description;


    /**
     * Business Sector Constructor
     * Creates a new Business Sector instance.
     **/
    public BusinessSector(String description) {
        this.description = new Description(description);
    }
}
