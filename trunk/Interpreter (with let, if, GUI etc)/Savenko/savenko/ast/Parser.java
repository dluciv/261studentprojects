/*
 * This class receives the string, then do the recursive descent
 * Savenko Maria(c)
 */
package savenko.ast;

import savenko.ast.Lexer.lexems;
import savenko.ParserException;
import savenko.RightBracketExpectedException;
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
                    sequence.addStatement(getExpression());
               }
          }
          return sequence;
     }

     private Expression getBoolExpression() throws ParserException {

          return boolExpression();

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
          return comparison();
     }

     private Expression comparison() throws ParserException {
          Expression left = null;
          Expression right = null;

          left = arithmeticExpression();
          switch (lexer.getCurrent().getTypeLexem()) {
               case LE:
                    lexer.moveNext();
                    right = comparison();
                    return new LE(left, right);
               case GE:
                    lexer.moveNext();
                    right = comparison();
                    return new GE(left, right);
               case GREATER:
                    lexer.moveNext();
                    right = comparison();
                    return new Greater(left, right);
               case LESS:
                    lexer.moveNext();
                    right = comparison();
                    return new Less(left, right);
               case UNEQUAL:
                    lexer.moveNext();
                    right = comparison();
                    return new Unequal(left, right);
               case Equation:
                    lexer.moveNext();
                    right = comparison();
                    return new Equal(left, right);
               case TRUE:
                    lexer.moveNext();
                    return new BooleanOp(true);
               case FALSE:
                    lexer.moveNext();
                    return new BooleanOp(false);
               default:
                    return left;
          }
     }

     private Expression getExpression() throws ParserException {
          Expression left = null;

          left = boolExpression();
          if (lexer.getCurrent().getTypeLexem() == lexems.Unknown) {
               throw new UnknownSymbolException(lexer.getCurrent().getPosition());
          }

          return left;
     }

     private Expression arithmeticExpression() throws ParserException {
          Expression left = null;

          left = term();
          while (lexer.getCurrent().getTypeLexem() == lexems.Plus
                  || lexer.getCurrent().getTypeLexem() == lexems.Minus) {
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

          left = UnarOp();
          while (lexer.getCurrent().getTypeLexem() == lexems.Multiply || lexer.getCurrent().getTypeLexem() == lexems.Divide) {
               lexems sign = lexer.getCurrent().getTypeLexem();
               lexer.moveNext();
               Expression right = UnarOp();
               if (sign == lexems.Multiply) {
                    left = new Multiply(left, right);
               } else {
                    left = new Divide(left, right);
               }
          }

          return left;
     }

     private Expression UnarOp() throws ParserException {//+ unar minus
          if (lexer.getCurrent().getTypeLexem() == lexems.NO) {
               lexer.moveNext();
               return new Negate(prime());
          } else {
               return prime();
          }
     }

     private Expression prime() throws ParserException {
          Expression left = null;
          Expression expr = null;
          Expression expr2 = null;
          Identifier identifier = null;

          if (lexer.getCurrent().getTypeLexem() == lexems.Number) {
               left = new Number(lexer.getCurrent().getIntLexem());
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == lexems.TRUE) {
               left = new BooleanOp(true);
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == lexems.FALSE) {
               left = new BooleanOp(false);
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == lexems.LeftBracket) {
               lexer.moveNext();
               left = arithmeticExpression();
               if (lexer.getCurrent().getTypeLexem() != lexems.RightBracket) {
                    throw new RightBracketExpectedException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == lexems.Identifier) {
               left = new Identifier(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == lexems.FUN) {
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != lexems.Identifier) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               identifier = new Identifier(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != lexems.ARROW) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();
               expr = getExpression();
               return new Function(identifier, expr);
          } else if (lexer.getCurrent().getTypeLexem() == lexems.LET) {
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
               expr2 = getExpression();
               return new Binding(identifier, expr, expr2);
          } else if (lexer.getCurrent().getTypeLexem() == lexems.PRINT) {
               lexer.moveNext();
               return new Print(getExpression());
          } else if (lexer.getCurrent().getTypeLexem() == lexems.IF) {
               Expression else_expr = null;
               lexer.moveNext();
               expr = getBoolExpression();
               if (lexer.getCurrent().getTypeLexem() != lexems.THEN) {
                    throw new ParserException(lexer.getCurrent().getPosition());
               }
               lexer.moveNext();
               expr2 = getExpression();
               if (lexer.getCurrent().getTypeLexem() == lexems.ELSE) {
                    lexer.moveNext();
                    else_expr = getExpression();
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
          } else {
               throw new ParserException(lexer.getCurrent().getPosition());
          }
     }
}
