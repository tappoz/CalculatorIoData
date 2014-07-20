package org.tappoz.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.CharEncoding;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.tappoz.beans.Operation;
import org.tappoz.enums.MathOperatorsEnum;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Unit tests on the class involving I/O operations
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FileUtils.class, OperationsFileService.class})
public class OperationsFileServiceUnitTest {

    @Mock
    LineProcessingService lineProcessingService;
    @Mock
    OperationPerformingService operationPerformingService;
    @Mock
    FactoryService factoryService;
    @InjectMocks
    OperationsFileService sut; // system under test

    @Test
    public void test_processFile() throws IOException {

        // given

        final String FILE_DUMMY_PATH = "DUMMY/PATH";
        File mockFile = Mockito.mock(File.class);
        Mockito.when(factoryService.getNewFileInstance(FILE_DUMMY_PATH)).thenReturn(mockFile);

        final String DUMMY_OPERATION = "add 2";
        final String DUMMY_APPLY = "apply X";
        LineIterator mockLineIterator = Mockito.mock(LineIterator.class);
        Mockito.when(mockLineIterator.nextLine())
                .thenReturn(DUMMY_OPERATION)
                .thenReturn(DUMMY_APPLY);
        Mockito.when(mockLineIterator.hasNext()).thenReturn(true);

        PowerMockito.mockStatic(FileUtils.class);
        PowerMockito.when(FileUtils.lineIterator(mockFile, CharEncoding.UTF_8)).thenReturn(mockLineIterator);

        Operation addOperation = new Operation(MathOperatorsEnum.add, new BigDecimal("2"));
        BigDecimal applyAmount = new BigDecimal("10");
        Operation applyOperation = new Operation(MathOperatorsEnum.apply, applyAmount);
        Mockito.when(lineProcessingService.parseThis(DUMMY_OPERATION)).thenReturn(addOperation);
        Mockito.when(lineProcessingService.parseThis(DUMMY_APPLY)).thenReturn(applyOperation);

        BigDecimal expectedOutput = new BigDecimal("12");
        Mockito.when(operationPerformingService.doOperation(addOperation, applyAmount)).thenReturn(expectedOutput);

        // when
        BigDecimal result = sut.processFile(FILE_DUMMY_PATH, applyAmount);

        // then
        MatcherAssert.assertThat(result.doubleValue(), Matchers.is(expectedOutput.doubleValue()));

    }

}
