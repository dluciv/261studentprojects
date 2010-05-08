package name.stepa.ml.model.interpreter.syntax;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 08.05.2010
 * Time: 22:59:01
 * To change this template use File | Settings | File Templates.
 */
public class AssignNode extends SyntaxTreeNode {
    public String variable;

    public AssignNode(String variable, SyntaxTreeNode assignExpression) {
        super(assignExpression, null);
        this.variable = variable;
    }

    @Override
    public String toString() {
        return "[assign " + variable + " <- " + left.toString() + "]";
    }
}
