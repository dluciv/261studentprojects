/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yaskov;

import ast.Expression;

public class EnvironmentCell {
    private int id;
    private Expression value; // может быть целым числом, логическим операндом или функцией;

    public EnvironmentCell(int id, Expression value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    public Expression getValue() {
        return value;
    }
}
