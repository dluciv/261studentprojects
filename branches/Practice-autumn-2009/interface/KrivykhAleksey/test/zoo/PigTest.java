/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zoo;

import org.junit.Assert;
import org.junit.Test;

public class PigTest {

    @Test
    public void testFoodMeet() {
        Pig BoPbKa = new Pig();
        Assert.assertEquals(BoPbKa.eat(Food.MEET), State.HUNGRY);
    }

    @Test
    public void testFoodNull() {
        Pig BoPbKa = new Pig();
        Assert.assertEquals(BoPbKa.eat(null), State.HUNGRY);
    }
    @Test
    public void testFoodTurnip() {
        Pig BoPbKa = new Pig();
        Assert.assertEquals(BoPbKa.eat(Food.TURNIP), State.CHEESED);
    }

}