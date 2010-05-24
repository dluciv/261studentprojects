/*
 * узел для операции отрицания
 * !<expr>
 * Savenko Maria(c)
 */
package savenko.ast;

public class Negate implements Expression{

	private Expression expr = null;
	
	public Negate(Expression expr) {
		this.expr = expr;
	}
	
	

}
