package savenko;

public class Divide extends BinaryOperation{

	Divide(Tree leftNode, Tree rightNode) {
		super(leftNode, rightNode);
	}

	@Override
	public void print() {
	    LeftNode.print();
        RightNode.print();
		System.out.print("/");
	}

}
