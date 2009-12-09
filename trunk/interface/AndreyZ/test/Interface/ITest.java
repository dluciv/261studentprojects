/**
 * Testfile(Testing Interface IShop,Classes Chocolate and Water);
 * @author Zubrilin Andrey (c)2009
 */
package Interface;

import org.junit.Test;

public class ITest {

    //Testing Interface IShop,Class Water
    public IShop item;

    @Test
    public void theFirstPositiveTest() {
        ShopException.checkShop(new Water());
    }

    @Test
    public void theSecondPositiveTest() {
        ShopException.checkShop(new Chocolate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void theFirstNegativeTest() {
        ShopException.checkShop(null);
    }
}
