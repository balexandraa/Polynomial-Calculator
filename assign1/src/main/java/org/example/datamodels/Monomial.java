package org.example.datamodels;

public class Monomial implements Comparable<Monomial> {
    private int degree;
    private Number coefficient;

    public Monomial(int degree, Number coefficient) {
        this.degree = degree;
        this.coefficient = coefficient;

    }

    public Number getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = coefficient;
    }

    public int getDegree() {
        return degree;
    }

    public String toString() {
        if (coefficient instanceof Integer)
            return toStringInt();
        else if (coefficient.doubleValue() % 1 == 0)
            return toStringInt();
        else
            return toStringDouble();
    }

    public String toStringInt() {
        if (coefficient.intValue() == 0)
            return "";
        else if (degree == 0) {
            return coefficient.intValue() + "";
        } else if (degree == 1) {
            if (coefficient.intValue() == 1)
                return "x";
            else if (coefficient.intValue() == -1)
                return "-x";
            else
                return coefficient.intValue() + "x";
        } else {
            if (coefficient.intValue() == 1)
                return "x^" + degree;
            else if (coefficient.intValue() == -1)
                return "-x^" + degree;
            else
                return coefficient.intValue() + "x^" + degree;
        }
    }

    public String toStringDouble() {
        if (coefficient.doubleValue() == 0)
            return "";
        else if (degree == 0) {
            return String.format("%.2f", coefficient.doubleValue());
        } else if (degree == 1) {
            if (coefficient.doubleValue() == 1.0)
                return "x";
            else if (coefficient.doubleValue() == -1.0)
                return "-x";
            else
                return String.format("%.2fx", coefficient.doubleValue());
        } else {
            if (coefficient.doubleValue() == 1.0)
                return "x^" + degree;
            else if (coefficient.doubleValue() == -1.0)
                return "-x^" + degree;
            else
                return String.format("%.2fx^%d", coefficient.doubleValue(), degree);
        }
    }

    @Override
    public int compareTo(Monomial altMonom) {
        //ordonare descrescator dupa grad
        return -degree + altMonom.degree;
    }

}
