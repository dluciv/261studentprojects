

package LexerAndParser;

public class Main {

     public static void main(String[] args) throws Exception {

        String input = "let x = 6 in x; if x < 7 then let x = 2*x in x else let x = 0 in x; print x";
        //String input = "let x = 6 in x; let x = x + 1 in x; print x";
        //String input ="";
        Interpret interpreter = new Interpret();
        interpreter.interpret((new ParserTwo(new LexerTwo(input))).getSequence());
        
     }

}
