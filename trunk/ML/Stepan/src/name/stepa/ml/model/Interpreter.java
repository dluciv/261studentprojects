package name.stepa.ml.model;

import name.stepa.ml.model.interpreter.Context;
import name.stepa.ml.model.interpreter.IInterpreterStateListener;
import name.stepa.ml.model.interpreter.IO;
import name.stepa.ml.model.interpreter.InterpretationCore;
import name.stepa.ml.model.interpreter.lexer.ComparisonLexeme;
import name.stepa.ml.model.interpreter.lexer.Lexeme;
import name.stepa.ml.model.interpreter.lexer.LexemeStream;
import name.stepa.ml.model.interpreter.lexer.Lexer;
import name.stepa.ml.model.interpreter.syntax.*;

import java.util.Arrays;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Interpreter {

    IInterpreterStateListener stateListener = null;
    public InterpretationCore core = null;

    public void setProgram(String program) throws Exception {
        if (program != null) {
            Lexeme[] lexemes = new Lexer().parse(program);
            SyntaxTreeNode syntax = new SyntaxProcessor().process(new LexemeStream(lexemes));
            core = new InterpretationCore(syntax);
            core.stateListener = stateListener;
        }
    }

    public void setStateListener(IInterpreterStateListener stateListener) {
        this.stateListener = stateListener;
        if (core != null)
            core.stateListener = stateListener;
    }

    public void startStepByStep() {
        if (core != null) {
            core.run();
        }
    }
}
