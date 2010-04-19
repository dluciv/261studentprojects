/*
 * This class receives the string, then do the recursive descent
 * Savenko Maria(c)
 */
package savenko.ast;

import savenko.ast.Lexer.lexems;
import savenko.ParserException;
import savenko.RightBracketExpectedException;
import savenko.SemicolonExpectedException;
import savenko.UnexpectedRightBracketException;
import savenko.UnknownSymbolException;

public class Parser {

     private Lexer lexer;

     public Parser(Lexer args) {
          lexer = args;
          lexer.moveNext();
     }

     public Sequence getSequence() throws ParserException {
          Sequence sequence = new Sequence();
          while (lexer.getCurrent().getTypeLexem() != lexems.EOF && lexer.getCurrent().getTypeLexem() != lexems.END) {
               if (lexer.getCurrent().getTypeLexem() == lexems.Semicolon) {
                    lexer.moveNext();
               } else {
                    sequence.addStatement(getStatement());
               }
          }
          return sequence;
     }

     public Expression getStatement() throws ParserException {
          Expression expr = null;
          Expression expr2 = null;
          Identifier identifier = null;

          if (lexer.getCurrent().getTypeLexem() == lexems.LET) {
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != lexems.Identifier) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               identifier = new Identifier(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != lexems.Equation) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();
               expr = getExpression();
               if (lexer.getCurrent().getTypeLexem() != lexems.IN) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();
               expr2 = getStatement();
               return new Binding(identifier, expr, expr2);
          } else if (lexer.getCurrent().getTypeLexem() == lexems.PRINT) {
               lexer.moveNext();
               return new Print(getExpression());
          } else if (lexer.getCurrent().getTypeLexem() == lexems.IF) {
               Expression else_expr = null;
               lexer.moveNext();
               /*//????????????????
               if (lexer.getCurrent().getTypeLexem() != lexems.Identifier) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               identifier = new Identifier(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != lexems.Equation) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();*/
               expr = getBoolExpression();
               if (lexer.getCurrent().getTypeLexem() != lexems.THEN) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();
               expr2 = getStatement();
               if (lexer.getCurrent().getTypeLexem() == lexems.ELSE) {
                    lexer.moveNext();
                    else_expr = getStatement();
               }
               return new If(expr, expr2, else_expr);
          } else if (lexer.getCurrent().getTypeLexem() == lexems.BEGIN) {
               Sequence seq;
               lexer.moveNext();
               seq = getSequence();
               if (lexer.getCurrent().getTypeLexem() != lexems.END) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();
               return new Begin(seq);
          } else if (lexer.getCurrent().getTypeLexem() == lexems.Identifier) {
               identifier = new Identifier(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
               /* //THIS IS ONLY IF SIMPLE EQ.(NOT ON LET STAT.) ARE ALLOWED - THINK!
               if (lexer.getCurrent().getTypeLexem() == lexems.Equation) {
                    lexer.moveNext();
                    expr = getExpression();
                    return new Binding(identifier, expr, expr2);
               } else*/
               if (ifEndEofSem()) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               return identifier;
          } else {
               throw new ParserException(lexer.getCurrent().getPosition());
          }
     }

     private boolean ifEndEofSem() {
          return (lexer.getCurrent().getTypeLexem() != lexems.EOF
                  && lexer.getCurrent().getTypeLexem() != lexems.END
                  && lexer.getCurrent().getTypeLexem() != lexems.Semicolon);
     }
     
     private Expression getBoolExpression() throws ParserException{
          Expression left = null;
          
          left = boolExpression();
          if (lexer.getCurrent().getTypeLexem() == lexems.THEN) {
               return left; //then and in will not be erased
          } else {
               if (lexer.getCurrent().getTypeLexem() == lexems.Unknown) {
                    throw new UnknownSymbolException(lexer.getCurrent().getPosition());
               }
               if (lexer.getCurrent().getTypeLexem() == lexems.RightBracket) {
                    throw new UnexpectedRightBracketException(lexer.getCurrent().getPosition());
               }
               throw new ParserException(lexer.getCurrent().getPosition());
          }
     }
     
