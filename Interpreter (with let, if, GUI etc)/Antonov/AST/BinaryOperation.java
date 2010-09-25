/*
 *General class for tree nodes: /,*,-,+ extends this class
 * Antonov Kirill(c), 2010
 */
package AST;

public abstract class BinaryOperation extends Expression {

    public Tree Left;
    public Tree Right;

    public BinaryOperation(Tree left_node, Tree right_node) {
        this.Left = left_node;
        this.Right = right_node;
    }

    public Tree LeftNode() {
        return Left;
    }

    public Tree RightNode() {
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
