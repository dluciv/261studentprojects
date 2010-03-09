/* 
 * Mock tests in Java
 * Victor Polozov (c) 2009 
 */

package mocktest;

public class Customer {
    private IShop shop;
    private boolean isDrunk = false;

    public Customer(IShop shop)
    {
      this.shop = shop;
    }

    public boolean getIsDrunk() {
      return isDrunk;
    }

    private void drink() {
      isDrunk = true;
    }

    public void VisitShop()
    {
      if (shop.isInStock(Goods.Vodka))
      {
        shop.buy(Goods.Vodka);
        shop.buy(Goods.Iriska);
        shop.buy(Goods.Vodka);
        drink();
      }
    }

}
