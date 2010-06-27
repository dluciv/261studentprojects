/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexpr;

/**
 *
 * @author kate
 */
public class Pair<L, R> {


    private final L left;
    private final R right;

    public R getRight() {
        return right;
    }

    public L getLeft() {
        return left;
    }

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Pair))
            return false;

        final Pair<?, ?> other = (Pair) o;
        return equal(getLeft(), other.getLeft()) && equal(getRight(), other.getRight());
    }

    public static final boolean equal(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }

    @Override
    public int hashCode() {
        int hLeft = getLeft() == null ? 0 : getLeft().hashCode();
        int hRight = getRight() == null ? 0 : getRight().hashCode();

        return hLeft + (57 * hRight);
    }
}
