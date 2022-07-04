package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Customer {

    /**
     * Customer Class
     * Attributes - The customer is composed of an automatically generated ID, an email address and a customer name.
     **/
    private Description customerName;

    /**
     * Customer Constructor
     * Creates a new Customer instance.
     **/

    private Customer(String customer) {
        this.customerName = new Description(customer);
    }

    public static Customer create(String customer){
        return new Customer(customer);
    }
}
