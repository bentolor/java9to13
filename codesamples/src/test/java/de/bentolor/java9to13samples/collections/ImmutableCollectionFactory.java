package de.bentolor.java9to13samples.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableCollectionFactory {

    /**
     * Create immutable collections on the fly.
     * <b>Note:</b> They do not accept {@code null} or duplicate entries (Set/Map)
     */
    @Test
    void createValueCollections() {
        List<Integer> java8ListOfInts =
                Collections.unmodifiableList(Arrays.asList(1, 2, 3, 4, 5));
        out.println(java8ListOfInts);

        // /* Commented-out */ code will produce a runtime-error!

        List<Integer> listOfInts = List.of(1, 2, 3, 4, 5/*, null */);
        out.println(listOfInts);

        Set<Integer> setOfNumbers = Set.of(1, 2, 3, 4, 5/*, 1 */);
        out.println(setOfNumbers);

        // up to 10 key/value pairs
        Map<Integer, String> mapOfString = Map.of(1, "a", 2, "b");
        out.println(mapOfString);

        Map<String, String> moreMapOfString = Map.ofEntries(
                Map.entry("key1", "value1"),
                Map.entry("key2", "value2")/*,
                Map.entry("key1", "value3") */
        );
        out.println(moreMapOfString);
    }

    @Test
    void copyCollections() {
        final var originalMap = Map.of(1,"a", 2, "b");
        final var originalList = new ArrayList<String>();
        originalList.addAll(List.of("A","B","C", "A"));

        var listCopy = List.copyOf(originalList);
        var setCopy = Set.copyOf(originalList);
        var mapCopy = Map.copyOf(originalMap);

        // it's a collection copy.
        originalList.clear();
        assertEquals(0, originalList.size());
        assertEquals(4, listCopy.size());
        assertEquals(3, setCopy.size());

        assertThrows(UnsupportedOperationException.class, () -> listCopy.add("Z"));
    }
}