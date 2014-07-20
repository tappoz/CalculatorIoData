package org.tappoz.injector;

import com.google.inject.AbstractModule;
import org.tappoz.service.LineProcessingService;
import org.tappoz.service.OperationPerformingService;
import org.tappoz.service.OperationsFileService;
import org.tappoz.utils.LoggingHelper;

/**
 * Created by tappoz on 19/07/14.
 */
public class InjectorModule extends AbstractModule {
    @Override
    protected void configure() {

        // if there are no interfaces with multiple implementations
        // then we can just ignore the following binding statements

//        bind(FactoryService.class).to(FactoryService.class);
//        bind(LineProcessingService.class).to(LineProcessingService.class);
//        bind(OperationPerformingService.class).to(OperationPerformingService.class);
//        bind(OperationsFileService.class).to(OperationsFileService.class);
//        bind(SessionProcessingService.class).to(SessionProcessingService.class);

        LoggingHelper.info("Just binded all the dependencies");
    }
}
