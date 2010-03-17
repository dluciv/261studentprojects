/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package interpreter;

import java.util.LinkedList;
import parser.*;
import lexer.*;

public class Interpreter {
    public LinkedList<StackCell> stack = new LinkedList<StackCell>();
    public LinkedList<TableCell> varTable = new LinkedList<TableCell>();
    LinkedList<Statement> input;
    private int errorCounter = 0;

    public Interpreter(LinkedList<Statement> parserOutput, LinkedList<TableCell> varTable, int parseErrorQnt) {
        if (parseErrorQnt == 0) {
            input = parserOutput;
            this.varTable = varTable;
        }
        else {
            fixError("there are lexical errors");
        }
    }

    public void toInterpret() {
        int i;

        for (i = 0; i < input.size(); ++i) {
            findVarValue(input.get(i));
        }
    }

    public void findVarValue(Statement treeRoot) {
        bypassExpressionTree(treeRoot.getExpression());

        if (treeRoot.getClass().getSimpleName().equals("Print")) {
            System.out.println(stack.pop().getValue());
        }
        else {
            findVar(treeRoot.getVarId()).setVarValue(stack.pop().getValue());
        }
    }

    public void bypassExpressionTree(BinOp treeRoot) {
        Num temp;
        Variable temp2;
        String sign;
        int num;

        if (treeRoot.getLeftOperand() != null) {
            bypassExpressionTree(treeRoot.getLeftOperand());
            bypassExpressionTree(treeRoot.getRightOperand());
            sign = treeRoot.getClass().getSimpleName();
            if (sign.equals("Plus")) {
               stack.push(new StackCell(stack.pop().getValue() + stack.pop().getValue()));
            }
            else if (sign.equals("Minus")) {
               stack.push(new StackCell(-(stack.pop().getValue() - stack.pop().getValue())));
            }
            else if (sign.equals("Mult")) {
               stack.push(new StackCell(stack.pop().getValue() * stack.pop().getValue()));
            }
            else if (sign.equals("Div")) {
               num = stack.pop().getValue();
               if (num == 0) {
                   fixError("devide by zero");
                   return;
               } 
               stack.push(new StackCell(stack.pop().getValue() / num));
            }
            else {
                fixError("strange symbol");
            }
        }
        else {
            if (treeRoot.getClass().getSimpleName().equals("Num")) {
                temp = (Num)treeRoot;
                stack.push(new StackCell(temp.getValue()));
            }
            else {
                temp2 = (Variable)treeRoot;
                stack.push(new StackCell(findVar(temp2.getId()).getVarValue()));
            }
        }
    }

    private TableCell findVar(int id) {
        for (int i = 0; i < varTable.size(); ++i) {
            if (varTable.get(i).getId() == id) {
                return varTable.get(i);
            }
        }

        fixError("no such id");
        return null;
    }

    private void fixError(String message) {
        errorCounter++;
        System.out.println("\ninterpreter error: " + message + ";");
    }
}
