package org.example.datamodels;

import java.util.*;

public class Polynomial {
    private final Map<Integer, Monomial> monomials;

    public Polynomial() {
        monomials = new HashMap<Integer, Monomial>();
    }

    public void addMonomial(Monomial monomial) {
        //adaugam monomul la polinom pe pozitia corespunzatoare;
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            //daca polinoamele au acelasi grad, iar lista de polinoame are deja un polinom de acest grad
            if (entry.getKey().equals(monomial.getDegree())) {
                // adunam coeficientii
                int newCoef = entry.getValue().getCoefficient().intValue() + monomial.getCoefficient().intValue();
                // actualizam coeficientul polinomului cu grad = grad(monomial)
                entry.getValue().setCoefficient(newCoef);
                return;
            }
        }
        // daca nu s-a gasit monom de acelasi grad, il adaugam in polinom
        monomials.put(monomial.getDegree(), monomial);
    }

    @Override
    public String toString() {
        String result = "";
        List<Monomial> monoms = new ArrayList<>();

        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet())
            monoms.add(entry.getValue());

        //afisare in ordine descrescatoare a gradului
        Collections.sort(monoms);

        for (Monomial mono : monoms) {
            if (result.isEmpty()) {
                result += mono.toString();
                continue;
            } else if (mono.getCoefficient().intValue() > 0)
                result += "+" + mono.toString();
            else if (mono.getCoefficient().doubleValue() > 0 && mono.getCoefficient().doubleValue() < 1)
                result += "+" + mono.toString();
            else
                result += mono.toString();  //pt minus pastram - de la coeficient
        }
        return result;
    }

    public Map<Integer, Monomial> getMonomials() {
        return monomials;
    }

    public Polynomial sortMonoms() {
        Polynomial sortedPoly = new Polynomial();
        List<Monomial> monoms = new ArrayList<>();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet())
            monoms.add(entry.getValue());

        Collections.sort(monoms);
        for (Monomial mono : monoms)
            sortedPoly.addMonomial(mono);

        return sortedPoly;
    }

    public Integer getMaxDegree() {
        int maxDegree = 0;

        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            Monomial m = entry.getValue();
            if (m.getCoefficient().intValue() != 0 && maxDegree < m.getDegree()) {
                maxDegree = m.getDegree();
            }
        }

        return maxDegree;
    }

    public boolean isZero() {
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            if (entry.getValue().getCoefficient().doubleValue() != 0)
                return false;
        }
        return true;
    }

}
