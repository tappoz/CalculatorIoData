package org.tappoz.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.lang3.CharEncoding;
import org.tappoz.beans.Operation;
import org.tappoz.enums.MathOperatorsEnum;
import org.tappoz.exceptions.OperationIoException;
import org.tappoz.utils.LoggingHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;

/**
 * This class deals with the I/O of the input file containing the instructions, it also performs the parsed operations.
 */
@Singleton
public class OperationsFileService {

    // the expected encoding of the input file
    private static final String FILE_ENCODING = CharEncoding.UTF_8;

    private LineProcessingService lineProcessingService;
    private OperationPerformingService operationPerformingService;

    @Inject
    public void setLineProcessingService(LineProcessingService lineProcessingService) {
        this.lineProcessingService = lineProcessingService;
    }
    @Inject
    public void setOperationPerformingService(OperationPerformingService operationPerformingService) {
        this.operationPerformingService = operationPerformingService;
    }

    /**
     * This method looks for the last line of the file in an efficient way
     * (i.e. not loading the whole file in memory, but guessing and looking for the last line to load).
     *
     * An utility class from the Apache Commons library is used here, one of the input parameters
     * is the file system block size, to check the file system block size under Linux run:
     * <code>$ sudo blockdev --getbsz /dev/sda1</code>
     * I am assuming the block size is 1024
     *
     * @param filePath a string representing the relative path to the file
     *                 (e.g. from the "target" folder it might be "../src/main/resources/example1.txt")
     * @return a string representing the last line of the file
     * @throws IOException when there's some problem with the I/O loading or processing the file
     * @throws OperationIoException when the file path is not found
     */
    public String getLastLine(String filePath) throws IOException, OperationIoException {

        try {
            ReversedLinesFileReader reversedLinesFileReader =
                    new ReversedLinesFileReader(new File(filePath), 1024, Charset.forName(FILE_ENCODING));
            // getting the last line of the file
            String lastLineToReturn = reversedLinesFileReader.readLine();
            // closing the underlying resource
            reversedLinesFileReader.close();

            return lastLineToReturn;
        } catch (FileNotFoundException e) {
            throw new OperationIoException("The file was not found: " + e.getMessage());
        }
    }

    /**
     * This method provides the amount coming from the last line of the file
     * that should be identified by an "apply" operation.
     *
     * The method also throws a runtime exception when the expected "apply" operation is not recognized.
     *
     * @param filePath a string representing the relative path to the file
     *                 (e.g. from the "target" folder it might be "../src/main/resources/example1.txt")
     * @return an initialising amount, null if the file is not found
     * @throws IOException when there's some problem with the I/O loading or processing the file
     */
    public BigDecimal getValidatedLastLine(String filePath) throws IOException {
        try {
            String rawLine = this.getLastLine(filePath);
            // parsing the line representing the operation
            Operation expectedApply = lineProcessingService.parseThis(rawLine);
            if (expectedApply.getOperation() == MathOperatorsEnum.apply) {
                return expectedApply.getAmount();
            }
            else {
                throw new IllegalStateException("An expected last line was not an apply operation, it was instead: '" + rawLine + "'");
            }
        } catch (OperationIoException e) {
            LoggingHelper.error("An exception has occurred, returning null: " + e.getMessage());
            return null;
        }
    }

    /**
     * This method loops on all the lines of a file to process it in order to obtain the result.
     * It uses some classes from the Apache Commons frameworks.
     *
     * This method uses a utility class to log some statements regarding nulls and any kind of exception that might be catch.
     * @see {@link org.tappoz.utils.LoggingHelper}
     *
     * @param filePath a string representing the relative path to the file
     *                 (e.g. from the "target" folder it might be "../src/main/resources/example1.txt")
     * @param initAmount the initial amount to bootstrap and get the first parsed operation executed
     * @return the result of all the parsed operations, null if the file path is not found
     * @throws IOException when there's some problem with the I/O loading or processing the file
     */
    public BigDecimal processFile(String filePath, BigDecimal initAmount) throws IOException {

        try {
            BigDecimal currentAmount = initAmount;
            // efficiently read the file line by line
            LineIterator lineIterator = FileUtils.lineIterator(new File(filePath), FILE_ENCODING);
            try {
                while (lineIterator.hasNext()) {
                    // getting the line
                    String currentLine = lineIterator.nextLine();
                    // parsing the operation
                    Operation currentOperation = lineProcessingService.parseThis(currentLine);
                    if(currentOperation.getOperation() == MathOperatorsEnum.apply) {
                        // reaching the end of the file, no operation to perform here
                        LineIterator.closeQuietly(lineIterator);
                        // returning the amount
                        return currentAmount;
                    } else if (currentOperation == null) {
                        // dealing with nulls
                        LoggingHelper.warn("A null was found... Returning the current amount.");
                        return currentAmount;
                    } else {
                        // performing the operation
                        currentAmount = operationPerformingService.doOperation(currentOperation, currentAmount);
                    }
                }
            } catch (Exception e) {
                LineIterator.closeQuietly(lineIterator);
                LoggingHelper.error("There has been a problem in the current iteration, the message was '"
                        + e.getMessage() + "', returning the amount computed so far.");
                return currentAmount;
            }
            return currentAmount;
        } catch (FileNotFoundException e) {
            LoggingHelper.error("An exception has occurred, returning null: " + e.getMessage());
            return null;
        }
    }
}
