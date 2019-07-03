package de.bentolor.java9to13samples.util;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static java.lang.System.out;

class StackWalkerSamples {

    /**
     * Easily traverse stackframes.
     */
    @Test
    void walkAndFilterStackframe1() {
        walkAndFilterStackframe2();
    }

    void walkAndFilterStackframe2() {
        walkAndFilterStackframe();
    }


    void walkAndFilterStackframe() {
        StackWalker.getInstance().walk(
                s -> s.map(frame -> frame.getClassName() + '/' + frame.getMethodName())
                        .filter(name -> name.startsWith("de.bentolor"))
                        .limit(10)
                        .collect(Collectors.toList())
        ).forEach(out::println);
    }

}