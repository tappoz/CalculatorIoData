package org.tappoz.run;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.tappoz.injector.InjectorModule;
import org.tappoz.service.SessionProcessingService;
import org.tappoz.utils.LoggingHelper;

import java.io.IOException;

/**
 * This class contains the main method, it is used to run the whole application
 */
public class CalculatorRunner {

    /**
     * The main method retrieves the input parameter representing the file path,
     * the instantiates an instance of this same class,
     * then calls the processing method to process the input parameter
     *
     * @see {@link org.tappoz.service.SessionProcessingService#processInput(String)}
     *
     * This method uses a utility class to log some statements related to the process flow.
     * @see {@link org.tappoz.utils.LoggingHelper}
     *
     * @param args the input parameters when running the application from the command line
     * @throws IOException when there's some problem with the I/O loading or processing the file
     */
    public static void main(String[] args) throws IOException {

        if(args.length != 1)
            LoggingHelper.warn("There might be some problems with the input parameters provided from the command line...");

        String filePath = args[0];

        Injector injector = Guice.createInjector(new InjectorModule());
        SessionProcessingService sessionProcessingService = injector.getInstance(SessionProcessingService.class);

        LoggingHelper.info("The result is: " + sessionProcessingService.processInput(filePath));
    }
}
