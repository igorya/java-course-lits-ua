package ua.lits.lesson11.calc;

import java.math.BigDecimal;

abstract class Operator implements BiFunctionCalc<BigDecimal, BigDecimal, BigDecimal> {
    /**
     * Expression builder
     * @param val1 value1
     * @param val2 value2
     * @return Human-readable expression
     */
    String makeExpression(double val1, double val2) {
        return String.format("%s %s %s", val1, getSymbol(), val2);
    }

    /**
     * @return Symbol or sign for current operator
     */
    abstract String getSymbol();
}
