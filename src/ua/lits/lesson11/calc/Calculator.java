package ua.lits.lesson11.calc;

import java.math.BigDecimal;

public class Calculator {
    private final Operator operatorAdd;
    private final Operator operatorSubtract;
    private final Operator operatorMultiply;
    private final Operator operatorDivide;
    private double result;
    private String expression;

    public Calculator() {
        operatorAdd = new OperatorAdd();
        operatorSubtract = new OperatorSubtract();
        operatorMultiply = new OperatorMultiply();
        operatorDivide = new OperatorDivide();
        reset();
    }

    public void reset() {
        result = 0.0;
        expression = "";
    }

    public double getResult() {
        return result;
    }

    public Calculator add(double v1, double v2) throws CalculatorException {
        return performOperation(operatorAdd, v1, v2);
    }

    public Calculator subtract(double v1, double v2) throws CalculatorException {
        return performOperation(operatorSubtract, v1, v2);
    }

    public Calculator multiply(double v1, double v2) throws CalculatorException {
        return performOperation(operatorMultiply, v1, v2);
    }

    public Calculator divide(double v1, double v2) throws CalculatorException {
        return performOperation(operatorDivide, v1, v2);
    }

    private Calculator performOperation(Operator operator, double v1, double v2) throws CalculatorException {
        reset();
        result = operator.apply(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2)).doubleValue();
        expression = operator.makeExpression(v1, v2);
        return this;
    }

    public void display() {
        System.out.println(expression.length() == 0 ? result : String.format("%s = %s", expression, result));
    }
}
