//Lebedev Dmitry g261 2009 (c)
//This task include throwing exception
package tests;

import calculations.Addition;
import calculations.Calculator;
import calculations.Division;
import calculations.Main;
import org.junit.*;

public class InterfaceTests {

    static final int NUMBER_ONE = 15;
    static final int NUMBER_TWO = 5;
    static final int NUMBER_THREE = 0;
    public static Calculator additor = new Addition();
    public static Calculator divisor = new Division();

    @Test
    public void additionTest() {
        Assert.assertNotNull((Main.calculate(additor, NUMBER_ONE, NUMBER_TWO)));
    }

    @Test
    public void divisionTest() {
        Assert.assertNotNull((Main.calculate(divisor, NUMBER_ONE, NUMBER_TWO)));
    }

    @Test(expected = NullPointerException.class)
    public void NullPointer() {
        Main.calculate(null, NUMBER_ONE, NUMBER_THREE);
    }
}
