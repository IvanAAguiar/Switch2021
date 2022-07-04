package switch2021.project.entities.valueObjects.vos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Nif;

class NifTest {

    @Test
    void checkNIFMinLimitLength(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            long num = 12345678;
            //Act
            Nif teste = new Nif(num);
        });
    }

    @Test
    void checkNIFMaxLimitLength(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            long num = 1234567890;
            //Act
            Nif teste = new Nif(num);
        });
    }

    @Test
    void checkControlDigitNIFFail(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            long num = 123456780;
            //Act
            Nif teste = new Nif(num);
        });
    }

    @Test
    void checkControlDigitNIFSuccess(){
        //Arrange
        long num = 123456789;
        //Act
        Nif test = new Nif();
        test.setNumber(num);
        //Assert
        assertEquals(num, test.getNumber());

    }

    @Test
    void checkControlDigitNIFFail2(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            long num = 987654321;
            //Act
            Nif teste = new Nif(num);
        });
    }

    @Test
    void checkControlDigitNIFSuccess2(){
        //Arrange
        long num = 987654322;
        //Act
        Nif test = new Nif(num);
        //Assert
        assertTrue(test.hasSameNif(num));

    }

    @Test
    void checkDifferentNifs(){
        //Arrange
        long num1 = 123456789;
        long num2 = 987654322;
        //Act
        Nif test = new Nif(num1);
        //Assert
        assertFalse(test.hasSameNif(num2));
    }

}