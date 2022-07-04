package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void NullTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Act
            new Email(null);
        });

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void emailEmptyOrBlankTest(String x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange and Act
            new Email(x);
        });
    }

    @Test
    void withoutAtSignTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123test.pt";
            //Act
            new Email(test);
        });
    }

    @Test
    void withAtSignFirstPositionTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "@123test.pt";
            //Act
            new Email(test);
        });
    }

    @Test
    void withAtSignLastPositionTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123test.pt@";
            //Act
            new Email(test);
        });
    }

    @Test
    void withoutDotAfterAtSignTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@test";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"!", "#", "$", "%", "&", "*", "(", ")", "'", "+", ",", "/", ":", ";", "<", "=", ">", "?", "[", "]", "^", "`", "{", "|", "}"})
    void notAllowedSpecialCharactersTest(String x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "1" + x +"23@test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 51, 60})
    void lengthRulesBeforeAtSignFailTest(int x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[x];
            Arrays.fill(data, 'a');
            String local = new String(data);
            String test = local + "@test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 51, 60})
    void lengthRulesAfterAtSignFailTest(int x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[x];
            Arrays.fill(data, 'a');
            String domain = new String(data);
            String test = "123@" + domain + ".pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 10})
    void lengthRulesLastPartFailTest(int x){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            char[] data = new char[x];
            Arrays.fill(data, 'a');
            String lastPart = new String(data);
            String test = "123@test." + lastPart;
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreFirstBeforeAtSignTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = c + "123@test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreLastBeforeAtSignTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123" + c + "@test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreFirstAfterAtSignBeforeDotTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@" + c + "test.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreLastAfterAtSignBeforeDotTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@test" + c + ".pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreFirstAfterAtSignAfterDotTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@test." + c + "pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {".","_","-"})
    void dotHyphenUnderscoreLastAfterAtSignAfterDotTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@test.pt" + c;
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"..","._",".-", "__", "_-", "--"})
    void twoConsecutiveDotsBeforeAtSignTest(String c){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "12" + c + "3@test.pt";
            //Act
            new Email(test);
        });
    }

    @Test
    void twoConsecutiveDotsAfterAtSignFailTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "123@te..st.pt";
            //Act
            new Email(test);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 50})
    void createEmailWithLengthRulesBeforeAtSighSuccessTest(int x){
        //Arrange
        char[] data = new char[x];
        Arrays.fill(data, 'a');
        String local = new String(data);
        //Act
        String text = local + "@test.pt";
        Email test = new Email(text);
        //Assert
        assertTrue(test.hasEmail(text));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 50})
    void createEmailWithLengthRulesAfterAtSighSuccessTest(int x){
        //Arrange
        char[] data = new char[x];
        Arrays.fill(data, 'a');
        String domain = new String(data);
        //Act
        String text = "123@" + domain + ".pt";
        Email test = new Email(text);
        //Assert
        assertTrue(test.hasEmail(text));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3})
    void lengthRulesLastPartSuccessTest(int x){
        //Arrange
        char[] data = new char[x];
        Arrays.fill(data, 'a');
        String lastPart = new String(data);
        //Act
        String text = "123@test." + lastPart ;
        Email test = new Email(text);
        //Assert
        assertTrue(test.hasEmail(text));
    }

    @Test
    void withTwoAtSign(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "12@3@test.pt";
            //Act
            new Email(test);
        });
    }

    @Test
    void withFourAtSign(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String test = "12@3@te@st.p@t";
            //Act
            new Email(test);
        });
    }

    @Test
    void checkDifferentEmails(){
        //Arrange
        Email test = new Email("123@test.pt");
        Email test2 = new Email("1234@test.pt");
        //Act
        String text1 = test.getEmailText();
        //Assert
        assertFalse(test2.hasEmail(text1));
    }

    @Test
    void getEmail(){
        //Arrange
        Email test = new Email("123@test.pt");
        //Act
        //Assert
        assertEquals("123@test.pt", test.getEmailText());
    }

    @Test
    public void equalsSuccessTest() {
        //Arrange
        Email email = new Email("Test@gmail.com");
        Email test = new Email("Test@gmail.com");
        //Assert
        assertEquals(test, email);
    }


    @Test
    public void equalsFailTest() {
        //Arrange
        Email email = new Email();
        email.setEmailText("Test@gmail.com");
        Email test = new Email();
        test.setEmailText("Test1@gmail.com");
        //Assert
        assertNotEquals(test, email);
    }

    @Test
    public void equalsClassFailTest() {
        //Arrange
        Email email = new Email("Test@gmail.com");
        Description test = new Description("Test@gmail.com");
        //Assert
        assertNotEquals(test.getClass(), email.getClass());
    }

    @Test
    public void equalsNullTest() {
        //Arrange
        Email email = new Email("Test@gmail.com");
        //Assert
        assertNotEquals(null, email);
    }

    @Test
    public void hashCodeSuccessTest() {
        //Arrange
        Email email = new Email("Test@gmail.com");
        Email test = new Email("Test@gmail.com");
        //Assert
        assertEquals(test.hashCode(), email.hashCode());
    }

    @Test
    @DisplayName("Test to check Override Method.")
    void overrideTest_Success(){
        //Arrange
        Email email = new Email("xpto@xpto.pt");
        Email email1 = new Email("xpto@xpto.pt");
        //Assert
        assertEquals(email, email1);
    }

    @Test
    @DisplayName("Test to check Override Method.")
    void overrideTest_Success_1(){
        //Arrange
        Email email = new Email("xpto@xpto.pt");
        Email email1 = new Email("xpto@xpto.pt");
        //Assert
        assertEquals(email, email1);
    }


    @Test
    @DisplayName("Test to check Override Method.")
    void overrideTest_Fail(){
        //Arrange
        Email email = new Email("xpto@xpto.pt");
        Email email1 = new Email("xpto12@xpto.pt");
        //Assert
        assertNotEquals(email, email1);
    }

    @Test
    @DisplayName("Test to check Override Method.")
    void overrideTest_Fail_2(){
        //Arrange
        Email email = new Email("xpto@xpto.pt");
        Email email1 = new Email("xpto12@xpto.pt");
        //Assert
        assertNotEquals(email, email1);
    }

    @Test
    @DisplayName("Test 3 to check Override Method, with one null object.")
    void overrideTest_NotEquals(){
        //Arrange
        Email email = new Email("xpto@xpto.pt");
        Email email1 = null;
        //Act and Assert
        assertNotEquals(email,email1);
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Success(){
        //Arrange
        Email email = new Email("xpto@xpto.pt");
        Email email1 = new Email("xpto@xpto.pt");
        //Act
        Object x = email.hashCode();
        Object y = email1.hashCode();
        //Assert
        assertEquals(x,y);
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Fail(){
        //Arrange
        Email email = new Email("xpto@xpto.pt");
        Email email1 = new Email("xpto12@xpto.pt");
        //Act
        Object x = email.hashCode();
        Object y = email1.hashCode();
        //Assert
        assertNotEquals(x,y);
    }

    @Test
    public void hashCodeFailTest() {
        //Arrange
        Email email = new Email("Test@gmail.com");
        Email test = new Email("Test1@gmail.com");
        //Assert
        assertNotEquals(test.hashCode(), email.hashCode());
    }

    @Test
    public void sameValueAsTest_1() {
        //Arrange
        Email email = new Email("Test@gmail.com");
        Email test = new Email("Test1@gmail.com");
        //Assert
        assertFalse(test.sameValueAs(email));
    }


    @Test
    public void sameValueAsTest_2() {
        //Arrange
        Email email = new Email("Test@gmail.com");
        Email test = new Email("Test@gmail.com");
        //Assert
        assertTrue(test.sameValueAs(email));
    }
}