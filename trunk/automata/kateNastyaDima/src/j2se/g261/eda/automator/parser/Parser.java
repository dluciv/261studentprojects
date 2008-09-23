/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.parser;

import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.GraphWorker;

/**
 *
 * @author nastya
 */
public class Parser {

    int cursor = 0;
    String stringToParse;
    String string;
    private static final char OPERATION_KLINI_STAR = '*';
    private static final char OR = '|';
    private static final char ONE = '?';
    private static final char RIGHT_STAPLER = ')';
    private static final char LEFT_STAPLER = '(';

    public Parser(String string) {
        this.stringToParse = string;
        this.string = string;
    }

    private boolean checkStaplers() {
        char[] str = string.toCharArray();
        int counter = 0;
        for (char c : str) {
            if (c == LEFT_STAPLER) {
                counter++;
            } else if (c == RIGHT_STAPLER) {
                counter--;
            }
            if (counter < 0) {
                break;
            }
        }

        return counter == 0;
    }

    private Tree expression() throws ParserException {
        char c = stringToParse.charAt(cursor);
//        System.out.print("[");
        //Leaf g = terminal();
        Tree g = rest();
//        System.out.print("]");
        stringToParse = string;
        return g;
    }

    private Tree rest() throws ParserException {
        if (cursor >= stringToParse.length()) {
            return null;
        }
        char c = stringToParse.charAt(cursor);
        if (c == OR) {
            cursor++;
            return new Tree(terminal(), Operation.OR, rest());
        } else if (c == OPERATION_KLINI_STAR) {
            throw new ParserException(ParserException.WRONG_USING_OPERATORS);
        } else if (c == ONE) {
            throw new ParserException(ParserException.WRONG_USING_OPERATORS);
        } else if (c == LEFT_STAPLER) {
            cursor++;
            Tree g1 = expression();

            switch (stringToParse.charAt(cursor - 1)) {
                case OR: {
                    cursor++;
                    return new Tree(g1, Operation.OR, rest());
                }
                default: {
                    while (stringToParse.length() > cursor + 1 &&
                            (stringToParse.charAt(cursor + 1) == OPERATION_KLINI_STAR) || (stringToParse.charAt(cursor + 1) == ONE)) {
                        cursor++;
                    }
                    char c1 = stringToParse.charAt(cursor + 1);
                    Operation o = (c1 == OR) ? Operation.OR : Operation.AND;
                    return new Tree(rightParse(cursor, g1), o, rest());
                }
            }
        } else if (c != RIGHT_STAPLER) {
            while (stringToParse.length() > cursor + 1 &&
                    (stringToParse.charAt(cursor + 1) == OPERATION_KLINI_STAR) || (stringToParse.charAt(cursor + 1) == ONE)) {
                cursor++;
            }
            return new Tree(rightParse(cursor, new Leaf(c)), Operation.AND, rest());
        } else {
            return null;
        }
    }

    
    private TreeStruct rightParse(int curs, TreeStruct low) {
        char c = stringToParse.charAt(curs);
        curs--;
        if (c == OPERATION_KLINI_STAR) {
            return new Tree(rightParse(curs, low), Operation.ANY, null);
        } else if (c == ONE) {
            return new Tree(rightParse(curs, low), Operation.ONE, null);
        } else {
            return low;
        }
    }

    private Leaf terminal() throws ParserException {
        if (cursor >= stringToParse.length()) {
            return null;
        }
        char c = stringToParse.charAt(cursor);
        if (c != OR && c != OPERATION_KLINI_STAR && c != ONE) {
            if (c != LEFT_STAPLER && c != RIGHT_STAPLER) {
//                System.out.print(c);
                Graph g = new Graph();
                g.addNode(String.valueOf(c));
                GraphWorker.markAllNodes(g);
                cursor++;
                return new Leaf(c);
            }
            return null;
        } else {
            throw new ParserException(ParserException.WRONG_USING_OPERATORS);
        }
    }

    public void parse() throws ParserException {
        if (!checkStaplers()) {
            throw new ParserException(ParserException.WRONG_STAPLERS);
        }
        Tree g = expression();
        stringToParse = string;
//        return g;
    }

    public static void main(String[] args) {
        Parser p = new Parser("a*");
//        try {
//            System.out.println(p.parse());
//        } catch (ParserException ex) {
//            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}

class TreeStruct {
}

class Tree extends TreeStruct {

    TreeStruct left;
    Operation op;
    TreeStruct right;

    public Tree(TreeStruct left, Operation op, TreeStruct right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }
}

class Leaf extends TreeStruct {

    String name;

    public Leaf(String name) {
        this.name = name;
    }

    public Leaf(char name) {
        this.name = String.valueOf(name);
    }

    public String getName() {
        return name;
    }
}

enum Operation {

    AND, OR, ANY, ONE
}