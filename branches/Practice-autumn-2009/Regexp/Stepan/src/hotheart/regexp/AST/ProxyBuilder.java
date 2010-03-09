/*
 * Proxy Builder for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp.AST;

import hotheart.regexp.AST.node.AbstractNode;
import java.text.ParseException;

/**
 * @author Korshakov Stepan
 */
public class ProxyBuilder implements IBuilder {

    private IBuilder parent;

    public ProxyBuilder(IBuilder parent) {
        this.parent = parent;
    }

    public AbstractNode parse() throws ParseException {
        return parent.parse();
    }
}
