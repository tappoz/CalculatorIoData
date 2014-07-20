package org.tappoz.service;

import org.tappoz.beans.Operation;

import java.math.BigDecimal;

/**
 * Mocked version of the original service
 */
public class MockOperationPerformingService {

    // http://www.journaldev.com/2403/google-guice-dependency-injection-example-tutorial
    // http://www.journaldev.com/2394/dependency-injection-design-pattern-in-java-example-tutorial

    /**
     * Mocked version of the original method
     *
     * @param operation
     * @param previousAmount
     * @return
     */
    public BigDecimal doOperation(Operation operation, BigDecimal previousAmount) {
        return previousAmount;
    }
}
