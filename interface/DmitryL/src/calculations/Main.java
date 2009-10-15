//Lebedev Dmitry g261 2009 (c)

package calculations;

public class Main {
        static final int NUMBER_ONE = 15;
        static final int NUMBER_TWO = 5;
        static final int NUMBER_THREE = 0;

        public static Calculating calculator1 = new Addition();
        public static Calculating calculator2 = new Division();

        public static int calculation (Calculating a, int x, int y) {
        return a.calculate(x, y);
    }

    public static void main(String[] args) {

          System.out.println(calculation(calculator1, NUMBER_ONE, NUMBER_TWO));
          System.out.println(calculation(calculator1, NUMBER_ONE, NUMBER_THREE));

          System.out.println(calculation(calculator2, NUMBER_ONE, NUMBER_TWO));
          System.out.println(calculation(calculator2, NUMBER_ONE, NUMBER_THREE));
    }

}
