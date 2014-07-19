package org.tappoz.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.tappoz.utils.LoggingHelper;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by tappoz on 19/07/14.
 */
@Singleton
public class SessionProcessingService {

    private OperationsFileService operationsFileService;

    @Inject
    public void setOperationsFileService(OperationsFileService operationsFileService) {
        this.operationsFileService = operationsFileService;
    }

    /**
     * This method receives in input a string representing the path to a file,
     * then it tries to reach the end of the file to acquire the initial value,
     * then it tries to parse the file line by line keeping updating the expected result at that time.
     *
     * This method uses a utility class to log some statements in case an operator can not be validated.
     * @see {@link org.tappoz.utils.LoggingHelper}
     *
     * @param filePath a string representing the relative path to the file
     *                 (e.g. from the "target" folder it might be "../src/main/resources/instructions.txt")
     * @return the amount calculated after all the operations
     *          (or at least all the operations that the application understood so far)
     * @throws java.io.IOException when there's some problem with the I/O loading or processing the file
     */
    public BigDecimal processInput(String filePath) throws IOException {
        BigDecimal initialAmount = operationsFileService.getValidatedLastLine(filePath);
        LoggingHelper.debug("Just retrieved the initial amount: " + initialAmount);
        BigDecimal calculatedAmount = operationsFileService.processFile(filePath, initialAmount);
        LoggingHelper.debug("About to return the calculated amount: " + calculatedAmount);
        return calculatedAmount;
    }
}
