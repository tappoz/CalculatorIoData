package org.tappoz.exceptions;

/**
 * A convenience exception class to describe relevant scenarios to this context (operations, operators etc.)
 */
public class OperationIoException extends Exception {

    /**
     * To generate a proper exception with a proper message relevant to the context.
     *
     * @param exceptionMessage the input message to be thrown
     */
    public OperationIoException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
