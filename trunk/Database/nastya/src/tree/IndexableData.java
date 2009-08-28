package tree;

/**
 * Представляет собой простейшие методв для работы с данными, позволяющие
 * занести эти данные в B+дерево, предварительно определив ключ для дерева
 * и информацию для хранения в дереве.
 *
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 19:12:08
 * @see tree.Key
 * @see tree.BPlusTree
 */
public interface IndexableData<K extends Key, A extends UsableData> {
    /**
     * Генерирует ключ на основе каких-либо внутренних характеристик объекта
     *
     * @return ключ, пригодный для использования в B+дереве
     * @see tree.Key
     * @see tree.BPlusTree
     */
    public K extractKey();

    /**
     * Определяет данные, которые для текущего объекта необходимо хранить в дереве
     *
     * @return данные, подходящие для хранения в дереве
     */
    public A extractUsableData();
}
