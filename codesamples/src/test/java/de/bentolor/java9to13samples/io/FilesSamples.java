package de.bentolor.java9to13samples.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Java (N)IO enhancements.
 */
class FilesSamples {

    @Test
    void testNewPathAndFilesMethods() throws IOException {
        var txtPath = Path.of("src", "test", "resources", "sample-text.txt");
        assertTrue(txtPath.toFile().exists());

        String fileContent = Files.readString(txtPath);
        assertEquals("Nostrum iure ullam et sunt tempora dolore.", fileContent);

        Path newPath = Path.of("src/test/resources/newfile.txt");
        Files.writeString(newPath, "Nostrum lore", StandardOpenOption.CREATE_NEW);

        // JDK12
        long firstMismatch = Files.mismatch(txtPath, newPath);
        assertEquals(8, firstMismatch);

        Files.delete(newPath);
    }


}
