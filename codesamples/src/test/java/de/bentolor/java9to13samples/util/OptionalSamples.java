package de.bentolor.java9to13samples.util;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * New Optional methods since 9+
 */
public class OptionalSamples {

    @Test
    void newJava9OptionalMethods() {
        var maybeInt = Optional.ofNullable( (Math.random()<0.5) ? 42 : null );

        // Depending on value presence do either this or that
        maybeInt.ifPresentOrElse(
                (n) -> System.out.println(n),
                () -> System.out.println("Nada")
        );

        // Optional empty? → Build a new one on-the-fly…
        var triedAgain = maybeInt.or(() -> Optional.of(-1));

        // Provide a stream → [] or [42]
        Stream<Integer> intStream = maybeInt.stream();
    }

    @Test
    void newJava10OptionalMethods() {
        var maybeVal = (Math.random() < 0.99) ? 42 : null;
        var maybeInt = Optional.ofNullable(maybeVal);

        // new short-hand throw()
        int answer = maybeInt.orElseThrow();
    }

    @Test
    void newJava11OptionalMethods() {
        var maybeVal = (Math.random() < 0.5) ? 42 : null;
        var maybeInt = Optional.ofNullable(maybeVal);

        // complements isPresent()
        assert maybeInt.isEmpty() == !maybeInt.isPresent();
    }
}
