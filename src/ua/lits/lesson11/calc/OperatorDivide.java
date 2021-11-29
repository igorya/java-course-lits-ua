package ua.lits.lesson11.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

class OperatorDivide extends Operator {

    /**
     * Applies this function to the given arguments.
     *
     * @param val1  the first function argument
     * @param val2 the second function argument
     * @return the function result
     */
    @Override
    public BigDecimal apply(BigDecimal val1, BigDecimal val2) throws CalculatorException {
        if (val2.compareTo(BigDecimal.ZERO) == 0) {
            throw new CalculatorException("Operand "+ val1 +" Can't be divided by zero");
        }
        return val1.divide(val2, RoundingMode.HALF_UP);
    }

    @Override
    String getSymbol() {
        return "/";
    }
}
