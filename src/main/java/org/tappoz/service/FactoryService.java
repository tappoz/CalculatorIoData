package org.tappoz.service;

import com.google.inject.Singleton;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * A convenience factory class to get new instances of different types.
 */
@Singleton
public class FactoryService {

    public File getNewFileInstance(String filePath) {
        return new File(filePath);
    }

    public ReversedLinesFileReader getNewFileInstance(File file, String fileEncoding) throws IOException {
        return new ReversedLinesFileReader(file, 1024, Charset.forName(fileEncoding));
    }
}
