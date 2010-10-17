/*
 *
 * Antonov Kirill(c), 2010
 */

package types;

import ast.Application;
import ast.BinaryOperation;
import ast.Binding;
import ast.BooleanOperation;
import ast.Div;
import ast.Expression;
import ast.Function;
import ast.Number;
import ast.Identifier;
import ast.If;
import ast.Minus;
import ast.Mult;
import ast.Plus;
import ast.Print;
import ast.Sequence;
import ast.Tree;
import exception.IncompatibleTypedException;


public class TypeCheckingVisitor implements Visitor {

    private boolean TypesEquiv(Type first, Type second) {
        if ((first instanceof BasicType) && (second instanceof BasicType)) {
            return (((BasicType) first).GetType() == ((BasicType) second).GetType());
        } else if ((first instanceof CombinedType) && (second instanceof CombinedType)) {
            return (TypesEquiv(((CombinedType) first).GetLeft(), ((CombinedType) second).GetLeft()))
                    && (TypesEquiv(((CombinedType) first).GetRight(), ((CombinedType) second).GetRight()));
        } else {
            return false;
        }
    }

    public Type Visit(Binding expr) throws IncompatibleTypedException {
        Type idType = expr.getIdentifier().GetType();
        Type exprType = Visit(expr.getExpression());
        Type valType = Visit(expr.getValue());
        if (TypesEquiv(exprType, valType) && TypesEquiv(exprType, idType)) {
            return idType;
        } else {
            throw new IncompatibleTypedException(null);
        }
    }

    public Type Visit(Expression expr) throws IncompatibleTypedException {
        if (expr instanceof BinaryOperation) {
            return Visit((BinaryOperation) expr);
        } else if (expr instanceof Number) {
            return new BasicType(TBasicType.Int);
        } else if (expr instanceof Identifier) {
            return ((Identifier) expr).GetType();
        } else if (expr instanceof BooleanOperation) {
            return new BasicType(TBasicType.Bool);
        } else if (expr instanceof Print) {
            return new BasicType(TBasicType.Unit);
        } else if (expr instanceof If) {
            return Visit((If) expr);
        } else if (expr instanceof Binding) {
            return Visit((Binding) expr);
        } else if (expr instanceof Application) {
            return Visit((Application) expr);
        } else if (expr instanceof Function) {
            return Visit((Function) expr);
        } else {
            throw new IncompatibleTypedException(null);
        }
    }

    public Type Visit(BinaryOperation expr) throws IncompatibleTypedException {
        Type left = Visit((Expression) expr.Left);
        Type right = Visit((Expression) expr.Right);

        if (TypesEquiv(left, right)) {
            if (((BasicType) left).GetType() == TBasicType.Int) {
                if ((((BinaryOperation) expr) instanceof Mult)
                        || (((BinaryOperation) expr) instanceof Plus)
                        || (((BinaryOperation) expr) instanceof Minus)
                        || (((BinaryOperation) expr) instanceof Div)) {
                    return left;
                } else {
                    return new BasicType(TBasicType.Bool);
                }
            } else {
                throw new IncompatibleTypedException(null);
            }
        } else {
            throw new IncompatibleTypedException(null);
        }
    }

    public Type Visit(Application expr) throws IncompatibleTypedException {
        Type funInType = ((CombinedType) Visit(expr.getFunction())).GetLeft();
        Type exprType = Visit(expr.getExpression());
        if (TypesEquiv(funInType, exprType)) {
            return Visit(expr.getFunction());
        } else {
            throw new IncompatibleTypedException(null);
        }
    }

    public Type Visit(Function expr) throws IncompatibleTypedException {
        Type funOutType = ((CombinedType) expr.getIdentifier().GetType()).GetRight();
        Type exprType = Visit(expr.getExpression());
        if (TypesEquiv(funOutType, exprType)) {
            return ((Function) expr).getIdentifier().GetType();
        } else {
            throw new IncompatibleTypedException(null);
        }
    }

    public Type Visit(If expr) throws IncompatibleTypedException {
        Type ifType = Visit(expr.getIfExpression());
        Type elseType = Visit(expr.getElseExpression());
        if ((TypesEquiv(ifType, elseType)) && (TypesEquiv(ifType, new BasicType(TBasicType.Bool)))) {
            return ifType;
        } else {
            throw new IncompatibleTypedException(null);
        }
    }

    public Type Visit(Sequence sequence) throws IncompatibleTypedException {
        for (Expression expr : sequence.returnStatement()) {
            Visit((Expression) expr);
        }
        return null;
    }

    public Type Visit(Tree tree) throws IncompatibleTypedException {
        if (tree instanceof Expression) {
            return Visit((Expression) tree);
        } else if (tree instanceof Sequence) {
            return Visit((Sequence) tree);
        } else {
            throw new IncompatibleTypedException(null);
        }
    }
}
