/*
 * общий класс для узлов дерева
 * класс Excpression наследуется от него
 * Antonov Kirill(c), 2010
 */
package ast;

import ml.exception.IncompatibleTypedException;
import ml.types.Visitor;

public class Tree {
    public void Accept(Visitor visitor)  throws IncompatibleTypedException {
        visitor.Visit(this);
    }
}
