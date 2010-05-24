/*
 * application of function to expr:
 * expr expr
 * Savenko Maria(c)
 */

package savenko.ast;

public class Application implements Expression{
	
	private Expression function;
    private Expression expression;

    public Application(Expression function, Expression expression) {
    	this.function = function;
        this.expression = expression;
    }

    public Expression getFunction(){
        return function;
    }

    public Expression getExpression(){
        return expression;
    }
}
