/*
 *
 * Antonov Kirill(c), 2010
 */

package types;

import ast.Tree;
import exception.IncompatibleTypedException;

public interface Visitor {
    public Type Visit(Tree tree) throws IncompatibleTypedException;
}
