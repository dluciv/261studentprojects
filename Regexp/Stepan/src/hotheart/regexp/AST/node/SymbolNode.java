/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.regexp.AST.node;

/**
 *
 * @author m08ksa
 */
public class SymbolNode extends AbstractNode {
    public char symbol;
    public SymbolNode(char sym)
    {
        symbol = sym;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }

}
