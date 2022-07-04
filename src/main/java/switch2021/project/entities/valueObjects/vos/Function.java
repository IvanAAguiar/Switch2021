package switch2021.project.entities.valueObjects.vos;

import switch2021.project.utils.ValueObject;


public class Function implements ValueObject<Function> {

    /*** Attributes **/
    private final String text;
    private final int MIN_DESCRIPTION_LENGTH = 2;
    private final int MAX_DESCRIPTION_LENGTH = 20;

    /*** Constructor **/
    public Function(String function) {
        String x = " characters";
        if (function == null)
            throw new IllegalArgumentException("Description field requires at least " + MIN_DESCRIPTION_LENGTH + x);
        if (function.trim().length() < MIN_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description field requires at least " + MIN_DESCRIPTION_LENGTH + x);
        if (function.trim().length() > MAX_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description field cannot have more than " + MAX_DESCRIPTION_LENGTH + x);
        this.text = function;
    }

    /*** Getter **/
    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function that = (Function) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public boolean sameValueAs(Function other) {
        return other != null && this.text.equals(other.text);
    }
}
