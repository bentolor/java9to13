package de.bentolor.java9to13samples.language;

import org.junit.jupiter.api.Test;

import static de.bentolor.java9to13samples.language.SwitchExpression.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SwitchExpression {

    enum Direction {N, S, W, E}

    String switchExpressionJDK8(Direction way) {
        String result;
        switch (way) {
            case N:
                result = "Up";
                break;
            case S:
                result = "Down";
                break;
            case E:
            case W:
                result = "Somewhere left or right";
                break;
            default:
                throw new IllegalStateException("Huh?: " + way);
        }
        return result;
    }

    String switchExpressionPreview13(Direction way) {
        return switch (way) {
            case N -> "Up";
            // JDK13: case S -> { yield "Down"; }
            case S -> "Down";
            case E, W -> "Somewhere left or right";
        };
    }

    void switchArrowForm(int num) {
        switch (num) {
            case 0 -> System.out.println("Zero");
            case 1, 2, 3 -> {
                var response = "value was: ";
                System.out.println(response);
            }
        }
    }

    @Test
    void testSwitch() {
        switchArrowForm(3);
        assertEquals("Down", switchExpressionJDK8(S));
        assertEquals("Down", switchExpressionPreview13(S));
    }

    private PortType replaceSimpleSwitch(int port) {
        PortType type = switch (port) {
            case 20 -> PortType.FTP;
            case 80, 8080 -> PortType.HTTP;
            case 27017 -> PortType.DATABASE;
            default -> PortType.UNKNOWN;
        };
        return type;
    }

    private int namePort(PortType type) {
        return switch (type) {
            case UNUSED -> 0;
            case UNKNOWN -> 0;
            case FTP -> 20;
            case HTTP -> 80;
            case DATABASE -> 123;
            case BUSY -> 0;
            case SAFE -> 0;
        };
    }

    public enum PortType {
        HTTP, DATABASE, UNUSED, UNKNOWN, FTP, BUSY, SAFE
    }

}
