/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexprnew;

/**
 *
 * @author kate
 */
public class Tree {
    private Tree left, right;
    private Lexem lexem;

    public Tree(Lexem lexem, Tree left, Tree right) {
        this.left = left;
        this.right = right;
        this.lexem = lexem;
    }

    @Override
    public String toString() {
        if (!lexem.isOperation()) {
            return lexem.toString();
        } else {
            return left+" "+right+" "+lexem;
        }
    }
}
