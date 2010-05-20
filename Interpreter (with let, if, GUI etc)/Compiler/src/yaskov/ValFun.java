/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yaskov;

import ast.Expression;
import java.util.LinkedList;

public class ValFun extends Value {
    private LinkedList<EnvironmentCell> funEnv = new LinkedList<EnvironmentCell>();

    public ValFun(Expression value, LinkedList<EnvironmentCell> env) {
        this.value = value;
        funEnv = env;
    }
    
    public LinkedList<EnvironmentCell> getFunEnv() {
        return funEnv;
    }
}
