/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;

import org.junit.Assert;
import org.junit.Test;


public class FeedWorkerTest {

    @Test (expected = NullPointerException.class)
    public void testFoodNull() throws Exception {
        FeedWorker feedworker = new FeedWorker();
        Lion lion = new Lion();
        feedworker.feedAnimal(lion, null);
    }

    @Test (expected = NullPointerException.class)
    public void testAnimalNull() throws Exception {
        FeedWorker feedworker = new FeedWorker();
        Lion lion = new Lion();
        feedworker.feedAnimal(lion, null);
    }

    @Test 
    public void testFeedLionWithMeet() throws Exception {
        FeedWorker feedworker = new FeedWorker();
        Lion lion = new Lion();
        Assert.assertEquals(feedworker.feedAnimal(lion, Food.MEET), State.CHEESED);
    }

}