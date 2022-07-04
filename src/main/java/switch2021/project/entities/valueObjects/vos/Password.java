package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

@Getter
public class Password implements ValueObject<Password> {

    /**
     * Attributes
     **/
    private final String pwd;
    private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Constructor
     **/
    public Password(String pwd) {
        checkPwdSize(pwd);
        checkNumberPresence(pwd);
        checkUpperCasePresence(pwd);
        checkLowerCasePresence(pwd);
        this.pwd = pwd;
    }

    public void checkPwdSize(String pwd) {
        if (pwd.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password too short.");
        }
    }

    public void checkNumberPresence(String pwd) {
        int count = 0;
        char[] chars = pwd.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                count = 1;
                break;
            }
        }
        if (count == 0) {
            throw new IllegalArgumentException("Password should contain at least 1 number");
        }
    }


    public void checkUpperCasePresence(String pwd){
        int count = 0;
        char[] chars = pwd.toCharArray();
        for(char c : chars) {
            if (Character.isUpperCase(c)) {
                count = 1;
                break;
            }
        }
        if (count == 0) {
            throw new IllegalArgumentException("Password should contain at least 1 upper case");
        }
    }

    public void checkLowerCasePresence(String pwd){
        int count = 0;
        char[] chars = pwd.toCharArray();
        for(char c : chars) {
            if (Character.isLowerCase(c)) {
                count = 1;
                break;
            }
        }
        if (count == 0) {
            throw new IllegalArgumentException("Password should contain at least 1 upper case");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password that = (Password) o;
        return sameValueAs(that);
    }

    @Override
    public boolean sameValueAs(Password other) {
        return other != null && this.pwd.equals(other.pwd);
    }

    @Override
    public int hashCode() {
        return pwd.hashCode();
    }
}
