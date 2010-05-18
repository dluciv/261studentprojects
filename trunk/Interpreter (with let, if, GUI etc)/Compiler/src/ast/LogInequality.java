/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lebedev.Position;

public class LogInequality extends BinaryOperation {
    public LogInequality(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
