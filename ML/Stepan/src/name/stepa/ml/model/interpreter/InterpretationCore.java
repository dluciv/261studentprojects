package name.stepa.ml.model.interpreter;

import name.stepa.ml.model.interpreter.values.ExecutionStateValue;
import name.stepa.ml.model.interpreter.values.functions.*;
import name.stepa.ml.model.interpreter.lexer.ComparisonLexeme;
import name.stepa.ml.model.interpreter.syntax.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:17:33
 * To change this template use File | Settings | File Templates.
 */
public class InterpretationCore {

    private static final void initContext(Context context) {
        // System
        context.put("print", new SystemFunctions.PrintlnFunctionValue());

        // Math
        context.put("abs", new MathFunctions.AbsFunctionValue());
        context.put("acos", new MathFunctions.AcosFunctionValue());
        context.put("asin", new MathFunctions.AsinFunctionValue());
        context.put("atan", new MathFunctions.AtanFunctionValue());
        context.put("ceil", new MathFunctions.CeilFunctionValue());
        context.put("cos", new MathFunctions.CosFunctionValue());
        context.put("exp", new MathFunctions.ExpFunctionValue());
        context.put("floor", new MathFunctions.FloorFunctionValue());
        context.put("log10", new MathFunctions.Log10FunctionValue());
        context.put("log", new MathFunctions.LogFunctionValue());
        context.put("round", new MathFunctions.RoundFunctionValue());
        context.put("sin", new MathFunctions.SinFunctionValue());
        context.put("sqrt", new MathFunctions.SqrtFunctionValue());
        context.put("tan", new MathFunctions.TanFunctionValue());

        context.put("PI", Math.PI);
    }

    ExecutionStack executionStack;
    ExecutionStack.ExecutionStackItem currentItem;

    public InterpretationCore(SyntaxTreeNode root) {
        this.executionStack = new ExecutionStack();
        Context rootContext = new Context();
        initContext(rootContext);
        this.executionStack.push(new ExecutionStack.ExecutionStackItem(root, new ExecutionStateValue(), rootContext));
        currentItem = getExecutionItem();
    }

    private void pushToStack(SyntaxTreeNode node) {
        IO.println("Adding to execution stack: " + node.toString());
        this.executionStack.push(new ExecutionStack.ExecutionStackItem(node, new ExecutionStateValue(), currentItem.context.clone()));
    }

    public ExecutionStack.ExecutionStackItem getExecutionItem() {
        if (executionStack.size() == 0)
            return null;
        else
            return executionStack.peek();
    }

    public Object step() throws Exception {
        currentItem = getExecutionItem();
        if (currentItem == null)
            return null;

        SyntaxTreeNode node = currentItem.parent;
        ExecutionStateValue state = currentItem.state;

        Object res = null;
        if (node instanceof CaparisonTreeNode)
            res = interpret((CaparisonTreeNode) node);
        else if (node instanceof ValueTreeNode)
            res = interpret((ValueTreeNode) node);
        else if (node instanceof VariableTreeNode)
            res = interpret((VariableTreeNode) node);
        else if (node instanceof BinaryOperationTreeNode)
            res = interpret((BinaryOperationTreeNode) node);
        else if (node instanceof UnaryOperationTreeNode)
            res = interpret((UnaryOperationTreeNode) node);
        else if (node instanceof AssignNode)
            res = interpret((AssignNode) node);
        else if (node instanceof InTreeNode)
            res = interpret((InTreeNode) node, state);
        else if (node instanceof ExpressionListTreeNode)
            res = interpret((ExpressionListTreeNode) node, state);
        else if (node instanceof IfTreeNode)
            res = interpret((IfTreeNode) node);
        else if (node instanceof FunctionDeclarationTreeNode)
            res = interpret((FunctionDeclarationTreeNode) node);
        else if (node instanceof ExpressionCallTreeNode)
            res = interpret((ExpressionCallTreeNode) node);
        else if (node instanceof BracketsTreeNode)
            res = interpret((BracketsTreeNode) node);
        else
            throw new Exception("Unsupported syntax tree item: " + node.getClass().getSimpleName());

        if (state.state == -1) {
            executionStack.pop();
            currentItem = getExecutionItem();
            if (currentItem != null)
                currentItem.calculatedExpressions.add(res);
            return res;
        } else
            return null;
    }


    public Object interpret(SyntaxTreeNode node) throws Exception {
        currentItem = getExecutionItem();
        if (currentItem == null)
            return null;
        Context old = currentItem.context.clone();

        Object res = null;
        if (node instanceof CaparisonTreeNode)
            res = interpret((CaparisonTreeNode) node);
        else if (node instanceof ValueTreeNode)
            res = interpret((ValueTreeNode) node);
        else if (node instanceof VariableTreeNode)
            res = interpret((VariableTreeNode) node);
        else if (node instanceof BinaryOperationTreeNode)
            res = interpret((BinaryOperationTreeNode) node);
        else if (node instanceof UnaryOperationTreeNode)
            res = interpret((UnaryOperationTreeNode) node);
        else if (node instanceof AssignNode)
            res = interpret((AssignNode) node);
        else if (node instanceof InTreeNode)
            res = interpret((InTreeNode) node, null);
        else if (node instanceof ExpressionListTreeNode)
            res = interpret((ExpressionListTreeNode) node, null);
        else if (node instanceof IfTreeNode)
            res = interpret((IfTreeNode) node);
        else if (node instanceof FunctionDeclarationTreeNode)
            res = interpret((FunctionDeclarationTreeNode) node);
        else if (node instanceof ExpressionCallTreeNode)
            res = interpret((ExpressionCallTreeNode) node);
        else if (node instanceof BracketsTreeNode)
            res = interpret((BracketsTreeNode) node);
        else
            throw new Exception("Unsupported syntax tree item: " + node.getClass().getSimpleName());

        currentItem.context = old;

        return res;
    }

