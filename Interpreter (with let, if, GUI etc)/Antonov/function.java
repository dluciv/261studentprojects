package LexerAndParser;

class Function implements Expression{

    private Identificator identifier;
    private Expression expression;

    public Function(Identificator new_identifier, Expression new_expression) {
        identifier = new_identifier;
        expression = new_expression;
    }

    public Identificator getIdentifier(){
        return identifier;
    }

    public Expression getExpression(){
        return expression;
    }

}