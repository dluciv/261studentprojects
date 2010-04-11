package savenko.ast;


public abstract class BinaryOperation implements Expression {
	
    private Tree LeftNode, RightNode;
	
    public BinaryOperation(Tree left_node, Tree right_node){
		LeftNode = left_node;
		RightNode = right_node;
	}
	
	public Tree LeftNode(){
		return LeftNode;
	}
	
	public Tree RightNode(){
		return RightNode;
	}
	
}
