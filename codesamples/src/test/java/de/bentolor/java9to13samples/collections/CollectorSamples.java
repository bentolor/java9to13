package de.bentolor.java9to13samples.collections;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public class CollectorSamples {

    @Test
    void umodifieableCollectionCollectors() {
        intStream().collect(Collectors.toUnmodifiableList());
        intStream().collect(Collectors.toUnmodifiableSet());
        intStream().collect(Collectors.toUnmodifiableMap(i -> i, Math::abs));
    }

    @NotNull
    private Stream<Integer> intStream() {
        return Stream.of(6, -12, -1, 3, 7, 5);
    }

    @Test
    void filteringCollector() {
        var filteredList = intStream()
                .collect(Collectors.filtering(i -> i<6, Collectors.toList()));
        assertEquals(List.of(-12, -1, 3, 5), filteredList);
    }

    @Test
    void flatMapExample() {
        var list1 = List.of(1, 2, 3);
        var list2 = List.of(5, 3);
        var listOfCollections = List.of(list1, list2, list1);

        var flattenedList = listOfCollections.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertEquals(List.of(1, 2, 3, 5, 3, 1, 2, 3), flattenedList);
    }

    @Test
    void teeingSample() {
        var numStream = Stream.of(8, 12, -1, 3, 6, 5);
        assertEquals(6.82d, rms(numStream), 0.019);
    }

    double rms(Stream<Integer> numStream) {
        double rms = numStream.collect(
                Collectors.teeing(                  //  apply two two collectors
                        Collectors.summingDouble(i -> i * i),     // collector #1
                        Collectors.counting(),                    // collector #2
                        (sum, n) -> Math.sqrt(sum / n) // final join op.
                )
        );
        return rms;
    }

}
