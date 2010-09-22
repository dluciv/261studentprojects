/*
 * общий класс для типов int, bool, unit..
 * Antonov Kirill(c), 2010
 */

package AST;

public class Type extends Expression{

        public Tree Left;
        public Tree Right;
        private Types type;

	public Type(Tree left_node, Tree right_node, Types type){
		this.Left = left_node;
		this.Right = right_node;
                this.type = type;
	}
        
        public Type (Types type){
            this.type = type;
        }

        public Types GetType(){
            return type;
        }

	public Tree LeftNode(){
		return Left;
	}
	public Tree RightNode(){
		return Right;
	}

}
