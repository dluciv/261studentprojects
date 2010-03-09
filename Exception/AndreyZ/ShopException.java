/**
 * Exception(Shop);
 * @author Zubrilin Andrey (c)2009
 */

package Interface;

public class ShopException {

    public static void checkShop(IShop shop) throws IllegalArgumentException {
        if (shop == null) {
            throw new IllegalArgumentException("Illegal Argument");
        }
    }
}
