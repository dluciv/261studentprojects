package savenko.ast;

import savenko.ast.Expression;

public class Print implements Expression {
	
	private Expression expression;
	
	public Print(Expression new_expr){
		expression = new_expr;
	}
	
	public Expression getExpression(){
		return expression;
	}

}
