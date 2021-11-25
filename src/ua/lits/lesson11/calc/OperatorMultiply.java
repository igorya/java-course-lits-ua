package ua.lits.lesson11.calc;

import java.math.BigDecimal;

class OperatorMultiply extends Operator {

    /**
     * Applies this function to the given arguments.
     *
     * @param val1  the first function argument
     * @param val2 the second function argument
     * @return the function result
     */
    @Override
    public BigDecimal apply(BigDecimal val1, BigDecimal val2) {
        return val1.multiply(val2);
    }

    @Override
    String getSymbol() {
        return "*";
    }
}
