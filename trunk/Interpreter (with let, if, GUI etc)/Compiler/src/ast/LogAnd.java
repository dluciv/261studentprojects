/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lebedev.Position;

public class LogAnd extends BinaryOperation {
    public LogAnd(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
