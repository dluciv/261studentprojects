/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LexerAndParser;

public class Or extends BinaryOperation {
    Or(Tree leftNode, Tree rightNode) {
    super(leftNode, rightNode);
    //this.Left=leftNode;
    //this.Right=rightNode;
    }

    @Override
    public void print() {
        Left.print();
        Right.print();
        System.out.print("or");
    }
    
}
