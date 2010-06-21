
package LexerAndParser;

public class Parser {

 
    private static Lexer lexer;


   /* public static void main(String[] args) {

        System.out.print("Введите выражение: ");
       
        Scanner input = new Scanner(System.in);
        lexer = new Lexer(input.nextLine());
        Expression left = null;

        try {
           left = expr();
             if (lexer.getCurrentSymbol() !=LexemKind.EOL){
                if (lexer.getCurrentSymbol() == LexemKind.Unknown)
                    System.out.println("Error in the input string (unknown symbol)");
    
                if (lexer.getCurrentSymbol() == LexemKind.RightBracket)
                    System.out.println("Error in the input string (unexepcted Left Bracket)");
    
                if (lexer.getCurrentSymbol() == LexemKind.LeftBracket)
                    System.out.println("Error in the input string (unexepcted Right Bracket)");
    
               throw new Exception();}
    
        } catch (Exception e){
            return;
        }
    
        left.print();
       }

*/
    private  static Expression expr(){
    	Expression left = null;

    	left = term();
        while (lexer.getCurrentSymbol()==LexemKind.Plus||lexer.getCurrentSymbol()==LexemKind.Minus) {
            LexemKind sign = lexer.getCurrentSymbol();
            lexer.moveNext();
            Expression right = term();
            if (sign == LexemKind.Plus) { //если было сложение складываем, аналогичнов терме только с умноженеим и делением
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
        while (lexer.getCurrentSymbol()==LexemKind.Multiply||lexer.getCurrentSymbol()==LexemKind.Divide) {
            LexemKind sign = lexer.getCurrentSymbol();
            lexer.moveNext();
            Expression right = factor();
            if (sign == LexemKind.Multiply){
                left = new Mult(left, right);
            }
            else{
            	left = new Div(left, right);
            }
        }

        return left;
    }

        public static Expression factor() {
        if (lexer.getCurrentSymbol() == LexemKind.Number) {
            int currentNumber = lexer.getNumber();
            lexer.moveNext();
            return new Number(currentNumber);
        } else {
            if (lexer.getCurrentSymbol() == LexemKind.LeftBracket) {
                lexer.moveNext();
                Expression left = expr();
                if (lexer.getCurrentSymbol() != LexemKind.RightBracket) {
                    throw new NumberFormatException("Missing closedBracked");
                }
                lexer.moveNext();
                return left;
            } else {
                throw new NumberFormatException("Illegal symbol");
            }
        }
    }

    
}
