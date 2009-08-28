package tree;

/**
 * ������������ ����� ���������� ������ ��� ������ � �������, �����������
 * ������� ��� ������ � B+������, �������������� ��������� ���� ��� ������
 * � ���������� ��� �������� � ������.
 *
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 19:12:08
 * @see tree.Key
 * @see tree.BPlusTree
 */
public interface IndexableData<K extends Key, A extends UsableData> {
    /**
     * ���������� ���� �� ������ �����-���� ���������� ������������� �������
     *
     * @return ����, ��������� ��� ������������� � B+������
     * @see tree.Key
     * @see tree.BPlusTree
     */
    public K extractKey();

    /**
     * ���������� ������, ������� ��� �������� ������� ���������� ������� � ������
     *
     * @return ������, ���������� ��� �������� � ������
     */
    public A extractUsableData();
}
