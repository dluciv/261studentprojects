/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexpr.expr;


/**
 *
 * @author kate
 */
public class OperationTree extends Tree<String>{

    private static final String OP_MINUS = "-";
    private static final String OP_PLUS = "+";
    private static final String OP_MULTIPLY = "*";
    private static final String OP_DIVIDE = "/";

    public enum Operations{PLUS, MINUS, MULTIPLY, DIVIDE;

        @Override
        public String toString(){
            switch(this){
                case PLUS: return OP_PLUS;
                case MINUS: return OP_MINUS;
                case MULTIPLY: return OP_MULTIPLY;
                case DIVIDE: return OP_DIVIDE;
            }
            return "";
        }

    };



    public OperationTree(Tree left, Tree right, Operations value) {
        super(left, right, value.toString());
    }


    @Override
    public String toString(){
       return left.toString() + right.toString() + value;
    }

}
