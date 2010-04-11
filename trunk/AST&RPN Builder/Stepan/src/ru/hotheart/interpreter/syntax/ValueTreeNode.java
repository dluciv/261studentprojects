package ru.hotheart.interpreter.syntax;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ValueTreeNode extends SyntaxTreeNode{
    public double value;
    public ValueTreeNode(double value) {
        super(null, null);
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value) + super.toString();
    }
}
