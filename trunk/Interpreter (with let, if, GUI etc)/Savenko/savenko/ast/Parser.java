/*
 * This class receives the string, then do the recursive descent
 * Savenko Maria(c)
 */
package savenko.ast;

import savenko.ast.Lexer.lexems;
import savenko.ParserException;
import savenko.SyntaxException;

public class Parser {
    
    private Lexer lexer;
    Sequence sequence;
    
    public Parser(Lexer args){
        lexer = args;
        lexer.moveNext();
    }
    
    public Sequence getSequence() throws ParserException{
    	sequence = new Sequence();
    	while (lexer.getCurrent().getTypeLexem() != lexems.EOF){
    		sequence.addStatement(getStatement());
    	}
	return sequence;    	
    }
    
    public Expression getStatement() throws ParserException{
        Expression expr = null;
        Expression expr2 = null;        
        Identifier identifier = null;
        
        if (lexer.getCurrent().getTypeLexem() == lexems.LET){
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() != lexems.Identifier) throw new ParserException();
            identifier = new Identifier(lexer.getCurrent().getStringLexem());
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() != lexems.Equation) throw new ParserException();
            lexer.moveNext();
            expr = getExpression();
            if (lexer.getCurrent().getTypeLexem() != lexems.IN)throw new ParserException();
        	lexer.moveNext();
            expr2 = getStatement();
        	return new Binding(identifier, expr, expr2);
        }else if (lexer.getCurrent().getTypeLexem() == lexems.PRINT){
            lexer.moveNext();
            return new Print(getExpression());
        }else if (lexer.getCurrent().getTypeLexem() == lexems.IF){
            Expression else_expr = null;
            
            lexer.moveNext();
            expr = getStatement(); ///??
            if (lexer.getCurrent().getTypeLexem() != lexems.THEN) throw new ParserException();
            lexer.moveNext();
            expr2 = getStatement();
            if (lexer.getCurrent().getTypeLexem() == lexems.ELSE){
                lexer.moveNext();
                else_expr = getStatement();
            }
            return new If(expr, expr2,else_expr);
        }else if (lexer.getCurrent().getTypeLexem() == lexems.BEGIN) {
            Sequence seq;
            lexer.moveNext();
            seq = getSequence();
            if (lexer.getCurrent().getTypeLexem() != lexems.END) throw new ParserException();
            lexer.moveNext();
            return new Begin(seq);
        }else if (lexer.getCurrent().getTypeLexem() == lexems.Identifier) {
            identifier = new Identifier(lexer.getCurrent().getStringLexem());
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == lexems.Equation){
                lexer.moveNext();
                expr = getExpression();
                return new Binding(identifier, expr, expr2);
            }else if(lexer.getCurrent().getTypeLexem() != lexems.EOF) throw new ParserException();
            return identifier;
        }else throw new ParserException();
    }

    public Expression getExpression() throws ParserException{
        Expression left = null;
        
        left = expression();
        if (lexer.getCurrent().getTypeLexem() == lexems.Semicolon){
            lexer.moveNext(); //semicolon is erased  
            return left;
        }else
        if (lexer.getCurrent().getTypeLexem()== lexems.THEN || lexer.getCurrent().getTypeLexem()== lexems.IN
                || lexer.getCurrent().getTypeLexem()== lexems.EOF){
            return left; //then and in will not be erased
        }else {
            if (lexer.getCurrent().getTypeLexem() == lexems.Unknown)
                System.out.println("Error in the input string (unknown symbol)");
            if (lexer.getCurrent().getTypeLexem() == lexems.RightBracket)
                System.out.println("Error in the input string (unexepcted Right Bracket)");
            throw new SyntaxException();
            }
        
    }
    
    private Expression expression(){
    	Expression left = null;
    	
    	left = term();
        while (lexer.getCurrent().getTypeLexem()==lexems.Plus||lexer.getCurrent().getTypeLexem()==lexems.Minus) {
            lexems sign = lexer.getCurrent().getTypeLexem();
            lexer.moveNext();
            Expression right = term();
            if (sign == lexems.Plus) {
            	left = new Plus(left, right);
            }
            else {
            	left = new Minus(left, right);
            }
        }
        
		return left;
    }
    
    private Expression term(){
    	Expression left = null;
    	
        left = factor();
        while (lexer.getCurrent().getTypeLexem()==lexems.Multiply||lexer.getCurrent().getTypeLexem()==lexems.Divide) {
            lexems sign = lexer.getCurrent().getTypeLexem();
            lexer.moveNext();
            Expression right = factor();
            if (sign == lexems.Multiply){
                left = new Multiply(left, right);
            }
            else{
            	left = new Divide(left, right);
            }
        }
        
        return left;
    }
    
    private Expression factor(){
        Expression left = null;

        if (lexer.getCurrent().getTypeLexem() == lexems.Number) {
            left = new Number(lexer.getCurrent().getIntLexem());
            lexer.moveNext();
            return left;
        }
        else 
            if (lexer.getCurrent().getTypeLexem() == lexems.LeftBracket){
                lexer.moveNext();
                left = expression();
                if (lexer.getCurrent().getTypeLexem() != lexems.RightBracket){
                    System.out.println("Error in the input string (missing right bracket)");
                    new Exception();
                }
                lexer.moveNext();
            }else 
                if (lexer.getCurrent().getTypeLexem() == lexems.Identifier) {
                    left = new Identifier(lexer.getCurrent().getStringLexem());
                    lexer.moveNext();
                }else new Exception();
        
        return left;
    }
    
}
