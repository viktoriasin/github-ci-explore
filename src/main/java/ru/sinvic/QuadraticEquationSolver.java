package ru.sinvic;

import java.util.stream.DoubleStream;

public class QuadraticEquationSolver {
    public double[] solve(double a, double b, double c, double e) {
        if (Math.abs(a) <= e) {
            throw new IllegalArgumentException("a не равно 0.");
        }

        if (isContainsNaN(a, b, c) || isContainsInfinity(a, b, c)) {
            throw new IllegalArgumentException("коэффициенты должны быть числами.");
        }

        double D = b * b - 4 * a * c;
        if (D < -e) {
            return new double[0];
        }
        double x1;
        double x2;
        if (D > e) {
            x1 = (-b + Math.sqrt(D)) / (2 * a);
            x2 = (-b - Math.sqrt(D)) / (2 * a);
            return new double[] {x1, x2};
        }

        if (Math.abs(D) <= e) {
            x1 = -b / (2 * a);
            return new double[] {x1};
        }
        throw new UnsupportedOperationException();
    }

    private boolean isContainsNaN(double a, double b, double c) {
        return DoubleStream.of(a, b, c).anyMatch(Double::isNaN);
    }

    private boolean isContainsInfinity(double a, double b, double c) {
        return DoubleStream.of(a, b, c)
                .anyMatch(i -> i == Double.POSITIVE_INFINITY || i == Double.NEGATIVE_INFINITY);
    }
}
