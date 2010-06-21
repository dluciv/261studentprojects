

package LexerAndParser;

public class Binding implements Expression {
    private Identificator identifier;
    private Expression expression;
    private Expression value;

    public Binding(Identificator new_identifier, Expression new_expression, Expression new_value) {
        identifier = new_identifier;
        expression = new_expression;
        value = new_value;
    }

    public Identificator getIdentifier(){
        return identifier;
    }

    public Expression getExpression(){
        return expression;
    }

    public Expression getValue(){
    	return value;
    }


 }
