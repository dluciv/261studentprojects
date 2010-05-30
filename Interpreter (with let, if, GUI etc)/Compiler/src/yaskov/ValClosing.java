/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yaskov;

import ast.Expression;
import java.util.LinkedList;

public class ValClosing extends Value {
    private int id;
    private Expression functionBody;
    private LinkedList<EnvironmentCell> funEnv = new LinkedList<EnvironmentCell>();

    public ValClosing(int id, Expression functionBody, LinkedList<EnvironmentCell> env) {
        this.id = id;
        this.functionBody = functionBody;
        funEnv = env;
    }

    public int getId() {
        return id;
    }

    public Expression getFunctionBody() {
        return functionBody;
    }

    public LinkedList<EnvironmentCell> getEnv() {
        return funEnv;
    }
}
