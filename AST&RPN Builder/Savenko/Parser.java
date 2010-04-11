/*
 * This class receives the string, then do the recursive descent
 * Savenko Maria(c)
 */
package savenko;

import java.util.Scanner;
import savenko.Lexer.lexems;

public class Parser {
    
    private static Lexer lexer;

    public static void main(String[] args) {
        System.out.print("¬ведите выражение: ");
        
        Scanner input = new Scanner(System.in);
        lexer = new Lexer(input.nextLine());
        Expression left = null;
        
        try {
            left = expressinon();
            if (lexer.getCurrent().getLeft() != lexems.EOL){
                if (lexer.getCurrent().getLeft() == lexems.Unknown)
                    System.out.println("Error in the input string (unknown symbol)");
                if (lexer.getCurrent().getLeft() == lexems.RightBracket) //???
                    System.out.println("Error in the input string (unexepcted Right Bracket)");
                throw new Exception();}
        } catch (Exception e){
            return;
        }
        
        left.print();     
    }
    
    private static Expression expressinon(){
    	Expression left = null;
    	
    	left = term();
        while (lexer.getCurrent().getLeft()==lexems.Plus||lexer.getCurrent().getLeft()==lexems.Minus) {
            lexems sign = lexer.getCurrent().getLeft();
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
    
    private static Expression term(){
    	Expression left = null;
    	
        left = factor();
        while (lexer.getCurrent().getLeft()==lexems.Multiply||lexer.getCurrent().getLeft()==lexems.Divide) {
            lexems sign = lexer.getCurrent().getLeft();
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
    
    private static Expression factor(){
        Expression left = null;

        if (lexer.getCurrent().getLeft() == lexems.Number) {
            left = new Number(Integer.parseInt(lexer.getCurrent().getRight()));
            lexer.moveNext();
            return left;
        }
        else 
            if (lexer.getCurrent().getLeft() == lexems.LeftBracket){
                lexer.moveNext();
                left = expressinon();
                if (lexer.getCurrent().getLeft() != lexems.RightBracket){
                    System.out.println("Error in the input string (missing right bracket)");
                    new Exception();
                }
                lexer.moveNext();
            }
            else new Exception(); ///???
        
        return left;
    }
    
}
