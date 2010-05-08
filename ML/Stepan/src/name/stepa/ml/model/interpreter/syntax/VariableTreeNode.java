package name.stepa.ml.model.interpreter.syntax;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class VariableTreeNode extends SyntaxTreeNode {
    public String variable;

    public VariableTreeNode(String variable) {
        super(null, null);
        this.variable = variable;
    }

    @Override
    public String toString() {
        return super.toString() + " " + variable;
    }
}