     private Expression boolExpression() throws ParserException {
          Expression left = null;

          left = or();
          while (lexer.getCurrent().getTypeLexem() == lexems.OR) {
               lexer.moveNext();
               Expression right = or();
               left = new Or(left, right);
          }

          return left;
     }

     private Expression or() throws ParserException {
          Expression left = null;

          left = and();
          while (lexer.getCurrent().getTypeLexem() == lexems.AND) {
               lexer.moveNext();
               Expression right = and();
               left = new And(left, right);
          }

          return left;
     }

     private Expression and() throws ParserException {
          Expression expr1 = null;
          Expression expr2 = null;
          lexems sign = null;

          if (lexer.getCurrent().getTypeLexem() == lexems.NO){
               lexer.moveNext();
               expr1 = getExpression();
               sign = lexer.getCurrent().getTypeLexem();
               lexer.moveNext();
               expr2 =  getExpression();
               return new False(expr1, sign, expr2);
          }else{
               expr1 = getExpression();
               sign = lexer.getCurrent().getTypeLexem();
               lexer.moveNext();
               expr2 =  getExpression();
               return new True(expr1, sign, expr2);
          }
     }
     
     private boolean ifBoolOperation(){
          return (lexer.getCurrent().getTypeLexem() == lexems.Equation
                  || lexer.getCurrent().getTypeLexem() == lexems.LE
                  || lexer.getCurrent().getTypeLexem() == lexems.GE
                  || lexer.getCurrent().getTypeLexem() == lexems.GREATER
                  || lexer.getCurrent().getTypeLexem() == lexems.LESS);
     }

     private Expression getExpression() throws ParserException {
          Expression left = null;

          left = expression();
          if (lexer.getCurrent().getTypeLexem() == lexems.Semicolon) {
               lexer.moveNext(); //semicolon is erased
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == lexems.THEN 
                  || lexer.getCurrent().getTypeLexem() == lexems.IN
                  || lexer.getCurrent().getTypeLexem() == lexems.EOF
                  || lexer.getCurrent().getTypeLexem() == lexems.END
                  || ifBoolOperation()) {
               return left; //then and in will not be erased
          } else {
               if (lexer.getCurrent().getTypeLexem() == lexems.Unknown) {
                    throw new UnknownSymbolException(lexer.getCurrent().getPosition());
               }
               if (lexer.getCurrent().getTypeLexem() == lexems.RightBracket) {
                    throw new UnexpectedRightBracketException(lexer.getCurrent().getPosition());
               }
               throw new SemicolonExpectedException(lexer.getCurrent().getPosition());
          }

     }

     private Expression expression() throws ParserException {
          Expression left = null;

          left = term();
          while (lexer.getCurrent().getTypeLexem() == lexems.Plus || lexer.getCurrent().getTypeLexem() == lexems.Minus) {
               lexems sign = lexer.getCurrent().getTypeLexem();
               lexer.moveNext();
               Expression right = term();
               if (sign == lexems.Plus) {
                    left = new Plus(left, right);
               } else {
                    left = new Minus(left, right);
               }
          }

          return left;
     }

     private Expression term() throws ParserException {
          Expression left = null;

          left = factor();
          while (lexer.getCurrent().getTypeLexem() == lexems.Multiply || lexer.getCurrent().getTypeLexem() == lexems.Divide) {
               lexems sign = lexer.getCurrent().getTypeLexem();
               lexer.moveNext();
               Expression right = factor();
               if (sign == lexems.Multiply) {
                    left = new Multiply(left, right);
               } else {
                    left = new Divide(left, right);
               }
          }

          return left;
     }

     private Expression factor() throws ParserException {
          Expression left = null;

          if (lexer.getCurrent().getTypeLexem() == lexems.Number) {
               left = new Number(lexer.getCurrent().getIntLexem());
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == lexems.LeftBracket) {
               lexer.moveNext();
               left = expression();
               if (lexer.getCurrent().getTypeLexem() != lexems.RightBracket) {
                    throw new RightBracketExpectedException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();
          } else if (lexer.getCurrent().getTypeLexem() == lexems.Identifier) {
               left = new Identifier(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
          } else {
               throw new ParserException(lexer.getCurrent().getPosition());
          }

          return left;
     }
}
