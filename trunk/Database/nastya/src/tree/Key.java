package tree;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 16:11:51
 */
public abstract class Key implements Comparable, Cloneable{
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

    public abstract Key clone();


}
