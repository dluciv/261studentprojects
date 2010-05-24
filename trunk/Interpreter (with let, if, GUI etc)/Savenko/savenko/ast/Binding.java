/*
 * связывает идентификатор с его значением
 * последнее выражение это область видимости этого идентификатора
 * Savenko Maria(c)
 */
package savenko.ast;

public class Binding implements Expression{
    
    private Identifier identifier;
    private Expression expression;
    private Expression value;
    
    public Binding(Identifier new_identifier, Expression new_expression, Expression new_value) {        
        identifier = new_identifier;
        expression = new_expression;
        value = new_value;
    }
    
    public Identifier getIdentifier(){
        return identifier;
    }
    
    public Expression getExpression(){
        return expression;
    }
    
    public Expression getValue(){
    	return value;
    }
}
