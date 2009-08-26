package tree;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 19:12:08
 */
public interface Keyable<K extends Key, A extends Address> {
    public K extractKey();
    public A extractAddress();
}
