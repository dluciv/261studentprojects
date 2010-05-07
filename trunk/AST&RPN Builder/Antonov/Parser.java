/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LexerAndParser;

import LexerAndParser.Lexer.lexems;
import java.util.Scanner;

public class Parser {

 
    private static Lexer lexer;


    public static void main(String[] args) {

        System.out.print("Введите выражение: ");

        Scanner input = new Scanner(System.in);
        lexer = new Lexer(input.nextLine());
        Expression left = null;

        try {
           left = expressinon();
             if (lexer.getCurrent().getLeft() != lexems.EOL){
                if (lexer.getCurrent().getLeft() == lexems.Unknown)
                    System.out.println("Error in the input string (unknown symbol)");
    
                if (lexer.getCurrent().getLeft() == lexems.RightBracket)
                    System.out.println("Error in the input string (unexepcted Right Bracket)");
    
                if (lexer.getCurrent().getLeft() == lexems.LeftBracket)
                    System.out.println("Error in the input string (unexepcted Left Bracket)");
    
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
            if (sign == lexems.Plus) { //если было сложение складываем, аналогичнов терме только с умноженеим и делением
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
                left = new Mult(left, right);
            }
            else{
            	left = new Div(left, right);
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
            else new Exception(); 

        return left;
    }

  
}
