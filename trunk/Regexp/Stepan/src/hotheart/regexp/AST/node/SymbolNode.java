/*
 * SymbolNode class for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp.AST.node;

/**
 * @author Korshakov Stepan
 */
public class SymbolNode extends AbstractNode {

    public char symbol;

    public SymbolNode(char sym) {
        symbol = sym;
    }

    @Override
    public String toString() {
        return "`" + Character.toString(symbol) + "`";
    }

    @Override
    public String getGraphVizString() {
        return this.getGraphVizNodeString("`" + symbol + "`");
    }
}
