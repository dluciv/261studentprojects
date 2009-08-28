package tree;

import java.io.Serializable;

/**
 * ������������ ����� ����, ��������� ��� ������������� � B+������.
 * ���� ������ ����� ������ �� ��������� ������� ������, ����� ������� ���������
 * � ����� ��� �������� "������ ��" �����, ������ � ������ ������� (��� ���������� ��� ���������
 * ������ ����� ������� �������)
 *
 * <b>�����</b>
 * <ul><li>������ ���� ������������� ����� equals(Object o)</li>
 * <li>�������, ���� ��������� ����� hashCode()</li></ul>
 *
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 16:11:51
 * @see tree.BPlusTree
 * @see tree.TreeElement
 * @see tree.TreeNode
 * @see tree.TreeLeaf
 */
public abstract class Key implements Comparable, Cloneable, Serializable {
    //������ �� ������� ������
    protected TreeElement link;

    protected Key(TreeElement link) {
        this.link = link;
    }

    public TreeElement getLink() {
        return link;
    }

    public void setLink(TreeElement link) {
        this.link = link;
    }

    /**
     * ������� ����� ������, ���������� ��������
     * @return ���������� �������� ����
     */
    public abstract Key clone();


}
