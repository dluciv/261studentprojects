package exception;

public class Main {

    public static int calculate(Calculator calculator, int firstNumber, int secondNumber) {
        if (calculator == null) {
            throw new IllegalArgumentException("Illegal argument");
        }

        return calculator.calculate(firstNumber, secondNumber);
    }
}
