/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zoo;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class LionTest {

    @Test
    public void testFoodTURNIP() {
        Lion lion = new Lion();
        Assert.assertEquals(lion.eat(Food.TURNIP), State.HUNGRY);
    }
    @Test
    public void testFoodNull() {
        Lion lion = new Lion();
        Assert.assertEquals(lion.eat(null), State.HUNGRY);
    }
    @Test
    public void testFoodMeet() {
        Lion lion = new Lion();
        Assert.assertEquals(lion.eat(Food.MEET), State.CHEESED);
    }

}