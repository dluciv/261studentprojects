package name.stepa.ml.model;

import name.stepa.ml.model.interpreter.Context;
import name.stepa.ml.model.interpreter.IInterpreterStateListener;
import name.stepa.ml.model.interpreter.IO;
import name.stepa.ml.model.interpreter.InterpretationCore;
import name.stepa.ml.model.interpreter.lexer.ComparisonLexeme;
import name.stepa.ml.model.interpreter.lexer.Lexeme;
import name.stepa.ml.model.interpreter.lexer.Lexer;
import name.stepa.ml.model.interpreter.syntax.*;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 08.05.2010
 * Time: 18:24:20
 * To change this template use File | Settings | File Templates.
 */
public class Interpreter {

    IInterpreterStateListener stateListener = null;
    public InterpretationCore core = null;

    public void setProgram(String program) throws Exception {
        if (program != null) {
            IO.println("Expression:");
            IO.println(program);
            Lexeme[] lexemes = new Lexer().parse(program);
            IO.println("lexemes:");
            IO.println(Arrays.toString(lexemes));
            SyntaxTreeNode syntax = new SyntaxProcessor().process(lexemes);
            IO.println("syntax:");
            IO.println(syntax.toString());
            core = new InterpretationCore(syntax);
        }
    }

    public void setStateListener(IInterpreterStateListener stateListener) {
        this.stateListener = stateListener;
    }

    private void notifyStateChanging() {
        if (stateListener != null) {
            stateListener.onLineChanged(0, 0);
        }
    }

    public void step() throws Exception {
        if (core != null)
            core.step();
        else
            IO.println("Nothing to interpret.");
    }
}
