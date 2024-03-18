package org.example;

import org.example.BusinessLogic.Operations;
import org.example.datamodels.Monomial;
import org.example.datamodels.Polynomial;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class OperationsTest {
    @Test
    public void addTest() {
        Monomial m1 = new Monomial(3, 4);
        Monomial m2 = new Monomial(1, 7);
        Monomial m3 = new Monomial(0, 2);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Monomial m4 = new Monomial(3, 5);
        Monomial m5 = new Monomial(2, 2);
        Monomial m6 = new Monomial(0, 4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m4);
        p2.addMonomial(m5);
        p2.addMonomial(m6);

        Polynomial p3 = new Polynomial();
        p3 = Operations.add(p1, p2);
        assertEquals("9x^3+2x^2+7x+6", p3.toString());
    }

    @Test
    public void addTestWrong() {
        Monomial m1 = new Monomial(3, 4);
        Monomial m2 = new Monomial(1, 7);
        Monomial m3 = new Monomial(0, 2);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Monomial m4 = new Monomial(3, 5);
        Monomial m5 = new Monomial(2, 2);
        Monomial m6 = new Monomial(0, 4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m4);
        p2.addMonomial(m5);
        p2.addMonomial(m6);

        Polynomial p3 = new Polynomial();
        p3 = Operations.add(p1, p2);
        assertEquals("9x^3+3x^2+7x+6", p3.toString());
    }

    @Test
    public void subTest() {
        Monomial m1 = new Monomial(3, 4);
        Monomial m2 = new Monomial(1, 7);
        Monomial m3 = new Monomial(0, 2);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Monomial m4 = new Monomial(3, 5);
        Monomial m5 = new Monomial(2, 2);
        Monomial m6 = new Monomial(0, 4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m4);
        p2.addMonomial(m5);
        p2.addMonomial(m6);

        Polynomial p3 = new Polynomial();
        p3 = Operations.sub(p1, p2);
        assertEquals("-x^3-2x^2+7x-2", p3.toString());
    }

    @Test
    public void subTestWrong() {
        Monomial m1 = new Monomial(3, 4);
        Monomial m2 = new Monomial(1, 7);
        Monomial m3 = new Monomial(0, 2);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Monomial m4 = new Monomial(3, 5);
        Monomial m5 = new Monomial(2, 2);
        Monomial m6 = new Monomial(0, 4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m4);
        p2.addMonomial(m5);
        p2.addMonomial(m6);

        Polynomial p3 = new Polynomial();
        p3 = Operations.sub(p1, p2);
        assertEquals("-x^3+2x^2+7x-2", p3.toString());
    }

    @Test
    public void mulTest() {
        Monomial m1 = new Monomial(3, 4);
        Monomial m2 = new Monomial(1, 7);
        Monomial m3 = new Monomial(0, 2);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Monomial m4 = new Monomial(3, 5);
        Monomial m5 = new Monomial(2, 2);
        Monomial m6 = new Monomial(0, 4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m4);
        p2.addMonomial(m5);
        p2.addMonomial(m6);

        Polynomial p3 = new Polynomial();
        p3 = Operations.mul(p1, p2);
        assertEquals("20x^6+8x^5+35x^4+40x^3+4x^2+28x+8", p3.toString());
    }

    @Test
    public void mulTestWrong() {
        Monomial m1 = new Monomial(3, 4);
        Monomial m2 = new Monomial(1, 7);
        Monomial m3 = new Monomial(0, 2);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Monomial m4 = new Monomial(3, 5);
        Monomial m5 = new Monomial(2, 2);
        Monomial m6 = new Monomial(0, 4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m4);
        p2.addMonomial(m5);
        p2.addMonomial(m6);

        Polynomial p3 = new Polynomial();
        p3 = Operations.mul(p1, p2);
        assertEquals("20x^6+8x^5+35x^4+41x^3+4x^2+28x+8", p3.toString());
    }

    @Test
    public void derivTest() {
        Monomial m1 = new Monomial(3, 4);
        Monomial m2 = new Monomial(1, 7);
        Monomial m3 = new Monomial(0, 2);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Polynomial p3 = new Polynomial();
        p3 = Operations.deriv(p1);
        assertEquals("12x^2+7", p3.toString());
    }

    @Test
    public void derivTestWrong() {
        Monomial m1 = new Monomial(3, 4);
        Monomial m2 = new Monomial(1, 7);
        Monomial m3 = new Monomial(0, 2);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Polynomial p3 = new Polynomial();
        p3 = Operations.deriv(p1);
        assertEquals("12x^2-7", p3.toString());
    }

    @Test
    public void integrTest() {
        Monomial m1 = new Monomial(3, 5);
        Monomial m2 = new Monomial(2, 2);
        Monomial m3 = new Monomial(0, 4);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Polynomial p3 = new Polynomial();
        p3 = Operations.integr(p1);
        assertEquals("1.25x^4+0.67x^3+4x", p3.toString());
    }

    @Test
    public void integrTestWrong() {
        Monomial m1 = new Monomial(3, 5);
        Monomial m2 = new Monomial(2, 2);
        Monomial m3 = new Monomial(0, 4);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Polynomial p3 = new Polynomial();
        p3 = Operations.integr(p1);
        assertEquals("1.25x^4+0.66x^3+4x", p3.toString());
    }

    @Test
    public void divTest() {
        Monomial m1 = new Monomial(4, 2);
        Monomial m2 = new Monomial(2, 1);
        Monomial m3 = new Monomial(1, 1);
        Monomial m7 = new Monomial(0, -3);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);
        p1.addMonomial(m7);

        Monomial m4 = new Monomial(2, 1);
        Monomial m5 = new Monomial(1, -4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m4);
        p2.addMonomial(m5);

        Polynomial[] res = Operations.div(p1, p2);
        Polynomial C = res[0];
        Polynomial R = res[1];
        assertEquals("2x^2+8x+33", C.toString());
        assertEquals("133x-3", R.toString());
    }

    @Test
    public void divTestWrong() {
        Monomial m1 = new Monomial(3, 1);
        Monomial m2 = new Monomial(2, -2);
        Monomial m3 = new Monomial(1, 6);
        Monomial m7 = new Monomial(0, -5);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);
        p1.addMonomial(m7);

        Monomial m4 = new Monomial(2, 1);
        Monomial m5 = new Monomial(0, -1);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m4);
        p2.addMonomial(m5);

        Polynomial[] res = Operations.div(p1, p2);
        Polynomial C = res[0];
        Polynomial R = res[1];
        assertEquals("x-2", C.toString());
        assertEquals("7x+7", R.toString());
    }

}
