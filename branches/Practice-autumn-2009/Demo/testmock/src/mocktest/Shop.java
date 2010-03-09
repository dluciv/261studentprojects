/* 
 * Mock tests in Java
 * Victor Polozov (c) 2009 
 */

package mocktest;

import java.util.Random;

public class Shop implements IShop {
    public boolean isInStock(Goods goods) {
      return new Random().nextInt(10) > 5;
    }

    public void buy(Goods goods) { }
}
