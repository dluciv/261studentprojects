package savenko;

public class Plus extends BinaryOperation{

	Plus(Tree leftNode, Tree rightNode) {
		super(leftNode, rightNode);
	}

	@Override
	public void print() {
	    LeftNode.print();
        RightNode.print();
		System.out.print("+");
	}

}
