package edu.estudiantat.upc.AndroidCalculator;

public class Calculator {

    private String current = "0";
    private String expression = "";
    private String pending = "";
    private double stored = 0;
    private boolean degrees = true;

    public String getCurrent() {
        return current;
    }

    public String getExpression() {
        return expression;
    }

    public void setDegrees(boolean value) {
        degrees = value;
    }

    public void number(String digit) {
        if (current.equals("0")) {
            current = digit;
        } else {
            current = current + digit;
        }
    }

    public void comma() {
        if (current.contains(",")) {
            return;
        }
        current = current + ",";
    }

    public void operator(String op) {
        if (pending.equals("")) {
            stored = value();
        } else {
            stored = calculate(stored, value(), pending);
        }
        pending = op;
        current = "0";
        expression = text(stored) + " " + op;
    }

    public void equal() {
        if (pending.equals("")) {
            return;
        }
        double a = stored;
        double b = value();
        String op = pending;
        if (calculate(a, b, op) == Double.POSITIVE_INFINITY){
            current = "Infinity";
        }
        else {
            current = text(calculate(a, b, op));
        }
        expression = text(a) + " " + op + " " + text(b) + " =";
        pending = "";
    }

    public void trig(String op) {
        double a = value();
        if (calculate(a, op) == Double.POSITIVE_INFINITY){
            current = "Infinity";
        }
        else {
            current = text(calculate(a, op));
        }
        expression = op + "(" + text(a) + ") =";
    }

    public void clear() {
        current = "0";
        expression = "";
        pending = "";
        stored = 0;
    }

    private double value() {
        return Double.parseDouble(current.replace(",", "."));
    }

    private double calculate(double a, double b, String op) {
        if (op.equals("+")) {
            return a + b;
        }
        if (op.equals("-")) {
            return a - b;
        }
        if (op.equals("×")) {
            return a * b;
        }
        if (op.equals("÷")) {
            if (b == 0) {
                return Double.POSITIVE_INFINITY;
            }
            return a / b;
        }
        return a;
    }

    private double calculate(double a, String op) {
        double r = degrees ? Math.toRadians(a) : a;
        if (op.equals("Sin")) {
            return Math.sin(r);
        }
        else if (op.equals("Cos")) {
            return Math.cos(r);
        }
        else {
            double coseno = Math.cos(r);
            if (Math.abs(coseno) < 1e-15) {
                return Double.POSITIVE_INFINITY;
            }
            return Math.tan(r);
        }
    }

    private String text(double number) {
        if (number == Math.rint(number)) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number).replace(".", ",");
    }
}