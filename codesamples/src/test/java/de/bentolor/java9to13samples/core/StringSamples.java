package de.bentolor.java9to13samples.core;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSamples {

    @Test
    void newWhitespaceDefintion() {
        // JDK8 method
        assertEquals(" \tword\u0019 \r".trim(), "word");

        // New JDK11
        assertEquals(" \tword\u0019 \r".strip(), "word\u0019");
        assertEquals(" \tword\u0019 \r".stripLeading(), "word\u0019 \r");
        assertEquals(" \tword\u0019 \r".stripTrailing(), " \tword\u0019");

        assertTrue(" \t".isBlank());
    }

    @Test
    void stringUtils() {
        assertEquals("hi".repeat(3), "hihihi");

        Stream<String> l = "1\n2\n3\n!".lines();
        assertEquals(l.count(), 4);

        assertEquals("word".chars().max().getAsInt(), 'w');
    }

    @Test
    void stringJDK12Utils() {
        assertEquals("hi\r\n  you!".indent(2), "  hi\n    you!\n");
        assertEquals("hi\r\n  you!".indent(-1), "hi\n you!\n");

        Stream<String> l = "1\n2\n3\n!".lines();
        assertEquals(l.count(), 4);

        IntStream stream = "word".chars();
        assertEquals(stream.max().getAsInt(), 'w');

        assertEquals("ABC".transform(String::toLowerCase), "abc");
    }

}
