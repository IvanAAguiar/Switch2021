package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.Password;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordTest {


    @DisplayName("Password Success - 2 numbers")
    @Test
    public void ValidPasswordNumbers() {
        //Arrange
        Password pwd = new Password("Qwerty_12");
        Password pwdRes = new Password("Qwerty_12");

        //Assert
        assertEquals(pwdRes, pwd);
    }

    @DisplayName("Password Success - lower case limit")
    @Test
    public void ValidPasswordLowerCaseLimit() {
        //Arrange
        Password pwd = new Password("QWERTy_1");
        Password pwdRes = new Password("QWERTy_1");
        //Assert
        assertEquals(pwdRes, pwd);
    }

    @DisplayName("Password Fail - less 8")
    @Test
    public void PasswordFailLess8() {
        assertThrows(IllegalArgumentException.class, () -> new Password("Qwert_1"));
    }

    @DisplayName("Password Fail - no number")
    @Test
    public void PasswordFailNoNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Password("Qwerty_q"));
    }

    @DisplayName("Password Fail - no Upper Case")
    @Test
    public void PasswordFailNoUpperCase() {
        assertThrows(IllegalArgumentException.class, () -> new Password("qwerty_1"));
    }

    @DisplayName("Password Fail - no Lower Case")
    @Test
    public void PasswordFailNoLowerCase() {
        assertThrows(IllegalArgumentException.class, () -> new Password("QWERTY_1"));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        Password pwd = new Password("Qwerty_1");
        Password pwd1 = new Password("Qwerty_1");
        Password pwd2 = null;
        Description test = new Description("test");
        // Act
        assertEquals(pwd,pwd1);
        assertNotEquals(pwd, pwd2);
        assertNotEquals(pwd, test);
        assertEquals(pwd,pwd);
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Success(){
        //Arrange
        Password pwd = new Password("Qwerty_1");
        Password pwd1 = new Password("Qwerty_1");
        //Act and Assert
        assertEquals(pwd.hashCode(),pwd1.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    void hashCodeTest_Fail(){
        //Arrange
        Password pwd = new Password("Qwerty_1");
        Typology pwd1 = new Typology(new TypologyID(new Description("Test")));
        //Act and Assert
        assertNotEquals(pwd.hashCode(),pwd1.hashCode());
    }
}