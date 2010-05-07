/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LexerAndParser;
s
public class And extends BinaryOperation{
    And(Tree leftNode, Tree rightNode) {
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
