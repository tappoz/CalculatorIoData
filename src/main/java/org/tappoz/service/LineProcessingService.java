package org.tappoz.service;

import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.tappoz.beans.Operation;
import org.tappoz.enums.MathOperatorsEnum;

import java.math.BigDecimal;

/**
 * This class is used to process a input string supposed to contain an operator and an amount
 */
@Singleton
public class LineProcessingService {

    /**
     * This method parses the input string representing a line in the input file.
     * It might throw a runtime exception when the input line format is not as expected
     * or an invalid operator is found in the input string.
     *
     * @param inputString the input string representing a line in the input file.
     * @return an Operation object containing a validated operator and a validated amount,
     *          null if the input is empty/null
     */
    public Operation parseThis(String inputString) {

        // if the input is empty/null, returning null
        if(StringUtils.isBlank(inputString))
            return null;

        // normalising leading and trailing blanks, normalising also multiple white spaces to one
        String stringToBeParsed = StringUtils.normalizeSpace(inputString);
        String[] parts = stringToBeParsed.split(" ");

        if(parts.length != 2)
            throw new IllegalStateException("An invalid line has been read: '" + inputString + "'");

        String expectedOperator = parts[0].trim();
        String expectedAmount = parts[1].trim();

        // validating the operator using the enum class
        MathOperatorsEnum operator = MathOperatorsEnum.validateMathOperation(expectedOperator);
        if(operator != null) {
            // validating the amount only if the operator is already valid
            // (i.e. not null according to the enum class)
            BigDecimal amount = new BigDecimal(expectedAmount);
            // returning the validated object
            return new Operation(operator, amount);
        } else {
            throw new IllegalStateException("An invalid operator has been parsed: '" + expectedOperator + "'" );
        }
    }
}
