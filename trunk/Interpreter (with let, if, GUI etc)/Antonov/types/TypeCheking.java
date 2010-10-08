/*
 *
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.types;

import name.kirill.ml.ast.Application;
import name.kirill.ml.ast.BinaryOperation;
import name.kirill.ml.ast.Binding;
import name.kirill.ml.ast.BooleanOp;
import name.kirill.ml.ast.Div;
import name.kirill.ml.ast.Expression;
import name.kirill.ml.ast.Function;
import name.kirill.ml.ast.Identifier;
import name.kirill.ml.ast.If;
import name.kirill.ml.ast.Minus;
import name.kirill.ml.ast.Mult;
import name.kirill.ml.ast.Number;
import name.kirill.ml.ast.Plus;
import name.kirill.ml.ast.Print;
import name.kirill.ml.ast.Sequence;
import name.kirill.ml.exception.IncompatibleTypedException;

public class TypeCheking {

    private Type GetExpressionType(Expression expr) throws IncompatibleTypedException {
        if (expr instanceof Number) {
            return new BasicType(TBasicType.Int);
        } else if (expr instanceof Identifier) {
            return ((Identifier) expr).GetType();
        } else if (expr instanceof BooleanOp) {
            return new BasicType(TBasicType.Bool);
        } else if (expr instanceof Print) {
            return new BasicType(TBasicType.Unit);
        } else if (expr instanceof Function) {
            Type funOutType = ((CombinedType) ((Function) expr).getIdentifier().GetType()).GetRight();
            Type exprType = GetExpressionType(((Function) expr).getExpression());
            if (TypesEquiv(funOutType, exprType)) {
                return ((Function) expr).getIdentifier().GetType();
            } else {
                throw new IncompatibleTypedException(null);
            }
        } else if (expr instanceof Application) {
            Type funInType = ((CombinedType) GetExpressionType(((Application) expr).getFunction())).GetLeft();
            Type exprType = GetExpressionType(((Application) expr).getExpression());
            if (TypesEquiv(funInType, exprType)) {
                return GetExpressionType(((Application) expr).getFunction());
            } else {
                throw new IncompatibleTypedException(null);
            }
        } else if (expr instanceof If) {
            if ((TypesEquiv(GetExpressionType(((If) expr).getIfExpression()),
                    GetExpressionType(((If) expr).getElseExpression())))
                    && (TypesEquiv(GetExpressionType(((If) expr).getExpression()), new BasicType(TBasicType.Bool)))) {
                return GetExpressionType(((If) expr).getIfExpression());
            } else {
                throw new IncompatibleTypedException(null);
            }
        } else if (expr instanceof Binding) {
            Type idType = ((Binding) expr).getIdentifier().GetType();
            Type exprType = GetExpressionType(((Binding) expr).getExpression());
            Type valType = GetExpressionType(((Binding) expr).getValue());
            if (TypesEquiv(exprType, valType) && TypesEquiv(exprType, idType)) {
                return idType;
            } else {
                throw new IncompatibleTypedException(null);
            }
        } else if (expr instanceof BinaryOperation) {
            Type left = GetExpressionType((Expression) ((BinaryOperation) expr).Left);
            Type right = GetExpressionType((Expression) ((BinaryOperation) expr).Right);

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
        throw new IncompatibleTypedException(null);
    }

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

    public void Check(Sequence sequence) throws IncompatibleTypedException {
        for (Expression expr : sequence.returnStatement()) {
            GetExpressionType(expr);
        }
    }
}
