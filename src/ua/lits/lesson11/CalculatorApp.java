package ua.lits.lesson11;

import ua.lits.common.PrintHelper;
import ua.lits.lesson11.calc.Calculator;
import ua.lits.lesson11.calc.CalculatorException;

class CalculatorApp {

    Calculator calc;

    public CalculatorApp() {
        calc = new Calculator();
    }

    void run() {
        try {
            // Undefined zero result
            calc.display();

            // Display result only
            System.out.println("Custom text for expr 2 + 3 = " + calc.add(2, 3).getResult());

            // Human-readable mode: expression with result
            calc.add(2.1, 3.8).display();
            calc.subtract(20.5, 15.4).display();
            calc.multiply(3, 6.1).display();
            calc.multiply(312.8045, 69.123).display();
            calc.divide(70, 7).display();
            calc.divide(54.145, 0.457).display();
            calc.divide(43.20145, 12.07812).display();

            // Throws exception
            calc.divide(70, 0).display();

        } catch (CalculatorException e) {
            PrintHelper.error(e.getMessage());
            PrintHelper.error("App has been stopped");

        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintHelper.info("--== END of Calculator Application ==--");
    }
}
