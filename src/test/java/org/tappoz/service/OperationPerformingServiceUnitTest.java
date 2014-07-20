package org.tappoz.service;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tappoz.beans.Operation;
import org.tappoz.enums.MathOperatorsEnum;

import java.math.BigDecimal;

/**
 * Unit testing class
 */
public class OperationPerformingServiceUnitTest {

    private Injector injector;
    private OperationPerformingService sut;

    @Before
    public void init() throws Exception{
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                // no mocking here, this is the sut (system under test)
//                bind(OperationPerformingService.class);
            }
        });
    }

    @Test
    public void test_doOperation() {

        sut = injector.getInstance(OperationPerformingService.class);

        Operation operation = new Operation(MathOperatorsEnum.divide, new BigDecimal("10"));
        BigDecimal result = sut.doOperation(operation, new BigDecimal("5"));

        MatcherAssert.assertThat(result.doubleValue(), Matchers.is(2.0));
    }

    @After
    public void cleanTheContext() throws Exception {
        injector = null;
    }
}
