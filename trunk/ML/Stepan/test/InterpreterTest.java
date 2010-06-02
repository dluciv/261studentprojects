import junit.framework.TestCase;
import name.stepa.ml.model.interpreter.exceptions.LexemeTypeMismatchException;
import name.stepa.ml.model.interpreter.exceptions.UnexectedSymbolException;
import name.stepa.ml.model.interpreter.lexer.Lexeme;
import name.stepa.ml.model.interpreter.lexer.LexemeStream;
import name.stepa.ml.model.interpreter.lexer.Lexer;
import name.stepa.ml.model.interpreter.syntax.SyntaxProcessor;
import name.stepa.ml.model.interpreter.syntax.SyntaxTreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: Uzver
 * Date: 01.06.2010
 * Time: 14:39:23
 * To change this template use File | Settings | File Templates.
 */
public class InterpreterTest extends TestCase {
    private Lexeme[] lexerTest(String text, boolean accept) throws Exception {
        try {
            Lexeme[] res = new Lexer().parse(text);
            if (!accept)
                throw new Exception();
            else
                return res;
        } catch (UnexectedSymbolException e) {
            if (!accept)
                return null;
            else
                throw e;
        }

    }

    private void syntaxTest(String text, boolean accept) throws Exception {
        try {
            Lexeme[] res = new Lexer().parse(text);
            SyntaxTreeNode tn = SyntaxProcessor.process(new LexemeStream(res));
            if (!accept)
                throw new Exception();
            else
                return;
        } catch (LexemeTypeMismatchException e) {
            if (!accept)
                return;
            else
                throw e;
        }
    }

    public void testLexer() throws Exception {
        assert (lexerTest("(1)", true).length == 3);
        lexerTest("(1+3)", true);
        lexerTest("(1-6)", true);
        lexerTest("(1/6-a+43&dkvsd/9)(-+=&", true);
        lexerTest(".?", false);
        lexerTest("^*&", false);

        assert (lexerTest("(1)    ", true).length == 3);
        assert (lexerTest("(1)\t\n\t\n", true).length == 3);
        assert (lexerTest("\t\n(\t 1 \n)\t\n\t\n", true).length == 3);
    }

    public void testSyntaxProcessor() throws Exception {
        syntaxTest("print fun x->x*2;", true);
        syntaxTest("print fun x->x*2", false);
        syntaxTest("fun x->x*2;", true);

        syntaxTest("let x=x*2;", true);
        syntaxTest("let a = 7 in a+2;", true);

        syntaxTest("let x-x+2;", false);
        syntaxTest("let a = 7 in a+2", false);
        syntaxTest("x->x*2;", false);

        syntaxTest("let a = 7 in a+2;;", true);

        syntaxTest("print 1;", true);

        syntaxTest("begin  end;", true);

        syntaxTest("begin print 1; end;", true);

        syntaxTest("print 1;; print2;", true);

        syntaxTest("let x = 1+2;", true);
        syntaxTest("let x = 1-2;", true);
        syntaxTest("let x = 1*2;", true);
        syntaxTest("let x = 1/2;", true);
        syntaxTest("let x = 1==2;", true);
        syntaxTest("let x = 1!=2;", true);
        syntaxTest("let x = (1==2)|(2==8);", true);
        syntaxTest("let x = (1==2)&(2!=8);", true);
        syntaxTest("let x = (1==2)&&(2!=8);", false);
    }
}
