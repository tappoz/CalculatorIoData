package org.tappoz.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * A helper class to deal with logging statements and logging levels.
 */
public class LoggingHelper {

    private enum loggingLevel
    {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    public static void debug(String debugString) {
        System.out.println(getPrefix(loggingLevel.DEBUG.name())  + debugString);
    }

    public static void info(String infoString) {
        System.out.println(getPrefix(loggingLevel.INFO.name())  + infoString);
    }

    public static void warn(String warnString) {
        System.out.println(getPrefix(loggingLevel.WARN.name()) + warnString);
    }

    public static void error(String errorString) {
        System.err.println(getPrefix(loggingLevel.ERROR.name()) + errorString);
    }

    public static String getPrefix(String loggingLevel) {
        String formattedLoggingLevel = String.format("%5s", loggingLevel.trim());
        Date date= new Date();
        String formattedTimeStamp = String.format("%-23s", new Timestamp(date.getTime()));
        return formattedTimeStamp + " - " + formattedLoggingLevel + ": ";
    }
}
