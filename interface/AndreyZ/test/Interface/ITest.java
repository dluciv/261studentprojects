package Interface;

/**
 * Testfile(Testing Interface IPrice,Classes Cars and Socks);
 * @author Zubrilin Andrey (c)2009
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class ITest {

    //Testing Interface IPrice,Class Socks
    public IPrice modelSocks;

    @Test
    public void TestingSocks1()
    {
        modelSocks = new Socks(3);
        assertTrue(modelSocks.seePrice() == 9);
    }

    @Test
    public void TestingSocks2()
    {
        modelSocks = new Socks(4);
        assertTrue(modelSocks.seePrice() == 0);
    }

    @Test
    public void TestingSocks3()
    {
        modelSocks = new Socks(-1);
        assertFalse(modelSocks.seePrice() == 9);
    }

    @Test
    public void TestingSocks4()
    {
        modelSocks = new Socks(2);
        assertFalse(modelSocks.seePrice() == 7);
    }

    //Testing Interface IPrice,Class Cars.
    public IPrice modelCars;

    @Test
    public void TestingCars1()
    {
        modelCars = new Cars(0);
        assertTrue(modelCars.seePrice() == 0);
    }

    @Test
    public void TestingCars2()
    {
        modelCars = new Cars(1);
        assertTrue(modelCars.seePrice() == 700);
    }

    @Test
    public void TestingCars3()
    {
        modelCars = new Cars(2);
        assertFalse(modelCars.seePrice() == 900);
    }

    @Test
    public void TestingCars4()
    {
        modelCars = new Cars(0);
        assertFalse(modelCars.seePrice() == 500);
    }
}
