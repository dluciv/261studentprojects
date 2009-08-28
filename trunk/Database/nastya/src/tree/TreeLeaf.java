package tree;

import java.util.Vector;

/**
 * ������������ "����" ������, �� ���� ����� ������ �������, ���������� ������.
 *
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 3:57:24
 */
public class TreeLeaf implements TreeElement{
    // ����� �����, ���������� � ����. ���������� ��� ����, ��� �� ����� ���� �������
    // ��������� ������� � ����������� �������
    private Vector<UsableData> data;

    public TreeLeaf(){
        data = new Vector<UsableData>();
    }

  /**
     * ��������� ����� ����� ������ ��� �������� � ������� ����
     *
     * @param usableData �������� ������
     */
    public void add(UsableData usableData){
        data.add(usableData);
    }
    @Override
    public String toString() {
        return "[TreeLeaf: " + data + "]\n";
    }
}
