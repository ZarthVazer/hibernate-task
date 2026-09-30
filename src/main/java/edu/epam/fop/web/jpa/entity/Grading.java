package edu.epam.fop.web.jpa.entity;

import java.util.Arrays;

/**
 * @author Adilov Abdubosit
 */
public enum Grading {
    TEST,
    EXAM,
    REPORT;

    public static Grading parse(String value) {
        return Arrays.stream(values())
                .filter(grading -> grading.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown grading: " + value + ", expected one of " + Arrays.toString(values())));
    }
}
