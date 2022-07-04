package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Customer;
import switch2021.project.entities.valueObjects.vos.Description;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @DisplayName("Test to create Customer")
    @Test
    public void createCustomer() {
        //Arrange
        String customer = "Customer";
        //Act
        Customer customer1 = Customer.create(customer);
        //Assert
        assertEquals("Customer", customer1.getCustomerName().getText());
    }

    @DisplayName("Test to create Customer - Fail")
    @Test
    public void createCustomer_Fail() {
        //Arrange
        String x = "Customer";
        //Act
        Customer customer1 = Customer.create(x);
        //Assert
        assertNotEquals("Customer XPTO", customer1.getCustomerName().getText());
    }

    @DisplayName("Test to set Customer")
    @Test
    public void setCustomer() {
        //Arrange
        String customer = "Customer";
        Customer customer1 = Customer.create(customer);
        //Act
        String des = "IT";
        Description description = new Description(des);
        customer1.setCustomerName(description);
        //Assert
        assertEquals("IT", customer1.getCustomerName().getText());
    }


    @Test
    @DisplayName("Empty Constructor")
    public void emptyConstructor() {
        //Act
        Customer customer = new Customer();
        //Assert
        assertNull(customer.getCustomerName());
    }
}
