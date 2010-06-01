/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lexerandparser.Position;

public class LogLess extends BinaryOperation {
    public LogLess(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
