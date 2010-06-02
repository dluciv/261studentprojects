package name.stepa.ml.model.interpreter;

import name.stepa.ml.model.interpreter.exceptions.InvalidOperationException;
import name.stepa.ml.model.interpreter.exceptions.TypeMismatchException;
import name.stepa.ml.model.interpreter.execution.ExecutionStack;
import name.stepa.ml.model.interpreter.execution.ExecutionStackItem;
import name.stepa.ml.model.interpreter.values.functions.*;
import name.stepa.ml.model.interpreter.lexer.ComparisonLexeme;
import name.stepa.ml.model.interpreter.syntax.*;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
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

    public SyntaxTreeNode root;
    public Context rootContext;
    public boolean paused = false;
    public IInterpreterStateListener stateListener;
    public ExecutionStack stack;
    public boolean isStepInto = false;
    private Thread executionThread;

    public InterpretationCore(SyntaxTreeNode root) {
        this.root = root;
        this.rootContext = new Context();
        this.stack = new ExecutionStack();
        initContext(this.rootContext);
    }

    public void run() {
        if (executionThread != null)
            executionThread.interrupt();

        executionThread = new Thread() {
            @Override
            public void run() {
                try {
                    if (stateListener != null)
                        stateListener.onExecutionStarted();
                    interpret(root, false);
                    notifyStop();
                } catch (Exception e) {
                    IO.println(e.getClass().getSimpleName() + ":" + e.getMessage());
                    if (stateListener != null)
                        stateListener.onExecutionStopped();
                    e.printStackTrace();
                }
            }
        };
        executionThread.start();

    }

    public void step() {
        isStepInto = false;
        paused = false;
    }

    public void stepInto() {
        isStepInto = true;
        paused = false;
    }

    private void notifyListener() {
        if (stateListener != null) {
            ExecutionStackItem head = stack.peek();
            stateListener.onLineChanged(head.parent.start.start, head.parent.end.end);
        }
    }

    private void notifyStop() {
        if (stateListener != null) {
            stateListener.onExecutionStopped();
        }
    }

    private void breakInterpretation() {
        notifyListener();
        paused = true;
        while (paused) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object interpret(SyntaxTreeNode node, boolean breakExecution) throws TypeMismatchException, InvalidOperationException {
        stack.add(new ExecutionStackItem(node));

        if (breakExecution)
            breakInterpretation();

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
            res = interpret((InTreeNode) node);
        else if (node instanceof ExpressionListTreeNode)
            res = interpret((ExpressionListTreeNode) node);
        else if (node instanceof IfTreeNode)
            res = interpret((IfTreeNode) node);
        else if (node instanceof FunctionDeclarationTreeNode)
            res = interpret((FunctionDeclarationTreeNode) node);
        else if (node instanceof ExpressionCallTreeNode)
            res = interpret((ExpressionCallTreeNode) node);
        else if (node instanceof BracketsTreeNode)
            res = interpret((BracketsTreeNode) node);
        else
            throw new InvalidOperationException("Unsupported syntax tree item: " + node.getClass().getSimpleName());

        //rootContext = stack.pop().context;

        return res;
    }

    private Object interpret(ExpressionListTreeNode node) throws TypeMismatchException, InvalidOperationException {
        Object res = null;
        for (SyntaxTreeNode i : node.nodes) {
            res = interpret(i, true);
        }
        return res;
    }


    private Object interpret(BracketsTreeNode node) throws TypeMismatchException, InvalidOperationException {
        return interpret(node.inner, false);
    }


    private Object interpret(ExpressionCallTreeNode node) throws TypeMismatchException, InvalidOperationException {
        Object expression = interpret(node.expression, false);
        if (expression instanceof AbstractFunctionValue) {
            return ((AbstractFunctionValue) expression).execute(interpret(node.argument, false));
        } else if (expression instanceof FunctionValue) {
            FunctionValue fun = (FunctionValue) expression;
            Object argument = interpret(node.argument, isStepInto);
            Context oldCOntext = fun.context;
            this.rootContext = fun.context;
            this.rootContext.put(fun.argumentName, argument);
            Object res = interpret(fun.expression, isStepInto);
            this.rootContext = oldCOntext;
            return res;
        } else
            throw new TypeMismatchException("Function", expression);
        //return expression;
    }

    private Object interpret(FunctionDeclarationTreeNode node) throws TypeMismatchException, InvalidOperationException {
        return new FunctionValue(rootContext.clone(), node.expression, node.argumentName, this);
    }

    private Object interpret(IfTreeNode node) throws TypeMismatchException, InvalidOperationException {
        if (getLogicValue(interpret(node.ifExpr, isStepInto))) {
            return interpret(node.thenExpr, isStepInto);
        } else
            return interpret(node.elseExpr, isStepInto);
    }

    private Object interpret(InTreeNode node) throws TypeMismatchException, InvalidOperationException {

        Object assignVal = interpret(node.assignment.assignExpression, isStepInto);
        rootContext.put(node.assignment.variable, assignVal);
        Object res = interpret(node.expression, isStepInto);
        rootContext.remove(node.assignment.variable);
        return res;
    }

    private Object interpret(AssignNode node) throws TypeMismatchException, InvalidOperationException {
        Object value = interpret(node.assignExpression, isStepInto);
        String name = node.variable;
        rootContext.put(name, value);
        IO.println("set value " + value + " -> " + name);
        return value;
    }

    private Object interpret(UnaryOperationTreeNode node) throws TypeMismatchException, InvalidOperationException {
        if (node.operation == UnaryOperationTreeNode.MINUS) {
            return -getAlgebraicValue(interpret(node.argument, false));
        } else if (node.operation == UnaryOperationTreeNode.NOT) {
            return !getLogicValue(interpret(node.argument, false));
        }

        throw new InvalidOperationException("Unsupported unary operation: " + node.operation);
    }

    private Object interpret(CaparisonTreeNode node) throws TypeMismatchException, InvalidOperationException {
        if ((node.operation == ComparisonLexeme.E) || (node.operation == ComparisonLexeme.NE)) {
            Object left = interpret(node.left, false);
            Object right = interpret(node.right, false);
            switch (node.operation) {
                case ComparisonLexeme.E:
                    return left.equals(right);
                case ComparisonLexeme.NE:
                    return !left.equals(right);
            }
        } else {
            Double left = (Double) interpret(node.left, false);
            Double right = (Double) interpret(node.right, false);
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

        throw new InvalidOperationException("Invalid comparison operation: " + node.operation);
    }

    private Object interpret(ValueTreeNode node) {
        return node.value;
    }

    private Object interpret(VariableTreeNode node) {
        return rootContext.get(node.variable);
    }

    private Object interpret(BinaryOperationTreeNode node) throws TypeMismatchException, InvalidOperationException {
        if (isAlgebraicOperation(node.operation)) {
            Double left = getAlgebraicValue(interpret(node.left, false));
            Double right = getAlgebraicValue(interpret(node.right, false));
            if (node.operation == '+')
                return left + right;
            else if (node.operation == '-')
                return left - right;
            else if (node.operation == '*')
                return left * right;
            else if (node.operation == '/')
                return left / right;
        } else {
            Boolean left = getLogicValue(interpret(node.left, false));
            Boolean right = getLogicValue(interpret(node.right, false));
            if (node.operation == '&')
                return left & right;
            else if (node.operation == '|')
                return left | right;
            else if (node.operation == '^')
                return left ^ right;
        }

        throw new InvalidOperationException("Invalid binary operation: " + node.operation);
    }

    private boolean isAlgebraicOperation(char c) {
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

    private Double getAlgebraicValue(Object value) throws TypeMismatchException, InvalidOperationException {
        if (value == null)
            throw new TypeMismatchException("Double", value);

        if (value instanceof Double)
            return (Double) value;

        throw new TypeMismatchException("Double", value);
    }

    private Boolean getLogicValue(Object value) throws TypeMismatchException, InvalidOperationException {
        if (value == null)
            throw new TypeMismatchException("Boolean", value);

        if (value instanceof Boolean)
            return (Boolean) value;

        throw new TypeMismatchException("Boolean", value);
    }
}