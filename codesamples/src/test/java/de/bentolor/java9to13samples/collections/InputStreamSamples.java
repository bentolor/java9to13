package de.bentolor.java9to13samples.collections;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Examples on new Stream methods since Java 9+
 */
class InputStreamSamples {


    @Test
    void redirectStream() throws IOException {
        // 128 random bytes
        byte[] buf = new byte[128];
        new Random().nextBytes(buf);

        // JDK9: All bytes from an InputStream at once
        byte[] result = new ByteArrayInputStream(buf).readAllBytes();

        // JDK9: Directly redirect an InputStream to an OutputStream
        new ByteArrayInputStream(buf).transferTo(System.out);
    }


}
