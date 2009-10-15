/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author m08ksa
 *
 * Current gramatics.
 * alp:=a,b,c,d,...,0,1,...9,\\,\., etc
 * var:=alp|var+var|(var)|cycle
 * node:=(var)|alp|cycle
 * cycle:=node(*|+|?)
 */
public class AstBuilder {

    char[] regex;
    int currentPos;

    public AstBuilder(String regexp) {
        regex = regexp.toCharArray();
        currentPos = 0;
    }

    public AbstractNode parseOr() throws ParseException {
        AbstractNode node = parseVar();
        if (node == null) {
            return null;
        }

        if ((regex[currentPos] == '|') || (regex.length<=currentPos)) {
            currentPos++;
            return new OrNode(node, parseOr());
        } else {
            return node;
        }
    }

    public AbstractNode parse() throws ParseException {
        return parseVar();
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

//        GroupNode res = new GroupNode();
//        
//        AstNode res = new AstNode();
//        res.prev = prev;
//        if (prev != null) {
//            prev.next = res;
//        }
//
//        res.type = AstNode.TYPE_GROUP;
//
//        res.subNodes = new AstNode[1];
//        res.subNodes[0] = parseVar(res);

        AbstractNode inner = parseOr();

        if (regex[currentPos] != ')') {
            throw new ParseException("Brackets error!", 0);
        }

        return new GroupNode(inner);
    }
//
//    private AstNode parseCycle(AstNode prev) throws ParseException {
//
//        AstNode res = new AstNode();
//        res.prev = prev;
//        if (prev != null) {
//            prev.next = res;
//        }
//
//        if (regex[currentPos] == '*') {
//            res.type = AstNode.TYPE_STAR;
//        } else if (regex[currentPos] == '+') {
//            res.type = AstNode.TYPE_PLUS;
//        } else if (regex[currentPos] == '?') {
//            res.type = AstNode.TYPE_QUESTION;
//        }
//
//        res.prev = prev.prev;
//
//        if (prev.prev != null) {
//            prev.prev.next = res;
//        }
//
//        res.subNodes = new AstNode[1];
//        res.subNodes[0] = prev;
//        prev.prev = res;
//        prev.next = new AstNode();
//
//        return res;
//    }
}
