package org.tappoz.beans;

import org.tappoz.enums.MathOperatorsEnum;

import java.math.BigDecimal;

/**
 * This bean is used to encapsulate an operation. Every operation should have:
 * <ul>
 *     <li>an operator</li>
 *     <li>an amount</li>
 * </ul>
 * The system using this bean may apply this bean to another amount they are already processing in order
 * to get a result.
 * @see {@link org.tappoz.enums.MathOperatorsEnum for the validation of the operators}
 */
public class Operation {

    MathOperatorsEnum operation;
    BigDecimal amount;

    /**
     * A constructor with parameters.
     * @param operation
     * @param amount
     */
    public Operation(MathOperatorsEnum operation, BigDecimal amount) {
        this.operation = operation;
        this.amount = amount;
    }

    public MathOperatorsEnum getOperation() {
        return operation;
    }

    public void setOperation(MathOperatorsEnum operation) {
        this.operation = operation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
