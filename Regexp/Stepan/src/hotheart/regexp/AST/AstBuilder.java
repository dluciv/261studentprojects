/*
 * Parser for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp.AST;

import hotheart.regexp.AST.node.AbstractNode;
import hotheart.regexp.AST.node.AndNode;
import hotheart.regexp.AST.node.CycleNode;
import hotheart.regexp.AST.node.GroupNode;
import hotheart.regexp.AST.node.SymbolNode;
import hotheart.regexp.AST.node.OrNode;
import java.text.ParseException;

/**
 * Current gramatics.
 * alp:=a,b,c,d,...,0,1,...9,\\,\., etc
 * or:= var|or'|'or
 * var:=alp|var+var|(var)|cycle
 * node:=(var)|alp|cycle
 * cycle:=node(*|+|?)
 * @author Korshakov Stepan
 */
public class AstBuilder implements IBuilder {

    char[] regex;
    int currentPos;

    public AstBuilder(String regexp) {
        regex = regexp.toCharArray();
        currentPos = 0;
    }

    private AbstractNode parseOr() throws ParseException {
        AbstractNode node = parseVar();
        if (node == null) {
            return null;
        }

        if ((currentPos >= regex.length) || (regex[currentPos] == '|')) {
            currentPos++;
            AbstractNode right = parseOr();
            if (right == null) {
                return node;
            } else {
                return new OrNode(node, right);
            }
        } else {
            return node;
        }
    }

    public AbstractNode parse() throws ParseException {
        return parseOr();
    }

    private AbstractNode parseVar() throws ParseException {

        if (currentPos >= regex.length) {
            return null;
        }
        if (regex[currentPos] == ')') {
            return null;
        }

        if (regex[currentPos] == '|') {
            return null;
        }

        AbstractNode left = null;

        if (regex[currentPos] == '(') {
            currentPos++;

            left = parseBrackets();

        } else if ((regex[currentPos] == '+') |
                (regex[currentPos] == '*') |
                (regex[currentPos] == '?')) {

            int type = 0;

            if (regex[currentPos] == '*') {
                type = CycleNode.STAR;
            }
            if (regex[currentPos] == '+') {
                type = CycleNode.PLUS;
            }
            if (regex[currentPos] == '?') {
                type = CycleNode.QUESTION;
            }

            currentPos++;


            return new CycleNode(null, type);

        } else {
            left = new SymbolNode(regex[currentPos]);
        }

        currentPos++;
        AbstractNode right = parseVar();

        if (right instanceof CycleNode) {
            CycleNode cycle = (CycleNode) right;
            if (cycle.inner == null) {
                left = new CycleNode(left, cycle.type);
                right = parseVar();
            }
        }

        if (right == null) {
            return left;
        } else {
            return new AndNode(left, right);
        }
    }

    private AbstractNode parseBrackets() throws ParseException {
        AbstractNode inner = parseOr();

        if (regex[currentPos] != ')') {
            throw new ParseException("Brackets error!", 0);
        }

        return new GroupNode(inner);
    }
}
