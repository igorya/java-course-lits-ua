package ua.lits.lesson11.calc;

@FunctionalInterface
interface BiFunctionCalc<T, U, R> {
    R apply(T t, U u) throws CalculatorException;
}
