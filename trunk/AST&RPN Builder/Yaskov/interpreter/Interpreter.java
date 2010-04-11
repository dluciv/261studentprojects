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
    Sequence input;
    public LinkedList<StackCell> stack = new LinkedList<StackCell>();
    public LinkedList<TableCell> varTable = new LinkedList<TableCell>();
    private int errorCounter = 0;

    public Interpreter(Sequence parserOutput, LinkedList<TableCell> varTable, int parseErrorQnt) {
        if (parseErrorQnt == 0) {
            this.varTable = varTable;
            toInterpretSequence(parserOutput);
        }
        else {
            fixError("there are lexical errors");
        }
    }

    public void toInterpretSequence(AbstractTree treeRoot) {
        if (treeRoot.getLeftOperand() != null) {
            toInterpretSequence(treeRoot.getLeftOperand());
        }
        toInterpretStatement(treeRoot.getRightOperand());
    }

    public void toInterpretStatement(AbstractTree treeRoot) {
        Variable temp2;

        toInterpretExpression(treeRoot.getRightOperand());
        if (treeRoot.getClass().getSimpleName().equals("Print")) {
            toInterpretExpression(treeRoot.getRightOperand());
            System.out.println(stack.pop().getValue());
        }
        else {
            temp2 = (Variable) treeRoot.getLeftOperand();
            findVar(temp2.getId()).setVarValue(stack.pop().getValue());
        }
    }

    public void toInterpretExpression(AbstractTree treeRoot) {
        String sign;
        int num;
        Num temp;
        Variable temp2;
        if (treeRoot.getLeftOperand() != null) {
            toInterpretExpression(treeRoot.getLeftOperand());
            toInterpretExpression(treeRoot.getRightOperand());
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
            if (treeRoot.getClass().getSimpleName().equals("Variable")) {
                    temp2 = (Variable) treeRoot;
                    stack.push(new StackCell(findVar(temp2.getId()).getVarValue()));
                }
            else {
                    temp = (Num) treeRoot;
                    stack.push(new StackCell(temp.getValue()));
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
