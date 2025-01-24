package ru.sinvic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class QuadraticEquationSolverTest {
    @Test
    void testSum() {
        QuadraticEquationSolver quadraticEquationSolver = new QuadraticEquationSolver();
        assertEquals(quadraticEquationSolver.sum(2, 2), 4);
    }

    @Test
    void testSumNegative() {
        QuadraticEquationSolver quadraticEquationSolver = new QuadraticEquationSolver();
        assertEquals(quadraticEquationSolver.sum(2, -3), -1);
    }
}