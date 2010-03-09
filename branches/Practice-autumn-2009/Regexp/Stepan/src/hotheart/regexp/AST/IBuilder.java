/*
 * Builder Interface for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp.AST;

import hotheart.regexp.AST.node.AbstractNode;
import java.text.ParseException;

/**
 * @author Korshakov Stepan
 */
public interface IBuilder {

    AbstractNode parse() throws ParseException;
}
