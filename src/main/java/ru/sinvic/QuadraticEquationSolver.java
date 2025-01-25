package ru.sinvic;

import java.util.stream.DoubleStream;

public class QuadraticEquationSolver {
    final static String ILLEGAL_A_MESSAGE = "a не должно быть равно 0.";
    final static String NAN_OR_INFINITE_MESSAGE = "коэффициенты должны быть числами.";

    public double[] solve(double a, double b, double c, double e) {
        if (Math.abs(a) <= e) {
            throw new IllegalArgumentException(ILLEGAL_A_MESSAGE);
        }

        if (isContainsNaN(a, b, c) || isContainsInfinity(a, b, c)) {
            throw new IllegalArgumentException(NAN_OR_INFINITE_MESSAGE);
        }

        double D = b * b - 4 * a * c;

        if (D < -e) {
            return new double[0];
        }

        if (D > e) {
            return new double[]{(-b + Math.sqrt(D)) / (2 * a), (-b - Math.sqrt(D)) / (2 * a)};
        }

        return new double[]{-b / (2 * a)};
    }

    private boolean isContainsNaN(double a, double b, double c) {
        return DoubleStream.of(a, b, c).anyMatch(Double::isNaN);
    }

    private boolean isContainsInfinity(double a, double b, double c) {
        return DoubleStream.of(a, b, c)
                .anyMatch(i -> i == Double.POSITIVE_INFINITY || i == Double.NEGATIVE_INFINITY);
    }
}
