package tree;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 19:12:08
 */
public interface IndexableData<K extends Key, A extends UsableData> {
    public K extractKey();
    public A extractUsableData();
}