    private Object interpret(ExpressionListTreeNode node, ExecutionStateValue state) throws Exception {
        if (state != null) {
            Object res = null;
            if (state.state == -1) {
                state.state = 0;
                for (int i = node.nodes.length - 1; i >= 0; i--) {
                    pushToStack(node.nodes[i]);
                }
                return null;
            } else {
                state.state = -1;
                return currentItem.calculatedExpressions.get(0);
            }
        } else {
            Object res = null;
            for (SyntaxTreeNode i : node.nodes) {
                res = interpret(i);
            }
            return res;
        }
    }


    private Object interpret(BracketsTreeNode node) throws Exception {
        return interpret(node.inner);
    }


    private Object interpret(ExpressionCallTreeNode node) throws Exception {
        Object expression = interpret(node.expression);
        if (expression instanceof AbstractFunctionValue) {
            return ((AbstractFunctionValue) expression).execute(interpret(node.argument));
        } else
            return expression;
    }

    private Object interpret(FunctionDeclarationTreeNode node) throws Exception {
        return new FunctionValue(currentItem.context.clone(), node.expression, node.argumentName, this);
    }

    private Object interpret(IfTreeNode node) throws Exception {
        if (getLogicValue(interpret(node.ifExpr))) {
            return interpret(node.thenExpr);
        } else
            return interpret(node.elseExpr);
    }

    private Object interpret(InTreeNode node, ExecutionStateValue state) throws Exception {

        if (state == null) {
            Object assignVal = interpret(node.assignment.assignExpression);
            currentItem.context.put(node.assignment.variable, assignVal);
            Object res = interpret(node.expression);
            return res;
        } else {
            if (state.state == -1) {
                state.state = 0;
                pushToStack(node.assignment.assignExpression);
                return null;
            } else if (state.state == 0) {
                state.state = 1;
                currentItem.context.put(node.assignment.variable, currentItem.calculatedExpressions.get(0));
                return null;
            } else if (state.state == 1) {
                state.state = 2;
                pushToStack(node.expression);
                return null;
            } else {
                state.state = -1;
                return currentItem.calculatedExpressions.get(0);
            }

        }
    }

    private Object interpret
            (AssignNode
                    node) throws Exception {
        Object value = interpret(node.assignExpression);
        String name = node.variable;
        currentItem.context.put(name, value);
        IO.println("set value " + value + " -> " + name);
        return value;
    }

    private Object interpret
            (UnaryOperationTreeNode
                    node) throws Exception {
        if (node.operation == UnaryOperationTreeNode.MINUS) {
            return -getAlgebraicValue(interpret(node.argument));
        } else if (node.operation == UnaryOperationTreeNode.NOT) {
            return !getLogicValue(interpret(node.argument));
        }

        throw new Exception("Unsupported unary operation: " + node.operation);
    }

    private Object interpret
            (CaparisonTreeNode
                    node) throws Exception {
        if ((node.operation == ComparisonLexeme.E) || (node.operation == ComparisonLexeme.NE)) {
            Object left = interpret(node.left);
            Object right = interpret(node.right);
            switch (node.operation) {
                case ComparisonLexeme.E:
                    return left.equals(right);
                case ComparisonLexeme.NE:
                    return !left.equals(right);
            }
        } else {
            Double left = (Double) interpret(node.left);
            Double right = (Double) interpret(node.right);
            switch (node.operation) {
                case ComparisonLexeme.G:
                    return left > right;
                case ComparisonLexeme.GE:
                    return left >= right;
                case ComparisonLexeme.L:
                    return left < right;
                case ComparisonLexeme.LE:
                    return left <= right;
            }
        }

        throw new Exception("Invalid comparison operation: " + node.operation);
    }

    private Object interpret
            (ValueTreeNode
                    node) {
        return node.value;
    }

    private Object interpret
            (VariableTreeNode
                    node) {
        return currentItem.context.get(node.variable);
    }

    private Object interpret
            (BinaryOperationTreeNode
                    node) throws Exception {
        if (isAlgebraicOperation(node.operation)) {
            Double left = getAlgebraicValue(interpret(node.left));
            Double right = getAlgebraicValue(interpret(node.right));
            if (node.operation == '+')
                return left + right;
            else if (node.operation == '-')
                return left - right;
            else if (node.operation == '*')
                return left * right;
            else if (node.operation == '/')
                return left / right;
        } else {
            Boolean left = getLogicValue(interpret(node.left));
            Boolean right = getLogicValue(interpret(node.right));
            if (node.operation == '&')
                return left & right;
            else if (node.operation == '|')
                return left | right;
            else if (node.operation == '^')
                return left ^ right;
        }

        throw new Exception("Invalid binary operation: " + node.operation);
    }

    private boolean isAlgebraicOperation
            (
                    char c) {
        if (c == '+')
            return true;
        else if (c == '-')
            return true;
        else if (c == '*')
            return true;
        else if (c == '/')
            return true;
        else
            return false;
    }

    private Double getAlgebraicValue
            (Object
                    value) throws Exception {
        if (value == null)
            throw new Exception("Type mismatch! Expected Double, got NULL");

        if (value instanceof Double)
            return (Double) value;

        throw new Exception("Type mismatch! Expected Double, got " + value.getClass().getSimpleName());
    }

    private Boolean getLogicValue
            (Object
                    value) throws Exception {
        if (value == null)
            throw new Exception("Type mismatch! Expected Boolean, got NULL");

        if (value instanceof Boolean)
            return (Boolean) value;

        throw new Exception("Type mismatch! Expected Boolean, got " + value.getClass().getSimpleName());
    }
}