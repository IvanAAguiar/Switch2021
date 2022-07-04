package switch2021.project.entities.aggregates.Typology;

import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TypologyTest {

    @Test
    public void get_TypologyTest() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        //Act
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        //Assert
        assertEquals("Test", typo.getDescriptionID().getDescription().getText());
    }

    @Test
    public void hasID_DescriptionSuccess() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        //Act
        when(id.hasDescription("Test")).thenReturn(true);
        //Assert
        assertTrue(typo.hasDescriptionID("Test"));
    }

    @Test
    public void hasID_DescriptionFail() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        //Act
        when(id.hasDescription("Test")).thenReturn(false);
        //Assert
        assertFalse(typo.hasDescriptionID("Fail"));
    }

    @Test
    public void equalsTestSuccess() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Typology typo2 = new Typology(id);
        //Act
        when(id.sameValueAs(id)).thenReturn(true);
        //Assert
        assertEquals(typo, typo2);
    }

    @Test
    public void equalsTestFail() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Typology typo2 = new Typology(id2);
        //Act
        when(id.sameValueAs(id2)).thenReturn(false);
        //Assert
        assertNotEquals(typo, typo2);
    }

    @Test
    public void typologyClass_True() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Typology typo2 = new Typology(id2);
        //Act
        when(id.sameValueAs(id2)).thenReturn(true);
        //Assert
        assertEquals(typo.getClass(), typo2.getClass());
    }

    @Test
    public void typologyClass_False() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Description typo2 = new Description("Test");
        //Assert
        assertNotEquals(typo.getClass(), typo2.getClass());
    }

    @Test
    public void typologyNull() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Typology typo2 = null;
        //Assert
        assertNotEquals(typo, typo2);
    }

    @Test
    public void hashCodeSuccess() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo1 = new Typology(id);
        Typology typo2 = new Typology(id);
        //Act
        int t1 = typo1.hashCode();
        int t2 = typo2.hashCode();
        //Assert
        assertEquals(t1, t2);
    }

    @Test
    public void hashCodeFail() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
        Typology typo1 = new Typology(id);
        Typology typo2 = new Typology(id2);
        //Act
        int t1 = typo1.hashCode();
        int t2 = typo2.hashCode();
        //Assert
        assertNotEquals(t1, t2);
    }

    @Test
    public void sameIdentityAs_true() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo1 = new Typology(id);
        Typology typo2 = new Typology(id);
        //Act
        when(id.sameValueAs(id)).thenReturn(true);
        //Assert
        assertTrue(typo1.sameIdentityAs(typo2));
    }

    @Test
    public void sameIdentityAs_false() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
        Typology typo1 = new Typology(id);
        Typology typo2 = new Typology(id2);
        //Act
        when(id.sameValueAs(id2)).thenReturn(false);
        //Assert
        assertFalse(typo1.sameIdentityAs(typo2));
    }

    @Test
    public void sameIdentityAs_Null() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo1 = new Typology(id);
        Typology typo2 = new Typology(null);
        //Act
        when(id.sameValueAs(null)).thenReturn(false);
        //Assert
        assertFalse(typo1.sameIdentityAs(typo2));
    }
}
