/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LexerAndParser;

public abstract class BinaryOperation implements Expression {

    	public Tree Left;
        public Tree Right;
	
	BinaryOperation(Tree left_node, Tree right_node){
		this.Left = left_node;
		this.Right = right_node;
	}

	public Tree LeftNode(){
		return Left;
	}

	public Tree RightNode(){
		return Right;
	}

        //public void print(){
        //    Left.print();
        //    System.out.print(" ");
        //    Right.print();
        //    System.out.print(" ");
        //    printOperation();
        //}

        //abstract protected void printOperation();

}




