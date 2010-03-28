package ru.hotheart.interpreter;

import ru.hotheart.interpreter.lexer.Lexeme;
import ru.hotheart.interpreter.lexer.Lexer;
import ru.hotheart.interpreter.syntax.SyntaxProcessor;
import ru.hotheart.interpreter.syntax.SyntaxTreeNode;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Lexeme[] data = new Lexer().parse("(((213+1-8*2)))");
        for (Lexeme i : data) {
            System.out.print(i);
        }
        System.out.println();
        SyntaxTreeNode tree = new SyntaxProcessor().process(data);
        System.out.println(tree.toString());
    }
}
