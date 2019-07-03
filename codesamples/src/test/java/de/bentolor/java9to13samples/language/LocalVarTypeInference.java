package de.bentolor.java9to13samples.language;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

class LocalVarTypeInference {

    @Test
    void localTypeInference() {
        var primitiveVal = 5;  // int
        var doubleVal = 5d;    // double

        // final List<String> obj = …
        final var objVal = List.of("A", "B", "C");

        var letters = "";
        for (var str : objVal) {
            letters += str.toLowerCase();
        }
    }

    @Test
    void localTypeInferenceBordercases() {
        // can't denote: byte, short → explicit typing:
        var byteVal = (byte) 5;

        // type must be inferrable:
        // var wontCompile;
        // var wontCompile = null;
        // var wontCompile = {-1, 1};

        // Type inferred at declaration time:
        var myMap = new HashMap<>();
        myMap.put(42, "The answer");
        // var wontCompile = myMap.get(42).trim();
        // myMap is Map<Object, Object>

        // Method references must be explicit
        // var wontCompile = String::toUpperCase;

        // anonymous types are different types per instance
        // … therefore cannot be reassigned
        var myPredicate = new IntPredicate() {
            public boolean test(int value) {
                return value > 0;
            }
        };
        // myPredicate = (int i) -> (i % 2) == 0;
    }

    @FunctionalInterface
    interface MyLambda {
        Long myFun(@NotNull Long x, Long y);
    }

    private MyLambda myLambda;

    @Test
    void explicitAnnotatedTypeInferenceInLambdas() {
        // JDK8 style type inference
        myLambda = (x, y) -> x + ((y != null) ? y : 0);
        myLambda.myFun(42L, null);

        // JDK8 style: explicit types → allows annotations
        myLambda = (Long x, @NotNull Long y) -> x + y;
        myLambda.myFun(42L, 0L);

        // JDK11: `var` style → type infer with annotations
        myLambda = (var x, @NotNull var y) -> x + y;
        myLambda.myFun(22L, 20L);
        //test2.myFun(42L, null); // IDEA will complain
    }


    void filterMatchesJDK8(Map<? extends Number, ? extends Number> map,
                       Predicate<Number> valid,
                       int max) {
        for (Iterator<? extends Map.Entry<? extends Number, ? extends Number>>
             it = map.entrySet().iterator();
             it.hasNext(); ) {
            Map.Entry<? extends Number, ? extends Number> e = it.next();
            if ((max > 0) && !valid.test(e.getValue())) {
                it.remove();
                max--;
            }
        }
    }

    void filterMatches(Map<? extends Number, ? extends Number> map,
                       Predicate<Number> valid,
                       int max) {
        for (var it = map.entrySet().iterator(); it.hasNext(); ) {
            var e = it.next();
            if ((max > 0) && !valid.test(e.getValue())) {
                it.remove();
                max--;
            }
        }
    }

    @Test
    void filterMatches() {
        var myMap = Map.of(0, 42L, 1, 12L, 2, 6L, 3, 3L, 4, 1L);
        filterMatchesJDK8(new HashMap<>(myMap), n -> n.intValue() == 42,  3);
    }


}
