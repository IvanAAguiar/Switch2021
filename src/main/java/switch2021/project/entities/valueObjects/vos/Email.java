package switch2021.project.entities.valueObjects.vos;

import lombok.NoArgsConstructor;
import switch2021.project.utils.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Email implements ValueObject<Email> {

    /**
     * Attributes
     */
    @Column(name = "email")
    private String emailText;
    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH_LAST_PART = 3;
    private static final int MIN_LENGTH_LAST_PART = 2;


    /**
     * Constructor
     */
    public Email(String input) {

        checkAllEmailRules(input);

        this.emailText = input;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    /**
     * Methods to verify all rules to a valid email.
     */

    private void checkAllEmailRules(String y) {

        if (y == null || y.trim().isEmpty()) {
            throw new IllegalArgumentException("The email cannot be empty, blank or null.");
        } else {
            checkAtSignAndDot(y);
            checkSpecialCharacters(y);
            checkValidSpecialCharactersPosition(y);
            checkDoubleSpecialChar(y);
            checkLength(y);
        }

    }

    private void checkAtSignAndDot(String y) {

        if (!y.contains("@")) {
            throw new IllegalArgumentException("The email must have @ sign.");
        } else if (y.split("@").length > 2) {
            throw new IllegalArgumentException("The email cannot have two or more at sign.");
        } else if(y.indexOf("@") == 0 || y.indexOf("@") == y.length() - 1){
            throw new IllegalArgumentException("The email cannot have at sign in the first or last position.");
        } else if (!y.split("@")[1].contains(".")) {
            throw new IllegalArgumentException("The email must have a dot sing after at sign.");
        }

    }

    private void checkSpecialCharacters(String input) {
        String specialCharactersString = "!#$%&*()'+,/:;<=>?[]^`{|}";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (specialCharactersString.contains(Character.toString(ch))) {
                throw new IllegalArgumentException("The email cannot have special characters, except dot, hyphen and underscore");
            }
        }
    }

    private void checkValidSpecialCharactersPosition(String y) {

        String valids = ".-_";

        String[] parts = y.split("@");

        //To iterate with valid special characters (dot, hyphen and underscore)
        for (int i = 0; i < valids.length(); i++) {

            //To iterate with the local(before @) and domain(after @) part of the email.
            for (String part : parts) {

                //To iterate with which part and ask which character if it has a valid special char.
                for (int k = 0; k < part.length(); k++) {

                    if (part.charAt(k) == valids.charAt(i)) {

                        if (k == 0 || k == part.length() - 1) {
                            throw new IllegalArgumentException("The email cannot have " + valids.charAt(i) + " sign in the first or last of local/domain part.");
                        }

                    }


                }

            }

        }

    }

    private void checkDoubleSpecialChar(String y) {

        String valids = ".-_";

        //To iterate with which character of input string
        for (int j = 0; j < y.length(); j++) {

            //To iterate with which valid special character (dot, hyphen and underscore)
            for (int i = 0; i < valids.length(); i++) {

                if (y.charAt(j) == valids.charAt(i)) {

                    //To check if string has consecutive valid special character
                    for (int k = 0; k < valids.length(); k++) {

                        if (y.charAt(j + 1) == valids.charAt(k)) {
                            throw new IllegalArgumentException("The email cannot have two consecutive special characters valid.");
                        }

                    }

                }

            }

        }

    }

    private void checkLength(String y) {

        int localSize = y.split("@")[0].length();
        String domainSize = y.split("@")[1];
        int lastDotPosition = domainSize.lastIndexOf(".");

        if (localSize < MIN_LENGTH || localSize > MAX_LENGTH) {
            throw new IllegalArgumentException("The local part of email must have at least " + MIN_LENGTH + " characters and a maximum of " + MAX_LENGTH + ".");
        }

        if (lastDotPosition < MIN_LENGTH  || lastDotPosition > MAX_LENGTH) {
            throw new IllegalArgumentException("The domain part of email must have at least " + MIN_LENGTH + " characters and a maximum of " + MAX_LENGTH + ".");
        }

        if (domainSize.length() - (lastDotPosition + 1) < MIN_LENGTH_LAST_PART || domainSize.length() - (lastDotPosition + 1) > MAX_LENGTH_LAST_PART) {
            throw new IllegalArgumentException("The last part of email must have at least " + MIN_LENGTH_LAST_PART + " characters and a maximum of " + MAX_LENGTH_LAST_PART + ".");
        }

    }


    /**
     * Methods to iterate with the object.
     */

    public boolean hasEmail(String toCompare) {
        return this.emailText.equals(toCompare);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email that = (Email) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return emailText.hashCode();
    }

    @Override
    public boolean sameValueAs(Email other) {
        return other != null && this.emailText.equals(other.emailText);
    }
}
