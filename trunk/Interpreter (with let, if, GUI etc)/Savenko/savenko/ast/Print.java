/*
 * узел для операции печати
 * Savenko Maria(c)
 */
package savenko.ast;

public class Print implements Expression {
	
	private Expression expression;
	
	public Print(Expression new_expr){
		expression = new_expr;
	}
	
	public Expression getExpression(){
		return expression;
	}

}
