
package LexerAndParser;


public class ParserTwo {

     private LexerTwo lexer;

     public ParserTwo(LexerTwo args) {
          lexer = args;
          lexer.moveNext();
     }

     public Sequence getSequence() throws Exception{
          Sequence sequence = new Sequence();
          while (lexer.getCurrent().getTypeLexem() != LexemKind.EOL && lexer.getCurrent().getTypeLexem() != LexemKind.END) {
               if (lexer.getCurrent().getTypeLexem() == LexemKind.semicolon) {
                    lexer.moveNext();
               } else {
                    sequence.addStatement(getExpression());
               }
          }
          return sequence;
     }

     private Expression getBoolExpression() throws Exception {

          return boolExpression();

     }

     private Expression boolExpression() throws Exception {
          Expression left = null;

          left = or();
          while (lexer.getCurrent().getTypeLexem() == LexemKind.Or) {
               lexer.moveNext();
               Expression right = or();
               left = new Or(left, right);
          }

          return left;
     }

     private Expression or() throws Exception {
          Expression left = null;

          left = and();
          while (lexer.getCurrent().getTypeLexem() == LexemKind.And) {
               lexer.moveNext();
               Expression right = and();
               left = new And(left, right);
          }

          return left;
     }

     private Expression and() throws Exception{
          return comparison();
     }

     private Expression comparison() throws Exception{
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
               case Greater:
                    lexer.moveNext();
                    right = comparison();
                    return new Greater(left, right);
               case Less:
                    lexer.moveNext();
                    right = comparison();
                    return new Less(left, right);
               case unequality:
                    lexer.moveNext();
                    right = comparison();
                    return new unequality(left, right);
               case equality:
                    lexer.moveNext();
                    right = comparison();
                    return new equality(left, right);
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

     private Expression getExpression() throws Exception {
          Expression left = null;

          left = boolExpression();
          if (lexer.getCurrent().getTypeLexem() == LexemKind.Unknown) {
               throw new Exception();
          }

          return left;
     }

     private Expression arithmeticExpression() throws Exception{
          Expression left = null;

          left = term();
          while (lexer.getCurrent().getTypeLexem() == LexemKind.Plus
                  || lexer.getCurrent().getTypeLexem() == LexemKind.Minus) {
               LexemKind sign = lexer.getCurrent().getTypeLexem();
               lexer.moveNext();
               Expression right = term();
               if (sign == LexemKind.Plus) {
                    left = new Plus(left, right);
               } else {
                    left = new Minus(left, right);
               }
          }

          return left;
     }

     private Expression term() throws Exception {
          Expression left = null;

          left = UnarOp();
          while (lexer.getCurrent().getTypeLexem() == LexemKind.Multiply || lexer.getCurrent().getTypeLexem() == LexemKind.Divide) {
               LexemKind sign = lexer.getCurrent().getTypeLexem();
               lexer.moveNext();
               Expression right = UnarOp();
               if (sign == LexemKind.Multiply) {
                    left = new Mult(left, right);
               } else {
                    left = new Div(left, right);
               }
          }

          return left;
     }

     private Expression UnarOp() throws Exception{
          if (lexer.getCurrent().getTypeLexem() == LexemKind.No) {
               lexer.moveNext();
               return new Negate(prime());
          } else {
               return prime();
          }
     }

     private Expression prime() throws Exception {
          Expression left = null;
          Expression expr = null;
          Expression expr2 = null;
          Identificator identifier = null;

          if (lexer.getCurrent().getTypeLexem() == LexemKind.Number) {
               left = new Number(lexer.getCurrent().getIntLexem());
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.TRUE) {
               left = new BooleanOp(true);
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.FALSE) {
               left = new BooleanOp(false);
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.LeftBracket) {
               lexer.moveNext();
               left = arithmeticExpression();
               if (lexer.getCurrent().getTypeLexem() != LexemKind.RightBracket) {
                    throw new NumberFormatException("Missing closedBracked");
               }
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Identifier) {
               left = new Identificator(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
               return left;
          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.FUN) {
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != LexemKind.Identifier) {
                    throw new Exception();
               }
               identifier = new Identificator(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != LexemKind.ARROW) {
                    throw new Exception();
               }
               lexer.moveNext();
               expr = getExpression();
               return new Function(identifier, expr);

          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.LET) {
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != LexemKind.Identifier) {
                    throw new Exception();
               }
               identifier = new Identificator(lexer.getCurrent().getStringLexem());
               lexer.moveNext();
               if (lexer.getCurrent().getTypeLexem() != LexemKind.equality) {
                    throw new Exception();
               }
               lexer.moveNext();
               expr = getExpression();
               if (lexer.getCurrent().getTypeLexem() != LexemKind.IN) {
                    throw new Exception();
               }
               lexer.moveNext();
               expr2 = getExpression();
               return new Binding(identifier, expr, expr2);
          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.PRINT) {
               lexer.moveNext();
               return new Print(getExpression());
          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.IF) {
               Expression else_expr = null;
               lexer.moveNext();
               expr = getBoolExpression();
               if (lexer.getCurrent().getTypeLexem() != LexemKind.THEN) {
                    throw new Exception();
               }
               lexer.moveNext();
               expr2 = getExpression();
               if (lexer.getCurrent().getTypeLexem() == LexemKind.ELSE) {
                    lexer.moveNext();
                    else_expr = getExpression();
               }
               return new If(expr, expr2, else_expr);
          } else if (lexer.getCurrent().getTypeLexem() == LexemKind.BEGIN) {
               Sequence seq;
               lexer.moveNext();
               seq = getSequence();
               if (lexer.getCurrent().getTypeLexem() != LexemKind.END) {
                    throw new Exception();
               }
               lexer.moveNext();
               return new Begin(seq);
          } else {
               throw new Exception();
          }
     }
}
