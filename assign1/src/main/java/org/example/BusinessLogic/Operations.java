package org.example.BusinessLogic;

import org.example.datamodels.Monomial;
import org.example.datamodels.Polynomial;

import java.util.Map;

public class Operations {

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();
        res = p1;
        for (Map.Entry<Integer, Monomial> entry : p2.getMonomials().entrySet()) {
            res.addMonomial(entry.getValue());
        }
        return res;
    }

    public static Polynomial sub(Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();
        res = p1;
        for (Map.Entry<Integer, Monomial> entry : p2.getMonomials().entrySet()) {
            //la fiecare monom din p2 ii schimbam semnul => p1 + (-p2)
            Monomial p2Neg = new Monomial(entry.getValue().getDegree(), -entry.getValue().getCoefficient().doubleValue());
            res.addMonomial(p2Neg);
        }
        return res;
    }

    public static Polynomial mul(Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry1 : p1.getMonomials().entrySet()) {
            for (Map.Entry<Integer, Monomial> entry2 : p2.getMonomials().entrySet()) {
                double coef = entry1.getValue().getCoefficient().doubleValue() * entry2.getValue().getCoefficient().doubleValue();
                int degree = entry1.getValue().getDegree() + entry2.getValue().getDegree();
                Monomial mulMonom = new Monomial(degree, coef);
                res.addMonomial(mulMonom);
            }
        }
        return res;
    }

    public static Polynomial deriv(Polynomial p1) {
        Polynomial res = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : p1.getMonomials().entrySet()) {
            int coef = entry.getValue().getDegree() * entry.getValue().getCoefficient().intValue();
            int degree = entry.getValue().getDegree() - 1;
            Monomial derivMonom = new Monomial(degree, coef);
            res.addMonomial(derivMonom);
        }
        return res;
    }

    public static Polynomial integr(Polynomial p1) {
        Polynomial res = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : p1.getMonomials().entrySet()) {
            double coef = entry.getValue().getCoefficient().doubleValue() / (entry.getValue().getDegree() + 1);
            int degree = entry.getValue().getDegree() + 1;

            if (coef % 1 == 0) {
                Monomial integrMonom = new Monomial(degree, (int) coef);
                res.addMonomial(integrMonom);
            } else {
                Monomial integrMonom = new Monomial(degree, coef);
                res.addMonomial(integrMonom);
            }
        }
        return res;
    }

    public static Polynomial[] div(Polynomial D, Polynomial I) {
        // daca I este 0 => nu se poate imparti
        if (I.isZero())
            throw new IllegalArgumentException("Cannot divide by 0!");

        Polynomial C = new Polynomial();
        D.sortMonoms();
        I.sortMonoms();
        Polynomial R = D;

        int maxDegreeR = R.getMaxDegree();
        int maxDegreeI = I.getMaxDegree();

        if (maxDegreeI == 0 && maxDegreeR == 0) {
            R = new Polynomial();
            C = new Polynomial();

            double d = D.getMonomials().get(0).getCoefficient().doubleValue();
            double i = I.getMonomials().get(0).getCoefficient().doubleValue();

            R.addMonomial(new Monomial(0, 0));
            C.addMonomial(new Monomial(0, d / i));

            return new Polynomial[]{C, R};
        }

        while (maxDegreeR >= maxDegreeI) {

            Monomial leadR = R.getMonomials().get(maxDegreeR);
            Monomial leadL = I.getMonomials().get(maxDegreeI);

            double coef = leadR.getCoefficient().doubleValue() / leadL.getCoefficient().doubleValue();
            int degree = leadR.getDegree() - leadL.getDegree();
            Monomial tMonom = new Monomial(degree, coef);
            Polynomial t = new Polynomial();
            t.addMonomial(tMonom);

            C = Operations.add(C, t);
            R = Operations.sub(R, Operations.mul(t, I));

            R.sortMonoms();
            maxDegreeR = R.getMaxDegree();
        }

        return new Polynomial[]{C, R};
    }
}
