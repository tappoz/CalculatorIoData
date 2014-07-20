package org.tappoz.service;

import com.google.inject.Singleton;
import org.tappoz.beans.Operation;
import org.tappoz.utils.LoggingHelper;

import java.math.BigDecimal;

/**
 * This class performs the operations according to an Operation bean and a previous provided amount.
 */
@Singleton
public class OperationPerformingService {

    /**
     * This method performs the operation details provided in the input bean
     * from the previous amount starting point.
     *
     * @see {@link org.tappoz.beans.Operation}
     *
     * This method uses a utility class to log some statements in different scenarios.
     * @see {@link org.tappoz.utils.LoggingHelper}
     *
     * @param operation
     * @param previousAmount
     * @return
     */
    public BigDecimal doOperation(Operation operation, BigDecimal previousAmount) {

        BigDecimal outputAmount = null;

        if(operation.getOperation() != null) {
            switch (operation.getOperation().name()) {
                case "add":
                    outputAmount = operation.getAmount().add(previousAmount);
                    break;
                case "divide":
                    outputAmount = operation.getAmount().divide(previousAmount);
                    break;
                case "subtract":
                    outputAmount = operation.getAmount().subtract(previousAmount);
                    break;
                case "multiply":
                    outputAmount = operation.getAmount().multiply(previousAmount);
                    break;
                default :
                    break;
            }
        } else {
            LoggingHelper.error("We were not able to process the operation for this amount: '" + previousAmount + "'");
        }
        LoggingHelper.debug("Operation: " + operation.getOperation() + ", amount: " + operation.getAmount());
        return outputAmount;
    }

}
