
package LexerAndParser;

public class Div extends BinaryOperation{

    Div(Tree leftNode, Tree rightNode) {
    super(leftNode, rightNode);
    //this.Left=leftNode;
    //this.Right=rightNode;
    }

    /*@Override
    public void print() {
        Left.print();
        Right.print();
        System.out.print("/");
    }


    //@Override
    //protected void printOperation() {
    //    System.out.print("/");
    //}

   /* public int calculate() {
        int result = this.LeftNode().calculate() / this.RightNode().calculate();
        return result;
    }
 */
}
