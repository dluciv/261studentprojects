/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lebedev.Position;

public class ArNegate extends UnaryOperation {
    public ArNegate(Expression arOperand, Position position) {
        super(arOperand, position);
    }
}
