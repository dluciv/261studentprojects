/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LexerAndParser;

public class Mult extends BinaryOperation {
    
    Mult(Tree leftNode, Tree rightNode) {
    super(leftNode, rightNode);
    //this.Left=leftNode;
    //this.Right=rightNode;
    }

     @Override
     public void print() {
         Left.print();
         Right.print();
         System.out.print("*");
     }

    //@Override
    //protected void printOperation() {
    //    System.out.print("*");
    //}

    //public int calculate() {
    //    int result = this.LeftNode().calculate() * this.RightNode().calculate();
    //    return result;
    //}


}
