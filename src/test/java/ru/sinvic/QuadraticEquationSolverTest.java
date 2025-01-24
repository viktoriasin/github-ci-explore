package ru.sinvic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class QuadraticEquationSolverTest {

    private QuadraticEquationSolver quadraticEquationSolver;

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Double.POSITIVE_INFINITY, 10, 1),
                Arguments.of(1, Double.POSITIVE_INFINITY, 1),
                Arguments.of(1, 10, Double.POSITIVE_INFINITY),
                Arguments.of(Double.NEGATIVE_INFINITY, 10, 1),
                Arguments.of(1, Double.NEGATIVE_INFINITY, 1),
                Arguments.of(1, 10, Double.NEGATIVE_INFINITY),
                Arguments.of(NaN, 10, 1),
                Arguments.of(1, NaN, 1),
                Arguments.of(1, 10, NaN)
        );
    }

    @BeforeEach
    public void setUp() {
        quadraticEquationSolver = new QuadraticEquationSolver();
    }


    @Test
    void testNoRoots() {
        double[] result = quadraticEquationSolver.solve(1.0, 0, 1.0, 1e-5);
        assertEquals(0, result.length);
    }

    @Test
    void testTwoRoots() {
        double[] result = quadraticEquationSolver.solve(1.0, 0, -1.0, 1e-5);
        Assertions.assertThat(result).containsExactlyInAnyOrder(1, -1);
    }

    @Test
    void testOneRoot() {
        double[] result = quadraticEquationSolver.solve(2, 1e-3, 1e-8, 1e-5);
        assertEquals(result.length, 1);
        assertEquals(result[0], -2.5E-4);
    }

    @Test
    void testAZero() {
        assertThrows(IllegalArgumentException.class,
                () -> quadraticEquationSolver.solve(1e-7, 2.0, 1.0, 1e-5));
    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void testWrongDouble(double a, double b, double c) {
        assertThrows(IllegalArgumentException.class,
                () -> quadraticEquationSolver.solve(a, b, c, 1e-5));
    }


}