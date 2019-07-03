package de.bentolor.java9to13samples.collections;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * New methods on streams
 */
public class StreamSamples {

    @Test
    void transformStreams() {
        var source = Stream.of(-1, 0, 1, 21, 42);
        source.dropWhile(i -> i < 2).forEach(System.out::println);

        source = Stream.of(-1, 0, 1, 21, 42);
        source.takeWhile(i -> i < 2).forEach(System.out::println);
    }

    @Test
    void generateStreams() {
        // JDK8 – abort via count
        Stream.iterate(2, i -> i * i).limit(4)
                .forEach(System.out::println);          // → [2, 4, 16, 256]

        // JDK 9 – provide abort criteria
        // Stream<T> iterate​(seed, hasNext, applyFunc)
        Stream.iterate(2, i -> i < 999, i -> i * i)
                .forEach(System.out::println);          // → [2, 4, 16, 256]

        Stream.iterate(0, i -> i < 3, i -> i + 1)
                .forEach(System.out::println);        // → [0, 1, 2]

        // Empty or single-element
        var e = Stream.ofNullable(null); // [ ]
        var h = Stream.ofNullable("Hi"); // [ "Hi" ]

        assertEquals(0, e.count());
        assertEquals(1, h.count());
    }
}
