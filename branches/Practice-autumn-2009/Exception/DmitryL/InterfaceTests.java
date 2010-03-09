//Lebedev Dmitry g261 2009 (c)
//This task include throwing exception
package tests;

import calculations.Addition;
import calculations.Division;
import calculations.Main;
import org.junit.*;

public class MainTests {

    static final int NUMBER_ONE = 15;
    static final int NUMBER_TWO = 5;
    static final int NUMBER_THREE = 0;
    public static Addition additor = new Addition();
    public static Division divisor = new Division();

    @Test
    public void additionTest() {
        Assert.assertNotNull((Main.calculate(additor, NUMBER_ONE, NUMBER_TWO)));
    }

    @Test
    public void divisionTest() {
        Assert.assertNotNull((Main.calculate(divisor, NUMBER_ONE, NUMBER_TWO)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullPointer() {
        Main.calculate(null, NUMBER_ONE, NUMBER_THREE);
    }
    @Test(expected = ArithmeticException.class)
    public void ArithmeticException() {
        Main.calculate(divisor, NUMBER_ONE, NUMBER_THREE);
    }
}
