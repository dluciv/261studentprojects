package moneyconverter;

/**
 * Testing MoneyConverter
 * @author Zubrilin Andrey (c) 2009
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class testconverter {

    public testconverter(){

    }
    
    public static double Course;
    public static String value;
    public static boolean isUSD;

    @Test
    public void TestingUsdToRub() {
        isUSD = true;
        value = "100";
        Course = 29.1;
        assertTrue(converter.calcMoney(value, Course, isUSD)==2910);
    }

    @Test
    public void TestingRubToUsd() {
        isUSD = false;
        value = "2910";
        Course = 29.1;
        assertTrue(converter.calcMoney(value, Course, isUSD)==100);
    }

    @Test
    public void TestingBadValue() {
        isUSD = true;
        value = "dfgdg";
        Course = 29.1;
        assertTrue(converter.calcMoney(value, Course, isUSD)==0);
    }
}
