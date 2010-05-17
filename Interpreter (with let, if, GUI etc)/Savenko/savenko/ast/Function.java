package savenko.ast;

public class Function implements Expression{

    private Identifier identifier;
    private Expression expression;

    public Function(Identifier new_identifier, Expression new_expression) {
        identifier = new_identifier;
        expression = new_expression;
    }

    public Identifier getIdentifier(){
        return identifier;
    }

    public Expression getExpression(){
        return expression;
    }

}
