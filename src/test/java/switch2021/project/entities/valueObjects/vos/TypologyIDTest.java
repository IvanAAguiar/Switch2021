package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TypologyIDTest {

    @Test
    public void getDescription_Success() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID();
        id.setDescription(des);
        //Act
        when(des.getText()).thenReturn("test");
        Description id_des = id.getDescription();
        String expected_des = "test";
        //Assert
        assertEquals(expected_des, id_des.getText());
    }

    @Test
    public void hasDescriptionSuccess() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID(des);
        //Act
        when(des.getText()).thenReturn("Test");
        //Assert
        assertTrue(id.hasDescription("Test"));
    }

    @Test
    public void hasDescriptionFail() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID(des);
        //Act
        when(des.getText()).thenReturn("Fail");
        //Assert
        assertFalse(id.hasDescription("Test"));
    }

    @Test
    public void equalsSuccess() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID(des);
        TypologyID expected = new TypologyID(des);
        //Act
        when(des.getText()).thenReturn("Test");
        //Assert
        assertEquals(expected, id);
    }

    @Test
    public void equalsFail() {
        //Arrange
        Description des = mock(Description.class);
        Description desEx = mock(Description.class);
        TypologyID id = new TypologyID(des);
        TypologyID expected = new TypologyID(desEx);
        //Act
        when(des.getText()).thenReturn("Test");
        when(desEx.getText()).thenReturn("Fail");
        //Assert
        assertNotEquals(expected, id);
    }

    @Test
    public void ClassFail() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID(des);
        Typology expected = new Typology(id);
        //Act and Assert
        assertNotEquals(expected, id);
    }

    @Test
    public void EqualsNullFail() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID(des);
        Typology expected = null;
        //Act and Assert
        assertNotEquals(expected, id);
    }

    @Test
    public void hashSuccess() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID(des);
        TypologyID expected = new TypologyID(des);
        //Act
        int hash_id = id.hashCode();
        int hash_exp = expected.hashCode();
        // Assert
        assertEquals(hash_exp, hash_id);
    }

    @Test
    public void hashFail() {
        //Arrange
        Description des = mock(Description.class);
        Description desEx = mock(Description.class);
        TypologyID id = new TypologyID(des);
        TypologyID expected = new TypologyID(desEx);
        //Act
        int hash_id = id.hashCode();
        int hash_exp = expected.hashCode();
        // Assert
        assertNotEquals(hash_exp, hash_id);
    }

    @Test
    public void sameValueAs_True() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID(des);
        TypologyID expected = new TypologyID(des);
        //Act
        when(des.getText()).thenReturn("Test");
        //Assert
        assertTrue(id.sameValueAs(expected));
    }

    @Test
    public void sameValueAs_False() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = new TypologyID(des);
        TypologyID expected = mock(TypologyID.class);
        Description des_ex = mock(Description.class);
        //Act
        when(des.getText()).thenReturn("Test");
        when(expected.getDescription()).thenReturn(des_ex);
        when(des_ex.getText()).thenReturn("Fail");
        //Assert
        assertFalse(id.sameValueAs(expected));
    }
}