package de.bentolor.java9to13samples.language;

import org.junit.jupiter.api.Test;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

/**
 * Various enhancements in Milling Project Coin:  address some rough edges
 */
public class MillingProjectCoin {

    @Test
    void tryWithEffectivelyFinals() throws IOException {
        InputStream inputStream = new FileInputStream(".gitignore");
        try (Closeable closeAfterTry = inputStream) {
        }

        // JDK9+: simply refer to effectively-final Closeables
        InputStream inputStream2 = new FileInputStream(".gitignore");
        try (inputStream2) {
        }
    }

    @Test
    void DiamondForAnonymousClasses() {
        Comparator<Integer> compareLongs = new Comparator<>() {
            public int compare(Integer l1, Integer l2) {
                return l2 - l1;
            }

            public Comparator<Integer> reversed() {
                return Collections.reverseOrder(this);
            }
        };
    }

    @FunctionalInterface
    interface Version {
        byte[] digits();

        default String text() {
            return text(digits());
        }

        private String text(byte[] version) {
            var v = "";
            for (byte b : version) {
                v += v.isEmpty() ? b : ("." + b);
            }
            return v;
        }
    }

    @Test
    void privateInterfaceMethods() {
        Version version = () -> new byte[]{1, 2, 3};
        assertEquals("1.2.3", version.text());
    }

    @Test
    void SafeVarargsAnnotationForPrivateInstanceMethods() {
        // Due to the @SafeVarargs this won't complain when compiling
        // using `-Xlint:unchecked`
        var flat = safeVarargsMethod(List.of("A", "B"), List.of("C", "D"));
        assertLinesMatch(List.of("A", "B", "C", "D"), flat);
    }


    @SafeVarargs // allowed since Java 9 on private methods
    private List<String> safeVarargsMethod(List<String>... lists) {
        var flat = new ArrayList<String>();
        for (var list : lists) {
            flat.addAll(list);
        }
        return flat;
    }

}
