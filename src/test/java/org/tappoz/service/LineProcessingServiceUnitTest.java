package org.tappoz.service;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tappoz.beans.Operation;
import org.tappoz.enums.MathOperatorsEnum;

/**
 * Unit tests for the service in LineProcessingService.class
 */
public class LineProcessingServiceUnitTest {

    private Injector injector;
    private LineProcessingService sut;

    @Before
    public void init() throws Exception{
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                // no mocking here, this is the sut (system under test)
                bind(LineProcessingService.class);
            }
        });
    }
    @After
    public void cleanTheContext() throws Exception {
        injector = null;
    }

    @Test
    public void test_parseThis() {

        // given
        sut = injector.getInstance(LineProcessingService.class);
        final String OPERATION = "add 3";

        // when
        Operation outputOperation = sut.parseThis(OPERATION);

        // then
        MatcherAssert.assertThat( outputOperation.getAmount().doubleValue() , Matchers.is(3.0)                   );
        MatcherAssert.assertThat( outputOperation.getOperation()            , Matchers.is(MathOperatorsEnum.add) );

    }
}
