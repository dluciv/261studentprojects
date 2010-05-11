package analizator;

public class BinaryOperation implements Node {

    private Tree LeftNode, RightNode;
    private BinaryOperationType value;
    // private Lexem lexem;

    public BinaryOperation(Tree left, Tree right, BinaryOperationType value) {
        // super();
        LeftNode = left;
        RightNode = right;
        this.value = value;
        //this.lexem = value;
    }

    public Tree LeftNode() {
        return LeftNode;
    }

    public Tree RightNode() {
        return RightNode;
    }

    public void print() {
//        LeftNode.print();
//        RightNode.print();
        switch (value) {
            case ADD: {
                System.out.print("+ ");
                break;
            }
            case SUB: {
                System.out.print("- ");
                break;
            }
            case MULT: {
                System.out.print("* ");
                break;
            }
            case DIV: {
                System.out.print("/ ");
                break;
            }
            case DEGREE: {
                System.out.print("^ ");
                break;
            }
        }
    }
}
