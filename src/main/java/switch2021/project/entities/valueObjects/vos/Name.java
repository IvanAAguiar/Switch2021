package switch2021.project.entities.valueObjects.vos;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import switch2021.project.utils.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Name implements ValueObject<Name> {

    /*** Attributes **/
    @Column(name = "name")
    private String text;


    /*** Constructor **/
    public Name(String name) {
        checkNameNotEmpty(name);
        checkIfNumber(name);
        checkIfSpecialChar(name);

        this.text = name;
    }

    /*** Getter **/
    public String getText() {
        return text;
    }

    /*** Methods **/
    public void checkNameNotEmpty(String name){
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
    }

    public void checkIfNumber(String name){
        char[] chars = name.toCharArray();
        for(char c : chars){
            if(Character.isDigit(c)){
                throw new IllegalArgumentException("Name cannot contain any numbers");
    }}}

    public void checkIfSpecialChar(String name){
        Pattern p = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher m = p.matcher(name);
        boolean b = m.find();
        if (b)
            throw new IllegalArgumentException("Name cannot contain any special character");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Name that = (Name) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public boolean sameValueAs(Name other) {
        return other != null && this.text.equals(other.text);
    }
}
