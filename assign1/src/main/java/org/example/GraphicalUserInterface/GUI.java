package org.example.GraphicalUserInterface;

import org.example.BusinessLogic.Operations;
import org.example.datamodels.Monomial;
import org.example.datamodels.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI implements ActionListener {

    JFrame frame;
    JTextField textField, textField2;
    JLabel label1, label2;
    JButton[] opBtns = new JButton[8];
    JButton addBtn, subBtn, mulBtn, divBtn, derivBtn, integrBtn, equBtn, clrBtn;
    JPanel panel;
    Font myFont = new Font("Inter", Font.ITALIC, 23);
    char op;

    public GUI() {

        frame = new JFrame("Polynom Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(150, 25, 200, 30);
        textField.setFont(myFont);
        textField2 = new JTextField();
        textField2.setBounds(150, 55, 200, 30);
        textField2.setFont(myFont);

        //create label
        label1 = new JLabel("poly1 = ");
        label1.setFont(myFont);
        label1.setBounds(50, 25, 115, 30);
        label2 = new JLabel("poly2 = ");
        label2.setFont(myFont);
        label2.setBounds(50, 55, 115, 30);

        //create buttons for operations
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        derivBtn = new JButton("∂");
        integrBtn = new JButton("∫");
        equBtn = new JButton("=");
        clrBtn = new JButton("Clear");

        // add buttons in array
        opBtns[0] = addBtn;
        opBtns[1] = subBtn;
        opBtns[2] = mulBtn;
        opBtns[3] = divBtn;
        opBtns[4] = derivBtn;
        opBtns[5] = integrBtn;
        opBtns[6] = equBtn;
        opBtns[7] = clrBtn;

        for (JButton btns : opBtns) {
            btns.addActionListener(this);
            btns.setFont(myFont);
            btns.setFocusable(false);
        }

        equBtn.setBounds(50, 430, 145, 50);
        clrBtn.setBounds(205, 430, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 150, 300, 200);
        panel.setLayout(new GridLayout(3, 3, 8, 8));

        panel.add(addBtn);
        panel.add(subBtn);
        panel.add(mulBtn);
        panel.add(divBtn);
        panel.add(derivBtn);
        panel.add(integrBtn);

        frame.add(panel);
        frame.add(label1);
        frame.add(label2);
        frame.add(equBtn);
        frame.add(clrBtn);
        frame.add(textField);
        frame.add(textField2);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GUI calc = new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Polynomial result = new Polynomial();
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        Polynomial remain = new Polynomial();

        if (e.getSource() == addBtn)
            op = '+';

        if (e.getSource() == subBtn)
            op = '-';

        if (e.getSource() == mulBtn)
            op = '*';

        if (e.getSource() == divBtn)
            op = '/';

        if (e.getSource() == derivBtn)
            op = 'd';

        if (e.getSource() == integrBtn)
            op = 'i';

        if (e.getSource() == equBtn) {

            switch (op) {
                case '+':
                    p1 = parsePolynomial(textField.getText());
                    p2 = parsePolynomial(textField2.getText());
                    result = Operations.add(p1, p2);
                    break;
                case '-':
                    p1 = parsePolynomial(textField.getText());
                    p2 = parsePolynomial(textField2.getText());
                    result = Operations.sub(p1, p2);
                    break;
                case '*':
                    p1 = parsePolynomial(textField.getText());
                    p2 = parsePolynomial(textField2.getText());
                    result = Operations.mul(p1, p2);
                    break;
                case '/':
                    p1 = parsePolynomial(textField.getText());
                    p2 = parsePolynomial(textField2.getText());
                    Polynomial[] res = Operations.div(p1, p2);
                    result = res[0];
                    remain = res[1];
                    textField.setText(result.toString());
                    textField2.setText(remain.toString());
                    break;
                case 'd':
                    p1 = parsePolynomial(textField.getText());
                    result = Operations.deriv(p1);
                    break;
                case 'i':
                    p1 = parsePolynomial(textField.getText());
                    result = Operations.integr(p1);
                    break;
            }

            if (op == '/') {
                label1.setText("quotient = ");
                label2.setText("remain = ");
            } else {
                label1.setText("res = ");
                label2.setText("");
                textField2.setText("");
            }

            textField.setText(result.toString());
            textField.setEditable(false);
            textField2.setEditable(false);
        }

        if (e.getSource() == clrBtn) {
            textField.setText("");
            textField2.setText("");
            textField.setEditable(true);
            textField2.setEditable(true);
            label1.setText("poly1 = ");
            label2.setText("poly2 = ");
        }
    }

    private Polynomial parsePolynomial(String input) {
        Polynomial polynomial = new Polynomial();
        Pattern monomPattern = Pattern.compile("([+-]?(\\d*x(?:\\^\\d+)?|\\d+)\\b)");
        Matcher matcher = monomPattern.matcher(input);
        boolean found = false;

        while (matcher.find()) {
            String term = matcher.group(1);
            //  System.out.println("Monom gasit: " + matcher.group(1));
            Monomial monomial = parseMonomial(term);
            polynomial.addMonomial(monomial);
            found = true;
        }

        if (!found){
            throw new IllegalArgumentException("This is not a polynomial!!!");
        }

        return polynomial;
    }

    private Monomial parseMonomial(String input) {
        Pattern coeffPattern = Pattern.compile("(-?\\d*)(?:x\\^?\\d*|(?!\\d)x|$)");
        Pattern degreePattern = Pattern.compile("\\^(\\d+)");

        Matcher coefficientMatcher = coeffPattern.matcher(input);
        Matcher degreeMatcher = degreePattern.matcher(input);

        int coeff = 0;
        int degr = 0;

        if (coefficientMatcher.find()) {
            String coefficientStr = coefficientMatcher.group(1);
            // System.out.println("Monom coeficient gasit: " + coefficientMatcher.group(1));
            if (coefficientStr.isEmpty()) {
                coeff = 1;
            } else if (coefficientStr.equals("-")) {
                coeff = -1;
            } else {
                coeff = Integer.parseInt(coefficientStr);
            }
        }

        if (degreeMatcher.find()) {
            String degreeStr = degreeMatcher.group(1);
            //  System.out.println("Monom grad gasit: " + degreeMatcher.group(1));
            if (degreeStr != null) {
                degr = Integer.parseInt(degreeStr);
            }
        } else {
            Pattern degreePattern01 = Pattern.compile("x");
            Matcher degreeMatcher01 = degreePattern01.matcher(input);
            if (degreeMatcher01.find()) {
                // System.out.println("Monom grad gasit: " + 1);
                degr = 1;
            } else {
                degr = 0;
                //   System.out.println("Monom grad gasit: " + 0);
            }
        }
        return new Monomial(degr, coeff);
    }
}
