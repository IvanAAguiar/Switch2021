package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Nif {

    /**
     * Attributes
     */

    private long number;
    private static final int NIF_LENGTH = 9;


    public Nif(long number) {

        checkNifRules(number);

        this.number = number;
    }

    /**
     * Methods to verify the rules to NIF number.
     */

    private void checkNifRules(long nif) {

        int length = nifSize(nif);

        if(length != NIF_LENGTH){

            throw new IllegalArgumentException("The NIF must have " + NIF_LENGTH + " numbers.");

        } else if (!checkLastDigit(nif)){
            throw new IllegalArgumentException("Digit control are wrong.");
        }

    }

    // NIF number has 9 digits. The method below check this.
    private int nifSize(long nif) {

        int size = 1;

        while(nif/10 != 0){
            nif /= 10;
            size++;
        }

        return size;

    }

    //The last number of the NIF is the digit control and has a rule to generate it. The method below will verify this rule.
    private boolean checkLastDigit(long nif) {

        int validDigitControl = 0;
        int typedDigitControl = (int) nif%10;
        int produt = 2;

        while(nif != 0){

            nif /= 10;
            validDigitControl += (int) nif%10 * produt;
            produt++;

        }

        validDigitControl %= 11;

        if(validDigitControl == 0 || validDigitControl == 1){
            validDigitControl = 0;
        } else {
            validDigitControl = 11 - validDigitControl;
        }

        return validDigitControl == typedDigitControl;
    }

    //Method to compare two if the number received are equal to the object.
    public boolean hasSameNif(long num){
        return this.number == num;
    }

}
