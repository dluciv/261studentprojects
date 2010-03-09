/* 
 * Mock tests in Java
 * Victor Polozov (c) 2009 
 */

package mocktest;

public interface IShop {
	boolean isInStock(Goods goods);
    void buy(Goods goods);
}
