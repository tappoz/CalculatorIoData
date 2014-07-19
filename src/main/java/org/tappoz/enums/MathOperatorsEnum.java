package org.tappoz.enums;

import org.tappoz.utils.LoggingHelper;

import java.util.Arrays;
import java.util.List;

/**
 * This enum class validates the operators.
 * There is some kind of basic intelligence here, e.g. the "divide" enum operation may be fired by either the
 * "divide" input string and the "div" input string.
 */
public enum MathOperatorsEnum {

    add("add"),
    divide("divide", "div"),
    subtract("subtract", "sub"),
    multiply("multiply", "mult"),
    apply("apply");

    private final List<String> values;

    private MathOperatorsEnum(String... values) {
        this.values = Arrays.asList(values);
    }

    /**
     * This method returns the values belonging to the same enum validated operation.
     * @return
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * This method validates a given input string supposed to represent a valid operator.
     * This method uses a utility class to log some statements in case an operator can not be validated.
     * @see {@link org.tappoz.utils.LoggingHelper}
     *
     * @param operationString the input string supposed to represent a valid operator
     * @return an instance of this enum class representing the validated operator
     */
    public static MathOperatorsEnum validateMathOperation(String operationString) {

        for (MathOperatorsEnum currentEnumEvaluated : MathOperatorsEnum.values()) {
            if (currentEnumEvaluated.getValues().contains(operationString)) {
                return currentEnumEvaluated;
            }
        }
        LoggingHelper.warn("Can not find this expected unit of measure coming as an input string: '" + operationString + "'");
        return null;
    }

}
