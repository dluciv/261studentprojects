
public class Node implements Comparable<Node> {
	int fre;
	char sym;
	Node left;
	Node right;
	
	public Node(int fre, char sym){
		this.fre = fre;
		this.sym = sym;
	}
	
	public Node(Node left, Node right){
		this.fre = left.fre + right.fre;
		this.left = left;
		this.right = right;
	}
	public int getFre(){
		return this.fre;
	}
	public char getSym(){
		return this.sym;
	}

	public int compareTo(Node node1) {
		if(node1.fre < this.fre){
			return 1;
		}
		else if (node1.fre > this.fre){
			return -1;
		}
		else return 0;
	}
	
 
}

